package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Obstacle {
	
	private Position position;
	protected boolean isCrossable;
	protected Image img;
	
	public Obstacle(float x, float y, boolean isCrossable)
	{
		position = new Position(x, y);
		this.isCrossable = isCrossable;
	}
	
	public abstract void init();
	
	public boolean isCrossable(){
		return isCrossable;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position pos)
	{
		position = pos;
	}
	
	public void setPosition(float x, float y)
	{
		position.setAbsciss(x);
		position.setOrdinate(y);
	}
}
