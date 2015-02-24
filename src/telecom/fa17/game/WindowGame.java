package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author FLORENT / PE / ÉTIENNE
 *
 */
public class WindowGame extends BasicGame {

    private GameContainer container;
	private List<Map> map;
	
	// Constantes de la Map
	int TILE_SIZE, WIDTH_MAX, HEIGHT_MAX;
	// Map en cours d'affichage
	int indexMap = 0;
	
	// Coordonnees du personnage au départ
	private float x = 320,
				  y = 256;
	
	Player objPlayer;

	/**
     * Création de la fenetre.
     *
     * @param title - Titre de la fenetre.
     */
	public WindowGame(String title) {
		super(title);
	}
	
	/** 
	 * Initialise le contenu du jeu, charger les graphismes, la musique, etc...
	 */
	@Override
	public void init(GameContainer container) throws SlickException {
        this.container = container;
        
        // Charge la map
        map = new LinkedList<Map>();
        map.add(new Map("firstMap"));
        TILE_SIZE = map.get(0).getTileDimension();
        WIDTH_MAX = map.get(0).getWidth();
        HEIGHT_MAX = map.get(0).getHeight();
        map.get(0).addExit(new Exit(9, 0, 9 * TILE_SIZE, 14 * TILE_SIZE, 1));
        map.get(0).addExit(new Exit(10, 0, 16 * TILE_SIZE, 14 * TILE_SIZE, 1));
        
        map.add(new Map("secondMap"));
        map.get(1).addExit(new Exit(9, 14, 9 * TILE_SIZE, 0, 0));
        map.get(1).addExit(new Exit(16, 14, 10 * TILE_SIZE, 0, 0)); 
        map.get(1).addExit(new Exit(4, 6, 5 * TILE_SIZE, 7 * TILE_SIZE, 2)); 
        
        map.add(new Map("thirdMap"));
        map.get(2).addExit(new Exit(5, 7, 4 * TILE_SIZE, 6 * TILE_SIZE, 1));
        
        // Charge la musique
        //Music background = new Music("resources/music/general.ogg");
        //background.loop();
        
        // Création d'un joueur
        objPlayer = new Player(320, 256, TILE_SIZE);
        objPlayer.init();
    }
	
