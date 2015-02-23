package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Obstacle {
	
	private float absciss, ordinate; //a revoir mais je reprends le meme systeme que joueur
	protected boolean isCrossable;
	protected Image img;
	
	public Obstacle(int absciss, int ordinate, Image image){
		this.absciss = absciss;
		this.ordinate = ordinate;
		this.img = image;
	}
	
	public boolean isCrossable(){
		return isCrossable;
	}
}
