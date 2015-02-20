package pokeslick.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	
	// Direction demandée
	private int direction = 2;
	
	Player objPlayer;
	
	int nb = 0;
	

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
			nb++;
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
			
			// On est resté appuyé sur cette touche
			if(listener.isKeyDown(Input.KEY_UP)) 
			{
				objPlayer.setMoving();
			} else if(listener.isKeyDown(Input.KEY_LEFT)) 
			{
				objPlayer.setMoving();
			} else if(listener.isKeyDown(Input.KEY_DOWN)) 
			{
				objPlayer.setMoving();
			} else if(listener.isKeyDown(Input.KEY_RIGHT)) 
			{
				objPlayer.setMoving();
			}
		}
		
		// Calcul des futurs coordonnées désirées
		x = objPlayer.getNextAbsciss(direction, delta);
		y = objPlayer.getNextOrdinate(direction, delta);
	}
	
	/** 
	 * Démarre le jeu. 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new WindowGame("GameZ"), 640, 480, false);
		container.setShowFPS(false); // Désactivation de l'affichage des FPS
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
			switch (key) {
    			case Input.KEY_UP:    
    				if(objPlayer.getOrdinate() > 0) { this.direction = 0; objPlayer.setMoving(); }
    			break;
    		case Input.KEY_LEFT:
    				if(objPlayer.getAbsciss() > 0) { this.direction = 1; objPlayer.setMoving(); }
    			break;
    		case Input.KEY_DOWN:
    				if(objPlayer.getOrdinate() < HEIGHT_MAX) { this.direction = 2; objPlayer.setMoving(); }
    			break;
    		case Input.KEY_RIGHT:
    				if(objPlayer.getAbsciss() < WIDTH_MAX) { this.direction = 3; objPlayer.setMoving(); }
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
    			if(this.map.getTileId((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) - 1, layerCollision) == 0) { 
    				collision = false;
    			}
    			break;
    		case Input.KEY_LEFT:
    			if(this.map.getTileId((int) (objPlayer.getAbsciss() / TILE_SIZE) - 1, (int) objPlayer.getOrdinate() / TILE_SIZE, layerCollision) == 0) { 
    				collision = false;
    			}
    			break;
    		case Input.KEY_DOWN:
    			if(this.map.getTileId((int) objPlayer.getAbsciss() / TILE_SIZE, (int) (objPlayer.getOrdinate() / TILE_SIZE) + 1, layerCollision) == 0) {
    				collision = false;
    			}
    			break;
    		case Input.KEY_RIGHT:
    			if(this.map.getTileId((int) (objPlayer.getAbsciss() / TILE_SIZE) + 1, (int) objPlayer.getOrdinate() / TILE_SIZE, layerCollision) == 0) { 
    				collision = false;
    			}
    			break;
		}
		return collision;	
	}
}
