package test.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenario;
import restaurant.*;
import user.MyFoodora;
import user.MyFoodoraServiceImpl;
import user.Restaurant;
import user.RestaurantService;

public class RestaurantServiceTest {
	Restaurant restaurant;
	RestaurantService r;

	Restaurant restaurant2;
	RestaurantService r2;
	Dish terrine;
	Dish sandwich;
	Dish fondant;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		InitialScenario.load("scenario_test_services.ini");
		restaurant = (Restaurant)new MyFoodoraServiceImpl().selectUser("restaurant_1");
		r = restaurant.getRestaurantService();
		
		restaurant2 = (Restaurant)new MyFoodoraServiceImpl().selectUser("restaurant_2");
		r2 = restaurant2.getRestaurantService();
		
		terrine = new Starter("terrine","standard",3);
		sandwich = new MainDish("Sandwich","vegetarian",4);
		fondant = new Dessert("fondant","gluten-free",2);
		
	}

	@Test
	public void testAddDish() {
		System.out.println("-----testAddDish-----");
		r.addDish(sandwich);
		
		//verify that dish is added to the menu
		assertTrue(restaurant.getMenu().getDishes().contains(sandwich));
	}

	@Test
	public void testRemoveDish() {
		System.out.println("-----testRemoveDish-----");
		r.addDish(sandwich);
		assertTrue(restaurant.getMenu().getDishes().contains(sandwich));

		//verify that the added dish is removed
		r.displayMenu();
		r.removeDish("sandwich");
		r.displayMenu();
		assertFalse(restaurant.getMenu().getDishes().contains(sandwich));
		
	}

	@Test
	public void testCreateDish() {
		System.out.println("-----testCreateDish-----");
		r.addDish(sandwich);
		
		assertEquals(r.createDish("Sandwich"),sandwich);
		
	}

	@Test
	public void testAddMealMeal() {
		System.out.println("-----testAddMealMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		r.addMeal(meal);
		
		assertTrue(restaurant.getHalfMealMenu().getMeals().contains(meal));
		
	}

	@Test
	public void testAddMealStringStringString() {
		System.out.println("-----testAddMeal(mealname,dishname1,dishname2)-----");
		r.addDish(terrine);
		r.addDish(sandwich);
		
		
		r.addMeal("test_menu", "terrine", "sandwich");
		
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		assertTrue(restaurant.getHalfMealMenu().getMeals().contains(meal));
		
	}

	@Test
	public void testAddMealStringStringStringString() {
		System.out.println("-----testAddMeal(mealname,dishname1,dishname2,dishname3)-----");
		r.addDish(terrine);
		r.addDish(sandwich);
		r.addDish(fondant);
		
		r.addMeal("test_menu", "terrine", "sandwich","fondant");
		
		
		Meal meal = new FullMeal("test_menu",terrine,sandwich,fondant);
		assertTrue(restaurant.getFullMealMenu().getMeals().contains(meal));
	}

	@Test
	public void testCreateMeal() {
		System.out.println("-----testCreateMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		r.addMeal(meal);
		
		//verify that the meal is effectively the one created
		assertEquals(r.createMeal("Half-Meal", "test_menu"), meal);
	}

	@Test
	public void testRemoveMeal() {
		System.out.println("-----testRemoveMeal------");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		r.addMeal(meal);
		assertTrue(restaurant.getHalfMealMenu().getMeals().contains(meal));
		
		r.removeMeal("test_menu");
		r.displayAllMenu();
		assertFalse(restaurant.getHalfMealMenu().getMeals().contains(meal));
	}

	@Test
	public void testAddSpecialMeal() {
		System.out.println("-----testAddSpecialMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		r.addMeal(meal);
		r.addSpecialMeal("test_menu");
		r.displayAllMenu();
		assertTrue(restaurant.getSpecialmealmenu().getMeals().contains(meal));
	}

	@Test
	public void testRemoveSpecialMeal() {
		System.out.println("-----testRemoveSpecialMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		r.addMeal(meal);
		r.addSpecialMeal("test_menu");
		r.displayAllMenu();
		
		r.removeSpecialMeal("test_menu");
		assertFalse(restaurant.getSpecialmealmenu().getMeals().contains(meal));
	}

	@Test
	public void testSetGenericDiscountFactor() {
		r.setGenericDiscountFactor(0.2);
		assertTrue(restaurant.getGeneric_discount_factor()==0.2);
	}

	@Test
	public void testSetSpecialDiscountFactor() {
		r.setSpecialDiscountFactor(0.3);
		assertTrue(restaurant.getSpecial_discount_factor()==0.3);
	}

	@Test
	public void testDisplayMostOrderedHalfMeal() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		r.DisplayMostOrderedHalfMeal();
		r2.DisplayMostOrderedHalfMeal();
	}

	@Test
	public void testDisplayLeastOrderedHalfMeal() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		r.DisplayLeastOrderedHalfMeal();
		r2.DisplayLeastOrderedHalfMeal();
	}

	@Test
	public void testDisplayMostOrderedAlaCarte() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		r.DisplayMostOrderedAlaCarte();
		r2.DisplayMostOrderedAlaCarte();
	}

	@Test
	public void testDisplayLeastOrderedAlaCarte() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		r.DisplayLeastOrderedAlaCarte();
		r2.DisplayLeastOrderedAlaCarte();
	}

	@Test
	public void testDisplayMenu() {
		r.displayMenu();
		r2.displayMenu();
	}

	@Test
	public void testDisplayMealMenu() {
		r.displayMealMenu();
	}

	@Test
	public void testDisplaySpecialMenu() {
		r.displaySpecialMenu();
	}

	@Test
	public void testDisplayAllMenu() {
		r.displayMealMenu();
	}

}
