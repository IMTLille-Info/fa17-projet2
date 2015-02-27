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
	
	public static boolean equals(Position arg0, Position arg1)
	{
		boolean equals = false;
		if((arg0.getAbsciss() == arg1.getAbsciss()) && (arg0.getOrdinate() == arg1.getOrdinate()))
		{
			equals = true;
		}
		return equals;
	}
}
