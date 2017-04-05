package test.service;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import exceptions.UserNotFoundException;

import initialization.InitialScenario;

import model.myfoodora.*;
import model.restaurant.*;
import model.users.*;
import service.ManagerService;
import service.MyFoodoraService;
import service.impl.ManagerServiceImpl;
import service.impl.MyFoodoraServiceImpl;

public class MyFoodoraUseCaseTest {

	/**
	 * Use case scenario
	 * The following use case scenario describe examples of how the MyFoodora should function.
	 * @throws UserNotFoundException 
	 **/
	
	private MyFoodora myfoodora = MyFoodora.getInstance();
	private MyFoodoraService commonMyFoodoraService = new MyFoodoraServiceImpl();
	ManagerService managerService_director = new ManagerServiceImpl(new Manager("test","myfoodora","usecase"));
	
	
	/*
	 * Startup scenario
		1. the system loads all registered users: at least 2 manager (the CEO and his deputy
			i.e. adjoint), 5 restaurants and 2 couriers, 7 customers, 4 full-meals per restaurant...
		2. the system sends alerts to the customers that agreed to be notified of special offers
	 */
	@Before
	public void testStartupScenario() throws UserNotFoundException {
		
		System.out.println("----------------------- Startup scenario -----------------------");
		InitialScenario.load("init.ini");
		
		ArrayList<User> restaurants = myfoodora.getUsersOfAssignedType("RESTAURANT");
		for (User u:restaurants){
			System.out.println("\n-----"+((Restaurant)u).getName()+"-----");
			((Restaurant)u).getRestaurantService().displayMenu();
			((Restaurant)u).getRestaurantService().displayMealMenu();
		}
		myfoodora.displayUsers();
		commonMyFoodoraService.getHistory().displayAllOrders();
//		// send alerts to customers
//		commonMyFoodoraService.askAgree2customers("Do you agree to be notified of special offers ? By default it is no.");
//		customers.get(0).getCustomerService().giveConsensusBeNotifiedSpecialOffers();
//		customers.get(1).getCustomerService().giveConsensusBeNotifiedSpecialOffers();
//		customers.get(2).getCustomerService().giveConsensusBeNotifiedSpecialOffers();
//		customers.get(3).getCustomerService().giveConsensusBeNotifiedSpecialOffers();
//		customers.get(4).getCustomerService().removeConsensusBeNotifiedSpecialOffers();
//		customers.get(5).getCustomerService().removeConsensusBeNotifiedSpecialOffers();
//		customers.get(6).getCustomerService().removeConsensusBeNotifiedSpecialOffers();
//		
//		myFoodora.refreshMessageBoard();
//		customers.get(0).refreshMessageBoard();
//		customers.get(6).refreshMessageBoard();
//		
	}
	
	@Test
	public void historytest(){
		//history
		User restaurant_1 = commonMyFoodoraService.selectUser("restaurant_1");
		((Restaurant)restaurant_1).getHistory().displayAllOrders();
		((Restaurant)restaurant_1).getRestaurantService().DisplayMostOrderedAlaCarte();
		((Restaurant)restaurant_1).getRestaurantService().DisplayMostOrderedHalfMeal();

		ArrayList<Order> history =  commonMyFoodoraService.getHistory().getOrders();
		SortingByCriteria s = new SortingByRestaurant();
		s.displayAscending(history);
		managerService_director.getBestRestaurant();
		managerService_director.getWorstRestaurant();
		
		for (User u :commonMyFoodoraService.getUsersOfAssignedType("COURIER")){
			Courier c = (Courier)u;
			System.out.println("Courier <"+c.getName()+"> + has count: " + c.getCount());
		}
		managerService_director.getBestCourier();
		managerService_director.getWorstCourier();
		//		SortingByCriteria s = new SortingByHalfMeal();
//		s.displayDescending(MyFoodora.getInstance().getHistory().getOrders());
//		s = new SortingByAlaCarte();
//		s.displayAscending(MyFoodora.getInstance().getHistory().getOrders());
	}

