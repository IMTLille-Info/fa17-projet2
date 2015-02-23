package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Obstacle {
	
	private float absciss, ordinate; //a revoir mais je reprends le meme systeme que joueur
	protected boolean isCrossable;
	protected Image[] texture = new Image[1];
	
	public Obstacle(int absciss, int ordinate, Image image){
		this.absciss = absciss;
		this.ordinate = ordinate;
		this.texture[0] =  image;
	}
	
	public boolean isCrossable(){
		return isCrossable;
	}
}
