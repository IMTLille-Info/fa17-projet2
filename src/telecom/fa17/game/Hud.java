package telecom.fa17.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {

	private static int X_BAR = 16;
	private static int Y_BAR = 448;
	private static int X_BAR_LIFE = 57;
	private static int Y_BAR_LIFE = 450;
	private static int BAR_WIDTH = 129;
	private static int BAR_HEIGHT = 8;
	private static final Color LIFE_COLOR = new Color(0, 255, 0);
	private static final Color MONSTER_LIFE_COLOR = new Color(255, 0, 0);
	
	private Image hudImage;
	
	public void init() throws SlickException {
		this.hudImage = new Image("resources/hud/HP_Bar.png");
	}

	public void render(Graphics g, float life) {
		g.drawImage(hudImage, X_BAR, Y_BAR);
		g.setColor(LIFE_COLOR);
		g.fillRect(X_BAR_LIFE, Y_BAR_LIFE, (float) (life / 100) * BAR_WIDTH, BAR_HEIGHT);
	}
}
