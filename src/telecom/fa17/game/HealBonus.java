package telecom.fa17.game;


public class HealBonus extends Trigger {

	public HealBonus(float x, float y) {
		super(x, y, true);
	}

	public void action() {
		MapGameState.objPlayer.addLife(20);
		MapGameState.map.get(MapGameState.indexMap).removeTrigger(this);
	}

}
