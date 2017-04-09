/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Dessert;
import restaurant.MainDish;

// TODO: Auto-generated Javadoc
/**
 * The Class MainDishTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MainDishTest {

	/** The m. */
	private static MainDish m = null;
	
	/**
	 * Test main dish.
	 */
	@BeforeClass
	public static void testMainDish() {
		m = new MainDish("Pork ribs", "standard", 9.1);
		assertNotNull(m);
	}

	/**
	 * Test make copy.
	 */
	@Test
	public void testMakeCopy() {
		MainDish m2 = m.makeCopy();
		assertEquals(m, m2);
	}

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		assertEquals(31, m.hashCode());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		MainDish m3 = new MainDish("Pork ribs", "standard", 9.1);
		assertTrue(m.equals(m3));
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		System.out.println(m.toString());
	}
}
