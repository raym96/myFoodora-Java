package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.restaurant.FullMeal;
import model.restaurant.HalfMeal;
import model.restaurant.MealMenu;
import model.restaurant.Menu;

public class MealMenuTest {

	private static MealMenu mealMenu = null;
	private static final double discount_factor = 0.1;
	private static Menu menu = new Menu();
	
	@BeforeClass
	public static void testMealMenu() {
		menu.initMenu();
		
		mealMenu = new MealMenu(discount_factor);
		assertNotNull(mealMenu);
		assertNotNull(mealMenu.getMeals());
		assertTrue(discount_factor==mealMenu.getDiscount_factor());
	}

	@Test
	public void testAddMeal() {
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(1), menu.getMaindishes().get(1), menu.getDesserts().get(1));
		HalfMeal hm1 = new HalfMeal("HM1", menu.getStarters().get(0), menu.getMaindishes().get(0));
		HalfMeal hm2 = new HalfMeal("HM2", menu.getStarters().get(1), menu.getMaindishes().get(1));
		
		mealMenu.addMeal(fm1);
		mealMenu.addMeal(fm2);
		mealMenu.addMeal(hm1);
		mealMenu.addMeal(hm2);
		assertEquals(4, mealMenu.getMeals().size());
	}

	@Test
	public void testRemoveMeal() {
		HalfMeal hm3 = new HalfMeal("HM3", menu.getStarters().get(1), menu.getMaindishes().get(1));
		mealMenu.addMeal(hm3);
		assertTrue(mealMenu.getMeals().contains(hm3));
		mealMenu.removeMeal(hm3.getName());
		assertFalse(mealMenu.getMeals().contains(hm3));
	}

	@AfterClass
	public static void testDisplay() {
		mealMenu.display();
	}

}
