package test.usecase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import exceptions.UserNotFoundException;

import initialization.InitialScenario;
import restaurant.*;
import system.*;
import user.*;

/**
 * Use case scenario
 * The following use case scenario describe examples of how the MyFoodora should function.
 * @throws UserNotFoundException 
 * 
 * @author Ray
 * @author Hxa
 **/

//* Ordering a meal
//	1. a client start using the system because she wants to order a meal
//	2. the client inserts his credentials (username and password)
//	3. the system recognizes the client and proposes the available restaurants
//	4. the client select a restaurant and compose an order either by selecting dishes a la
//	carte or by selecting meals from the restaurant menu. For each item in the order the
//	client specifies the quantity.
//	5. Once the order is completed the client selects the end
//	6. the system shows the summary of the ordered dishes and the total price of the order
//	taking into account the pricing rules
//*/

public class OrderingAMealTest {

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
	public void testOfOrderingMeal(){
		
		System.out.println("----------------------- Ordering a meal -----------------------");
		
		
		//the client inserts credentials
		User user = myfoodora_service.selectUser("customer_1");
		user.logIn();
		
		
		//
		if( user!=null && user instanceof Customer){
			managerService_director.displayUsersOfAssignedType("RESTAURANT");
			System.out.println("Please select a restaurant by username.");
			Scanner s = new Scanner(System.in);
			s.nextLine();
			String restaurant_username = "restaurant_2";
			System.out.println(restaurant_username);
			Restaurant restaurant = null;
			if( (restaurant=(Restaurant)myfoodora_service.selectUser(restaurant_username)) != null ){
				restaurant.getRestaurantService().displayMenu();
				restaurant.getRestaurantService().displayMealMenu();
				
				System.out.println("Please select 1 meal type : Half-meal, Full-meal");
				String mealType = s.nextLine();
				
				System.out.println("Please enter 1 meal name corresponding the type specified:");
				String mealname = s.nextLine();
				while(!(mealname.equals("#"))){
					((Customer)user).getCustomerService().addStandardMealOrder(restaurant, mealType, mealname);
					mealname = s.nextLine();
				}
				((Customer)user).getCustomerService().pay();
				System.out.println("Session ended.");
			}
			s.close();
		}
	}
}
