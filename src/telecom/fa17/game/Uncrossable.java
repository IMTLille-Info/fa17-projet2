package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Uncrossable extends Obstacle{

	public Uncrossable(int absciss, int ordinate, Image image){
		super(absciss, ordinate, image);
		this.isCrossable = false;
	}
}
