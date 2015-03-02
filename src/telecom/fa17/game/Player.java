package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Mobile {
	
	public Player(float x, float y, int tileSize){
		super(x, y, tileSize);
		attack = 10;
		life = 50;
		// MARQUES DE COMMENTAIRES A METTRE POUR MODE TEST SUR LE TRY
	}
	
	public void init() throws SlickException{
		// Images du joueur correspondante à ces états statiques
		standings[Direction.NORTH.index] = new Image("resources/map/player/personStandUp.png");
		standings[Direction.EAST.index] = new Image("resources/map/player/personStandLeft.png");
		standings[Direction.SOUTH.index] = new Image("resources/map/player/personStandDown.png");
		standings[Direction.WEST.index] = new Image("resources/map/player/personStandRight.png");

		// Marcher vers le haut
		this.animations[Direction.NORTH.index] = new Animation();
		this.animations[Direction.NORTH.index].addFrame(new Image("resources/map/player/personWalkingUp.png"), DURATION_FRAME);
		this.animations[Direction.NORTH.index].addFrame(new Image("resources/map/player/personStandUp.png"), DURATION_FRAME);
    
		// Marcher vers la gauche
		this.animations[Direction.EAST.index] = new Animation();
		this.animations[Direction.EAST.index].addFrame(new Image("resources/map/player/personWalkingLeft.png"), DURATION_FRAME);
		this.animations[Direction.EAST.index].addFrame(new Image("resources/map/player/personStandLeft.png"), DURATION_FRAME);

		// Marcher vers le bas
		this.animations[Direction.SOUTH.index] = new Animation();
		this.animations[Direction.SOUTH.index].addFrame(new Image("resources/map/player/personWalkingDown.png"), DURATION_FRAME);
		this.animations[Direction.SOUTH.index].addFrame(new Image("resources/map/player/personStandDown.png"), DURATION_FRAME);

		// Marcher vers la droite
		this.animations[Direction.WEST.index] = new Animation();
		this.animations[Direction.WEST.index].addFrame(new Image("resources/map/player/personWalkingRight.png"), DURATION_FRAME);
		this.animations[Direction.WEST.index].addFrame(new Image("resources/map/player/personStandRight.png"), DURATION_FRAME);
	}
}

