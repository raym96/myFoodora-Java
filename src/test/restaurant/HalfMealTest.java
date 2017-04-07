package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.restaurant.FullMeal;
import model.restaurant.HalfMeal;
import model.restaurant.Menu;

public class HalfMealTest {

	private static HalfMeal hm1 = null;
	private static HalfMeal hm2 = null;
	private static HalfMeal hm3 = null;
	private static HalfMeal hm4 = null;
	
	private static Menu menu = new Menu();
	
	@BeforeClass
	public static void testHalfMealString() {
		
		hm1 = new HalfMeal("HM1");
		assertNotNull(hm1);
		assertNotNull(hm1.getDishes());
	}

	@BeforeClass
	public static void testHalfMealStringDishDish() {
		menu.initMenu();
		menu.display();
		
		hm2 = new HalfMeal("HM2", menu.getStarters().get(0), menu.getMaindishes().get(0));
		assertNotNull(hm2);
		assertNotNull(hm2.getDishes());
	}

	@Test
	public void testHalfMealMeal() {
		hm3 = new HalfMeal(hm2);
		assertNotNull(hm3);
		assertEquals(hm2, hm3);
		assertNotNull(hm3.getDishes());
	}

	@Test
	public void testHalfMealStringMenuStringString() {
		hm4 = new HalfMeal("HM4", menu,
								menu.getStarters().get(0).getDishName(), 
								menu.getMaindishes().get(0).getDishName());
		assertNotNull(hm4);
		assertNotNull(hm4.getDishes());
	}

	@Test
	public void testHashCode() {
		assertEquals(31, hm1.hashCode());
	}

	@Test
	public void testAddDish() {
		hm1.addDish(menu.getDishes().get(0));
		assertEquals(hm1.getDishes().get(hm1.getDishes().size()-1), menu.getDishes().get(0));
	}

	@Test
	public void testSameMealType() {
		assertTrue(hm2.sameMealType());
		
		hm3.getDishes().get(0).setDishType("vegetarian");
		hm3.getDishes().get(1).setDishType("gluten-free");
		assertFalse(hm3.sameMealType());
	}
	
	@Test
	public void testRefreshMealType() {
		hm2.refreshMealType();
		assertTrue(hm2.getMealType()=="standard");
		
		hm3.getDishes().get(0).setDishType("vegetarian");
		hm3.refreshMealType();
		assertTrue(hm3.getMealType()=="standard");
		
		hm3.getDishes().get(0).setDishType("vegetarian");
		hm3.getDishes().get(1).setDishType("vegetarian");
		hm3.refreshMealType();
		assertTrue(hm3.getMealType()=="vegetarian");
	}

	@Test
	public void testEqualsObject() {
		HalfMeal hm5 = hm4;
		assertTrue(hm4.equals(hm5));
	}

	@AfterClass
	public static void testToString() {
		System.out.println(hm1);
		System.out.println(hm2);
		System.out.println(hm3);
		System.out.println(hm4);
	}
}
