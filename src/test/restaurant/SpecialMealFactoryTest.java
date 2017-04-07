package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MealNotFoundException;
import model.restaurant.HalfMeal;
import model.restaurant.HalfMealFactory;
import model.restaurant.MealMenu;
import model.restaurant.Menu;
import model.restaurant.SpecialMeal;
import model.restaurant.SpecialMealFactory;

public class SpecialMealFactoryTest {

	private static Menu menu = new Menu();
	private static MealMenu mealMenu = new MealMenu(0.1);
	private static SpecialMealFactory specialMealFactory = null;
	
	@BeforeClass
	public static void testSpecialMealFactory() {
		
		menu.initMenu();
		SpecialMeal sm1 = new SpecialMeal("SM1", menu.getStarters().get(0), menu.getMaindishes().get(0));
		SpecialMeal sm2 = new SpecialMeal("SM2", menu.getStarters().get(0), menu.getMaindishes().get(0));

		mealMenu.addMeal(sm1);
		mealMenu.addMeal(sm2);
		
		specialMealFactory = new SpecialMealFactory(mealMenu);
		
		assertNotNull(specialMealFactory);
		assertEquals(mealMenu, specialMealFactory.getMealmenu());
	}

	@Test
	public void testCreateMealString() {
		
		SpecialMeal sm = (SpecialMeal) specialMealFactory.getMealmenu().getMeals().get(0);
		
		try {
			SpecialMeal sm_create = (SpecialMeal) specialMealFactory.createMeal(sm.getName());
			assertEquals(sm, sm_create);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = MealNotFoundException.class)
	public void testCreateMealStringException() {
		
		try {

			SpecialMeal sm_not_exist = (SpecialMeal) specialMealFactory.createMeal("aaaaa");
			assertNull(sm_not_exist);
			
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
}