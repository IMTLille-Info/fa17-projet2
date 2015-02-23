package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Mobile {
	
	public Player(float x, float y, int tileSize){
		super(x, y, tileSize);
		this.life = 50;
		this.isMobile = true;
		this.attack = 10;
		// MARQUES DE COMMENTAIRES A METTRE POUR MODE TEST SUR LE TRY
		
		try {
			// Images du joueur correspondante à ces états statiques
			standings[0] = new Image("resources/map/player/personStandUp.png");
			standings[1] = new Image("resources/map/player/personStandLeft.png");
			standings[2] = new Image("resources/map/player/personStandDown.png");
			standings[3] = new Image("resources/map/player/personStandRight.png");
 
			// Marcher vers le haut
			this.animations[0] = new Animation();
			this.animations[0].addFrame(new Image("resources/map/player/personWalkingUp.png"), DURATION_FRAME);
			this.animations[0].addFrame(new Image("resources/map/player/personStandUp.png"), DURATION_FRAME);
        
			// Marcher vers la gauche
			this.animations[1] = new Animation();
			this.animations[1].addFrame(new Image("resources/map/player/personWalkingLeft.png"), DURATION_FRAME);
			this.animations[1].addFrame(new Image("resources/map/player/personStandLeft.png"), DURATION_FRAME);

			// Marcher vers le bas
			this.animations[2] = new Animation();
			this.animations[2].addFrame(new Image("resources/map/player/personWalkingDown.png"), DURATION_FRAME);
			this.animations[2].addFrame(new Image("resources/map/player/personStandDown.png"), DURATION_FRAME);

			// Marcher vers la droite
			this.animations[3] = new Animation();
			this.animations[3].addFrame(new Image("resources/map/player/personWalkingRight.png"), DURATION_FRAME);
			this.animations[3].addFrame(new Image("resources/map/player/personStandRight.png"), DURATION_FRAME);
		} catch (SlickException ex) {
			
		}
	}
}
