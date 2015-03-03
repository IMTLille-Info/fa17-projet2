package telecom.fa17.game;

public abstract class Element {
	
	protected Position position;
	protected boolean isCrossable;
		
	public Element(float x, float y, boolean isCrossable){
		position = new Position(x, y);
		this.isCrossable = isCrossable;
	}
	
	public boolean isCrossable(){
		return isCrossable;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position pos){
		position = pos;
	}
	
	public void setPosition(float x, float y){
		position.setAbsciss(x);
		position.setOrdinate(y);
	}
}
