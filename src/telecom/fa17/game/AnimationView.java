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

	public Animation hit;
	
	public AnimationView() throws SlickException{
		SpriteSheet hitSheet = new SpriteSheet(new Image("/resources/map/animation/iceBlaster_hit.png"), 32, 32);
		hit = new Animation(hitSheet, 30);
		hit.setLooping(false);
	}
	
	/**
	 * Affiche l'animation hit une fois à l'emplacement du joueur puis se stop et réinitialise l'animation
	 * @param map
	 */
	public void hitAnimate(Map map){
		if(hit.isStopped()){
			map.stopHit();
			hit.restart();
		}else{
		hit.draw(map.getHitZone().getAbsciss(), map.getHitZone().getOrdinate());
		}
	}
}
