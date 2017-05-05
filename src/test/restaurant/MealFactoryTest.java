/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.NameNotFoundException;
import exceptions.NameAlreadyExistsException;
import restaurant.FullMeal;
import restaurant.MealFactory;
import restaurant.HalfMeal;
import restaurant.Meal;
import restaurant.MealMenu;
import restaurant.Menu;
import system.AddressPoint;
import user.model.Restaurant;


/**
 * The Class MealFactoryTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MealFactoryTest {

	/** The menu. */
	private static Menu menu = new Menu();
	
	/** The meal menu. */
	static Restaurant restaurant = new Restaurant("test","test", new AddressPoint(0,0),"password");
	
	/** The meal menu. */
	private static MealMenu mealMenu = new MealMenu(restaurant);
	
	/** The  meal factory. */
	private static MealFactory mealFactory = null;
	
	/**
	 * Test  meal factory.
	 */
	@BeforeClass
	public static void testMealFactory() {
		
		menu.initMenu();
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(1), menu.getMaindishes().get(1), menu.getDesserts().get(1));

		try {
			mealMenu.addMeal(fm1);
			mealMenu.addMeal(fm2);
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mealFactory = new MealFactory(mealMenu);
		
		assertNotNull(mealFactory);
		assertEquals(mealMenu, mealFactory.getMealmenu());
	}

	/**
	 * Test create meal string.
	 */
	@Test
	public void testCreateMealString() {
		
		Meal fm = (FullMeal) mealFactory.getMealmenu().getMeals().get(0);
		
		try {
			Meal fm_create = mealFactory.createMeal(fm.getName());
			assertEquals(fm, fm_create);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test create meal string exception.
	 */
	@Test
	public void testCreateMealStringException() {
		
		try {

			Meal fm_not_exist = mealFactory.createMeal("aaaaa");
			assertNull(fm_not_exist);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}

}
