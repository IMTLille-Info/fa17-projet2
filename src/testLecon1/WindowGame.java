/**
 * 
 */
package testLecon1;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author FLORENT
 *
 */
public class WindowGame extends BasicGame {

    private GameContainer container;

	/**
     * CrÃ©tion de la fenetre.
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
    }

	/** 
	 * Initialise le contenu du jeu, charger les graphismes, la musique, etc…
	 */
	@Override
	public void init(GameContainer container) throws SlickException {
        this.container = container;
    }

	/** 
	 * Met à jour les élément de la scène en fonction du delta temps qui est survenu. 
	 * C’est ici que la logique du jeux est renfermé.
	 */
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
    }
	
	/** 
	 * Démarre le jeu. 
	 */
	public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame("WindowTitle"), 640, 480, false).start();
    }

	
	/** 
	 * Méthode qui permet de quitter le jeu (fenetre) en cours d'éxécution.
	 * 
	 * Touche pour quitter : ESC. 
	 */
	@Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
