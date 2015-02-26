package telecom.fa17.game;

public class Position {
	protected float absciss,
		  ordinate;
	
	public Position(float abs, float ord)
	{
		absciss = abs;
		ordinate = ord;
	}
	
	public float getAbsciss()
	{
		return absciss;
	}
	
	public void setAbsciss(float abs)
	{
		absciss = abs;
	}
	
	public float getOrdinate()
	{
		return ordinate;
	}
	
	public void setOrdinate(float ord)
	{
		ordinate = ord;
	}
}
