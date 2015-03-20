package telecom.fa17.game;


public class HealBonus extends Trigger {

	public HealBonus(float x, float y) {
		super(x, y, true);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		MapGameState.objPlayer.addLife(20);
		MapGameState.map.get(MapGameState.indexMap).removeTrigger(this);
	}

}
