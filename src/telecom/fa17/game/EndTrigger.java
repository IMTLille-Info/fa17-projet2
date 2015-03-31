package telecom.fa17.game;

import telecom.fa17.end.EndState;

public class EndTrigger extends Trigger {

	public EndTrigger(float x, float y) {
		super(x, y, true);
	}

	@Override
	public void action() {
		MapGameState.game.enterState(EndState.ID);
	}

	@Override
	public void drawItself(Map map) {}

}
