package clui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.LoginErrorException;
import exceptions.MealNotFoundException;
import exceptions.NameAlreadyExistsException;
import exceptions.OrderNotFoundException;
import exceptions.SyntaxErrorException;
import exceptions.UserNotFoundException;
import system.AddressPoint;
import user.model.*;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;

/**
 * The Class CommandProcessor.
 */
public class CommandProcessor{

 	/** The user. */
 	User user;
	 
	 /** The command. */
 	Command command;
	 
 	/** The cmd. */
 	String cmd;
	 
 	/** The arguments. */
 	String[] arguments;
	 

	/**
 	 * Process command.
 	 *
 	 * @param rawInput the raw input
 	 * @throws SyntaxErrorException the syntax error exception
 	 */
 	public void processCommand(String rawInput){
		 Command command = new Command(rawInput);
		 cmd = command.getCommand();
		 arguments = command.getArguments();
		 
		 try{
			 switch (cmd.toLowerCase()){
			 case "login":
				 login();
				 break;
			 case "logout":
				 logout();
				 break;
			 case "registerrestaurant":
				 registerRestaurant();
				 break;
			 case "registercustomer":
				 registerCustomer();
				 break;
			 case "registercourier":
				 registerCourier();
				 break;
			 case "adddishrestaurantmenu":
				 addDishRestaurantMenu();
				 break;
			 case "createmeal":
				 createMeal();
				 break;
			 case "adddish2meal":
				 addDish2Meal();
				 break;
			 case "showmeal":
				 showMeal();
				 break;
			 case "savemeal":
				 saveMeal();
				 break;
			 case "setspecialoffer":
				 setSpecialOffer();
				 break;
			 case "removefromspecialoffer":
				 removeFromSpecialOffer();
				 break;
			 case "createorder":
				 createOrder();
				 break;
			 case "additem2order":
				 addItem2Order();
				 break;
			 case "endorder":
				 endOrder();
				 break;
			 case "onduty":
				 onDuty();
				 break;
			 case "offduty":
				 offDuty();
				 break;
			 case "finddeliverer":
				 findDeliverer();
				 break;
			 case "setdeliverypolicy":
				 setDeliveryPolicy();
				 break;
			 case "setprofitpolicy":
				 setProfitPolicy();
				 break;
			 case "associatecard":
				 associateCard();
				 break;
			 case "showcourierdeliveries":
			 	showCourierDeliveries();
				 break;
			 case "showrestauranttop":
			 	showRestaurantTop();
				 break;
			 case "showcustomers":
				 showCustomers();
				 break;
			 case "showmenuitem":
				 showMenuItem();
				 break;
			 case "showtotalprofit":
				 showTotalProfit();
				 break;
			 case "runtest":
				 runTest();
				 break;
			 case "help":
				 help();
				 break;
			 }
		 }
		 catch (SyntaxErrorException e){
			 e.printStackTrace();
		 }
}





	/**
	 * login "username" "password"
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void login() throws SyntaxErrorException{
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		String username = arguments[0];
		String password = arguments[1];
		MyFoodoraService m = new MyFoodoraServiceImpl();
		try{
		m.login(username,password);
		user = m.selectUser(username);
		System.out.println("user "+username+" logged on myfoodora.");
		}
		catch (LoginErrorException e){}
	}

	/**
	 * Logout.
	 */
	public void logout() {
		user.logOut();
		System.out.println("user "+user.getUsername()+" logged out from myfoodora.");
	}
	
