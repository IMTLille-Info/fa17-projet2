package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mobile extends Object{
	
	final int DURATION_FRAME = 100, SLOW_ANIM = 5;
	
	private boolean moving = false;
	
	private Direction direction = Direction.SOUTH;
	protected int life;
	protected boolean isMobile;
	protected int attack;
	
	protected Animation[] animations;
	protected Image[] standings;
	
	private int scale, tempScale = 0;
	
	public Mobile(float x, float y, int tileSize){
		super(x, y, false);
		scale = tileSize * SLOW_ANIM;
		
		animations = new Animation[4];
		standings = new Image[4];
	}
	
	public abstract void init() throws SlickException;
		
	public void setDirection(Direction dir){
		direction = dir;
	}
	
	public void setMoving(){
		moving = true;
	}
	
	public boolean isMoving(){
		return moving;
	}

	public void getNextPosition(){
		if (moving) {
	        switch (direction) {
        		// On veut monter
        		case NORTH :
        			getNext(false, true);
        			break;
	        	// On veut aller à gauche
	        	case EAST :
	        			getNext(true, false);
        				break;
        	        	// On veut descendre
        	    case SOUTH :
        	        	getNext(false, false);
            			break;
	        	// On veut aller à droite
	        	case WEST :
	        			getNext(true, true);
						break;
	        	default:
	        			break;
	        }
		}
	}
	
	private void getNext(boolean HORIZONTAL, boolean UP){			
		int x = 0, y = 0;
		if((tempScale < scale)) { 
			tempScale++;
			if(tempScale % SLOW_ANIM == 0) {
				if(HORIZONTAL){ 
					x = (UP) ? 1 : -1; 
				} else { 
					y = (UP) ? -1 : 1; 			
				}
				setPosition(position.getAbsciss() + x, position.getOrdinate() + y);
			} 
		} else {
			moving = false;
			tempScale = 0;
		}
	}
	
	public Image getStandingImage()	{
		return standings[direction.index];
	}
	
	public Animation getAnimation()	{
		return animations[direction.index];
	}
	
	public boolean isAlive() {
		return (getLife() == 0) ? false : true;
	}

	private int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public boolean isMobile() {
		//certains monstres peuvent être non mobile / ou effet immobilisant temporaire sur joueur
		return isMobile;
	}
	
	public void attack(){
		
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		//objet récupéré peut augmenter l'attaque du joueur
		this.attack = attack;
	}
}
