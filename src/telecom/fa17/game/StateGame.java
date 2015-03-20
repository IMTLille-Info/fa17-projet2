package telecom.fa17.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {

	public StateGame() {
		super("GameZ");
	}

	/*
	 * C'est ici qu'on ajoute les différentes phases de jeu
	 * WARNING : Chaque phase doit avoir un identifiant unique
	 * 
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MapGameState());
	}

	/*
	 * Méthôde main de référence, lance le jeu 
	 */
	public static void main(String[] args) throws SlickException {
	    new AppGameContainer(new StateGame(), 640, 480, false).start();
	}
}
