package pokeslick.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mobile {
	
	final int DURATION_FRAME = 100, SLOW_ANIM = 6;
	
	private boolean moving = false;
	private float absciss, ordinate;
	private Direction direction = Direction.SOUTH;
	
	protected Animation[] animations = new Animation[4];
	protected Image[] standings = new Image[4];
	
	private int scale, tempScale = 0;
	
	public Mobile(float x, float y, int tileSize) 
	{
		absciss = x;
		ordinate = y;
		scale = tileSize * SLOW_ANIM;
	}
	
	// Cette méthode doit être redéfinie pour ajouter les images
	public abstract void init() throws SlickException;
	
	public void setDirection(Direction dir)
	{
		direction = dir;
	}
	
	public void setMoving()
	{
		tempScale = 0;
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
	
	public void setAbsciss(float prmAbs)
	{
		absciss = prmAbs;
	}
	
	public void setOrdinate(float prmOrd)
	{
		ordinate = prmOrd;
	}
	
	public float getNextAbsciss(int delta)
	{
		if (this.moving) {
	        switch (direction) {
	        	// On veut aller à gauche
	        	case EAST :
	        			if(tempScale < scale) 
	        			{ 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0)
	        					absciss--;
	        			} else {
	        				this.moving = false;
	        			}
        				break;
	        	// On veut aller à droite
	        	case WEST :
	        			if((tempScale < scale))
	        			{ 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0)
	        					absciss++; 
	        			} else {
	        				this.moving = false;
	        			}
						break;
			default:
				break;
	        }
		} 
		return absciss;
	}
	
	public float getNextOrdinate(int delta)
	{		
		if (this.moving) {
	        switch (direction) {
	        	// On veut monter
	        	case NORTH :
	        			if((tempScale < scale)) 
	        			{ 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0)
	        					ordinate--;
	        			} else {
	        				this.moving = false;
	        			}
	        			break;
	        	// On veut descendre
	        	case SOUTH :
	        			if((tempScale < scale))
	        			{ 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0)
	        					ordinate++;
	        			} else {
	        				this.moving = false;
	        			}
    					break;
			default:
				break;
	        }
	    } 
		return ordinate;
	}
	
	public Image getStandingImage()
	{
		return standings[direction.index];
	}
	
	public Animation getAnimation()
	{
		return animations[direction.index];
	}
}
