package telecom.fa17.game;

public class Position {
	
	@Override
	public String toString() {
		return "Position [absciss=" + absciss + ", ordinate=" + ordinate + "]";
	}

	protected float absciss,
		  			ordinate;
	
	public Position(float abs, float ord){
		absciss = abs;
		ordinate = ord;
	}
	
	public float getAbsciss(){
		return absciss;
	}
	
	public void setAbsciss(float abs){
		absciss = abs;
	}
	
	public float getOrdinate(){
		return ordinate;
	}
	
	public void setOrdinate(float ord){
		ordinate = ord;
	}
	
	public static boolean equals(Position arg0, Position arg1){
		return ((arg0.getAbsciss() == arg1.getAbsciss()) && (arg0.getOrdinate() == arg1.getOrdinate()));
	}
}
