package pokeslick.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public abstract class Avatar {
	
	final int DURATION_FRAME = 1, SLOW_ANIM = 10;
	
	private boolean moving = false;
	private float absciss, ordinate;
	private int direction = 2;
	
	protected Animation[] animations = new Animation[4];
	protected Image[] standings = new Image[4];
	
	private int xScale, yScale;
	
	public Avatar(float x, float y, int tileSize) 
	{
		absciss = x;
		ordinate = y;
		xScale = yScale = tileSize * SLOW_ANIM;
	}
	
	public void setMoving()
	{
		xScale = 320;
		yScale = 320;
		moving = true;
	}
	
	public boolean isMoving()
	{
		return moving;
	}
	
	public float getAbsciss()
	{
		return absciss;
	}
	
	public float getOrdinate()
	{
		return ordinate;
	}
	
	public float getNextAbsciss(int where, int xLimit, int delta)
	{
		if (this.moving) {
			this.direction = where;
	        switch (where) {
	        	// On veut aller à gauche
	        	case 1 :
	        			if(absciss > xLimit) 
	        			{ 
	        				xScale -= delta;
	        				if(xScale % SLOW_ANIM == 0)
	        					absciss -= DURATION_FRAME;
	        			}
        				break;
	        	// On veut aller à droite
	        	case 3 :
	        			if((absciss < xLimit))
	        			{ 
	        				xScale -= delta;	
	        				if(xScale % SLOW_ANIM == 0)
	        					absciss += DURATION_FRAME; 
	        			}
						break;
	        }
		} else {
			moving = false;
		}
		return absciss;
	}
	
	public float getNextOrdinate(int where, int yLimit, int delta)
	{		
		if (this.moving) {
			this.direction = where;
	        switch (where) {
	        	// On veut monter
	        	case 0 :
	        			if((ordinate > yLimit)) 
	        			{ 
	        				yScale -= delta;
	        				if(yScale % SLOW_ANIM == 0)
	        					ordinate -= DURATION_FRAME;
	        			}
	        			break;
	        	// On veut descendre
	        	case 2 :
	        			if((ordinate < yLimit))
	        			{ 
	        				yScale -= delta;
	        				if(yScale % SLOW_ANIM == 0)
	        					ordinate += DURATION_FRAME;
	        			}
    					break;
	        }
	    } else {
			moving = false;
		}
		return ordinate;
	}
	
	
	public Image getStandingImage()
	{
		return standings[direction];
	}
	
	public Animation getAnimation()
	{
		return animations[direction];
	}
}
