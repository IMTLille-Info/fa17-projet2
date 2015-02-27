package telecom.fa17.game;

public class Exit extends Object {
		private Position nextPosition;
		private int nextMapNumber;

		public Exit(float x, float y, float nextAbs, float nextOrd, int nextMapNb){
				super(x, y, true);
				nextPosition = new Position(nextAbs, nextOrd);
				this.nextMapNumber = nextMapNb;
		}

		public Position getNextPosition(){
				return nextPosition;
		}

		public int getMapNumber(){
			return nextMapNumber;
		}
}
