/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MealNotFoundException;
import exceptions.NameAlreadyExistsException;
import restaurant.FullMeal;
import restaurant.HalfMeal;
import restaurant.MealMenu;
import restaurant.Menu;
import system.AddressPoint;
import user.model.Restaurant;


/**
 * The Class MealMenuTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MealMenuTest {

	/** The meal menu. */
	private static MealMenu mealMenu = null;
	
	/** The restaurant of the meal menu. */
	static Restaurant restaurant = new Restaurant("test","test", new AddressPoint(0,0));

	
	/** The menu. */
	private static Menu menu = new Menu();
	
	/**
	 * Test meal menu.
	 */
	@BeforeClass
	public static void testMealMenu() {
		menu.initMenu();
		
		mealMenu = new MealMenu(restaurant);
		assertNotNull(mealMenu);
		assertNotNull(mealMenu.getMeals());
		assertTrue(restaurant==mealMenu.getRestaurant());
	}

	/**
	 * Test add meal.
	 */
	@Test
	public void testAddMeal() {
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(1), menu.getMaindishes().get(1), menu.getDesserts().get(1));
		HalfMeal hm1 = new HalfMeal("HM1", menu.getStarters().get(0), menu.getMaindishes().get(0));
		HalfMeal hm2 = new HalfMeal("HM2", menu.getStarters().get(1), menu.getMaindishes().get(1));
		
		try {
			mealMenu.addMeal(fm1);
			mealMenu.addMeal(fm2);
			mealMenu.addMeal(hm1);
			mealMenu.addMeal(hm2);
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(4, mealMenu.getMeals().size());
	}

	/**
	 * Test remove meal.
	 */
	@Test
	public void testRemoveMeal() {
		HalfMeal hm3 = new HalfMeal("HM3", menu.getStarters().get(1), menu.getMaindishes().get(1));
		try {
			mealMenu.addMeal(hm3);
		} catch (NameAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertTrue(mealMenu.getMeals().contains(hm3));
		try {
			mealMenu.removeMeal(hm3.getName());
		} catch (MealNotFoundException e) {
			e.printStackTrace();
		}
		assertFalse(mealMenu.getMeals().contains(hm3));
	}

	/**
	 * Test display.
	 */
	@AfterClass
	public static void testDisplay() {
		mealMenu.display();
	}

}
