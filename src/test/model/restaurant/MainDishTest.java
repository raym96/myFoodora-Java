package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.restaurant.Dessert;
import model.restaurant.MainDish;

public class MainDishTest {

	private static MainDish m = null;
	
	@BeforeClass
	public static void testMainDish() {
		m = new MainDish("Pork ribs", "standard", 9.1);
		assertNotNull(m);
	}

	@Test
	public void testMakeCopy() {
		MainDish m2 = m.makeCopy();
		assertEquals(m, m2);
	}

	@Test
	public void testHashCode() {
		assertEquals(31, m.hashCode());
	}

	@Test
	public void testEqualsObject() {
		MainDish m3 = new MainDish("Pork ribs", "standard", 9.1);
		assertTrue(m.equals(m3));
	}

	@Test
	public void testToString() {
		System.out.println(m.toString());
	}
}
