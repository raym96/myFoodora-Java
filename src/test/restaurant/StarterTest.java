/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Starter;

// TODO: Auto-generated Javadoc
/**
 * The Class StarterTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class StarterTest {

	/** The s. */
	private static Starter s = null;
	
	/**
	 * Test starter.
	 */
	@BeforeClass
	public static void testStarter() {
		s = new Starter("Japanese soup", "gluten-free", 3.5);
		assertNotNull(s);
	}

	/**
	 * Test make copy.
	 */
	@Test
	public void testMakeCopy() {
		Starter s2 = s.makeCopy();
		assertEquals(s, s2);
	}

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		assertEquals(31, s.hashCode());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		Starter s3 = new Starter("Japanese soup", "gluten-free", 3.5);
		assertTrue(s.equals(s3));
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		System.out.println(s.toString());
	}

}
