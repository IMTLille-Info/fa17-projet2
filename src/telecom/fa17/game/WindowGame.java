﻿package telecom.fa17.game;

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
	int WIDTH_MAX, HEIGHT_MAX;
	// Map en cours d'affichage
	int indexMap = 0;
	
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
        WIDTH_MAX = map.get(0).getWidth();
        HEIGHT_MAX = map.get(0).getHeight();
        map.get(0).addExit(new Exit(9, 0, 9 * 32, 14 * 32, 1));
        map.get(0).addExit(new Exit(10, 0, 16 * 32, 14 * 32, 1));
        
        map.add(new Map("secondMap"));
        map.get(1).addExit(new Exit(9, 14, 9 * 32, 0, 0));
        map.get(1).addExit(new Exit(16, 14, 10 * 32, 0, 0)); 
        map.get(1).addExit(new Exit(4, 6, 5 * 32, 7 * 32, 2)); 
        
        map.add(new Map("thirdMap"));
        map.get(2).addExit(new Exit(5, 7, 4 * 32, 6 * 32, 1));
        
        // Charge la musique
        //Music background = new Music("resources/music/general.ogg");
        //background.loop();
        
        // Création d'un joueur
        objPlayer = new Player(320, 256, this.map.get(indexMap).getTileDimension());
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
			g.drawAnimation(objPlayer.getAnimation(), objPlayer.getPosition().getAbsciss(), objPlayer.getPosition().getOrdinate());
		} else {
			// Sinon, on affiche le personnage statique en fonction de sa dernière direction
			g.drawImage(objPlayer.getStandingImage(), objPlayer.getPosition().getAbsciss(), objPlayer.getPosition().getOrdinate());
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
		objPlayer.getNextPosition();
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
		boolean collision = false;
		
		// Touche ESC on termine le programme
		if (key == Input.KEY_ESCAPE) {
            container.exit();
        }
		
	    // Si l'on a fini le mouvement
		if(!objPlayer.isMoving()){
			switch (key) {
    			case Input.KEY_UP:  
    				objPlayer.setDirection(Direction.NORTH);
    			break;
    		case Input.KEY_LEFT:
    				objPlayer.setDirection(Direction.EAST);
    			break;
    		case Input.KEY_DOWN:
    				objPlayer.setDirection(Direction.SOUTH);
    			break;
    		case Input.KEY_RIGHT:
    				objPlayer.setDirection(Direction.WEST);
    			break;
			}
			
			exit = map.get(indexMap).findExit(key, objPlayer.getPosition());
			collision = map.get(indexMap).findCollision(key, objPlayer.getPosition());
			
			if((!objPlayer.isOnEdge(WIDTH_MAX, HEIGHT_MAX) && !collision))
			{
				move(exit, key);
			}
	    }
	}
	
	public void move(Exit exit, int direction){
		if(exit != null){
			indexMap = exit.getMapNumber();
			objPlayer.setPosition(exit.getNextPosition());
		} else {
			objPlayer.setMoving();
		}
	}
		
	
	public void displayText(Graphics g, String text, float absOrigi, float ordOrigin)
	{
		g.drawString(text, absOrigi, ordOrigin);
	}
}