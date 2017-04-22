/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.NameNotFoundException;
import exceptions.NameAlreadyExistsException;
import restaurant.HalfMeal;
import restaurant.HalfMealFactory;
import restaurant.MealMenu;
import restaurant.Menu;
import system.AddressPoint;
import user.model.Restaurant;


/**
 * The Class HalfMealFactoryTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class HalfMealFactoryTest {

	/** The menu. */
	private static Menu menu = new Menu();
	
	/** The meal menu. */
	static Restaurant restaurant = new Restaurant("test","test", new AddressPoint(0,0));
	private static MealMenu mealMenu = new MealMenu(restaurant);
	

	/** The half meal factory. */
	private static HalfMealFactory halfMealFactory = null;
	
	/**
	 * Test half meal factory.
	 */
	@BeforeClass
	public static void testHalfMealFactory() {
		
		menu.initMenu();
		HalfMeal hm1 = new HalfMeal("HM1", menu.getStarters().get(0), menu.getMaindishes().get(0));
		HalfMeal hm2 = new HalfMeal("HM2", menu.getStarters().get(1), menu.getMaindishes().get(1));

		try {
			mealMenu.addMeal(hm1);
			mealMenu.addMeal(hm2);
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		halfMealFactory = new HalfMealFactory(mealMenu);
		
		assertNotNull(halfMealFactory);
		assertEquals(mealMenu, halfMealFactory.getMealmenu());
	}

	/**
	 * Test create meal string.
	 */
	@Test
	public void testCreateMealString() {
		
		HalfMeal hm = (HalfMeal) halfMealFactory.getMealmenu().getMeals().get(0);
		
		try {
			HalfMeal hm_create = halfMealFactory.createMeal(hm.getName());
			assertEquals(hm, hm_create);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test create meal string exception.
	 */
	@Test(expected = NameNotFoundException.class)
	public void testCreateMealStringException() {
		
		try {

			HalfMeal hm_not_exist = halfMealFactory.createMeal("aaaaa");
			assertNull(hm_not_exist);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}


}