	/** 
	 * Affiche le contenu du jeux
	 */
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
	    // Affichage du fond principal de la carte
		map.get(indexMap).renderBackground();
		// Si on appuie sur une touche de direction, on joue une animation
		if (objPlayer.isMoving()) {
			g.drawAnimation(objPlayer.getAnimation(), x, y);
		} else {
			// Sinon, on affiche le personnage statique en fonction de sa dernière direction
			g.drawImage(objPlayer.getStandingImage(), x, y);
		}
		// Affichage de l'Avant-Plan
		map.get(indexMap).renderForeground();
	    // On affiche pas la couche de collision qui serait la prochaine
	    
    }
	
	/** 
	 * Met à jour les élément de la scène en fonction du delta temps qui est survenu. 
	 * C’est ici que la logique du jeux est renfermé.
	 */
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
				
		if(!objPlayer.isMoving()){
			Input listener = container.getInput();			
			
			// On est resté appuyé sur une touche - callback keyPressed
			if(listener.isKeyDown(Input.KEY_UP)) {
				keyPressed(Input.KEY_UP, ' ');
			} else if(listener.isKeyDown(Input.KEY_LEFT)) {
				keyPressed(Input.KEY_LEFT, ' ');
			} else if(listener.isKeyDown(Input.KEY_DOWN)) {
				keyPressed(Input.KEY_DOWN, ' ');
			} else if(listener.isKeyDown(Input.KEY_RIGHT)) {
				keyPressed(Input.KEY_RIGHT, ' ');
			}
		}
		
		// Calcul des futurs coordonnées désirées
		x = objPlayer.getNextAbsciss(delta);
		y = objPlayer.getNextOrdinate(delta);
	}
	
	/** 
	 * Démarre le jeu. 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new WindowGame("GameZ"), 640, 480, false);
		container.setShowFPS(false); // Désactivation de l'affichage des FPS
		container.start(); // Démarrage du jeu (lancement de la fenêtre)
    }
	
	/** 
	 * Méthode qui permet de savoir quand une touche est pressée.
	 * 
	 * Touche pour quitter : ESC. 
	 */
	@Override
	public void keyPressed(int key, char c) {
		Exit exit = null;
		
		// Touche ESC on termine le programme
		if (key == Input.KEY_ESCAPE) {
            container.exit();
        }
		
	    // Si l'on a fini le mouvement
		if(!objPlayer.isMoving()){
			exit = isExit(key);
			switch (key) {
    			case Input.KEY_UP:  
    				objPlayer.setDirection(Direction.NORTH);
    				if(objPlayer.getOrdinate() > 0){ 
    					move(exit , key);
    				}
    			break;
    		case Input.KEY_LEFT:
    				objPlayer.setDirection(Direction.EAST);
    				if(objPlayer.getAbsciss() > 0){ 
    					move(exit , key);
    				}
    			break;
    		case Input.KEY_DOWN:
    				objPlayer.setDirection(Direction.SOUTH);
    				if(objPlayer.getOrdinate() < HEIGHT_MAX){ 
    					move(exit , key);
    				}
    			break;
    		case Input.KEY_RIGHT:
    				objPlayer.setDirection(Direction.WEST);
    				if(objPlayer.getAbsciss() < WIDTH_MAX){ 
    					move(exit , key);
    				}
    			break;
			}
	    }
	}
	
	public void move(Exit exit, int direction){
		if(exit != null){
			indexMap = exit.getMapNumber();
			objPlayer.setAbsciss(exit.getNextAbsciss());
			objPlayer.setOrdinate(exit.getNextOrdinate());
		} else {
			if(!isCollision(direction)) { objPlayer.setMoving(); }
		}
	}
	// Met à jour les variables pour le mouvement
	public boolean isCollision(int key)	{			
		boolean collision = true;
		
		switch (key) {
    		case Input.KEY_UP:  
    			// Vérification tuile (X, Y - 1) par rapport à l'actuel
    			if(this.map.get(indexMap).getTileId((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) - 1, "logic") == 0) { 
    				collision = false;
    			}
    			break;
    		case Input.KEY_LEFT:
    			// Vérification tuile (X - 1, Y) par rapport à l'actuel
    			if(this.map.get(indexMap).getTileId((int) (objPlayer.getAbsciss() / TILE_SIZE) - 1, (int) objPlayer.getOrdinate() / TILE_SIZE, "logic") == 0) { 
    				collision = false;
    			}
    			break;
    		case Input.KEY_DOWN:
    			// Vérification tuile (X, Y + 1) par rapport à l'actuel
    			if(this.map.get(indexMap).getTileId((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) + 1, "logic") == 0) {
    				collision = false;
    			}
    			break;
    		case Input.KEY_RIGHT:
    			// Vérification tuile (X + 1, Y) par rapport à l'actuel
    			if(this.map.get(indexMap).getTileId((int) (objPlayer.getAbsciss() / TILE_SIZE) + 1, (int) objPlayer.getOrdinate() / TILE_SIZE, "logic") == 0) { 
    				collision = false;
    			}
    			break;
		}
		return collision;	
	}
	
	// Met à jour les variables pour le mouvement
	public Exit isExit(int key){			
		Exit exit = null;
		
		switch (key) {
    		case Input.KEY_UP:  
    			// Vérification tuile (X, Y - 1) par rapport à l'actuel
    			exit = map.get(indexMap).getExitByCoordinate((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) - 1);
    			break;
    		case Input.KEY_LEFT:
    			// Vérification tuile (X - 1, Y) par rapport à l'actuel
    			exit = map.get(indexMap).getExitByCoordinate((int) (objPlayer.getAbsciss() / TILE_SIZE) - 1, (int) objPlayer.getOrdinate() / TILE_SIZE);
    			break;
    		case Input.KEY_DOWN:
    			// Vérification tuile (X, Y + 1) par rapport à l'actuel
    			exit = map.get(indexMap).getExitByCoordinate((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) + 1);
    			break;
    		case Input.KEY_RIGHT:
    			// Vérification tuile (X + 1, Y) par rapport à l'actuel
    			exit = map.get(indexMap).getExitByCoordinate((int) (objPlayer.getAbsciss() / TILE_SIZE) + 1, (int) objPlayer.getOrdinate() / TILE_SIZE);
    			break;
		}
		return exit;	
	}
}
