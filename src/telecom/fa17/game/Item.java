package telecom.fa17.game;

public abstract class Item {

	int LifeBonus;// peut etre positif ou negatif
	int attackBonus;//peut etre positif ou negatif
	String descriptif;
	// int/float speedBonus -- proposition
	
	public Item(int life, int attack, String descriptif){
		this.LifeBonus = life;
		this.attackBonus = attack;
	}

}
