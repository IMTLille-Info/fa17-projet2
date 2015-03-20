package telecom.fa17.game;

public abstract class Trigger extends Element {

	public Trigger(float x, float y, boolean isCrossable) {
		super(x, y, isCrossable);
	}
	
	/**
	 * effectue l'action du déclencheur
	 */
	public abstract void action();

}
