package pokeslick.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mobile {
	
	final int DURATION_FRAME = 100, SLOW_ANIM = 5;
	
	private boolean moving = false;
	private float absciss, ordinate;
	private Direction direction = Direction.SOUTH;
	
	protected Animation[] animations = new Animation[4];
	protected Image[] standings = new Image[4];
	
	private int scale, tempScale = 0;
	
	public Mobile(float x, float y, int tileSize){
		absciss = x;
		ordinate = y;
		scale = tileSize * SLOW_ANIM;
	}
	
	public abstract void init() throws SlickException;
	
	public void setDirection(Direction dir){
		direction = dir;
	}
	
	public void setMoving(){
		tempScale = 0;
		moving = true;
	}
	
	public boolean isMoving(){
		return moving;
	}
	
	public float getAbsciss(){
		return absciss;
	}
	
	public float getOrdinate(){
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
	
	public float getNextAbsciss()
	{
		if (this.moving) {
	        switch (direction) {
	        	// On veut aller à gauche
	        	case EAST :
	        			getNext(false, -1);
        				break;
	        	// On veut aller à droite
	        	case WEST :
	        			getNext(false, 1);
						break;
	        }
		} 
		return absciss;
	}
	
	public float getNextOrdinate(){		
		if (this.moving){
	        switch (direction){
	        	// On veut monter
	        	case NORTH :
	        			getNext(true, -1);
	        			break;
	        	// On veut descendre
	        	case SOUTH :
	        			getNext(true, 1);
    					break;
	        }
	    } 
		return ordinate;
	}
	
	public void getNext(boolean NORTHSOUTH, int move){
		if((tempScale < scale)) { 
			tempScale++;
			if(tempScale % SLOW_ANIM == 0) {
				if(NORTHSOUTH){
					ordinate += move;
				} else {
					absciss += move;
				}				
			}
		} else {
			this.moving = false;
		}
	}
	
	
	public Image getStandingImage()	{
		return standings[direction.index];
	}
	
	public Animation getAnimation()	{
		return animations[direction.index];
	}
}