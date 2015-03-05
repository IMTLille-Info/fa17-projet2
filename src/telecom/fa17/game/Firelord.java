package telecom.fa17.game;

import org.newdawn.slick.SlickException;

public class Firelord extends PNJ{

	public Firelord(float x, float y, int tileSize, int attack, int life) {
		super(x, y, tileSize, attack, life);
	}
	
	public Fireball attack(Position playerPosition) throws SlickException{

		Position fireLordPosition = this.getPosition();
		float x = fireLordPosition.getAbsciss()-playerPosition.getAbsciss();
		float y = fireLordPosition.getOrdinate()-playerPosition.getOrdinate();
		
		int signumAbsciss = Long.signum((long)x);
		int signumOrdinate = Long.signum((long)y);
		
		Fireball ball = new Fireball(position, this.scale, signumAbsciss, signumOrdinate, getAttack());
		return ball;
	}
}