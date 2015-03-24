package telecom.fa17.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import telecom.fa17.end.EndState;

public class StateGame extends StateBasedGame {

	private GameContainer container;
	
	public StateGame() {
		super("GameZ");
	}

	/*
	 * C'est ici qu'on ajoute les différentes phases de jeu
	 * WARNING : Chaque phase doit avoir un identifiant unique
	 * 
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.container = container;
		addState(new MapGameState());
		addState(new telecom.fa17.end.EndState());
	}
	
	/*
	 * Méthode main de référence, lance le jeu 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new StateGame(), 640, 480, false);
		container.setShowFPS(false); // Désactivation de l'affichage des FPS
		container.start(); // Démarrage du jeu (lancement de la fenêtre)
	}
}
