package test.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenario;
import restaurant.*;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.RestaurantService;
import user.service.impl.MyFoodoraServiceImpl;

public class RestaurantServiceTest {
	Restaurant restaurant1;
	RestaurantService restaurant1_service;

	Restaurant restaurant2;
	RestaurantService restaurant2_service;
	Dish terrine;
	Dish sandwich;
	Dish fondant;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		InitialScenario.load("scenario_test_services.ini");
		restaurant1 = (Restaurant)new MyFoodoraServiceImpl().selectUser("restaurant_1");
		restaurant1_service = restaurant1.getRestaurantService();
		
		restaurant2 = (Restaurant)new MyFoodoraServiceImpl().selectUser("restaurant_2");
		restaurant2_service = restaurant2.getRestaurantService();
		
		terrine = new Starter("terrine","standard",3);
		sandwich = new MainDish("Sandwich","vegetarian",4);
		fondant = new Dessert("fondant","gluten-free",2);
		
	}

	@Test
	public void testAddDish() {
		System.out.println("-----testAddDish-----");
		restaurant1_service.addDish(sandwich);
		
		//verify that dish is added to the menu
		assertTrue(restaurant1.getMenu().getDishes().contains(sandwich));
	}

	@Test
	public void testRemoveDish() {
		System.out.println("-----testRemoveDish-----");
		restaurant1_service.addDish(sandwich);
		assertTrue(restaurant1.getMenu().getDishes().contains(sandwich));

		//verify that the added dish is removed
		restaurant1_service.displayMenu();
		restaurant1_service.removeDish("sandwich");
		restaurant1_service.displayMenu();
		assertFalse(restaurant1.getMenu().getDishes().contains(sandwich));
		
	}

	@Test
	public void testCreateDish() {
		System.out.println("-----testCreateDish-----");
		restaurant1_service.addDish(sandwich);
		
		assertEquals(restaurant1_service.createDish("Sandwich"),sandwich);
		
	}

	@Test
	public void testAddMealMeal() {
		System.out.println("-----testAddMealMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		
		assertTrue(restaurant1.getHalfMealMenu().getMeals().contains(meal));
		
	}

	@Test
	public void testAddMealStringStringString() {
		System.out.println("-----testAddMeal(mealname,dishname1,dishname2)-----");
		restaurant1_service.addDish(terrine);
		restaurant1_service.addDish(sandwich);
		
		
		restaurant1_service.addMeal("test_menu", "terrine", "sandwich");
		
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		assertTrue(restaurant1.getHalfMealMenu().getMeals().contains(meal));
		
	}

	@Test
	public void testAddMealStringStringStringString() {
		System.out.println("-----testAddMeal(mealname,dishname1,dishname2,dishname3)-----");
		restaurant1_service.addDish(terrine);
		restaurant1_service.addDish(sandwich);
		restaurant1_service.addDish(fondant);
		
		restaurant1_service.addMeal("test_menu", "terrine", "sandwich","fondant");
		
		
		Meal meal = new FullMeal("test_menu",terrine,sandwich,fondant);
		assertTrue(restaurant1.getFullMealMenu().getMeals().contains(meal));
	}

	@Test
	public void testCreateMeal() {
		System.out.println("-----testCreateMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		
		//verify that the meal is effectively the one created
		assertEquals(restaurant1_service.createMeal("Half-Meal", "test_menu"), meal);
	}

	@Test
	public void testRemoveMeal() {
		System.out.println("-----testRemoveMeal------");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		assertTrue(restaurant1.getHalfMealMenu().getMeals().contains(meal));
		
		restaurant1_service.removeMeal("test_menu");
		assertFalse(restaurant1.getHalfMealMenu().getMeals().contains(meal));
	}

	@Test
	public void testAddSpecialMeal() {
		System.out.println("-----testAddSpecialMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		restaurant1_service.addSpecialMeal("test_menu");
		restaurant1_service.displayAllMenu();
		assertTrue(restaurant1.getSpecialmealmenu().getMeals().contains(meal));
	}

	@Test
	public void testRemoveSpecialMeal() {
		System.out.println("-----testRemoveSpecialMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		restaurant1_service.addSpecialMeal("test_menu");
		restaurant1_service.displayAllMenu();
		
		restaurant1_service.removeSpecialMeal("test_menu");
		assertFalse(restaurant1.getSpecialmealmenu().getMeals().contains(meal));
	}

	@Test
	public void testSetGenericDiscountFactor() {
		restaurant1_service.setGenericDiscountFactor(0.2);
		assertTrue(restaurant1.getGeneric_discount_factor()==0.2);
	}

	@Test
	public void testSetSpecialDiscountFactor() {
		restaurant1_service.setSpecialDiscountFactor(0.3);
		assertTrue(restaurant1.getSpecial_discount_factor()==0.3);
	}

	@Test
	public void testDisplayMostOrderedHalfMeal() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.DisplayMostOrderedHalfMeal();
		restaurant2_service.DisplayMostOrderedHalfMeal();
	}

	@Test
	public void testDisplayLeastOrderedHalfMeal() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.DisplayLeastOrderedHalfMeal();
		restaurant2_service.DisplayLeastOrderedHalfMeal();
	}

	@Test
	public void testDisplayMostOrderedAlaCarte() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.DisplayMostOrderedAlaCarte();
		restaurant2_service.DisplayMostOrderedAlaCarte();
	}

	@Test
	public void testDisplayLeastOrderedAlaCarte() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.DisplayLeastOrderedAlaCarte();
		restaurant2_service.DisplayLeastOrderedAlaCarte();
	}

	@Test
	public void testDisplayMenu() {
		restaurant1_service.displayMenu();
		restaurant2_service.displayMenu();
	}

	@Test
	public void testDisplayMealMenu() {
		restaurant1_service.displayMealMenu();
	}

	@Test
	public void testDisplaySpecialMenu() {
		restaurant1_service.displaySpecialMenu();
	}

	@Test
	public void testDisplayAllMenu() {
		restaurant1_service.displayMealMenu();
	}

}
