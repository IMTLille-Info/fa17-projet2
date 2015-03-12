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
	
	@Override
	public boolean equals(Object o){
		if (! (o instanceof Position)) { return false; }
		Position other = (Position)o;
		final float EPSILON = 1e-6f;
		return Math.abs(other.getAbsciss() - this.getAbsciss()) < EPSILON
				&& Math.abs(other.getOrdinate() - this.getOrdinate()) < EPSILON;
	}
}
