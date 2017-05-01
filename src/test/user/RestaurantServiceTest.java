/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenarioOld;
import restaurant.*;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.RestaurantService;
import user.service.impl.MyFoodoraServiceImpl;


/**
 * The Class RestaurantServiceTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class RestaurantServiceTest {
	
	/** The restaurant 1. */
	Restaurant restaurant1;
	
	/** The restaurant 1 service. */
	RestaurantService restaurant1_service;

	/** The restaurant 2. */
	Restaurant restaurant2;
	
	/** The restaurant 2 service. */
	RestaurantService restaurant2_service;
	
	/** The terrine. */
	Dish terrine;
	
	/** The sandwich. */
	Dish sandwich;
	
	/** The fondant. */
	Dish fondant;
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		InitialScenario.load("my_foodora.ini");;
		restaurant1 = (Restaurant)MyFoodora.getInstance().getService().selectUser("restaurant_1");
		restaurant1_service = restaurant1.getService();
		
		restaurant2 = (Restaurant)MyFoodora.getInstance().getService().selectUser("restaurant_2");
		restaurant2_service = restaurant2.getService();
		
		terrine = new Starter("terrine","standard",3);
		sandwich = new MainDish("Sandwich","vegetarian",4);
		fondant = new Dessert("fondant","gluten-free",2);
		
	}

	/**
	 * Test add dish.
	 */
	@Test
	public void testAddDish() {
		System.out.println("-----testAddDish-----");
		restaurant1_service.addDish(sandwich);
		
		//verify that dish is added to the menu
		assertTrue(restaurant1.getMenu().getDishes().contains(sandwich));
	}

	/**
	 * Test remove dish.
	 */
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

	/**
	 * Test create dish.
	 */
	@Test
	public void testCreateDish() {
		System.out.println("-----testCreateDish-----");
		restaurant1_service.addDish(sandwich);
		
		assertEquals(restaurant1_service.createFactoryDish("Sandwich"),sandwich);
		
	}

	/**
	 * Test add meal meal.
	 */
	@Test
	public void testAddMealMeal() {
		System.out.println("-----testAddMealMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		
		assertTrue(restaurant1.getMealMenu().getMeals().contains(meal));
		
	}

	/**
	 * Test add meal string string string.
	 */
	@Test
	public void testAddMealStringStringString() {
		System.out.println("-----testAddMeal(mealname,dishname1,dishname2)-----");
		restaurant1_service.addDish(terrine);
		restaurant1_service.addDish(sandwich);
		
		
		restaurant1_service.addMeal("test_menu", "terrine", "sandwich");
		
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		assertTrue(restaurant1.getMealMenu().getMeals().contains(meal));
		
	}

	/**
	 * Test add meal string string string string.
	 */
	@Test
	public void testAddMealStringStringStringString() {
		System.out.println("-----testAddMeal(mealname,dishname1,dishname2,dishname3)-----");
		restaurant1_service.addDish(terrine);
		restaurant1_service.addDish(sandwich);
		restaurant1_service.addDish(fondant);
		
		restaurant1_service.addMeal("test_menu", "terrine", "sandwich","fondant");
		
		
		Meal meal = new FullMeal("test_menu",terrine,sandwich,fondant);
		assertTrue(restaurant1.getMealMenu().getMeals().contains(meal));
	}

	/**
	 * Test create meal.
	 */
	@Test
	public void testCreateMeal() {
		System.out.println("-----testCreateMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		
		//verify that the meal is effectively the one created
		assertEquals(restaurant1_service.createFactoryMeal("Half-Meal", "test_menu"), meal);
	}

	/**
	 * Test remove meal.
	 */
	@Test
	public void testRemoveMeal() {
		System.out.println("-----testRemoveMeal------");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		assertTrue(restaurant1.getMealMenu().getMeals().contains(meal));
		
		restaurant1_service.removeMeal("test_menu");
		assertFalse(restaurant1.getMealMenu().getMeals().contains(meal));
	}

	/**
	 * Test add special meal.
	 */
	@Test
	public void testAddSpecialMeal() {
		System.out.println("-----testAddSpecialMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		restaurant1_service.setSpecialOffer("test_menu");
		restaurant1_service.displayAllMenu();
		assertTrue(restaurant1.getSpecialmealmenu().getMeals().contains(meal));
	}

	/**
	 * Test remove special meal.
	 */
	@Test
	public void testRemoveSpecialMeal() {
		System.out.println("-----testRemoveSpecialMeal-----");
		Meal meal = new HalfMeal("test_menu",terrine,sandwich);
		restaurant1_service.addMeal(meal);
		restaurant1_service.setSpecialOffer("test_menu");
		restaurant1_service.displayAllMenu();
		
		restaurant1_service.removeSpecialOffer("test_menu");
		assertFalse(restaurant1.getSpecialmealmenu().getMeals().contains(meal));
	}

	/**
	 * Test set generic discount factor.
	 */
	@Test
	public void testSetGenericDiscountFactor() {
		restaurant1_service.setGenericDiscountFactor(0.2);
		assertTrue(restaurant1.getGeneric_discount_factor()==0.2);
	}

	/**
	 * Test set special discount factor.
	 */
	@Test
	public void testSetSpecialDiscountFactor() {
		restaurant1_service.setSpecialDiscountFactor(0.3);
		assertTrue(restaurant1.getSpecial_discount_factor()==0.3);
	}

	/**
	 * Test display most ordered half meal.
	 */
	@Test
	public void testDisplayMostOrderedHalfMeal() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.displayMostOrderedHalfMeal();
		restaurant2_service.displayMostOrderedHalfMeal();
	}

	/**
	 * Test display least ordered half meal.
	 */
	@Test
	public void testDisplayLeastOrderedHalfMeal() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.displayLeastOrderedHalfMeal();
		restaurant2_service.displayLeastOrderedHalfMeal();
	}

	/**
	 * Test display most ordered ala carte.
	 */
	@Test
	public void testDisplayMostOrderedAlaCarte() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.displayMostOrderedAlaCarte();
		restaurant2_service.displayMostOrderedAlaCarte();
	}

	/**
	 * Test display least ordered ala carte.
	 */
	@Test
	public void testDisplayLeastOrderedAlaCarte() {
		System.out.println("History of all orders on MyFoodora:");
		System.out.println(MyFoodora.getInstance().getHistory());
		restaurant1_service.displayLeastOrderedAlaCarte();
		restaurant2_service.displayLeastOrderedAlaCarte();
	}

	/**
	 * Test display menu.
	 */
	@Test
	public void testDisplayMenu() {
		restaurant1_service.displayMenu();
		restaurant2_service.displayMenu();
	}

	/**
	 * Test display meal menu.
	 */
	@Test
	public void testDisplayMealMenu() {
		restaurant1_service.displayMealMenu();
	}

	/**
	 * Test display special menu.
	 */
	@Test
	public void testDisplaySpecialMenu() {
		restaurant1_service.displaySpecialMenu();
	}

	/**
	 * Test display all menu.
	 */
	@Test
	public void testDisplayAllMenu() {
		restaurant1_service.displayMealMenu();
	}

}
