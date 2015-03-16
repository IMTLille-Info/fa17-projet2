package telecom.fa17.game;

public abstract class Item extends Element{

	//	Can be positif or negatif
	int lifeBonus, attackBonus;
	
	String descriptif;
	
	public Item(int x, int y, boolean crossable, int life, int attack, String desc){
		super (x, y, true);
		this.lifeBonus = life;
		this.attackBonus = attack;
		this.descriptif = desc;
	}
}
