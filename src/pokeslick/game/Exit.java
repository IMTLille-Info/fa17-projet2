package pokeslick.game;

import org.newdawn.slick.SlickException;

public class Exit extends Obstacle {

	private float nextAbsciss,
				  nextOrdinate;
	private int nextMapNumber;
	
	public Exit(float abs, float ord, float nextAbs, float nextOrd, int nextMapNb)
	{
		super(abs, ord);
		this.nextAbsciss = nextAbs;
		this.nextOrdinate = nextOrd;
		this.nextMapNumber = nextMapNb;
	}
	
	public float getNextAbsciss()
	{
		return nextAbsciss;
	}
	
	public float getNextOrdinate()
	{
		return nextOrdinate;
	}
	
	public int getMapNumber()
	{
		return nextMapNumber;
	}

	@Override
	public void init() throws SlickException {
				
	}	
}
