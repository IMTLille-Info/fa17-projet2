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
	
	public void redirectPlayerInArena(){
		//transporte le joueur dans l'arêne
		MapGameState.indexMap = 4;
		MapGameState.objPlayer.setPosition(new Position(8*32, 10*32));
		MapGameState.objPlayer.startFight();
		//transporte le monstre avec
		this.position = new Position(9*32, 5*32);
		MapGameState.map.get(4).addAdversary(this);
		
	}
	
	public void returnToLastPosition(){
		MapGameState.indexMap = previousMapIdx;
		MapGameState.objPlayer.setPosition(firstPosition);
		MapGameState.objPlayer.stopFight();
		MapGameState.map.get(4).removeAdversary(this);
		MapGameState.map.get(4).stopHit();
	}

	@Override
	public void attack(Map map) {
		// TODO Auto-generated method stub
		
	}
}
