package telecom.fa17.game;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PNJ extends Mobile {
	
	protected Map map;

	public PNJ(float x, float y, int tileSize, int life, int attack, Map map) {
		super(x, y, tileSize, life, attack);		this.map = map;
	}

	public void init() throws SlickException {
		switch(new Random().nextInt(3)) {
			case 0 :
					standings[Direction.SOUTH.index] = new Image("resources/map/monster/monsterOne.png");
					break;
			case 1 :
					standings[Direction.SOUTH.index] = new Image("resources/map/monster/monsterTwo.png");
					break;
			case 2 :
					standings[Direction.SOUTH.index] = new Image("resources/map/monster/monsterThree.png");
					break;
		}
	}
	
	public void trigger(){
		WindowGame.indexMap = 4;
		WindowGame.objPlayer.setPosition(new Position(8*32, 10*32));
		WindowGame.objPlayer.startFight();
	}

	@Override
	public void attack(Map map) {
		// TODO Auto-generated method stub
		
	}
}
