package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * this class contain all animation we can use
 * @author pierre-edouard
 *
 */
public class AnimationView {

	protected final int DURATION_FRAME = 100;
	
	public Animation hit;
	public Animation swordAttackSouth;
	public Animation swordAttackNorth;
	public Animation swordAttackEast;
	public Animation swordAttackWest;
	
	public AnimationView() throws SlickException{
		SpriteSheet hitSheet = new SpriteSheet(new Image("/resources/map/animation/iceBlaster_hit.png"), 32, 32);
		hit = new Animation(hitSheet, 30);
		hit.setLooping(false);
		
		SpriteSheet zelda = new SpriteSheet("resources/map/player/zelda.png", 38, 38);
		swordAttackSouth = new Animation(zelda, 0, 6, 6, 6, true, 30, true);
		swordAttackSouth.setLooping(false);
		swordAttackNorth = new Animation(zelda, 0, 9, 6, 9, true, 1000, true);
		swordAttackNorth.setLooping(false);
		swordAttackEast = new Animation(zelda, 0, 8, 6, 8, true, 1000, true);
		swordAttackEast.setLooping(false);
		swordAttackWest = new Animation(zelda, 0, 6, 6, 6, true, 1000, true);
		swordAttackWest.setLooping(false);
	}
	
	/**
	 * Affiche l'animation hit une fois à l'emplacement du joueur puis se stop et réinitialise l'animation
	 * @param map
	 * @param player 
	 */
	public void hitAnimate(Map map, Player player){
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
		
		if(!endAnimation(hit, map) && !endAnimation(animation, map)){
			hit.draw(map.getHitZone().getAbsciss(), map.getHitZone().getOrdinate());
			animation.draw(player.getPosition().getAbsciss(), player.getPosition().getOrdinate());
		}
	}
	
	public boolean endAnimation(Animation animation, Map map){
		if(animation.isStopped()){
			map.stopHit();
			animation.restart();
			return true;
		}
		return false;
	}
}
