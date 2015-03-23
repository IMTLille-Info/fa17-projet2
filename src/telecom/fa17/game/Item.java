package telecom.fa17.game;

public abstract class Item extends Trigger{

	//	Can be positif or negatif
	int lifeBonus, attackBonus;
	
	public Item(float x, float y, boolean crossable, int life, int attack){
		super (x, y, true);
		this.lifeBonus = life;
		this.attackBonus = attack;
	}
}
