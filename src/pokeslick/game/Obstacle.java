package pokeslick.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Obstacle {
	
	private float absciss,
				  ordinate;
	protected boolean visible = false;
	protected Image[] img;
	
	public Obstacle(float absciss, float ordinate)
	{
		this.absciss = absciss;
		this.ordinate = ordinate;
		img = new Image[4];
	}
	
	public abstract void init() throws SlickException;
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setVisible()
	{
		visible = true;
	}
	
	public float getAbsciss()
	{
		return absciss;
	}

	public float getOrdinate()
	{
		return ordinate;
	}
}

