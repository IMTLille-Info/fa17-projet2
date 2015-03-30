package telecom.fa17.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Trap extends Trigger {

	private int damage;
	private boolean touch;
	private Animation trapAnim;
	private Animation trap;
	
	public Trap(float x, float y, int damage) {
		super(x, y, true);
		// TODO Auto-generated constructor stub
		this.damage = damage;
		this.touch = false;
		
		trap = new Animation(AnimationView.trapSprite, 0, 0, 6 ,0 , true, 50, true);
		trapAnim = new Animation(AnimationView.trapSprite, 0, 1, 12 ,1, true, 30, true);
		trapAnim.setLooping(false);
	}

	@Override
	public void action() {
		if (!touch){MapGameState.objPlayer.hurt(this.damage);}
		touch = true;
	}

	@Override
	public void drawItself(Map map) {
		if(!touch){
			trap.draw(position.getAbsciss() , position.getOrdinate());
		}else{
			trapAnim.draw(position.getAbsciss() , position.getOrdinate());
			if(trapAnim.isStopped()){
				map.removeTrigger.add(this);
			}
		}
	}
}
