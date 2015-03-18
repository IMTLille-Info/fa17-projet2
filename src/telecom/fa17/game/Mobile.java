package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mobile extends Element {

	protected final int DURATION_FRAME = 100;

	private boolean moving = false;
	private Direction direction = Direction.SOUTH;

	private int life;
	private boolean isMobile;
	private int attack;
	// BOOLEAN pour indiquer si le joueur est en combat
	private boolean isFighting;

	protected Animation[] animations;
	protected Image[] standings;

	protected int scale;
	private int tempScale = 0;
	private int moveAnim = 0;

	public Mobile(float x, float y, int tileSize, int life, int attack) {
		super(x, y, false);
		scale = tileSize;
		isFighting = false;
		this.life = life;
		this.attack = attack;

		animations = new Animation[4];
		standings = new Image[4];
	}

	public abstract void init() throws SlickException;

	public void setDirection(Direction dir) {
		direction = dir;
	}

	public void setMoving() {
		moving = true;
	}

	public boolean isMoving() {
		return moving;
	}

	public void getNextPosition(int delta) {
		if (moving) {
			moveAnim += delta;
			switch (direction) {
			// On veut monter
			case NORTH:
				getNext(false, true);
				break;
			// On veut aller à gauche
			case EAST:
				getNext(true, false);
				break;
			// On veut descendre
			case SOUTH:
				getNext(false, false);
				break;
			// On veut aller à droite
			case WEST:
				getNext(true, true);
				break;
			default:
				break;
			}
		}
	}

	public void getNext(boolean HORIZONTAL, boolean UP) {
		int x = 0, y = 0;
		if ((tempScale < scale)) {
			// Toutes les 4ms, on bouge le personnage d'un pixel
			if (moveAnim > 4) {
				tempScale++;
				if (HORIZONTAL) {
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

	public Image getStandingImage() {
		return standings[direction.index];
	}

	public Animation getAnimation() {
		return animations[direction.index];
	}

	public boolean isAlive() {
		if (this.life <= 0) {
			this.isCrossable = true;
			return false;
		} else {
			return true;
		}
	}

	public int getLife() {
		return life;
	}

	/**
     * Ajoute de la vie au personnage.
     *
     * @param hp - Vie à ajouter
     * INFO - Limitation of the life : 100 HP
     */
	public void addLife(int hp) {
		if ((this.life + hp) < 100) {
			this.life += hp;
		} else {
			this.life = 100;
		}
	}

	public void hurt(int attack) {
		this.life -= attack;
	}

	public boolean isMobile() {
		// certains monstres peuvent être non mobile / ou effet immobilisant
		// temporaire sur joueur
		return isMobile;
	}

	public Position getInfrontPosition() {
		float x = 0, y = 0;
		switch (direction) {
		case NORTH:
			y = -scale;
			break;
		case EAST:
			x = -scale;
			break;
		case SOUTH:
			y = scale;
			break;
		case WEST:
			x = scale;
			break;
		default:
			break;
		}
		return new Position(position.getAbsciss() + x, position.getOrdinate() + y);
	}

	
	public void attack(Map map) {
		Position target = getInfrontPosition();
		for (PNJ pnj : map.getAliveAdversaries()) {
			if (pnj.getPosition().equals(target)) {
				pnj.hurt(attack);
				this.hurt(pnj.getAttack());
				map.setHitZone(target);
			}
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Direction getDirection() {
		return direction;
	}

	public void addAttack(int power) {
		this.attack += attack;
	}

	public boolean isFighting() {
		return isFighting;
	}

	public void setInFight(boolean inFight) {
		this.isFighting = inFight;
	}
}
