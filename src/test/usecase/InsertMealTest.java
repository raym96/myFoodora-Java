package test.usecase;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import exceptions.UserNotFoundException;
import initialization.InitialScenario;
import restaurant.Dessert;
import restaurant.MainDish;
import restaurant.Starter;
import user.Manager;
import user.ManagerService;
import user.ManagerServiceImpl;
import user.MyFoodora;
import user.MyFoodoraService;
import user.MyFoodoraServiceImpl;
import user.Restaurant;
import user.User;

/**
 * Use case scenario
 * The following use case scenario describe examples of how the MyFoodora should function.
 * @throws UserNotFoundException 
 * 
 * @author Ray
 * @author Hxa
 **/

/*
 * Inserting a meal or dish in a restaurant menu
	1. a restaurant person start using the system because she wants to insert a new meal
	2. she inserts the restaurant credentials (username and password)
	3. the system recognizes the restaurant and shows the list of dishes and meals in the
	menu
	4. the restaurant selects the insert new meal (or dish) operations
	5. the restaurant inserts the name of the new meal (or dish) to be added and specify
	whether it is an half-meal or a full-meal or a meal-of-the-week
	6. in case of a dish the restaurant specify the unit price and the category its category
	(starter, main dish, dessert)
	7. in case of meal
	鈥� the restaurant inserts the dishes of the meal
	鈥� the restaurant compute and save the price of the meal
	8. the restaurant saves the new created meal (or dish) in the menu
 */

public class InsertMealTest {
	
	private MyFoodora myfoodora;
	private MyFoodoraService myfoodora_service;
	private ManagerService managerService_director;

	
	@Before
	public void testStartupScenario() throws UserNotFoundException {
	
		System.out.println("----------------------- Startup scenario -----------------------");
		InitialScenario.load("init.ini");
		
		myfoodora = MyFoodora.getInstance();
		myfoodora_service = new MyFoodoraServiceImpl();
		managerService_director = new ManagerServiceImpl(new Manager("test","myfoodora","usecase"));
	
		// send alerts to customers
		myfoodora_service.askAgree2customers("Do you agree to be notified of special offers ? By default it is no.");

	}

	@Test
	public void testOfInsertMealDish2menu(){
		System.out.println("----------------------- Insert a meal/dish in a restaurant menu -----------------------");
		
		//the client restaurant inserts credentials
		User user = myfoodora_service.selectUser("restaurant_1");
		user.logIn();
		
		
		if (user!=null && user instanceof Restaurant){
			Restaurant restaurant = (Restaurant)user;
			restaurant.getRestaurantService().displayMenu();
			restaurant.getRestaurantService().displayMealMenu();
			
			System.out.println("Please choose your operation: \n"+"1.add a dish 2.add a meal");
			Scanner s = new Scanner(System.in);
			int choice = s.nextInt();
			s.nextLine();
			if (choice==1){
				System.out.println("Please enter the name of the dish you want to add.");
				String dishName = s.nextLine();
				System.out.println("Please enter the price of the dish");
				Double price = s.nextDouble();
				s.nextLine();
				System.out.println("Please specify the type of the dish: \n" + "standard ; vegetarian ; gluten-free");
				String dishType = s.nextLine();
				System.out.println("Please specify the category of the dish by entering its number: \n"+"1. starter 2. main-dish  3. dessert");
				int category = s.nextInt();
				s.nextLine();
				switch(category){
				case 1: 
					restaurant.getRestaurantService().addDish(new Starter(dishName,dishType,price));
					break;
				case 2:
					restaurant.getRestaurantService().addDish(new MainDish(dishName,dishType,price));
					break;
				case 3:
					restaurant.getRestaurantService().addDish(new Dessert(dishName,dishType,price));
					break;
				}
				restaurant.getRestaurantService().displayMenu();
			}
			if (choice==2){
				System.out.println("Please enter the name of the meal you want to add.");
				String mealname = s.nextLine();
				System.out.println("Please choose the type of the meal: \n"+"1.Half-meal 2.Full-meal");
				int mealType = s.nextInt();
				s.nextLine();
				System.out.println("Please enter the names of the dishes of the meal. You may enter 2 or 3 meal names, for respectively a half-meal or a full-meal.");
				String dishname1 = s.nextLine();
				String dishname2 = s.nextLine();
				if (mealType ==1){
					restaurant.getRestaurantService().addMeal(mealname, dishname1, dishname2);
				}
				else{
					String dishname3 = s.nextLine();
					restaurant.getRestaurantService().addMeal(mealname,dishname1,dishname2,dishname3);
				}
				System.out.println("The price of the meal has been automatically computed and updated");
				restaurant.getRestaurantService().displayMealMenu();
			}
			
			// The price is automatically updated
		s.close();
		}
	}
	
}
