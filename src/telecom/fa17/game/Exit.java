package telecom.fa17.game;

public class Exit extends Obstacle {
		private Position nextPosition;
		private int nextMapNumber;

		public Exit(float x, float y, float nextAbs, float nextOrd, int nextMapNb){
				super(x, y);
				nextPosition = new Position(nextAbs, nextOrd);
				this.nextMapNumber = nextMapNb;
		}

		public Position getNextPosition(){
				return nextPosition;
		}


		public int getMapNumber(){
			return nextMapNumber;
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
}