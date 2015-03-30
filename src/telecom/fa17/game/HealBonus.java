package telecom.fa17.game;


public class HealBonus extends Item {

	public HealBonus(float x, float y, int life) {
		super(x, y, true, life, 0);
	}

	public void action() {
		MapGameState.objPlayer.addLife(this.lifeBonus);
		MapGameState.map.get(MapGameState.indexMap).removeTrigger(this);
		MapGameState.textInit("You got a bonus life : +"+this.lifeBonus+" !");
	}

	@Override
	public void drawItself(Map map) {
		AnimationView.potion.draw(position.getAbsciss() , position.getOrdinate());
	}

}
