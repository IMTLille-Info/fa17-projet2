package telecom.fa17.game;

import org.newdawn.slick.SlickException;

public class Firelord extends PNJ{
	
	int lifeFireball = 1;
	
	public Firelord(float x, float y, int tileSize, int life, int attack, Map map) {
		super(x, y, tileSize, life, attack, map, 1);
		this.map = map;
	}
	
	public void attack(Position playerPosition) throws SlickException, InterruptedException{

		Position fireLordPosition = this.getPosition();
		float x = fireLordPosition.getAbsciss() - playerPosition.getAbsciss();
		float y = fireLordPosition.getOrdinate() - playerPosition.getOrdinate();
		
		int signumAbsciss = Long.signum((long) x);
		int signumOrdinate = Long.signum((long) y);
		
		int key = 0;

		Position fireballPosition = new Position(0,0);
		fireballPosition.setAbsciss(fireLordPosition.getAbsciss()+signumAbsciss);
		fireballPosition.setOrdinate(fireLordPosition.getOrdinate()+signumOrdinate);
		
		boolean isok = false;
		if(!this.map.findCollision(key, fireballPosition)){
			isok = true;
		}
		
		if(isok){
			Fireball ball = new Fireball(fireballPosition, this.scale, signumAbsciss, signumOrdinate, getAttack(), 1, map);
			map.addAdversary(ball);
		}
	}
}