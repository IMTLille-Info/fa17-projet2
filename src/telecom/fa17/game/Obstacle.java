package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Obstacle {
	
	private float absciss,
				  ordinate; //a revoir mais je reprends le meme systeme que joueur
	protected boolean isCrossable;
	protected Image img;
	
	public Obstacle(float absciss, float ordinate, boolean isCrossable){
		this.absciss = absciss;
		this.ordinate = ordinate;
		this.isCrossable = isCrossable;
	}
	
	public Obstacle(float absciss, float ordinate,boolean isCrossable, Image image){
		this(absciss, ordinate, isCrossable);
		this.img = image;
	}
	
	public boolean isCrossable(){
		return isCrossable;
	}
	
	public float getAbsciss(){
			return absciss;
	}

	public float getOrdinate(){
			return ordinate;
	}
}
