package telecom.fa17.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {

	// Positions of Element with X & Y Coordinates
	static final int X_BAR = 16,
					 X_BAR_MONSTER = 444,
					 Y_BAR = 432,
					 X_START_BAR = 57,
		    		 Y_BAR_LIFE = 434,
					 X_START_BAR_MONSTER = 485,
					 Y_BAR_ATTACK = 451,
					 BAR_WIDTH = 129,
		             BAR_HEIGHT = 8;
	static final Color LIFE_COLOR = new Color(0, 255, 0),
					   ATTACK_COLOR = new Color(0, 0, 255),
					   MONSTER_LIFE_COLOR = new Color(255, 0, 0);
	
	private Image hudImage;
	
	public void init(boolean player) throws SlickException {
		this.hudImage = (player) ? new Image("resources/hud/FullBar.png") : new Image("resources/hud/MonsterBar.png");
	}

	/**
     * Affichage du HUD Player sur la fenêtre du jeu.
     *
     * @param g - Contexte Graphique.
     * @param life - Vie du joueur.
     * @param attack - Attaque du joueur.
     */
	public void render(Graphics g, int life, int attack) {
		g.drawImage(hudImage, X_BAR, Y_BAR);
		g.setColor(LIFE_COLOR);
		g.fillRect(X_START_BAR, Y_BAR_LIFE, (life / 100f) * BAR_WIDTH, BAR_HEIGHT);
		g.setColor(ATTACK_COLOR);
		g.fillRect(X_START_BAR, Y_BAR_ATTACK, (attack / 100f) * BAR_WIDTH, BAR_HEIGHT);
	}
	
	/**
     * Affichage du HUD Monstre sur la fenêtre du jeu.
     *
     * @param g - Contexte Graphique.
     * @param life - Vie du monstre.
     */
	public void renderMonster(Graphics g, int life) {
		g.drawImage(hudImage, X_BAR_MONSTER, Y_BAR);
		g.setColor(MONSTER_LIFE_COLOR);
		g.fillRect(X_START_BAR_MONSTER, Y_BAR_LIFE, (life / 100f) * BAR_WIDTH, BAR_HEIGHT);
	}
}
