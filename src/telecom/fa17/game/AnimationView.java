package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

//this class contain all animation we can use
public class AnimationView {

	public Animation hit;
	
	public AnimationView() throws SlickException{
		SpriteSheet hitSheet = new SpriteSheet(new Image("/resources/map/animation/iceBlaster_hit.png"), 50, 50);
		hit = new Animation(hitSheet, 1);
	}
}
