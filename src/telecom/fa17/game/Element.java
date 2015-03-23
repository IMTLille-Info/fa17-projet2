package telecom.fa17.game;

public abstract class Element {
	
	protected Position position;
	protected boolean isCrossable;
		
	public Element(float x, float y, boolean isCrossable){
		this.position = new Position(x * 32, y * 32);
		this.isCrossable = isCrossable;
	}
	
	public boolean isCrossable(){
		return isCrossable;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position pos){
		this.position.setAbsciss(pos.getAbsciss());
		this.position.setOrdinate(pos.getOrdinate());
	}
	
	public void setPosition(float x, float y){
		this.position.setAbsciss(x);
		this.position.setOrdinate(y);
	}
}
