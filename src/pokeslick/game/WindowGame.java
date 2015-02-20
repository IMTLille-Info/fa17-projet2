package pokeslick.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author FLORENT / PE / ÉTIENNE
 *
 */
public class WindowGame extends BasicGame {

    private GameContainer container;
	private TiledMap map;
	
	// Constantes de la Map
	int TILE_SIZE, WIDTH_MAX, HEIGHT_MAX;
	
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
        this.map = new TiledMap("resources/map/firstMap.tmx");
        TILE_SIZE = this.map.getTileHeight();
        WIDTH_MAX = (this.map.getWidth() * TILE_SIZE) - TILE_SIZE;
        HEIGHT_MAX = (this.map.getHeight() * TILE_SIZE) - TILE_SIZE;
        
        // Création d'un joueur
        objPlayer = new Player(320, 256, TILE_SIZE);
    }
	
	/** 
	 * Affiche le contenu du jeux
	 */
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
	    // Affichage du fond principal de la carte
		this.map.render(0, 0, 0);
		// Si on appuie sur une touche de direction, on joue une animation
		if (objPlayer.isMoving()) {
			g.drawAnimation(objPlayer.getAnimation(), x, y);
		} else {
			// Sinon, on affiche le personnage statique en fonction de sa dernière direction
			g.drawImage(objPlayer.getStandingImage(), x, y);
		}
		// Affichage de l'Avant-Plan
	    this.map.render(0, 0, 1);
	    // On affiche pas la couche de collision qui serait la prochaine
	    
    }
	
	/** 
	 * Met à jour les élément de la scène en fonction du delta temps qui est survenu. 
	 * C’est ici que la logique du jeux est renfermé.
	 */
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
				
		if(!objPlayer.isMoving())
		{
			Input listener = container.getInput();			
			
			// On est resté appuyé sur une touche - callback keyPressed
			if(listener.isKeyDown(Input.KEY_UP)) 
			{
				keyPressed(Input.KEY_UP, ' ');
			} else if(listener.isKeyDown(Input.KEY_LEFT)) 
			{
				keyPressed(Input.KEY_LEFT, ' ');
			} else if(listener.isKeyDown(Input.KEY_DOWN)) 
			{
				keyPressed(Input.KEY_DOWN, ' ');
			} else if(listener.isKeyDown(Input.KEY_RIGHT)) 
			{
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
		container.setShowFPS(true); // Désactivation de l'affichage des FPS
		container.start(); // Démarrage du jeu (lancement de la fenêtre
    }
	
	/** 
	 * Méthode qui permet de savoir quand une touche est pressée.
	 * 
	 * Touche pour quitter : ESC. 
	 */
	@Override
	public void keyPressed(int key, char c) {
		
		// Touche ESC on termine le programme
		if (key == Input.KEY_ESCAPE) {
            container.exit();
        }
		
	    // Si l'on a fini le mouvement
		if(!objPlayer.isMoving())
		{
			boolean collision = isCollision(key);
			switch (key) {
    			case Input.KEY_UP:  
    				objPlayer.setDirection(0);
    				if(objPlayer.getOrdinate() > 0 && !collision) { objPlayer.setMoving(); }
    			break;
    		case Input.KEY_LEFT:
    				objPlayer.setDirection(1);
    				if(objPlayer.getAbsciss() > 0 && !collision) { objPlayer.setMoving(); }
    			break;
    		case Input.KEY_DOWN:
    				objPlayer.setDirection(2);
    				if(objPlayer.getOrdinate() < HEIGHT_MAX && !collision) { objPlayer.setMoving(); }
    			break;
    		case Input.KEY_RIGHT:
    				objPlayer.setDirection(3);
    				if(objPlayer.getAbsciss() < WIDTH_MAX && !collision) { objPlayer.setMoving(); }
    			break;
			}
	    }
	}
	
	// Met à jour les variables pour le mouvement
	public boolean isCollision(int key)
	{	
		boolean collision = true;
		int layerCollision = this.map.getLayerIndex("logic");
		
		switch (key) {
    		case Input.KEY_UP:   
    			// Vérification tuile (X, Y - 1) par rapport à l'actuel
    			if(this.map.getTileId((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) - 1, layerCollision) == 0) { 
    				collision = false;
    			}
    			break;
    		case Input.KEY_LEFT:
    			// Vérification tuile (X - 1, Y) par rapport à l'actuel
    			if(this.map.getTileId((int) (objPlayer.getAbsciss() / TILE_SIZE) - 1, (int) objPlayer.getOrdinate() / TILE_SIZE, layerCollision) == 0) { 
    				collision = false;
    			}
    			break;
    		case Input.KEY_DOWN:
    			// Vérification tuile (X, Y + 1) par rapport à l'actuel
    			if(this.map.getTileId((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) + 1, layerCollision) == 0) {
    				collision = false;
    			}
    			break;
    		case Input.KEY_RIGHT:
    			// Vérification tuile (X + 1, Y) par rapport à l'actuel
    			if(this.map.getTileId((int) (objPlayer.getAbsciss() / TILE_SIZE) + 1, (int) objPlayer.getOrdinate() / TILE_SIZE, layerCollision) == 0) { 
    				collision = false;
    			}
    			break;
		}
		return collision;	
	}
}
