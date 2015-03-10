package telecom.fa17.game;

import org.newdawn.slick.SlickException;

public class Firelord extends PNJ{
	
	int lifeFireball = 1;
	
	public Firelord(float x, float y, int tileSize, int attack, int life, Map map) {
		super(x, y, tileSize, attack, life, map);
		this.map = map;
	}
	
	public void attack(Position playerPosition) throws SlickException, InterruptedException{

		Position fireLordPosition = this.getPosition();
		float x = fireLordPosition.getAbsciss()-playerPosition.getAbsciss();
		float y = fireLordPosition.getOrdinate()-playerPosition.getOrdinate();
		
		int signumAbsciss = Long.signum((long)x);
		int signumOrdinate = Long.signum((long)y);
		
		//tester future position de fireball
		
		Fireball ball = new Fireball(position, this.scale, signumAbsciss, signumOrdinate, getAttack(), lifeFireball, map);
		map.addAdversary(ball);
	}
}