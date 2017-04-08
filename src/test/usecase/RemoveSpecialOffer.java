package test.usecase;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.UserNotFoundException;
import initialization.InitialScenario;
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
 * Removing a meal of the week special oer
	1. a restaurant staff starts using the system and inserts the restaurant credentials
	2. the system shows all restaurant's available meals
	3. the restaurant selects a meal in the meal of the week list and selects the remove from
	its special offer state.
 */

public class RemoveSpecialOffer {

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
	public void testOfRemoveMealOfWeekSpecialOffer(){
		
		System.out.println("----------------------- Removing a meal of the week special offer -----------------------");
		
		//the client restaurant inserts credentials
		User user = myfoodora_service.selectUser("restaurant_1");
		user.logIn();
		
		
		
		if (user!=null && user instanceof Restaurant){
			Restaurant restaurant = (Restaurant)user;
			restaurant.getRestaurantService().displaySpecialMenu();
			Scanner s = new Scanner(System.in);
			System.out.println("Please enter the name of the meal to be removed from the meal-of-the-week list");
			String mealname = s.nextLine();
			restaurant.getRestaurantService().removeSpecialMeal(mealname);
			restaurant.getRestaurantService().displayMealMenu();
			s.close();
	}
	}
}
