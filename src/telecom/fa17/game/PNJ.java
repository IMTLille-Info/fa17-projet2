package telecom.fa17.game;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PNJ extends Mobile {
	
	protected Map map;
	private Position firstPosition;
	private int previousMapIdx;

	public PNJ(float x, float y, int tileSize, int life, int attack, Map map, int previousMapIdx) {
		super(x, y, tileSize, life, attack);
		firstPosition = new Position (x*32, y*32);
		this.previousMapIdx = previousMapIdx;
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
		//transporte le joueur dans l'arêne
		WindowGame.indexMap = 4;
		WindowGame.objPlayer.setPosition(new Position(8*32, 10*32));
		WindowGame.objPlayer.startFight();
		//transporte le monstre avec
		this.position = new Position(9*32, 5*32);
		WindowGame.map.get(4).addAdversary(this);
		
	}
	
	public void returnToLastPosition(){
		WindowGame.indexMap = previousMapIdx;
		WindowGame.objPlayer.setPosition(firstPosition);
		WindowGame.objPlayer.stopFight();
		WindowGame.map.get(4).removeAdversary(this);
	}

	@Override
	public void attack(Map map) {
		// TODO Auto-generated method stub
		
	}
}
