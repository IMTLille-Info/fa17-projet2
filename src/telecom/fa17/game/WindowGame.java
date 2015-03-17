package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
	
	// Constantes de Map
	int WIDTH_MAX, HEIGHT_MAX;
	// Map en cours d'affichage
	int indexMap = 0;
	
	Player objPlayer;
	AnimationView animations;
	Sounds music;
	Hud myHud;

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
        map.add(new Map("firstMap", "townMap.ogg", false));
        WIDTH_MAX = map.get(0).getWidth();
        HEIGHT_MAX = map.get(0).getHeight();
        map.get(0).addExit(new Exit(9 * 32, 0, 9 * 32, 14 * 32, 1));
        map.get(0).addExit(new Exit(10 * 32, 0, 16 * 32, 14 * 32, 1));
        
        map.add(new Map("secondMap", "caveMap.ogg", false));
        map.get(1).addExit(new Exit(9 * 32, 14 * 32, 9 * 32, 0, 0));
        map.get(1).addExit(new Exit(16 * 32, 14 * 32, 10 * 32, 0, 0)); 
        map.get(1).addExit(new Exit(7 * 32, 3 * 32, 1 * 32, 13 * 32, 2));
        map.get(1).addExit(new Exit(17 * 32, 4 * 32, 18 * 32, 1 * 32, 2));
        map.get(1).addExit(new Exit(18 * 32, 1 * 32, 17 * 32, 5 * 32, 3));
        PNJ monster = new PNJ(11 * 32, 5 * 32, map.get(1).getTileDimension(), 5, 50, map.get(1));
        monster.init();
        map.get(1).addAdversary(monster);
        
        map.add(new Map("thirdMap", "caveMap.ogg", false));
        map.get(2).addExit(new Exit(1 * 32, 13 * 32, 7 * 32, 3 * 32, 1));
        map.get(2).addExit(new Exit(18 * 32, 1 * 32, 17 * 32, 4 * 32, 1));
        PNJ monster2 = new PNJ(4 * 32, 8 * 32, map.get(1).getTileDimension(), 10, 50, map.get(2));
        monster2.init();
        map.get(2).addAdversary(monster2);
        
        map.add(new Map("fourthMap", "townMap.ogg", false));
        PNJ monster3 = new PNJ(9 * 32, 9 * 32, map.get(3).getTileDimension(), 10, 50, map.get(3));
        monster3.init();
        PNJ monster4 = new PNJ(2 * 32, 4 * 32, map.get(3).getTileDimension(), 10, 50, map.get(3));
        monster4.init();
        PNJ monster5 = new PNJ(11 * 32, 5 * 32, map.get(3).getTileDimension(), 10, 50, map.get(3));
        monster5.init();
        map.get(3).addAdversary(monster3);
        map.get(3).addAdversary(monster4);
        map.get(3).addAdversary(monster5);
        
        // Création d'un joueur
        objPlayer = new Player(224, 192, this.map.get(indexMap).getTileDimension());
        objPlayer.init();
        
        animations = new AnimationView();
               
		music = new Sounds(map.get(0).getMusicFilename());
		myHud = new Hud();
		myHud.init(true);
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
		
		displayMonsters(g, map.get(indexMap).getAliveAdversaries());
		
		// Affichage de l'Avant-Plan
		map.get(indexMap).renderForeground();
	    
		// Affichage des barres de vie, attaque, etc...
		myHud.render(g, objPlayer.getLife(), objPlayer.getAttack());

		if(objPlayer.getFightingState()){
			// RECUPERATION DE LA VIE DU MONSTRE QUE L'ON EST EN TRAIN DE COMBATTRE
			displayHUDMonsters(g, 50);
		}
		
		//affiche l'animation des degats si un joueur est touché
		if(map.get(indexMap).playerHit()){
			objPlayer.setFightingState(true);
			animations.hitAnimate(map.get(indexMap));
			animations.swordAttack.draw(objPlayer.getPosition().getAbsciss(), objPlayer.getPosition().getOrdinate());
		}
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
		objPlayer.getNextPosition(delta);
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
		
		boolean isOnEdge = true;
		// Touche ESC on termine le programme
		if (key == Input.KEY_ESCAPE) {
            container.exit();
        }
		
		if(key == Input.KEY_SPACE){
			objPlayer.attack(map.get(indexMap));
		}
		
	    // Si l'on a fini le mouvement
		if(!objPlayer.isMoving()){
			switch (key){
    			case Input.KEY_UP:  
    				objPlayer.setDirection(Direction.NORTH);
    				if(objPlayer.getPosition().getOrdinate() > 0){
    					isOnEdge = false;
    				}
    			break;
    		case Input.KEY_LEFT:
    				objPlayer.setDirection(Direction.EAST);
       				if(objPlayer.getPosition().getAbsciss() > 0){
    					isOnEdge = false;
    				}
    			break;
    		case Input.KEY_DOWN:
    				objPlayer.setDirection(Direction.SOUTH);
       				if(objPlayer.getPosition().getOrdinate() < HEIGHT_MAX){
    					isOnEdge = false;
    				}
    			break;
    		case Input.KEY_RIGHT:
    				objPlayer.setDirection(Direction.WEST);
       				if(objPlayer.getPosition().getAbsciss() < WIDTH_MAX){
    					isOnEdge = false;
    				}
    			break;
			}

			// Il n'est pas sur le bord de la fenêtre
			if(!isOnEdge){ 
				// La prochaine case n'est pas une collision
				if(!map.get(indexMap).findCollision(key, objPlayer.getPosition())){
					Exit exit = map.get(indexMap).findExit(key, objPlayer.getPosition());
					// Pas de collision, on vérifie si ce n'est pas une sortie
					if(exit != null){
						indexMap = exit.getMapNumber();
						objPlayer.setPosition(exit.getNextPosition());
						try {
							music.setBackgroundMusic(map.get(indexMap).getMusicFilename());
						} catch (SlickException e) {
							
						}
					} else {
						objPlayer.setMoving();
					}
				}
			}
		}
	}
	
	/**
     * Affichage de texte sur la fenêtre du jeu.
     *
     * @param g - Contexte Graphique.
     * @param text - Texte à afficher.
     * @param absOrigin - Coordonnée Horizontale en haut à gauche de la première lettre du texte.
     * @param ordOrigin - Coordonnée Verticale en haut à gauche de la première lettre du texte.
     */
	public void displayText(Graphics g, String text, float absOrigin, float ordOrigin){
		g.setColor(new Color(255, 255, 255));
		g.drawString(text, absOrigin, ordOrigin);
	}
	
	/**
     * Affichage des monstres sur la fenêtre du jeu.
     *
     * @param g - Contexte Graphique.
     * @param monsters - Liste des monstres à afficher
     */
	private void displayMonsters(Graphics g, List<PNJ> monsters){
		for(PNJ monster : monsters){
			g.drawImage(monster.getStandingImage(), monster.getPosition().getAbsciss(), monster.getPosition().getOrdinate());
		}
	}
	
	/**
     * Affichage du HUD monstre en combat.
     *
     * @param g - Contexte Graphique.
     * @param monsterLife - Vue du Monstre
     */
	private void displayHUDMonsters(Graphics g, int monsterLife) throws SlickException{
		Hud monsterHud = new Hud();
		monsterHud.init(false);
		monsterHud.renderMonster(g, monsterLife);
	}
	
}
