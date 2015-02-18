package telecom.game;

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
 * @author FLORENT
 *
 */
public class WindowGame extends BasicGame {

    private GameContainer container;
	private TiledMap map;
	
	// Taille d'une tuile du jeu
	final int DURATION_FRAME = 2;
	int TILE_SIZE, WIDTH_MAX, HEIGHT_MAX;
	
	// Coordonnees du personnage au départ
	private float x = 16 * TILE_SIZE,
				  y = 8 * TILE_SIZE;
	
	// Direction demandée
	private int direction = 2;
	
	// État du personnage
	private boolean moving = false;
	
	// Coordonnees a atteindre
	private float nextX = x,
			  nextY = y;
	
	// Animations de mouvement
	private Animation[] animations = new Animation[4];
	
	// Image quand le personnage est fixe
	private Image[] standings = new Image[4];

	/**
     * Création de la fenetre.
     *
     * @param title - Titre de la fenetre.
     */
	public WindowGame(String title) {
		super(title);
	}

	/** 
	 * Affiche le contenu du jeux
	 */
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
	    // Affichage du fond principal de la carte
		this.map.render(0, 0, 0);
		// Si on appuie sur une touche de direction, on joue une animation
		if (this.moving) {
			g.drawAnimation(animations[direction], x, y);
		} else {
			// Sinon, on affiche le personnage statique en fonction de sa dernière direction
			g.drawImage(standings[direction], x, y);
		}
		// Affichage de l'Avant-Plan
	    this.map.render(0, 0, 1);
	    // On affiche pas la couche de collision qui serait la prochaine

    }

	/** 
	 * Initialise le contenu du jeu, charger les graphismes, la musique, etc…
	 */
	@Override
	public void init(GameContainer container) throws SlickException {
        this.container = container;
        
        // Charge la map
        this.map = new TiledMap("resources/map/firstMap.tmx");
        TILE_SIZE = this.map.getTileHeight();
        WIDTH_MAX = (this.map.getWidth() * TILE_SIZE) - TILE_SIZE;
        HEIGHT_MAX = (this.map.getHeight() * TILE_SIZE) - TILE_SIZE;
        
        // Images du joueur correspondante à ces états statique
        standings[0] = new Image("resources/map/player/personStandUp.png");
        standings[1] = new Image("resources/map/player/personStandLeft.png");
        standings[2] = new Image("resources/map/player/personStandDown.png");
        standings[3] = new Image("resources/map/player/personStandRight.png");
        
        /* ANIMATIONS */
        
        // Marcher vers le haut
        Animation walkNorth = new Animation();
        walkNorth.addFrame(new Image("resources/map/player/personWalkingUp.png"), DURATION_FRAME);
        walkNorth.addFrame(new Image("resources/map/player/personWalkingUp2.png"), DURATION_FRAME);
        this.animations[0] = walkNorth;
        this.animations[0].setLooping(true);
        
        // Marcher vers la gauche
        Animation walkLeft = new Animation();
        walkLeft.addFrame(new Image("resources/map/player/personWalkingLeft.png"), DURATION_FRAME);
        walkLeft.addFrame(new Image("resources/map/player/personStandLeft.png"), DURATION_FRAME);
        this.animations[1] = walkLeft;
        
        // Marcher vers le bas
        Animation walkSouth = new Animation();
        walkSouth.addFrame(new Image("resources/map/player/personWalkingDown.png"), DURATION_FRAME);
        walkSouth.addFrame(new Image("resources/map/player/personWalkingDown2.png"), DURATION_FRAME);

        this.animations[2] = walkSouth;
        
        // Marcher vers la droite
        Animation walkRight = new Animation();
        walkRight.addFrame(new Image("resources/map/player/personWalkingRight.png"), DURATION_FRAME);
        walkRight.addFrame(new Image("resources/map/player/personStandRight.png"), DURATION_FRAME);
        this.animations[3] = walkRight;
    }

	/** 
	 * Met à jour les élément de la scène en fonction du delta temps qui est survenu. 
	 * C’est ici que la logique du jeux est renfermé.
	 */
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		// Calcul des futurs coordonnées désirées
		if (this.moving) {
	        switch (this.direction) {
	        	// On veut monter
	        	case 0 :
	        			if((y > nextY)) { y -= DURATION_FRAME; }
	        			break;
	        	// On veut aller à gauche
	        	case 1 :
	        			if((x > nextX)) { x -= DURATION_FRAME; }
        				break;
	        	// On veut descendre
	        	case 2 :
	        			if((y < nextY)) { y += DURATION_FRAME; }
    					break;
	        	// On veut aller à droite
	        	case 3 :
	        			if((x < nextX)) { x += DURATION_FRAME; }
						break;
	        }
	    } 
		
		if((x == nextX) && (y == nextY))
		{
			
			Input listener = container.getInput();
			
			// On est resté appuyé sur cette touche
			if(listener.isKeyDown(Input.KEY_UP)) 
			{
        		this.direction = 0;
        		this.moving = true;
        		if(y > 0) { nextY = y - TILE_SIZE; }
			} else if(listener.isKeyDown(Input.KEY_LEFT)) 
			{
				this.direction = 1; 
        		this.moving = true;
        		if(x > 0) { nextX = x - TILE_SIZE; }
			} else if(listener.isKeyDown(Input.KEY_DOWN)) 
			{
        		this.direction = 2;
        		this.moving = true;
        		if(y != HEIGHT_MAX) { nextY = y + TILE_SIZE; }
			} else if(listener.isKeyDown(Input.KEY_RIGHT)) 
			{
        		this.direction = 3;
        		this.moving = true;
        		if(x != WIDTH_MAX) { nextX = x + TILE_SIZE; }
			} else { this.moving = false; }
		}
	}
	
	/** 
	 * Démarre le jeu. 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new WindowGame("GameZ"), 640, 480, false);
		container.setShowFPS(false); // Désactivation de l'Affichage des FPS
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
		if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
		
	    // Si l'on a fini le mouvement
		if(!this.moving)
		{
			switch (key) {
	        	case Input.KEY_UP:    
	        		this.direction = 0;
	        		this.moving = true;
	        		if(y > 0) { nextY = y - TILE_SIZE; }
	        		break;
	        	case Input.KEY_LEFT:
	        		this.direction = 1;
	        		this.moving = true;
	        		if(x > 0) { nextX = x - TILE_SIZE; }
	        		break;
	        	case Input.KEY_DOWN:
	        		this.direction = 2;
	        		this.moving = true;
	        		if(y != HEIGHT_MAX) { nextY = y + TILE_SIZE; }
	        		break;
	        	case Input.KEY_RIGHT:
	        		this.direction = 3;
	        		this.moving = true;
	        		if(x != WIDTH_MAX) { nextX = x + TILE_SIZE; }
	        		break;
			}
	    }
	}
}
