package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Obstacle {
	
	private float absciss,
				  ordinate; //a revoir mais je reprends le meme systeme que joueur
	protected boolean isCrossable;
	protected Image img;
	
	public Obstacle(float absciss, float ordinate)
	{
		this.absciss = absciss;
		this.ordinate = ordinate;
	}
	
	public Obstacle(float absciss, float ordinate, Image image){
		this(absciss, ordinate);
		this.img = image;
	}
	
	public boolean isCrossable(){
		return isCrossable;
	}
	
	public float getAbsciss()
	{
			return absciss;
	}

	public float getOrdinate()
	{
			return ordinate;
	}
}
