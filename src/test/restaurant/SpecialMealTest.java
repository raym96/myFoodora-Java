package test.restaurant;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Menu;
import restaurant.SpecialMeal;

public class SpecialMealTest {

	private static SpecialMeal sm1 = null;
	private static SpecialMeal sm2 = null;
	private static SpecialMeal sm3 = null;
	
	private static Menu menu = new Menu();
	
	@BeforeClass
	public static void testSpecialMealString() {
		sm1 = new SpecialMeal("SM1");
		assertNotNull(sm1);
		assertNotNull(sm1.getDishes());
	}

	@BeforeClass
	public static void testSpecialMealStringDishDish() {
		menu.initMenu();
		menu.display();
		
		sm2 = new SpecialMeal("SM2", menu.getStarters().get(0), menu.getMaindishes().get(0));
		assertNotNull(sm2);
		assertNotNull(sm2.getDishes());
	}

	@BeforeClass
	public static void testSpecialMealMeal() {
		sm3 = new SpecialMeal(sm2);
		assertNotNull(sm3);
		assertEquals(sm2, sm3);
		assertNotNull(sm3.getDishes());
	}

	
	
	@Test
	public void testHashCode() {
		assertEquals(31, sm1.hashCode());
	}

	@Test
	public void testAddDish() {
		sm1.addDish(menu.getDishes().get(0));
		assertEquals(sm1.getDishes().get(sm1.getDishes().size()-1), menu.getDishes().get(0));
	}

	@Test
	public void testSameMealType() {
		assertTrue(sm2.sameMealType());
		
		sm3.getDishes().get(0).setDishType("vegetarian");
		sm3.getDishes().get(1).setDishType("gluten-free");
		assertFalse(sm3.sameMealType());
	}
	
	@Test
	public void testRefreshMealType() {
		sm2.refreshMealType();
		assertTrue(sm2.getMealType()=="standard");
		
		sm3.getDishes().get(0).setDishType("vegetarian");
		sm3.refreshMealType();
		assertTrue(sm3.getMealType()=="standard");
		
		sm3.getDishes().get(0).setDishType("vegetarian");
		sm3.getDishes().get(1).setDishType("vegetarian");
		sm3.refreshMealType();
		assertTrue(sm3.getMealType()=="vegetarian");
	}

	@Test
	public void testEqualsObject() {
		SpecialMeal sm4 = sm3;
		assertTrue(sm4.equals(sm3));
	}

	@AfterClass
	public static void testToString() {
		System.out.println(sm1);
		System.out.println(sm2);
		System.out.println(sm3);
	}
}
