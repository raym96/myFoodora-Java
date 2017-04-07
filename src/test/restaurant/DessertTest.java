package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.restaurant.Dessert;


public class DessertTest {

	private static Dessert d = null;
	
	@BeforeClass
	public static void testDessert() {
		d = new Dessert("Nougat", "gluten-free", 2.2);
		assertNotNull(d);
	}

	@Test
	public void testMakeCopy() {
		Dessert d2 = d.makeCopy();
		assertEquals(d, d2);
	}

	@Test
	public void testHashCode() {
		assertEquals(31, d.hashCode());
	}

	@Test
	public void testEqualsObject() {
		Dessert d3 = new Dessert("Nougat", "gluten-free", 2.2);
		assertTrue(d.equals(d3));
	}

	@Test
	public void testToString() {
		System.out.println(d.toString());
	}

}
