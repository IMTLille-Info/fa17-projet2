package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Class that contains all Animations
 * @author Pierre-Édouard
 *
 */
public class AnimationView {

	protected final int DURATION_FRAME = 100;
	
	public Animation hit;
	public Animation swordAttackSouth;
	public Animation swordAttackNorth;
	public Animation swordAttackEast;
	public Animation swordAttackWest;
	public static Image potion;
	public static SpriteSheet trapSprite;
	
	public AnimationView(SpriteSheet playerSprite) throws SlickException{
		SpriteSheet hitSheet = new SpriteSheet(new Image("/resources/map/animation/iceBlaster_hit.png"), 32, 32);
		hit = new Animation(hitSheet, 30);
		hit.setLooping(false);
		potion =new Image("/resources/map/bonus/health_potion.png");
		trapSprite = new SpriteSheet(new Image("/resources/map/trap/trap.png"), 52 , 46);
	
		swordAttackNorth = new Animation(playerSprite, 0, 7, 7, 7, true, 25, true);
		swordAttackNorth.setLooping(false);
		swordAttackEast = new Animation(playerSprite, 0, 6, 7, 6, true, 25, true);
		swordAttackEast.setLooping(false);
		swordAttackSouth = new Animation(playerSprite, 0, 4, 7, 4, true, 25, true);
		swordAttackSouth.setLooping(false);
		swordAttackWest = new Animation(playerSprite, 0, 5, 7, 5, true, 25, true);
		swordAttackWest.setLooping(false);
	}
	
	/**
	 * Affiche l'animation 'hit' une fois
	 * 
	 * @param map
	 * @param player 
	 */
	public void hitAnimate(Map map){
		if(endAnimation(hit)){
			map.stopHit();
		}else{
			hit.draw(map.getHitZone().getAbsciss(), map.getHitZone().getOrdinate());
		}
	}
	
	
	public void attackAnimate(Map map, Player player){
		Animation animation = swordAttackSouth ;
		switch (player.getDirection()){
			case SOUTH :
				animation = swordAttackSouth;
				break;
			case NORTH :
				animation = swordAttackNorth;
				break;
			case EAST :
				animation = swordAttackEast;
				break;
			case WEST :
				animation = swordAttackWest;
				break;
		}
		
		if(endAnimation(animation)){
			player.stopAttackAnime();
		} else {
			animation.draw(player.getPosition().getAbsciss() - 15, player.getPosition().getOrdinate() -22);
		}
	}
	
	public boolean endAnimation(Animation animation){
		if(animation.isStopped()){
			animation.restart();
			return true;
		}
		return false;
	}
}
