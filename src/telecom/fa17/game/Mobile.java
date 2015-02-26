package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mobile {
	
	final int DURATION_FRAME = 100, SLOW_ANIM = 5;
	
	private boolean moving = false;
	private Position position;
	
	private Direction direction = Direction.SOUTH;
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
		position = new Position(x, y);
		scale = tileSize * SLOW_ANIM;
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
	
	public Position getPosition(){
		return position;
	}
<<<<<<< HEAD
	
	public void setAbsciss(float prmAbs){
		absciss = prmAbs;
	}
	
	public void setOrdinate(float prmOrd){
		ordinate = prmOrd;
	}
	
	public float getNextAbsciss(){
		if (this.moving) {
=======

	public void setPosition(Position pos)
	{
		position = pos;
	}
	
	public void setPosition(float x, float y)
	{
		position.setAbsciss(x);
		position.setOrdinate(y);
	}
	
	public void getNextPosition()
	{
		if (moving) {
>>>>>>> RefactoringMethod
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
<<<<<<< HEAD
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
	        	default:
	        			break;
	        }
=======
>>>>>>> RefactoringMethod
	    } 
	}
	
	public void getNext(boolean HORIZONTAL, boolean UP)
	{			
		int x = 0, y = 0;
		if((tempScale < scale)) { 
			tempScale++;
			if(tempScale % SLOW_ANIM == 0) {
				if(HORIZONTAL)
				{ 
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
		if(getLife() == 0){
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
	
	public void attack(){
		
	}
}
