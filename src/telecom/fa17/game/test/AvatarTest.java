package telecom.fa17.game.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import telecom.fa17.game.Direction;
import telecom.fa17.game.Player;

public class AvatarTest {

	Player player = new Player(64, 64, 32);
	Player PNG = new Player(64, 64, 32);
	
	@Before
	public void setUp() throws Exception {
		player.setMoving();
	}

	/*
	@Test
	public void testGetAbsciss() {
		assertEquals(0,0, player.getAbsciss());
	}

	@Test
	public void testGetOrdinate() {
		assertEquals(0,0, player.getOrdinate());
	}

	@Test
	public void testGetNextAbsciss() {
		player.setDirection(Direction.EAST);
		for(int i = 0; i < 320; i++)
		{
			player.getNextAbsciss();
		}
		assertEquals(32,0, player.getNextAbsciss());
	}

	@Test
	public void testGetNextOrdinate() {
		player.setDirection(Direction.NORTH);
		for(int j = 0; j < 320; j++)
		{
			player.getNextOrdinate();
		}
		assertEquals(32,0, player.getNextOrdinate());
	}*/

	@Test
	public void testGetStandingImage() {
		assertEquals(null, player.getStandingImage());
	}

	@Test
	public void testGetAnimation() {
		assertEquals(null, player.getAnimation());
	}
	
	//test l'action attaquer 
	@Test
	public void testAttaquer() {
		int pvBefore = PNG.getLife();
		player.attack();
		assertEquals(pvBefore - player.getAttack(), PNG.getLife());
	}
}
