package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public abstract class Mobile {
	
	final int DURATION_FRAME = 100, SLOW_ANIM = 4;
	
	private boolean moving = false;
	private float absciss, ordinate;
	private int direction = 2;
	protected int life;
	protected boolean isMobile;
	protected int attack;
	
	protected Animation[] animations = new Animation[4];
	protected Image[] standings = new Image[4];
	
	private int scale, tempScale = 0;
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		//objet récupéré peut augmenter l'attaque du joueur
		this.attack = attack;
	}

	public Mobile(float x, float y, int tileSize){
		absciss = x;
		ordinate = y;
		scale = tileSize * SLOW_ANIM;
	}
	
	public void setDirection(int dir){
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
	
	public float getNextAbsciss(int delta)
	{
		if (this.moving) {
	        switch (direction) {
	        	// On veut aller à gauche
	        	case 1 :
	        			if(tempScale < scale) { 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0){
	        					absciss--;
	        				}
	        			} else {
	        				this.moving = false;
	        			}
        				break;
	        	// On veut aller à droite
	        	case 3 :
	        			if((tempScale < scale)) { 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0){
	        					absciss++; 
	        				}
	        			} else {
	        				this.moving = false;
	        			}
						break;
	        }
		} 
		return absciss;
	}
	
	public float getNextOrdinate(int delta){		
		if (this.moving){
	        switch (direction){
	        	// On veut monter
	        	case 0 :
	        			if((tempScale < scale)) { 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0) {
	        					ordinate--;
	        				}
	        			} else {
	        				this.moving = false;
	        			}
	        			break;
	        	// On veut descendre
	        	case 2 :
	        			if((tempScale < scale)) { 
	        				tempScale++;
	        				if(tempScale % SLOW_ANIM == 0) {
	        					ordinate++;
	        				}
	        			} else {
	        				this.moving = false;
	        			}
    					break;
	        }
	    } 
		return ordinate;
	}
	
	
	public Image getStandingImage()	{
		return standings[direction];
	}
	
	public Animation getAnimation()	{
		return animations[direction];
	}
	
	public boolean isAlive() {
		if(getLife()==0){
			return false;
		}
		return true;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public boolean isMobile() {
		//certains monstres peuvent être non mobile / ou effet immobilisant temporaire sur joueur
		return isMobile;
	}
}
