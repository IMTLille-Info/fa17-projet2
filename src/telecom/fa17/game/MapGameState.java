package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import telecom.fa17.end.EndState;
/**
 * @author FLORENT / PE / ÉTIENNE
 *
 */
public class MapGameState extends BasicGameState {

    private GameContainer container;
	private StateBasedGame game;
    public static final int ID = 1;
    
	public static List<Map> map;
	
	// Constantes de Map
	private static int WIDTH_MAX;
	private static int HEIGHT_MAX;
	
	// Contenu standard de la fenêtre
	public static int indexMap;
	public static Player objPlayer;
	
	private static AnimationView animations;
	private static Sounds music;
	private static Hud myHud;
	
	int tempoText;
	private static String displayText = "";	
	
	@Override
	public int getID() {
		return ID;
	}
	
	/** 
	 * Initialise le contenu du jeu, charger les graphismes, la musique, etc...
	 * @throws SlickException 
	 */
	public static void razGame() throws SlickException {
        indexMap = 0;
        
        // Init First Map
        map.add(new Map("firstMap", "townMap.ogg", false));
        WIDTH_MAX = map.get(0).getWidth();
        HEIGHT_MAX = map.get(0).getHeight();
        map.get(0).addTrigger(new Exit(9, 0, 9, 14, 1));
        map.get(0).addTrigger(new Exit(10, 0, 16, 14, 1));
        map.get(0).addTrigger(new HealBonus(9, 1, 20));
        
        // Init Second Map
        map.add(new Map("secondMap", "caveMap.ogg", false));
        map.get(1).addTrigger(new Exit(9, 14, 9, 0, 0));
        map.get(1).addTrigger(new Exit(16, 14, 10, 0, 0)); 
        map.get(1).addTrigger(new Exit(7, 3, 1, 13, 2));
        map.get(1).addTrigger(new Exit(17, 4, 18, 1, 2));
        map.get(1).addTrigger(new Exit(18, 1, 17, 5, 3));
 
        PNJ monster = new PNJ(11, 5, map.get(1).getTileDimension(), 30, 20, map.get(1), 1);
        monster.init();
        map.get(1).addAdversary(monster);
        
        // Init Third Map
        map.add(new Map("thirdMap", "caveMap.ogg", false));
        map.get(2).addTrigger(new Exit(1, 13, 7, 3, 1));
        map.get(2).addTrigger(new Exit(18, 1, 17, 4, 1));
        PNJ monster2 = new PNJ(4, 8, map.get(1).getTileDimension(), 50, 20, map.get(2), 2);
        monster2.init();
        map.get(2).addAdversary(monster2);
        
        // Init Final Map
        map.add(new Map("fourthMap", "townMap.ogg", false));
        PNJ monster3 = new PNJ(9, 9, map.get(3).getTileDimension(), 50, 40, map.get(3), 3);
        monster3.init();
        PNJ monster4 = new PNJ(2, 4, map.get(3).getTileDimension(), 80, 70, map.get(3), 3);
        monster4.init();
        PNJ monster5 = new PNJ(11, 5, map.get(3).getTileDimension(), 100, 100, map.get(3), 3);
        monster5.init();
        map.get(3).addAdversary(monster3);
        map.get(3).addAdversary(monster4);
        map.get(3).addAdversary(monster5);
        map.get(3).addTrigger(new HealBonus(9, 8, 20));
        
        // Init ARENA Map
        map.add(new Map("Arena", "caveMap.ogg", true));
        map.get(4).setArena(true);
        
        // Création d'un joueur
        SpriteSheet playerSprite = new SpriteSheet("resources/map/player/zelda.png", 60, 75);
        objPlayer = new Player(7, 6, MapGameState.map.get(indexMap).getTileDimension());
        objPlayer.init(playerSprite);
        
        // Init Animations
        animations = new AnimationView(playerSprite);
           
        // Init Music
		music = new Sounds(map.get(0).getMusicFilename());
		
		// Init Player HUD
		myHud = new Hud();
		myHud.init(true);
		
		textInit("GameZ - The Best game you've ever played !");
		//textInit("Welcome in our game !");
    }
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.game = game;
        
        map = new LinkedList<Map>();
		