	/**
	 * registerRestaurant "name" "address" "username" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void registerRestaurant() throws SyntaxErrorException{
		if (arguments.length!=4){
			throw new SyntaxErrorException();
		}
		String name = arguments[0];
		String[] parts = arguments[1].split(",");
		AddressPoint address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String userName = arguments[2];
		String password = arguments[3];
		Manager manager = (Manager)user;
		manager.getManagerService().addUser(new Restaurant(name,userName,address,password));
	}
	
	/**
	 * registerCustomer "firstname" "lastname" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void registerCustomer() throws SyntaxErrorException {
		if (arguments.length!=5){
			throw new SyntaxErrorException();
		}
		String firstName = arguments[0];
		String lastName = arguments[1];
		String userName = arguments[2];
		String[] parts = arguments[3].split(",");
		AddressPoint address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String password = arguments[4];
		Manager manager = (Manager)user;
			manager.getManagerService().addUser(new Customer(firstName,lastName,userName,address,password));
	}

	/**
	 * registerCourier "firstname" "lastname" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void registerCourier() throws SyntaxErrorException {
		if (arguments.length!=5){
			throw new SyntaxErrorException();
		}
		String firstName = arguments[0];
		String lastName = arguments[1];
		String userName = arguments[2];
		String[] parts = arguments[3].split(",");
		AddressPoint position = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String password = arguments[4];
		Manager manager = (Manager)user;
		manager.getManagerService().addUser(new Courier(firstName,lastName,userName,position,password));		
	}

	/**
	 * addDishRestaurantMenu  "dishName" "dishCategory" "foodCategory" "unitPrice".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void addDishRestaurantMenu() throws SyntaxErrorException {
		if (arguments.length!=4){
			throw new SyntaxErrorException();
		}
		String dishName = arguments[0];
		String dishCategory = arguments[1];
		String foodCategory = arguments[2];
		double unitPrice = Double.parseDouble(arguments[3]);
		Restaurant restaurant = (Restaurant)user;
		restaurant.getRestaurantService().addDish(dishName,dishCategory,foodCategory,unitPrice);
	}
	
	/**
	 * createMeal "mealName" .
	 * @throws SyntaxErrorException 
	 */
	public void createMeal() throws SyntaxErrorException {
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().createMeal(mealName);
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * addDish2Meal "dishName" "mealName"
	 * @throws SyntaxErrorException 
	 */
	public void addDish2Meal() throws SyntaxErrorException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		String dishName = arguments[0];
		String mealName = arguments[1];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().addDish2Meal(dishName, mealName);
		} catch (DishNotFoundException | MealNotFoundException | DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * showMeal "mealName"
	 * @throws SyntaxErrorException 
	 */
	public void showMeal() throws SyntaxErrorException {
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().showMeal(mealName);
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * saveMeal "mealName"
	 * @throws SyntaxErrorException 
	 */
	public void saveMeal() throws SyntaxErrorException {
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().saveMeal(mealName);
		} catch (MealNotFoundException | DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * setSpecialOffer "mealName"
	 * @throws SyntaxErrorException 
	 */
	public void setSpecialOffer() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().addSpecialMeal(mealName);
		} catch (MealNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * removeFromSpecialOffer "mealName"
	 * @throws SyntaxErrorException 
	 */
	public void removeFromSpecialOffer() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		restaurant.getRestaurantService().removeSpecialMeal(mealName);

	}

	/**
	 * createOrder "restaurantName" "orderName"
	 * @throws SyntaxErrorException 
	 */
	public void createOrder() throws SyntaxErrorException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		String restaurantName = arguments[0];
		String orderName = arguments[1];
		Customer customer = (Customer)user;
		customer.getCustomerService().createOrder(restaurantName, orderName);
	}

	/**
	 * addItem2Order "orderName" "itemName"
	 * @throws SyntaxErrorException 
	 */
	public void addItem2Order() throws SyntaxErrorException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		String orderName = arguments[0];
		String itemName = arguments[1];
		Customer customer = (Customer)user;
		try {
			customer.getCustomerService().addItem2Order(orderName, itemName);
		} catch (OrderNotFoundException | MealNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * endOrder "orderName" "date"
	 * @throws SyntaxErrorException 
	 */
	public void endOrder() throws SyntaxErrorException {
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String orderName = arguments[0];
		String date = arguments[1];
		Customer customer = (Customer)user;
		try {
			customer.getCustomerService().endOrder(orderName, date);
		} catch (OrderNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * onDuty ""
	 */
	public void onDuty() {
		// TODO Auto-generated method stub
		Courier courier = (Courier)user;
		courier.getCourierService().turnOnDuty();
	}

	/**
	 offDuty ""
	 */
	public void offDuty() {
		// TODO Auto-generated method stub
		Courier courier = (Courier)user;
		courier.getCourierService().turnOffDuty();
	}

	/**
	 * findDeliverer "orderName"
	 */
	public void findDeliverer() {
		// TODO Auto-generated method stub
	}

	/**
	 * setDeliveryPolicy  "delpolicy".
	 * @throws SyntaxErrorException 
	 */
	public void setDeliveryPolicy() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String deliveryPolicy = arguments[0];
		Manager manager = (Manager)user;
		manager.getManagerService().setDeliveryPolicy(deliveryPolicy);
	}

	/**
	 * setProfitPolicy  "ProfitPolicyName"
	 * @throws SyntaxErrorException 
	 */
	public void setProfitPolicy() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		String ProfitPolicyName = arguments[0];
		Manager manager = (Manager)user;
		manager.getManagerService().setTargetProfitPolicy(ProfitPolicyName);
	}

	/**
	 * associateCard "userName" "cardType"
	 * @throws SyntaxErrorException 
	 */
	public void associateCard() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		String userName = arguments[0];
		String cardType = arguments[1];
		Manager manager = (Manager)user;
		try {
			manager.getManagerService().associateCard(userName, cardType);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
		}

	}
	/**
	 * showCourierDeliveries ""
	 */
	public void showCourierDeliveries() {
		// TODO Auto-generated method stub
		Manager manager = (Manager)user;
		manager.getManagerService().getBestCourier();
	}

	/**
	 * showRestaurantTop ""
	 */
	public void showRestaurantTop() {
		// TODO Auto-generated method stub
		Manager manager = (Manager)user;
		manager.getManagerService().getBestRestaurant();
	}

	/**
	 * showCustomers
	 */
	public void showCustomers() {
		// TODO Auto-generated method stub
		Manager manager = (Manager)user;
		manager.getManagerService().displayUsersOfAssignedType("customer");
	}

	/**
	 * showMenuItem "restaurant-name"
	 */
	public void showMenuItem() {
		String restaurant_name = arguments[0];
		Manager manager = (Manager)user;
		try {
			Restaurant restaurant = (Restaurant) manager.getManagerService().selectUser(restaurant_name);
			restaurant.getRestaurantService().displayAllMenu();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * showTotalProfit ""
	 */
	public void showTotalProfit() {
		// TODO Auto-generated method stub
		Manager manager = (Manager)user;
		if (arguments.length==0){
			manager.getManagerService().getTotalProfit(new Date(), new Date());
		}
	}

	/**
	 * Run test.
	 */
	public void runTest() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Help.
	 */
	public void help() {
		// TODO Auto-generated method stub
		
	}
}