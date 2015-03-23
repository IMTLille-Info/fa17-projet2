package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Mobile {
	
	public Player(float x, float y, int tileSize){
		super(x, y, tileSize, 50, 20);
	}
	
	public void init(SpriteSheet playerSprite) throws SlickException{
		// Images du joueur correspondantes à ces états statiques
		standings[Direction.NORTH.index] = playerSprite.getSubImage(0, 1);
		standings[Direction.EAST.index] = playerSprite.getSubImage(0, 3);
		standings[Direction.SOUTH.index] = playerSprite.getSubImage(0, 2);
		standings[Direction.WEST.index] = playerSprite.getSubImage(0, 0);

		// Marcher vers le haut
		this.animations[Direction.NORTH.index] = new Animation(playerSprite, 0 , 1, 12, 1, true, DURATION_FRAME, true);
    
		// Marcher vers la gauche
		this.animations[Direction.EAST.index] = new Animation(playerSprite, 0 , 3, 12, 3, true, DURATION_FRAME, true);

		// Marcher vers le bas
		this.animations[Direction.SOUTH.index] = new Animation(playerSprite, 0 , 2, 12, 2, true, DURATION_FRAME, true);

		// Marcher vers la droite
		this.animations[Direction.WEST.index] = new Animation(playerSprite, 0 , 0, 12, 0, true, DURATION_FRAME, true);
	}
	
	public void attack(Map map) {
		startAttackAnime();
		Position target = getInfrontPosition();
		for (PNJ pnj : map.getAliveAdversaries()) {
			if (pnj.getPosition().equals(target)) {
				if(isFighting()){
					pnj.hurt(getAttack());
					map.setHitZone(target);
					if(!pnj.isAlive()){
						pnj.returnToLastPosition();		
					}
				}else{
					pnj.redirectPlayerInArena();
				}
				
			}
		}
	}

}

