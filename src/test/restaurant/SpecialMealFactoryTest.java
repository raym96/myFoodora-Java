/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MealNotFoundException;
import exceptions.NameAlreadyExistsException;
import restaurant.HalfMeal;
import restaurant.HalfMealFactory;
import restaurant.MealMenu;
import restaurant.Menu;
import restaurant.SpecialMealFactory;


/**
 * The Class SpecialMealFactoryTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SpecialMealFactoryTest {

	/** The menu. */
	private static Menu menu = new Menu();
	
	/** The meal menu. */
	private static MealMenu mealMenu = new MealMenu(0.1);
	
	/** The special meal factory. */
	private static SpecialMealFactory specialMealFactory = null;
	
	/**
	 * Test special meal factory.
	 */
	@BeforeClass
	public static void testSpecialMealFactory() {
		
		menu.initMenu();
		HalfMeal sm1 = new HalfMeal("SM1", menu.getStarters().get(0), menu.getMaindishes().get(0));
		HalfMeal sm2 = new HalfMeal("SM2", menu.getStarters().get(0), menu.getMaindishes().get(0));

		try {
			mealMenu.addMeal(sm1);
			mealMenu.addMeal(sm2);
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		specialMealFactory = new SpecialMealFactory(mealMenu);
		
		assertNotNull(specialMealFactory);
		assertEquals(mealMenu, specialMealFactory.getMealmenu());
	}

	/**
	 * Test create meal string.
	 */
	@Test
	public void testCreateMealString() {
		
		HalfMeal sm = (HalfMeal) specialMealFactory.getMealmenu().getMeals().get(0);
		
		try {
			HalfMeal sm_create = (HalfMeal) specialMealFactory.createMeal(sm.getName());
			assertEquals(sm, sm_create);
			
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

			HalfMeal sm_not_exist = (HalfMeal) specialMealFactory.createMeal("aaaaa");
			assertNull(sm_not_exist);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
}
