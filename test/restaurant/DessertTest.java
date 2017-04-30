/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Dessert;



/**
 * The Class DessertTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class DessertTest {

	/** The d. */
	private static Dessert d = null;
	
	/**
	 * Test dessert.
	 */
	@BeforeClass
	public static void testDessert() {
		d = new Dessert("Nougat", "gluten-free", 2.2);
		assertNotNull(d);
	}

	/**
	 * Test make copy.
	 */
	@Test
	public void testMakeCopy() {
		Dessert d2 = d.makeCopy();
		assertEquals(d, d2);
	}

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		assertEquals(31, d.hashCode());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		Dessert d3 = new Dessert("Nougat", "gluten-free", 2.2);
		assertTrue(d.equals(d3));
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		System.out.println(d.toString());
	}

}
