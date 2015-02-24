package pokeslick.game;

public class Exit {

	private float absciss,
				  ordinate,
				  nextAbsciss,
				  nextOrdinate;
	private int nextMapNumber;
	
	public Exit(float abs, float ord, float nextAbs, float nextOrd, int nextMapNb)
	{
		this.absciss = abs;
		this.ordinate = ord;
		this.nextAbsciss = nextAbs;
		this.nextOrdinate = nextOrd;
		this.nextMapNumber = nextMapNb;
	}
	
	public float getAbsciss()
	{
		return absciss;
	}
	
	public float getOrdinate()
	{
		return ordinate;
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
}
