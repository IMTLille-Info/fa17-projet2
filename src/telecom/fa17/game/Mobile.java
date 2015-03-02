package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import sun.net.www.content.image.png;

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
			if(HORIZONTAL){ 
				x = (UP) ? 1 : -1; 
			} else { 
				y = (UP) ? -1 : 1; 			
			}
			setPosition(position.getAbsciss() + x, position.getOrdinate() + y);
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
		if (!(getLife() > 0)){
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
	
	public Position getNearPosition(Map map){
		switch (direction) {
			case NORTH :
				return new Position(position.absciss , position.ordinate - map.getTileDimension());
		
			case EAST :
				return new Position(position.absciss + map.getTileDimension() , position.ordinate);
	        
			case SOUTH :
				return new Position(position.absciss , position.ordinate +map.getTileDimension());
    
			case WEST :
				return new Position(position.absciss - map.getTileDimension() , position.ordinate);
			
			default:
				return position;
				
		}
	}
	
	public void attack(Map map){
		Position target = getNearPosition(map);
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
