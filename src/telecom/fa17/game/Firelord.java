package telecom.fa17.game;

import org.newdawn.slick.SlickException;

public class Firelord extends PNJ{
	
	int lifeFireball = 1;
	
	public Firelord(float x, float y, int tileSize, int attack, int life, Map map) {
		super(x, y, tileSize, attack, life, map);
		this.map = map;
	}
	
	public void attack(Position playerPosition) throws SlickException, InterruptedException{

		Position fireLordPosition = this.getPosition();
		float x = fireLordPosition.getAbsciss() - playerPosition.getAbsciss();
		float y = fireLordPosition.getOrdinate() - playerPosition.getOrdinate();
		
		int signumAbsciss = Long.signum((long) x);
		int signumOrdinate = Long.signum((long) y);
		
		//tester future position de fireball
		
		// COMMENTAIRE DE FLORENT : ATTENTION, tu vas passer tes signum en coordoonées de carte
		//							et ce n'est pas les signum qu'il faut passer, c'est
		//							la position de la fireball par rapport
		//							au firelord si j'ai bien compris
		
		// CORRECTION SELON MOI (À VALiDER) : new Fireball(.. , .. , playerPosition.getAbsciss(), playerPosition.getOrdinate()
		//									  Au final, pas besoin de signum nan ?
		//									  Tu te fais attaquer, tu envoies sur le joueur ?	
		
		Fireball ball = new Fireball(position, this.scale, signumAbsciss, signumOrdinate, getAttack(), lifeFireball, map);
		map.addAdversary(ball);
	}
}