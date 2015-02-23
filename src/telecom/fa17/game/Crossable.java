package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Crossable extends Obstacle{

	public Crossable(int absciss, int ordinate, Image image){
		super(absciss, ordinate, image);
		this.isCrossable = true;
	}
}