        razGame();
	}
	
	/** 
	 * Affiche le contenu du jeux
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	    
		// Affichage du fond principal de la carte
		map.get(indexMap).renderBackground();
		
		// Si on appuie sur une touche de direction, on joue une animation
		if (objPlayer.isMoving()) {
			g.drawAnimation(objPlayer.getAnimation(), objPlayer.getPosition().getAbsciss()-15, objPlayer.getPosition().getOrdinate()-22);
		} else {
			// Affiche l'animation d'un coup d'epee
			if(objPlayer.isAttacking()){
				animations.attackAnimate(map.get(indexMap), objPlayer);
			} else {
				// Sinon, on affiche le personnage statique en fonction de sa dernière direction
				g.drawImage(objPlayer.getStandingImage(), objPlayer.getPosition().getAbsciss()-15, objPlayer.getPosition().getOrdinate()-22);		
			}
		}
		displayMonsters(g, map.get(indexMap).getAliveAdversaries());
		
		// Affiche l'animation des dégats si le joueur est touché
		if(map.get(indexMap).playerHit()){
			animations.hitAnimate(map.get(indexMap));
		}
		
		map.get(indexMap).drawTrigger();
		
		// Affichage de l'Avant-Plan de la Carte
		map.get(indexMap).renderForeground();		
		
		// Affichage info-bulle
		if(!displayText.isEmpty()) displayText(g, tempoText, displayText );
		
		// Affichage des barres de vie, attaque, etc...
		myHud.render(g, objPlayer.getLife(), objPlayer.getAttack());
		
		// MAP Arene, Affichage de la barre de vie du monstre responsable du changement de map
		if(indexMap == 4) {
			displayHUDMonsters(g, map.get(indexMap).getLifeMainPNJArena());
		}
    }
	
	/** 
	 * Met à jour les éléments de la scène en fonction du delta temps qui est survenu. 
	 * C’est ici que la logique du jeux est renfermé.
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		// Affichage INFO-BULLE
		textUpdate(delta);
		
		// Player mort = passage à l'état END
		if(!objPlayer.isAlive()){
			keyPressed(Input.KEY_NUMPAD9, ' ');
		}

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
	 * Méthode qui permet de savoir quand une touche est pressée.
	 * 
	 * Touche pour quitter : ESC. 
	 */	
	@Override
	public void keyPressed(int key, char c) {
		
		/* 
		 * TEST POUR ARRIVER A LA FIN
		 */
		if (key == Input.KEY_NUMPAD9) {
				game.enterState(EndState.ID);
		}
		/* ****** */
		
		// Touche ESC on termine le programme
		if (key == Input.KEY_ESCAPE) {
            container.exit();
        }
		
		// Touche SPACE on veut attaquer
		if(key == Input.KEY_SPACE){
				objPlayer.attack(map.get(indexMap));
		}
		
	    // Si l'on a fini le mouvement
		if(!objPlayer.isMoving()){
			boolean isOnEdge = true;
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
					
					Trigger trigger = map.get(indexMap).findTrigger(key, objPlayer.getPosition());
					// Pas de collision, on vérifie si il y a un trigger
					if(trigger != null){
						trigger.action();
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
	 * Mis à jour des attributs pour afficher un texte
	 * 
	 * @param delta
	 */
	static void textInit(String prmS) {
		displayText = prmS;
	}
	
	/**
	 * Mis à jour des attributs pour afficher un texte
	 * 
	 * @param delta
	 */
	private void textUpdate(int delta) {
		// Time in ms
		final int TIME_DISPLAY_TEXT = 3000;
		
		if(!displayText.isEmpty()) {
			tempoText += delta; 
			if(tempoText > TIME_DISPLAY_TEXT){
				displayText = "";
				tempoText = 0;
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
	public void displayText(Graphics g, int timeSpent, String text){
		final int TIME_FOR_ONE_MORE_LETTER = 45, TIME_TO_DISPLAY = 2000;
		
		// Positions of Element with X & Y Coordinates
		final int X_START = 220,
				  X_START_INTERIOR = 225,
				  Y_START = 420,
				  Y_START_INTERIOR = 425,
			      X_WIDTH = 410,
				  X_WIDTH_INTERIOR = 400,
				  Y_HEIGHT = 50,
				  Y_HEIGHT_INTERIOR = 40,
			      CORNER = 10;
		
		final int X_START_TEXT = 240, Y_START_TEXT = 435;
		
		// Couleur Blanche
		g.setColor(new Color(255, 255, 255));
		g.fillRoundRect(X_START, Y_START, X_WIDTH, Y_HEIGHT, CORNER);
		
		// Couleur Noire
		g.setColor(new Color(0, 0, 0));
		g.drawRoundRect(X_START, Y_START, X_WIDTH, Y_HEIGHT, CORNER);
		g.drawRoundRect(X_START_INTERIOR, Y_START_INTERIOR, X_WIDTH_INTERIOR, Y_HEIGHT_INTERIOR, CORNER);
		
		if(timeSpent < TIME_TO_DISPLAY)
		{
			int lengthToDisplay = timeSpent / TIME_FOR_ONE_MORE_LETTER;
			if(lengthToDisplay < text.length()){
				g.drawString(text.substring(0, lengthToDisplay), X_START_TEXT, Y_START_TEXT);
			} else {
				g.drawString(text, X_START_TEXT, Y_START_TEXT);
			}
		} else {
			g.drawString(text, X_START_TEXT, Y_START_TEXT);
		}
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
