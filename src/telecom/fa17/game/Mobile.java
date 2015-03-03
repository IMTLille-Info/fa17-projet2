package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mobile extends Element{
	
	final int DURATION_FRAME = 100;
	
	private boolean moving = false;
	
	private Direction direction = Direction.SOUTH;
	protected int life;
	protected boolean isMobile;
	protected int attack;
	
	protected Animation[] animations;
	protected Image[] standings;
	
	private int scale, tempScale = 0;
	private int moveAnim = 0;
	
	public Mobile(float x, float y, int tileSize){
		super(x, y, false);
		scale = tileSize;
		
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

	public void getNextPosition(int delta){
		if (moving) {
	        switch (direction) {
        		// On veut monter
        		case NORTH :
        			getNext(false, true, delta);
        			break;
	        	// On veut aller à gauche
	        	case EAST :
	        			getNext(true, false, delta);
        				break;
        	        	// On veut descendre
        	    case SOUTH :
        	        	getNext(false, false, delta);
            			break;
	        	// On veut aller à droite
	        	case WEST :
	        			getNext(true, true, delta);
						break;
	        	default:
	        			break;
	        }
		}
	}
	
	private void getNext(boolean HORIZONTAL, boolean UP, int delta){			
		int x = 0, y = 0;
		if((tempScale < scale)) {
			moveAnim += delta;
			// Toutes les 5ms, on bouge le personnage d'un pixel
			if(moveAnim > 5){
				tempScale++;
				if(HORIZONTAL){ 
					x = (UP) ? 1 : -1; 
				} else { 
					y = (UP) ? -1 : 1; 			
				}
				setPosition(position.getAbsciss() + x, position.getOrdinate() + y);
				moveAnim = 0;
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
		if (getLife() <= 0){
			//image mort / cadavre loot item ou disparait
			this.isCrossable = true;
			return false;
		} else {
			return true;
		}
	}

	private int getLife() {
		return life;
	}

	public void hurt(int attack) {
		this.life -= attack;
	}
	
	public boolean isMobile() {
		//certains monstres peuvent être non mobile / ou effet immobilisant temporaire sur joueur
		return isMobile;
	}
	
	public Position getNearPosition(){
		float x = 0, y = 0;
		switch (direction) {
			case NORTH :
					y = -scale;
					break;
			case EAST :
					x = -scale;
					break;
			case SOUTH :
					y = scale;
					break;
			case WEST :
					x = scale;
					break;				
		}
		return new Position(position.getAbsciss() + x, position.getOrdinate() + y);
	}
	
	public void attack(Map map){
		Position target = getNearPosition();
		System.out.println(target.toString());
		for (PNJ pnj : map.getAdversaries()){
			if (Position.equals(pnj.getPosition() , target)){
				pnj.hurt(attack);
			}
		}
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		//objet récupéré peut augmenter l'attaque du joueur
		this.attack += attack;
	}
}
