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
	public Animation swordAttack;
	public Animation zeldaRightMove;
	
	public AnimationView() throws SlickException{
		SpriteSheet hitSheet = new SpriteSheet(new Image("/resources/map/animation/iceBlaster_hit.png"), 32, 32);
		hit = new Animation(hitSheet, 30);
		hit.setLooping(false);
		
		/*swordAttack = new Animation();
		swordAttack.addFrame(new Image("resources/map/player/personAttack1.png"), DURATION_FRAME);
		swordAttack.addFrame(new Image("resources/map/player/personAttack2.png"), DURATION_FRAME);
		swordAttack.addFrame(new Image("resources/map/player/personAttack3.png"), DURATION_FRAME);
		*/
		SpriteSheet zelda = new SpriteSheet("resources/map/player/zelda.png", 38, 38);
		swordAttack = new Animation(zelda, 0, 6, 6, 6, true, 1000, true);
		swordAttack.setLooping(false);
	}
	
	/**
	 * Affiche l'animation hit une fois à l'emplacement du joueur puis se stop et réinitialise l'animation
	 * @param map
	 */
	public void hitAnimate(Map map){
		if(hit.isStopped() && swordAttack.isStopped()){
			map.stopHit();
			hit.restart();
			swordAttack.restart();
		} else {
			hit.draw(map.getHitZone().getAbsciss(), map.getHitZone().getOrdinate());
		}
	}
}
