package telecom.fa17.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fireball extends PNJ{

	Image image;
	int signumAbsciss;
	int signumOrdinate;
	
	public Fireball(Position position, int tileSize, int signumAbsciss, int signumOrdinate, int life, int attack, Map map) throws SlickException {
		//ne pas tester si case est libre ou non => firelord test deja
		super(position.getAbsciss()+signumAbsciss, position.getOrdinate()+signumOrdinate, tileSize, life, attack, 1);
		this.image = new Image("resources/map/monster/projectile_fireball.png");
		this.signumAbsciss = signumAbsciss;
		this.signumOrdinate = signumOrdinate;
		this.isCrossable = true;
		setMoving();
		//move();
	}

	@Override
	public void init() throws SlickException {
		standings[Direction.SOUTH.index] = image;
	}
	
	public void move() throws InterruptedException{
		//Thread.sleep(1000);
		// tester si prochaine case est libre ou non
		// si non libre (solid) => destroy fireball
		// si non libre (joueur) => maj player
		// si libre continue
	}

}
