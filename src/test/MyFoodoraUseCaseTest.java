package test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import exceptions.UserNotFoundException;
import model.myfoodora.*;
import model.user.*;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class MyFoodoraUseCaseTest {

	/**
	 * Use case scenario
	 * The following use case scenario describe examples of how the MyFoodora should function.
	 * @throws UserNotFoundException 
	 **/
	private MyFoodora myFoodora = MyFoodora.getInstance();
	private MyFoodoraService commonMyFoodoraService = new MyFoodoraServiceImpl();
	
	
	/*
	 * Startup scenario
		1. the system loads all registered users: at least 2 manager (the CEO and his deputy
			i.e. ajoint), 5 restaurants and 2 couriers, 7 customers, 4 full-meals per restaurant...
		2. the system sends alerts to the customers that agreed to be notified of special offers
	 */
	@Before
	public void testStartupScenario() throws UserNotFoundException {
		
		System.out.println("----------------------- Startup scenario -----------------------");
	
		// managers 
		Manager ceo = new Manager("Ji", "raym", "raym");
		Manager director = new Manager("He", "xiaoan", "hxa");
		// restaurants
		Restaurant restaurant_1 = new Restaurant("Beijing Restaurant", "restaurant_1", new AddressPoint(1.0, 1.0));
		Restaurant restaurant_2 = new Restaurant("Paris Restaurant", "restaurant_2", new AddressPoint(2.0, 2.0));
		Restaurant restaurant_3 = new Restaurant("New York Restaurant", "restaurant_3", new AddressPoint(3.0, 3.0));
		Restaurant restaurant_4 = new Restaurant("Mosco Restaurant", "restaurant_4", new AddressPoint(4.0, 4.0));
		Restaurant restaurant_5 = new Restaurant("Tokyo Restaurant", "restaurant_5", new AddressPoint(5.0, 5.0));
		// couriers
		Courier courier_1 = new Courier("Zhang", "David", "courier_1", new AddressPoint(10.0, 10.0), "+33 1 01 01 01 01 01");
		Courier courier_2 = new Courier("Chen", "Vincent", "courier_2", new AddressPoint(11.0, 11.0), "+33 1 01 01 01 01 02");
		// customers
		Customer customer_1 = new Customer("Zhang", "San", "customer_1", new AddressPoint(100.0, 100.0), "zhangsan@gmail.com", "+33 1 01 01 02 01");
		Customer customer_2 = new Customer("Li", "Si", "customer_2", new AddressPoint(101.0, 101.0), "lisi@gmail.com", "+33 1 01 01 02 02");
		Customer customer_3 = new Customer("Wang", "Wu", "customer_3", new AddressPoint(102.0, 102.0), "wangwu@gmail.com", "+33 1 01 01 02 03");
		Customer customer_4 = new Customer("Zhao", "Liu", "customer_4", new AddressPoint(103.0, 103.0), "zhaoliu@gmail.com", "+33 1 01 01 02 04");
		Customer customer_5 = new Customer("Chen", "Qi", "customer_5", new AddressPoint(104.0, 104.0), "chenqi@gmail.com", "+33 1 01 01 02 05");
		Customer customer_6 = new Customer("Liang", "Ba", "customer_6", new AddressPoint(105.0, 105.0), "liangba@gmail.com", "+33 1 01 01 02 06");
		Customer customer_7 = new Customer("Chu", "Jiu", "customer_7", new AddressPoint(106.0, 106.0), "chujiu@gmail.com", "+33 1 01 01 02 07");

		// registering
		ceo.registerOnFoodora();
		director.registerOnFoodora();
		restaurant_1.registerOnFoodora();
		restaurant_2.registerOnFoodora();
		restaurant_3.registerOnFoodora();
		restaurant_4.registerOnFoodora();
		restaurant_5.registerOnFoodora();
		courier_1.registerOnFoodora();
		courier_2.registerOnFoodora();
		customer_1.registerOnFoodora();
		customer_2.registerOnFoodora();
		customer_3.registerOnFoodora();
		customer_4.registerOnFoodora();
		customer_5.registerOnFoodora();
		customer_6.registerOnFoodora();
		customer_7.registerOnFoodora();
		// activiting
		commonMyFoodoraService.activateUser(ceo);
		commonMyFoodoraService.activateUser(director);
		commonMyFoodoraService.activateUser(restaurant_1);
		commonMyFoodoraService.activateUser(restaurant_2);
		commonMyFoodoraService.activateUser(restaurant_3);
		commonMyFoodoraService.activateUser(restaurant_4);
		commonMyFoodoraService.activateUser(restaurant_5);
		commonMyFoodoraService.activateUser(courier_1);
		commonMyFoodoraService.activateUser(courier_2);
		commonMyFoodoraService.activateUser(customer_1);
		commonMyFoodoraService.activateUser(customer_2);
		commonMyFoodoraService.activateUser(customer_3);
		commonMyFoodoraService.activateUser(customer_4);
		commonMyFoodoraService.activateUser(customer_5);
		commonMyFoodoraService.activateUser(customer_6);
		commonMyFoodoraService.activateUser(customer_7);
		
		commonMyFoodoraService.displayUsers();
		commonMyFoodoraService.displayActiveUsers();
		
		restaurant_1.getRestaurantService().displayMenu();
		restaurant_1.getRestaurantService().displayMealMenu();
		
		// send alerts to customers
		commonMyFoodoraService.askAgree2customers("Are you agree to be notified of special offers ? By default it is no.");
		customer_1.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_2.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_3.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_4.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_5.getCustomerService().removeConsensusBeNotifiedSpecialOffers();
		customer_6.getCustomerService().removeConsensusBeNotifiedSpecialOffers();
		customer_7.getCustomerService().removeConsensusBeNotifiedSpecialOffers();
	}

	/*
	 * Register a user
		1. a user start using the system because she wants to register
		2. the user inserts his first-name, his last-name, his username, his address, his birth-
		date...
		3. the user starts inserting a contact info with the type and the value (e.g. email, phone)
		•	 the user repeats step 3 since he ends to inserts his contact info
		4. if the user is a customer she sets the agreement about the special offer contact (by
		default it is no)
		5. the user is a customer selects the contact to be used to send the offers (by default it
		is the e-mail if exists)
		6. if the user is a courier he sets his current duty status (default on-duty)
		7. the user specify to save the account
	 */
//	@Test
	public void testOfRegisterUser() throws UserNotFoundException{
		
		System.out.println("----------------------- Register a User -----------------------");
		
		User user = null;
		Scanner s = new Scanner(System.in);
		
		System.out.println("You want to register an acount of which type ? \n"
				+ "1. customer   2. restaurant  3. courier");	
		int userType = s.nextInt();
		System.out.println("Please input your personal infos : \n"
				+ "First name = ");
		String firstname = s.nextLine();
		System.out.println("last name = ");
		String lastname = s.nextLine();
		System.out.println("username = ");
		String username = s.nextLine();
		System.out.println("address = ");
		String address = s.nextLine();
		AddressPoint address_point = new AddressPoint(2.0, 2.0);
		System.out.println("birthdate = ");
		String birthdate = s.nextLine();
		
		System.out.println("email = ");
		String email = s.nextLine();
		System.out.println("phone = ");
		String phone = s.nextLine();
		
		switch (userType) {
		case 1:
			user = new Customer(firstname, lastname, username, address_point, email, phone);
			System.out.println("Are you agree to be notified of special offers ? By default it is no. Y/N");
			String agree = s.nextLine();
			if(agree=="Y"){
				((Customer)user).getCustomerService().giveConsensusBeNotifiedSpecialOffers();
			}else if(agree=="N"){
				((Customer)user).getCustomerService().removeConsensusBeNotifiedSpecialOffers();
			}
			System.out.println("Please select the contact to be used to send the offers. By default it is the e-mail if exist. 1: email 2:SMS ");
			int contactWay = s.nextInt();
			break;

		case 2:
				
			break;
		case 3:
			user = new Courier(firstname, lastname, username, address_point, phone);
			System.out.println("Please sets your current duty status. By default off-duty. Y:on-duty N；off-duty");
			String status = s.nextLine();
			if(status=="Y"){
				((Courier)user).getCourierService().turnOnDuty();
			}else if(status=="N"){
				((Courier)user).getCourierService().turnOffDuty();
			}
			break;
		default:
			break;
		}
		
		System.out.println("Save ? Y/N");
		String specify = s.nextLine();
		if(specify=="Y"){
			user.registerOnFoodora();
			commonMyFoodoraService.activateUser(user);
			commonMyFoodoraService.displayActiveUsers();
		}else if(specify=="N"){
			
		}
	}
	
	/*
	 * Login user
		1. a user wants to login
		2. the user inserts username and password
		3. the system handles the login and presents to the user the available operations ac-
		cording to his role
	 */
//	@Test
	public User testOfLoginUser(){
		
		System.out.println("----------------------- Login User -----------------------");
		
		// the default password of all users is "password" now
		System.out.println("--- Login in  ---");
		Scanner s = new Scanner(System.in);
		System.out.println("username = ");
		String username = s.nextLine();
		System.out.println("password = ");
		String password = s.nextLine();
			
		User theUser = null;
		for(User user : myFoodora.getUsers()){
			if( username.equals(user.getUsername()) && password.equals(user.getPassword()) ){
				theUser = user;
				user.logIn();
				break;
//				user.loginOut();
			}
		}
		
		return theUser;
	}
	
	/*
	 * Ordering a meal
		1. a client start using the system because she wants to order a meal
		2. the client inserts his credentials (username and password)
		3. the system recognizes the client and proposes the available restaurants
		4. the client select a restaurant and compose an order either by selecting dishes a la
		carte or by selecting meals from the restaurant menu. For each item in the order the
		client specifies the quantity.
		5. Once the order is completed the client selects the end
		6. the system shows the summary of the ordered dishes and the total price of the order
		taking into account the pricing rules
	 */
	@Test
	public void testOfOrderingMeal(){
		
		System.out.println("----------------------- Ordering a meal -----------------------");
		
		User user = testOfLoginUser();
		if( user!=null && user instanceof Customer){
			commonMyFoodoraService.displayUsersOfAssignedType("RESTAURANT");
			System.out.println("Please select a restaurant by username.");
			Scanner s = new Scanner(System.in);
			String restaurant_username = s.nextLine();
			Restaurant restaurant = null;
			if( (restaurant=(Restaurant)commonMyFoodoraService.selectUser(restaurant_username)) != null ){
				restaurant.getRestaurantService().displayMenu();
				restaurant.getRestaurantService().displayMealMenu();
				
				System.out.println("Please select meals/dishs by meal/dish name, end with '#' ");
				// here we just test meal
				String mealname = s.nextLine();
				while(mealname!="#"){
					((Customer)user).getCustomerService().addStandardMealOrder(restaurant, "Full_meal", mealname);
				}
				((Customer)user).getCustomerService().pay();
				((Customer)user).getCustomerService().clearShoppingCart();
			}
		}
	}
	
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
		• the restaurant inserts the dishes of the meal
		• the restaurant compute and save the price of the meal
		8. the restaurant saves the new created meal (or dish) in the menu
	 */
	public void testOfInsertMealDish2menu(){
		
		System.out.println("----------------------- Insert a meal/dish in a restaurant menu -----------------------");
	}
	
	/*
	 * Adding a meal of the week special oer
		1. a restaurant sta starts using the system and inserts the restaurant credentials
		2. the system shows all restaurant's available meals
		3. the restaurant selects the meal to be set as meal of the week
		4. the system automatically updates the price of selected meal of the week, by application
		of special discount factor
		5. the system noties the users (that agreed to be notied of special oers) about the
		new offer
	 */
	public void testOfAddMealOfWeekSpecialOffer(){
		
		System.out.println("----------------------- Adding a meal of the week special offer -----------------------");
	}
	
	/*
	 * Removing a meal of the week special oer
		1. a restaurant staff starts using the system and inserts the restaurant credentials
		2. the system shows all restaurant's available meals
		3. the restaurant selects a meal in the meal of the week list and selects the remove from
		its special offer state.
	 */
	public void testOfRemoveMealOfWeekSpecialOffer(){
		
		System.out.println("----------------------- Removing a meal of the week special offer -----------------------");
	}
}
