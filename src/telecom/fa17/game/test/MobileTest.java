package telecom.fa17.game.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import telecom.fa17.game.Direction;
import telecom.fa17.game.Player;
import telecom.fa17.game.Position;

public class MobileTest {

	Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player(0, 0, 10);
	}

	@Test
	public void testSetAttack() {
		player.setAttack(50);
		assertEquals(50, player.getAttack());
	}

	@Test
	public void testSetDirection() {
		player.setDirection(Direction.EAST);
		assertEquals(Direction.EAST, player.getDirection());
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
		player.setPosition(0, 1);
		assertEquals(new Position(0, 1), player.getPosition());
	}

	@Test
	public void testGetNext() {
		player.setPosition(0, 0);
		player.setDirection(Direction.WEST);
		player.setMoving();
		player.getNextPosition(5); // TODO renommer en update(delta) ?
		assertEquals(new Position(1, 0), player.getPosition());
	}

	@Test
	public void testGetNextPosition() {
		player.getNextPosition(0);
		assertEquals(new Position(0, 0), player.getPosition());
	}

	@Test
	public void testIsAlive() {
		player.hurt(50);
		assertFalse(player.isAlive());
	}

	@Test
	public void testGetLife() {
		assertEquals(50, player.getLife());
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
