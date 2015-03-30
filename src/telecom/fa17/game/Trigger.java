package telecom.fa17.game;

public abstract class Trigger extends Element {

	public Trigger(float x, float y, boolean isCrossable) {
		super(x, y, isCrossable);
	}
	
	/**
	 * Action à effectuer lorsque l'on rencontre l'objet
	 */
	public abstract void action();
	
	/**
	 * @param map
	 * S'auto-dessine sur la carte
	 */
	public abstract void drawItself(Map map);

}
