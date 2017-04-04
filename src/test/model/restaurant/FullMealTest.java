package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.restaurant.FullMeal;
import model.restaurant.Menu;

public class FullMealTest {

	private static FullMeal fm1 = null;
	private static FullMeal fm2 = null;
	private static FullMeal fm3 = null;
	private static FullMeal fm4 = null;
	
	private static Menu menu = new Menu();
	
	@BeforeClass
	public static void testFullMealString() {
		
		fm1 = new FullMeal("FM1");
		assertNotNull(fm1);
	}

	@BeforeClass
	public static void testFullMealStringDishDishDish() {
		menu.initMenu();
		menu.display();
		
		fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		assertNotNull(fm2);
	}

	@Test
	public void testFullMealMeal() {
		fm3 = new FullMeal(fm2);
		assertNotNull(fm3);
		assertEquals(fm2, fm3);
	}

	@Test
	public void testFullMealStringMenuStringStringString() {
		fm4 = new FullMeal("FM4", menu,
								menu.getStarters().get(0).getDishName(), 
								menu.getMaindishes().get(0).getDishName(), 
								menu.getDesserts().get(0).getDishName());
		assertNotNull(fm4);
	}

	@Test
	public void testHashCode() {
		assertEquals(31, fm1.hashCode());
	}

	@Test
	public void testAddDish() {
		fm1.addDish(menu.getDishes().get(0));
		assertEquals(fm1.getDishes().get(fm1.getDishes().size()-1), menu.getDishes().get(0));
	}

	@Test
	public void testSameMealType() {
		assertTrue(fm2.sameMealType());
		
		fm3.getDishes().get(0).setDishType("vegetarian");
		fm3.getDishes().get(1).setDishType("gluten-free");
		assertFalse(fm3.sameMealType());
	}
	
	@Test
	public void testRefreshMealType() {
		fm2.refreshMealType();
		assertTrue(fm2.getMealType()=="standard");
		
		fm3.getDishes().get(0).setDishType("vegetarian");
		fm3.getDishes().get(1).setDishType("vegetarian");
		fm3.refreshMealType();
		assertTrue(fm3.getMealType()=="standard");
		
		fm3.getDishes().get(0).setDishType("vegetarian");
		fm3.getDishes().get(1).setDishType("vegetarian");
		fm3.getDishes().get(2).setDishType("vegetarian");
		fm3.refreshMealType();
		assertTrue(fm3.getMealType()=="vegetarian");
	}

	@Test
	public void testEqualsObject() {
		FullMeal fm5 = fm4;
		assertTrue(fm4.equals(fm5));
	}

	@AfterClass
	public static void testToString() {
		System.out.println(fm1);
		System.out.println(fm2);
		System.out.println(fm3);
		System.out.println(fm4);
	}
}
