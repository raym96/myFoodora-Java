/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MealNotFoundException;
import exceptions.NameAlreadyExistsException;
import restaurant.FullMeal;
import restaurant.FullMealFactory;
import restaurant.HalfMeal;
import restaurant.MealMenu;
import restaurant.Menu;
import system.AddressPoint;
import user.model.Restaurant;


/**
 * The Class FullMealFactoryTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class FullMealFactoryTest {

	/** The menu. */
	private static Menu menu = new Menu();
	
	/** The meal menu. */
	static Restaurant restaurant = new Restaurant("test","test", new AddressPoint(0,0));
	private static MealMenu mealMenu = new MealMenu(restaurant);
	
	/** The full meal factory. */
	private static FullMealFactory fullMealFactory = null;
	
	/**
	 * Test full meal factory.
	 */
	@BeforeClass
	public static void testFullMealFactory() {
		
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

		fullMealFactory = new FullMealFactory(mealMenu);
		
		assertNotNull(fullMealFactory);
		assertEquals(mealMenu, fullMealFactory.getMealmenu());
	}

	/**
	 * Test create meal string.
	 */
	@Test
	public void testCreateMealString() {
		
		FullMeal fm = (FullMeal) fullMealFactory.getMealmenu().getMeals().get(0);
		
		try {
			FullMeal fm_create = fullMealFactory.createMeal(fm.getName());
			assertEquals(fm, fm_create);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test create meal string exception.
	 */
	@Test(expected = MealNotFoundException.class)
	public void testCreateMealStringException() {
		
		try {

			FullMeal fm_not_exist = fullMealFactory.createMeal("aaaaa");
			assertNull(fm_not_exist);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}

}
