package telecom.fa17.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fireball extends Mobile{

	Image image;
	int signumAbsciss;
	int signumOrdinate;
	
	public Fireball(Position position, int tileSize, int signumAbsciss, int signumOrdinate, int attack) throws SlickException {
		//tester si case est libre ou non
		super(position.getAbsciss()+signumAbsciss, position.getOrdinate()+signumOrdinate, tileSize);
		this.image = new Image("resources/map/monster/projectile_fireball.png");
		this.signumAbsciss = signumAbsciss;
		this.signumOrdinate = signumOrdinate;
		setAttack(attack);
		this.isCrossable = true;
	}

	@Override
	public void init() throws SlickException {
		standings[Direction.SOUTH.index] = image;
	}
	
	public void move(){
		// tester si prochaine case est libre ou non
		// si non libre (solid) => destroy fireball
		// si non libre (joueur) => destroy maj player
		// si libre continue
	}

}
