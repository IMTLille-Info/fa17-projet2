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

	// Taille d'une tuile du jeu
	final int TILE_SIZE = 32;
	
    private GameContainer container;
	private TiledMap map;
	
	// Coordonnees du personnage au d�part
	private float x = 16 * TILE_SIZE,
				  y = 8 * TILE_SIZE;
	
	private int direction = 2;
	private boolean moving = false;
	
	// 
	private Animation[] animations = new Animation[4];
	private Image[] standings = new Image[4];

	/**
     * Cr�ation de la fenetre.
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
			// Sinon, on affiche le personnage statique en fonction de sa derni�re direction
			g.drawImage(standings[direction], x, y);
		}
		// Affichage de l'Avant-Plan
	    this.map.render(0, 0, 1);
	    // On affiche pas la couche de collision qui serait la seconde

    }

	/** 
	 * Initialise le contenu du jeu, charger les graphismes, la musique, etc�
	 */
	@Override
	public void init(GameContainer container) throws SlickException {
        this.container = container;
        
        // Charge la map
        this.map = new TiledMap("resources/map/firstMap.tmx");
        
        // Images du joueur correspondante � ces �tats statique
        standings[0] = new Image("src/resources/map/player/personStandUp.png");
        standings[1] = new Image("src/resources/map/player/personStandLeft.png");
        standings[2] = new Image("src/resources/map/player/personStandDown.png");
        standings[3] = new Image("src/resources/map/player/personStandRight.png");
        
        /* ANIMATIONS */
        
        // Marcher vers le haut
        Animation walkNorth = new Animation();
        walkNorth.addFrame(new Image("src/resources/map/player/personWalkingUp.png"), 100);
        walkNorth.addFrame(new Image("src/resources/map/player/personWalkingUp.png"), 100);
        walkNorth.addFrame(new Image("src/resources/map/player/personWalkingUp2.png"), 100);
        this.animations[0] = walkNorth;
        
        // Marcher vers la gauche
        Animation walkLeft = new Animation();
        walkLeft.addFrame(new Image("src/resources/map/player/personStandLeft.png"), 100);
        walkLeft.addFrame(new Image("src/resources/map/player/personWalkingLeft.png"), 100);
        this.animations[1] = walkLeft;
        
        // Marcher vers le bas
        Animation walkSouth = new Animation();
        walkSouth.addFrame(new Image("src/resources/map/player/personStandDown.png"), 100);
        walkSouth.addFrame(new Image("src/resources/map/player/personWalkingDown.png"), 100);
        walkSouth.addFrame(new Image("src/resources/map/player/personWalkingDown2.png"), 100);
        this.animations[2] = walkSouth;
        
        // Marcher vers la droite
        Animation walkRight = new Animation();
        walkRight.addFrame(new Image("src/resources/map/player/personStandRight.png"), 100);
        walkRight.addFrame(new Image("src/resources/map/player/personWalkingRight.png"), 100);
        this.animations[3] = walkRight;
        
    }

	/** 
	 * Met � jour les �l�ment de la sc�ne en fonction du delta temps qui est survenu. 
	 * C�est ici que la logique du jeux est renferm�.
	 */
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		float futurX = x, futurY = y;
		float futureTuileX = x, futureTuileY = y;
		
		// Calcul des futurs coordonn�es d�sir�es
		if (this.moving) {
	        switch (this.direction) {
	        	case 0 :futurY -= (0.1f * delta);
	        			futureTuileY = futurY;
	        			break;
	        	case 1 :futurX -= (0.1f * delta);
	        			futureTuileX = futurX;
        				break;
	        	case 2 :futurY += (0.1f * delta);
	        			futureTuileY = y + TILE_SIZE;
    					break;
	        	case 3 :futurX += (0.1f * delta);
	        			futureTuileX = x + TILE_SIZE;
						break;
	        }

			/* Savoir si l'on va sortir de la fen�tre */
	        // On arr�te si on est inf�rieur � 0 (� gauche) ou sup�rieur � la largeur de la fen�tre 
			if((futurX < 0.0) || (futurX > (this.container.getWidth() - TILE_SIZE)))
			{
				this.moving = false;
			}
			// On arr�te si on est inf�rieur � 0 (en haut) ou sup�rieur � la hauteur de la fen�tre 
			if((futurY < 0.0) || (futurY > (this.container.getHeight() - TILE_SIZE)))
			{
				this.moving = false;
			}	
			
			System.out.println("x : " + (int) (x / TILE_SIZE) + " | y : " + (int) (y / TILE_SIZE));
			System.out.println("TUILES - X : " + (int) (futureTuileX / TILE_SIZE) + " | Y : " + (int) (futureTuileY / TILE_SIZE));
			
			// R�cup�ration de la future tuile
			Image tile = this.map.getTileImage((int) (futureTuileX / TILE_SIZE),
											   (int) (futureTuileY / TILE_SIZE),
											   this.map.getLayerIndex("logic"));
			// Il exite une tuile de collision
			if(tile != null)
			{
				this.moving = false;
				System.out.println("COLLISION");
			}
					
			// Les futures coordonn�es sont bonnes
			if (this.moving) {
				x = futurX;
			    y = futurY;
			}
	    }
	}
	
	/** 
	 * D�marre le jeu. 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new WindowGame("The best Game"), 640, 480, false);
		container.setShowFPS(false); // D�sactivation de l'Affichage des FPS
		container.start(); // D�marrage du jeu (lancement de la fen�tre
    }
	
	/** 
	 * M�thode qui permet de savoir quand une touche est relach�e.
	 * 
	 * Touche pour quitter : ESC. 
	 */
	@Override
    public void keyReleased(int key, char c) {
		// Relachement de touche, on arr�te le mouvement
		this.moving = false;
		if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
	
	/** 
	 * M�thode qui permet de savoir quand une touche est press�e.
	 * 
	 * Touche pour quitter : ESC. 
	 */
	@Override
	public void keyPressed(int key, char c) {
	    // On appuie sur une touche
		switch (key) {
	        case Input.KEY_UP:    this.direction = 0; this.moving = true; break;
	        case Input.KEY_LEFT:  this.direction = 1; this.moving = true; break;
	        case Input.KEY_DOWN:  this.direction = 2; this.moving = true; break;
	        case Input.KEY_RIGHT: this.direction = 3; this.moving = true; break;
	    }
	}
}
