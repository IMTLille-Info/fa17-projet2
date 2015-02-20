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
				System.out.println("TOUCHE ENFONCEE");
				objPlayer.setMoving();
			} else if(listener.isKeyDown(Input.KEY_LEFT)) 
			{
				System.out.println("TOUCHE ENFONCEE");
				objPlayer.setMoving();
			} else if(listener.isKeyDown(Input.KEY_DOWN)) 
			{
				System.out.println("TOUCHE ENFONCEE");
				objPlayer.setMoving();
			} else if(listener.isKeyDown(Input.KEY_RIGHT)) 
			{
				System.out.println("TOUCHE ENFONCEE");
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
			objPlayer.setMoving();
			switch (key) {
    			case Input.KEY_UP:    
    				if(y > 0) { this.direction = 0; }
    			break;
    		case Input.KEY_LEFT:
    			if(x > 0) { this.direction = 1; }
    			break;
    		case Input.KEY_DOWN:
    			if(y != HEIGHT_MAX) { this.direction = 2; }
    			break;
    		case Input.KEY_RIGHT:
    			if(x != WIDTH_MAX) { this.direction = 3; }
    			break;
			}
	    }
	}
	
	// Met à jour les variables pour le mouvement
	/*public void setMoving(int key)
	{
		
		int layerCollision = this.map.getLayerIndex("logic");
		

	
	}*/
}
