package telecom.fa17.game;


public class HealBonus extends Item {

	public HealBonus(float x, float y, int life) {
		super(x, y, true, life, 0);
	}

	public void action() {
		MapGameState.objPlayer.addLife(this.lifeBonus);
		MapGameState.map.get(MapGameState.indexMap).removeTrigger(this);
	}

	@Override
	public void drawItself() {
		AnimationView.potion.draw(position.getAbsciss() , position.getOrdinate());
	}

}
