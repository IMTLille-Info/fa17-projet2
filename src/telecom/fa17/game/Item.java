package telecom.fa17.game;

public abstract class Item extends Object{

	int LifeBonus;// peut etre positif ou negatif
	int attackBonus;//peut etre positif ou negatif
	String descriptif;
	// int/float speedBonus -- proposition
	
	public Item(int x, int y, boolean crossable, int life, int attack, String descriptif){
		super (x, y, true);
		this.LifeBonus = life;
		this.attackBonus = attack;
	}

}
