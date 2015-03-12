package telecom.fa17.game.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import telecom.fa17.game.Direction;
import telecom.fa17.game.Player;
import telecom.fa17.game.Position;

public class MobileTest {
	
	Player player = new Player(0, 0, 10);
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testSetAttack() {
		player.setAttack(50);
		assertEquals(50 , player.getAttack());
	}

	@Test
	public void testSetDirection() {
		player.setDirection(Direction.EAST);
		assertEquals(Direction.EAST , player.getDirection());
	}

	@Test
	public void testSetMoving() {
		player.setMoving();
		assertTrue(player.isMoving());
	}

	@Test
	public void testIsMoving() {
		assertFalse(player.isMoving());
	}

	
	@Test
	public void testSetPositionPosition() {
		player.setPosition(new Position(0, 1));
		assertEquals(new Position(0, 1), player.getPosition());
	}

	@Test
	public void testSetPositionFloatFloat() {
		player.setPosition(0,1);
		assertEquals(player.getPosition().toString(), new Position(0, 1).toString());
	}
	
	@Test
	public void testGetNext() {
		player.setPosition(0,0);
		player.setMoving();
		player.getNextPosition(5);
		player.getNext(true,true);
		assertEquals(new Position(0, 1).toString(), player.getPosition().toString());
	}


	@Test
	public void testGetNextPosition() {
		player.getNextPosition(0);
		assertEquals(new Position(0, 0).toString() , player.getPosition().toString());
	}

	@Test
	public void testIsAlive() {
		player.setLife(0);
		assertFalse(player.isAlive());
	}

	@Test
	public void testGetLife() {
		player.setLife(20);
		assertEquals(20, player.getLife());
	}

	@Test
	public void testSetLife() {
		player.setLife(20);
		assertEquals(20, player.getLife());
	}

	@Test
	public void testIsMobile() {
		assertFalse(player.isMobile());
	}

	@Test
	public void testAttack() {
		int life = player.getLife();
		player.hurt(10);
		assertEquals(life - 10, player.getLife());
		
	}

}
