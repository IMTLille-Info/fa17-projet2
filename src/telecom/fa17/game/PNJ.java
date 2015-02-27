package telecom.fa17.game;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PNJ extends Mobile {

	public PNJ(float x, float y, int tileSize) {
		super(x, y, tileSize);
	}

	@Override
	public void init() throws SlickException {
		int result = 1 + new Random().nextInt(3);
		switch(result)
		{
			case 0 :
					standings[Direction.SOUTH.index] = new Image("resources/map/monster/monsterOne.png");;
					break;
			case 1 :
					standings[Direction.SOUTH.index] = new Image("resources/map/monster/monsterTwo.png");;
					break;
			case 2 :
					standings[Direction.SOUTH.index] = new Image("resources/map/monster/monsterThree.png");;
					break;
		}
	}

}
