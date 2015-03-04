package telecom.fa17.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {

	private static final int X_BAR = 10;
	private static final int Y_BAR = 10;
	private static final int X_BAR_LIFE = 10;
	private static final int Y_BAR_LIFE = 10;
	private static final int BAR_WIDTH = 10;
	private static final int BAR_HEIGHT = 10;
	private static final Color LIFE_COLOR = new Color(0, 255, 0);
	
	private Image hudImage;

	public void init() throws SlickException {
		this.hudImage = null;
	}

	public void render(Graphics g) {
		g.setColor(LIFE_COLOR);
		g.fillRect(X_BAR_LIFE, Y_BAR_LIFE, .9f * BAR_WIDTH, BAR_HEIGHT);
		g.drawImage(hudImage, X_BAR, Y_BAR);
	}
}
