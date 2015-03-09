package telecom.fa17.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {

	private static int X_BAR = 16;
	private static int X_BAR_MONSTER = 444;
	private static int Y_BAR = 432;
	private static int X_START_BAR = 57;
	private static int Y_BAR_LIFE = 434;
	private static int X_START_BAR_MONSTER = 485;
	private static int Y_BAR_ATTACK = 451;
	private static int BAR_WIDTH = 129;
	private static int BAR_HEIGHT = 8;
	private static final Color LIFE_COLOR = new Color(0, 255, 0);
	private static final Color ATTACK_COLOR = new Color(0, 0, 255);
	private static final Color MONSTER_LIFE_COLOR = new Color(255, 0, 0);
	
	private Image hudImage;
	
	public void init(boolean player) throws SlickException {
		this.hudImage = (player) ? new Image("resources/hud/FullBar.png") : new Image("resources/hud/MonsterBar.png");
	}

	public void render(Graphics g, int life, int attack) {
		g.drawImage(hudImage, X_BAR, Y_BAR);
		g.setColor(LIFE_COLOR);
		g.fillRect(X_START_BAR, Y_BAR_LIFE, (life / 100f) * BAR_WIDTH, BAR_HEIGHT);
		g.setColor(ATTACK_COLOR);
		g.fillRect(X_START_BAR, Y_BAR_ATTACK, (attack / 100f) * BAR_WIDTH, BAR_HEIGHT);
	}
	
	public void renderMonster(Graphics g, int life) {
		g.drawImage(hudImage, X_BAR_MONSTER, Y_BAR);
		g.setColor(MONSTER_LIFE_COLOR);
		g.fillRect(X_START_BAR_MONSTER, Y_BAR_LIFE, (life / 100f) * BAR_WIDTH, BAR_HEIGHT);
	}
}
