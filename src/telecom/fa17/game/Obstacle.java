package telecom.fa17.game;

import org.newdawn.slick.Image;

public abstract class Obstacle {
	
	private Position position;
	protected boolean isCrossable;
	protected Image img;
	
<<<<<<< HEAD
	public Obstacle(float absciss, float ordinate, boolean isCrossable){
		this.absciss = absciss;
		this.ordinate = ordinate;
		this.isCrossable = isCrossable;
	}
	
	public Obstacle(float absciss, float ordinate,boolean isCrossable, Image image){
		this(absciss, ordinate, isCrossable);
		this.img = image;
	}
=======
	public Obstacle(float x, float y)
	{
		position = new Position(x, y);
	}
	
	public abstract void init();
>>>>>>> RefactoringMethod
	
	public boolean isCrossable(){
		return isCrossable;
	}
	
<<<<<<< HEAD
	public float getAbsciss(){
			return absciss;
	}

	public float getOrdinate(){
			return ordinate;
=======
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position pos)
	{
		position = pos;
	}
	
	public void setPosition(float x, float y)
	{
		position.setAbsciss(x);
		position.setOrdinate(y);
>>>>>>> RefactoringMethod
	}
}