	/*
	 * Register a user
		1. a user start using the system because she wants to register
		2. the user inserts his first-name, his last-name, his username, his address, his birth-
		date...
		3. the user starts inserting a contact info with the type and the value (e.g. email, phone)
		鈥�	 the user repeats step 3 since he ends to inserts his contact info
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
			System.out.println("Please sets your current duty status. By default off-duty. Y:on-duty N锛沷ff-duty");
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
			managerService_director.addUser(user);
			managerService_director.activateUser(user);
			managerService_director.displayActiveUsers();
		}else if(specify=="N"){
			
		}
	}
//	
//	/*
//	 * Login user
//		1. a user wants to login
//		2. the user inserts username and password
//		3. the system handles the login and presents to the user the available operations ac-
//		cording to his role
//	 */
//	@Test
	public User testOfLoginUser(String username){
		
		System.out.println("----------------------- Login User -----------------------");
		
		// the default password of all users is "password" now
		System.out.println("--- Login in  ---");
		Scanner s = new Scanner(System.in);
//		System.out.println("username = ");
//		String username = s.nextLine();
//		String username = "customer_1";
		System.out.println(username);
		System.out.println("password");
//		String password = s.nextLine();
		String password = "password";
		System.out.println(password);
			
		User theUser = null;
		for(User user : myfoodora.getUsers()){
			if( username.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword()) ){
				theUser = user;
				user.logIn();
				break;
//				user.logOut();
			}
		}
		
		return theUser;
	}
//	
//	/*
//	 * Ordering a meal
//		1. a client start using the system because she wants to order a meal
//		2. the client inserts his credentials (username and password)
//		3. the system recognizes the client and proposes the available restaurants
//		4. the client select a restaurant and compose an order either by selecting dishes a la
//		carte or by selecting meals from the restaurant menu. For each item in the order the
//		client specifies the quantity.
//		5. Once the order is completed the client selects the end
//		6. the system shows the summary of the ordered dishes and the total price of the order
//		taking into account the pricing rules
//	 */
//	@Test
	public void testOfOrderingMeal(){
		
		System.out.println("----------------------- Ordering a meal -----------------------");
		
		User user = testOfLoginUser("customer_1");
		if( user!=null && user instanceof Customer){
			managerService_director.displayUsersOfAssignedType("RESTAURANT");
			System.out.println("Please select a restaurant by username.");
			Scanner s = new Scanner(System.in);
//			String restaurant_username = s.nextLine();
			String restaurant_username = "restaurant_2";
			System.out.println(restaurant_username);
			Restaurant restaurant = null;
			if( (restaurant=(Restaurant)commonMyFoodoraService.selectUser(restaurant_username)) != null ){
				restaurant.getRestaurantService().displayMenu();
				restaurant.getRestaurantService().displayMealMenu();
				
				System.out.println("Please select meals/dishs by meal/dish name, end with '#' ");
				// here we just test meal
				String mealname = s.nextLine();
				while(!(mealname.equals("#"))){
					((Customer)user).getCustomerService().addStandardMealOrder(restaurant, "Full_meal", mealname);
					mealname = s.nextLine();
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
		鈥� the restaurant inserts the dishes of the meal
		鈥� the restaurant compute and save the price of the meal
		8. the restaurant saves the new created meal (or dish) in the menu
	 */
//	@Test
	public void testOfInsertMealDish2menu(){
		
		System.out.println("----------------------- Insert a meal/dish in a restaurant menu -----------------------");
		User user = testOfLoginUser("restaurant_1");
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
				restaurant.getRestaurantService().displayMealMenu();
			}
		//lacks the price mechanism, to be completed...
		}
	}
	

	
	/*
//	 * Adding a meal of the week special oer
//		1. a restaurant sta starts using the system and inserts the restaurant credentials
//		2. the system shows all restaurant's available meals
//		3. the restaurant selects the meal to be set as meal of the week
//		4. the system automatically updates the price of selected meal of the week, by application
//		of special discount factor
//		5. the system noties the users (that agreed to be notied of special oers) about the
//		new offer
//	 */
//	@Test
	public void testOfAddMealOfWeekSpecialOffer(){
		
		System.out.println("----------------------- Adding a meal of the week special offer -----------------------");
		User user = testOfLoginUser("restaurant_1");
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
			restaurant.getRestaurantService().notifyAll();
		}
	}
	
	/*
	 * Removing a meal of the week special oer
		1. a restaurant staff starts using the system and inserts the restaurant credentials
		2. the system shows all restaurant's available meals
		3. the restaurant selects a meal in the meal of the week list and selects the remove from
		its special offer state.
	 */
//	@Test
	public void testOfRemoveMealOfWeekSpecialOffer(){
		
		System.out.println("----------------------- Removing a meal of the week special offer -----------------------");
		System.out.println("----------------------- Adding a meal of the week special offer -----------------------");
		User user = testOfLoginUser("restaurant_1");
		if (user!=null && user instanceof Restaurant){
			Restaurant restaurant = (Restaurant)user;
			restaurant.getRestaurantService().displaySpecialMenu();
			Scanner s = new Scanner(System.in);
			System.out.println("Please enter the name of the meal to be removed from the meal-of-the-week list");
			String mealname = s.nextLine();
			restaurant.getRestaurantService().removeSpecialMeal(mealname);
	}
	}
}
