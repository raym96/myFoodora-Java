package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MealNotFoundException;
import model.restaurant.FullMeal;
import model.restaurant.FullMealFactory;
import model.restaurant.HalfMeal;
import model.restaurant.MealMenu;
import model.restaurant.Menu;

public class FullMealFactoryTest {

	private static Menu menu = new Menu();
	private static MealMenu mealMenu = new MealMenu(0.1);
	private static FullMealFactory fullMealFactory = null;
	
	@BeforeClass
	public static void testFullMealFactory() {
		
		menu.initMenu();
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(1), menu.getMaindishes().get(1), menu.getDesserts().get(1));

		mealMenu.addMeal(fm1);
		mealMenu.addMeal(fm2);
		
		fullMealFactory = new FullMealFactory(mealMenu);
		
		assertNotNull(fullMealFactory);
		assertEquals(mealMenu, fullMealFactory.getMealmenu());
	}

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
