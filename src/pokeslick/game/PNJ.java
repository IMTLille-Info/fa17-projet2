package pokeslick.game;

import org.newdawn.slick.SlickException;

public class PNJ extends Obstacle {

	String name;
	
	public PNJ(String name, float x, float y)
	{
		super(x, y);
		this.name = name;
	}
	@Override
	public void init() throws SlickException {
		img[Direction.NORTH.index] = null;
		img[Direction.EAST.index] = null;
		img[Direction.SOUTH.index] = null;
		img[Direction.WEST.index] = null;
	}

}
