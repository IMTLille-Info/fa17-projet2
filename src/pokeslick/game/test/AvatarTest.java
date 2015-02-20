package pokeslick.game.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeslick.game.Avatar;

public class AvatarTest {

	Avatar objTest = new Avatar(64, 64, 32);
	
	@Before
	public void setUp() throws Exception {
		objTest.setMoving();
	}

	@Test
	public void testIsMoving() {
		assertEquals(true, objTest.isMoving());
	}

	@Test
	public void testGetAbsciss() {
		assertEquals(0,0, objTest.getAbsciss());
	}

	@Test
	public void testGetOrdinate() {
		assertEquals(0,0, objTest.getOrdinate());
	}

	@Test
	public void testGetNextAbsciss() {
		for(int i = 0; i < 9; i++)
		{
			objTest.getNextAbsciss(1, 32, 1);
		}
		assertEquals(63,0, objTest.getNextAbsciss(1, 32, 1));
	}

	@Test
	public void testGetNextOrdinate() {
		for(int j = 0; j < 9; j++)
		{
			objTest.getNextOrdinate(0, 32, 1);
		}
		assertEquals(63,0, objTest.getNextOrdinate(0, 32, 1));
	}

	@Test
	public void testGetStandingImage() {
		assertEquals(null, objTest.getStandingImage());
	}

	@Test
	public void testGetAnimation() {
		assertEquals(null, objTest.getAnimation());
	}

}