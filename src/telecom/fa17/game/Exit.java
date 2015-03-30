package telecom.fa17.game;

public class Exit extends Trigger {
		private Position nextPosition;
		private int nextMapNumber;

		public Exit(float x, float y, float nextAbs, float nextOrd, int nextMapNb){
				super(x, y, true);
				this.nextPosition = new Position(nextAbs*32, nextOrd*32);
				this.nextMapNumber = nextMapNb;
		}

		public Position getNextPosition(){
				return nextPosition;
		}

		public int getMapNumber(){
			return nextMapNumber;
		}

		@Override
		public void action() {
			MapGameState.objPlayer.setPosition(nextPosition);
			MapGameState.indexMap = nextMapNumber;
		}

		@Override
		public void drawItself(Map map){}
	
}
