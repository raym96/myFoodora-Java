/*
 * 
 */
package test.usecase;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.UserNotFoundException;
import initialization.InitialScenario;
import restaurant.Meal;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.ManagerService;
import user.service.MyFoodoraService;
import user.service.impl.ManagerServiceImpl;
import user.service.impl.MyFoodoraServiceImpl;


/**
 * Use case scenario
 * The following use case scenario describe examples of how the MyFoodora should function.
 *
 * @author He Xiaoan
 * @author Ji Raymond
 */

/*
// * Adding a meal of the week special oer
//	1. a restaurant sta starts using the system and inserts the restaurant credentials
//	2. the system shows all restaurant's available meals
//	3. the restaurant selects the meal to be set as meal of the week
//	4. the system automatically updates the price of selected meal of the week, by application
//	of special discount factor
//	5. the system noties the users (that agreed to be notied of special oers) about the
//	new offer
// */

public class AddSpecialOfferTest {

	/** The myfoodora. */
	private MyFoodora myfoodora;
	
	/** The myfoodora service. */
	private MyFoodoraService myfoodora_service;
	
	/** The manager service director. */
	private ManagerService managerService_director;

	
	/**
	 * Test startup scenario.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
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

	/**
	 * Test of add meal of week special offer.
	 */
	@Test
	public void testOfAddMealOfWeekSpecialOffer(){
		
		System.out.println("----------------------- Adding a meal of the week special offer -----------------------");
		
		//the client restaurant inserts credentials
		User user = myfoodora_service.selectUser("restaurant_1");
		user.logIn();
		
		
		if (user!=null && user instanceof Restaurant){
			Restaurant restaurant = (Restaurant)user;
			restaurant.getRestaurantService().displayMenu();
			restaurant.getRestaurantService().displayMealMenu();
			Scanner s = new Scanner(System.in);
			System.out.println("Please enter the name of the meal to be upgraded as meal-of-the-week");
			String mealname = s.nextLine();
			restaurant.getRestaurantService().addSpecialMeal(mealname);
			//the price is automatically updated when adding the meal to the special menu
			restaurant.getRestaurantService().displayMealMenu();
			restaurant.getRestaurantService().displaySpecialMenu();
			
			//notify all observers
			for (Meal m: restaurant.getSpecialmealmenu().getMeals()){
				if (m.getName().equalsIgnoreCase(mealname)){
					myfoodora.getMyFoodoraService().notifyAll(m);
				}
			}
			s.close();
		}
	}
}
