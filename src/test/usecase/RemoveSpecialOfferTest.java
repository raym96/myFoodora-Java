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
 * Removing a meal of the week special oer
	1. a restaurant staff starts using the system and inserts the restaurant credentials
	2. the system shows all restaurant's available meals
	3. the restaurant selects a meal in the meal of the week list and selects the remove from
	its special offer state.
 */

public class RemoveSpecialOfferTest {

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
	 * Test of remove meal of week special offer.
	 */
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
			restaurant.getRestaurantService().removeSpecialOffer(mealname);
			restaurant.getRestaurantService().displayMealMenu();
			s.close();
	}
	}
}
