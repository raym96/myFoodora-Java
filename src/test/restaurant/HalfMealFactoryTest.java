package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MealNotFoundException;
import restaurant.HalfMeal;
import restaurant.HalfMealFactory;
import restaurant.MealMenu;
import restaurant.Menu;

public class HalfMealFactoryTest {

	private static Menu menu = new Menu();
	private static MealMenu mealMenu = new MealMenu(0.1);
	private static HalfMealFactory halfMealFactory = null;
	
	@BeforeClass
	public static void testHalfMealFactory() {
		
		menu.initMenu();
		HalfMeal hm1 = new HalfMeal("HM1", menu.getStarters().get(0), menu.getMaindishes().get(0));
		HalfMeal hm2 = new HalfMeal("HM2", menu.getStarters().get(1), menu.getMaindishes().get(1));

		mealMenu.addMeal(hm1);
		mealMenu.addMeal(hm2);
		
		halfMealFactory = new HalfMealFactory(mealMenu);
		
		assertNotNull(halfMealFactory);
		assertEquals(mealMenu, halfMealFactory.getMealmenu());
	}

	@Test
	public void testCreateMealString() {
		
		HalfMeal hm = (HalfMeal) halfMealFactory.getMealmenu().getMeals().get(0);
		
		try {
			HalfMeal hm_create = halfMealFactory.createMeal(hm.getName());
			assertEquals(hm, hm_create);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = MealNotFoundException.class)
	public void testCreateMealStringException() {
		
		try {

			HalfMeal hm_not_exist = halfMealFactory.createMeal("aaaaa");
			assertNull(hm_not_exist);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}


}
