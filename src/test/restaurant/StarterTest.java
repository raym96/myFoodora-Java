package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Starter;

public class StarterTest {

	private static Starter s = null;
	
	@BeforeClass
	public static void testStarter() {
		s = new Starter("Japanese soup", "gluten-free", 3.5);
		assertNotNull(s);
	}

	@Test
	public void testMakeCopy() {
		Starter s2 = s.makeCopy();
		assertEquals(s, s2);
	}

	@Test
	public void testHashCode() {
		assertEquals(31, s.hashCode());
	}

	@Test
	public void testEqualsObject() {
		Starter s3 = new Starter("Japanese soup", "gluten-free", 3.5);
		assertTrue(s.equals(s3));
	}

	@Test
	public void testToString() {
		System.out.println(s.toString());
	}

}
