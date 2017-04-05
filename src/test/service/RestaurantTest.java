package test.service;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.restaurant.Dessert;
import model.restaurant.Dish;
import model.restaurant.MainDish;
import model.restaurant.Meal;
import model.restaurant.Menu;
import model.restaurant.Starter;
import model.users.*;
import service.RestaurantService;
import service.impl.RestaurantServiceImpl;

public class RestaurantTest {

	// number of methods in interface of restaurant service
	private final int totalTestCount = 14; 
	// count of test
	private int testCounter = 0;
	
	private Restaurant restaurant;
	private RestaurantService restaurantService;
	
	public void testValid(){
		testCounter++;
	}
	
	@Before
	public void initiateRestaurant() {

		System.out.println("\n----------------------- initiateRestaurant -----------------------");
		
		restaurant = new Restaurant("Beijing restaurant", "restaurant No.1", new AddressPoint(1.0,1.0));
		restaurantService = restaurant.getRestaurantService();
		
		restaurantService.displayMenu();
		
		restaurantService.displayMealMenu();
		
		restaurantService.displaySpecialMenu();
	}
	
	
	/*
	 * test of operations for :
	 * 1. add/remove dish to/from the menu : addDish(), removeDish()
	 * 2. see whether the menu has a dish using his name : hasDish()
	 * 3. copy dishes using dishFactory binded to the menu : createDish()
	 */
//	@Test
	public void menuOperations(){
		
		System.out.println("\n----------------------- menuOperations -----------------------");
		
//		initiateRestaurant();
		
		Dish starter_1 = new Starter("starter_1", "standard",  1.5);
		Dish starter_2 = new Starter("starter_2", "standard",  1.5);
		Dish mainDish_1 = new MainDish("mainDish_1", "standard", 5.0);
		Dish mainDish_2 = new MainDish("mainDish_2", "standard", 5.0);
		Dish dessert_1 = new Dessert("dessert_1", "standard", 2.1);
		Dish dessert_2 = new Dessert("dessert_2", "standard", 2.1);
		
		restaurantService.addDish(starter_1);
		restaurantService.addDish(starter_2);
		restaurantService.addDish(dessert_1);
		restaurantService.addDish(dessert_2);
		restaurantService.addDish(mainDish_1);
		restaurantService.addDish(mainDish_2);
		testValid();
		
		restaurantService.displayMenu();
		testValid();
		
		assertTrue(restaurantService.hasDish("starter_1"));
		assertTrue(restaurantService.hasDish("foie gras"));
		assertTrue(restaurantService.hasDish("poulet"));
		testValid();
		
		restaurantService.removeDish("starter_1");
		restaurantService.removeDish("mainDish_2");
		restaurantService.removeDish("dessert_2");
		testValid();
		
		restaurantService.displayMenu();
		
		assertFalse((restaurantService.hasDish("starter_1")));
		// DishNotFoundException desired !
		restaurantService.createDish("starter_1");
		
		restaurantService.createDish("foie gras");
		testValid();
	}

	/*
	 * test of operations for :
	 * 1. add/remove meal : addMeal(), removeMeal()
	 * 2. add/remove special meal : addSpecialMeal(), removeSpecialMeal()
	 * 3. copy meals using mealFactory : createMeal() 
	 */
	@Test
	public void mealOperations(){
		
		System.out.println("\n----------------------- mealOperations -----------------------");
		
		initiateRestaurant();
		
		restaurantService.addMeal("new meal 1", "foie gras", "poulet");
		restaurantService.addMeal("new meal 2", "cafe", "poisson");
		testValid();
		restaurantService.addMeal("new meal 3", "foie gras", "poisson", "glace");
		restaurantService.addMeal("new meal 4", "salade", "poisson", "cafe");
		testValid();
		restaurantService.displayMealMenu();
		testValid();
		
		restaurantService.removeMeal("new meal 2");
		restaurantService.removeMeal("new meal 4");
		testValid();
		restaurantService.displayMealMenu();
		
		// MealTypeErrorException and MealNotFoundException are desired!
//		restaurantService.createMeal("unknown type", "new meal 1");
		restaurantService.createMeal("half meal", "Salade-Poulet-glace");
		restaurantService.createMeal("full meal", "Salade-poulet");
		
		restaurantService.createMeal("half meal", "Salade-poulet");
		restaurantService.createMeal("full meal", "Salade-Poulet-glace");
		testValid();
		
		
		// MealNotFoundException desired !
		restaurantService.addSpecialMeal("new meal 2");
		
		restaurantService.addSpecialMeal("new meal 1");
		restaurantService.addSpecialMeal("Salade-Poulet-glace");
		testValid();
		restaurantService.displaySpecialMenu();
		testValid();
		
		restaurantService.removeSpecialMeal("new meal 1");
		testValid();
		
		restaurantService.displaySpecialMenu();
	}
	
	//@Test
	public void orderHistory(){
		System.out.println("\n----------------------- orderHistory -----------------------");
	}
	
	@After
	public void testResult(){
		double coverage = (double)testCounter/(double)totalTestCount;
		
		System.out.println("\n----------------------- Test Result -----------------------");
		System.out.println("test number=" + testCounter + ", total number=" + totalTestCount);
		System.out.println("test coverage=" + coverage);
	}
}
