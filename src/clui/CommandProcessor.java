package clui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import exceptions.NameNotFoundException;
import exceptions.PermissionException;
import exceptions.DishTypeErrorException;
import exceptions.LoginErrorException;
import exceptions.NameNotFoundException;
import exceptions.NameAlreadyExistsException;
import exceptions.NameNotFoundException;
import exceptions.SyntaxErrorException;
import exceptions.NameNotFoundException;
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
			 case "acceptCall":
				 acceptCall();
				 break;
			 case "refuseCall":
				 refuseCall();
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
			 default: 
				 throw new SyntaxErrorException();
			 }
		 }
		 catch (SyntaxErrorException e){
			 System.out.println(rawInput);
			 e.printError();
		 }
		 catch (PermissionException e){
			 e.printError();
		 }
}


	/**
	 * login "username" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void login() throws SyntaxErrorException{
		if (arguments.length<2){
			throw new SyntaxErrorException();
		}
		String username = arguments[0];
		String password = arguments[1];
		MyFoodoraService m = new MyFoodoraServiceImpl();
		try{
		m.login(username,password);
		user = m.selectUser(username);
		System.out.println("Welcome on MyFoodora, user "+username+". Please enter a command.");
		}
		catch (LoginErrorException e){
			e.printError();
		}
	}

	/**
	 * Logout.
	 */
	public void logout() {
		user.logOut();
		System.out.println("Goodbye "+user.getUsername()+". We hope to see you again on MyFoodora.");
	}
	
	/**
	 * registerRestaurant "name" "address" "username" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void registerRestaurant() throws SyntaxErrorException, PermissionException{
		if (arguments.length<4){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String name = arguments[0];
		String[] parts = arguments[1].split(",");
		AddressPoint address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String userName = arguments[2];
		String password = arguments[3];
		Manager manager = (Manager)user;
		manager.getManagerService().addUser(new Restaurant(name,userName,address,password));
		System.out.println("Restaurant "+userName+" has been registered on myfoodora.");
	}
	
	/**
	 * registerCustomer "firstname" "lastname" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void registerCustomer() throws SyntaxErrorException, PermissionException {
		if (arguments.length<5){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String firstName = arguments[0];
		String lastName = arguments[1];
		String userName = arguments[2];
		String[] parts = arguments[3].split(",");
		AddressPoint address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String password = arguments[4];
		Manager manager = (Manager)user;
		manager.getManagerService().addUser(new Customer(firstName,lastName,userName,address,password));
		System.out.println("Customer "+userName+" has been registered on myfoodora.");
	}

	/**
	 * registerCourier "firstname" "lastname" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void registerCourier() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=5){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String firstName = arguments[0];
		String lastName = arguments[1];
		String userName = arguments[2];
		String[] parts = arguments[3].split(",");
		AddressPoint position = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String password = arguments[4];
		Manager manager = (Manager)user;
		manager.getManagerService().addUser(new Courier(firstName,lastName,userName,position,password));
		System.out.println("Courier "+userName+" has been registered on myfoodora.");
	}

	/**
	 * addDishRestaurantMenu  "dishName" "dishCategory" "foodCategory" "unitPrice".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void addDishRestaurantMenu() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=4){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String dishName = arguments[0];
		String dishCategory = arguments[1];
		String foodCategory = arguments[2];
		double unitPrice = Double.parseDouble(arguments[3]);
		Restaurant restaurant = (Restaurant)user;
		restaurant.getRestaurantService().addDish(dishName,dishCategory,foodCategory,unitPrice);
		
		System.out.println(dishName+" has been added to the menu of "+user.getUsername());
	}
	
	/**
	 * createMeal "mealName" .
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void createMeal() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");

		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().createMeal(mealName);
			
			System.out.println("Meal " + mealName+" has been created");

		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}

	/**
	 * addDish2Meal "dishName" "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void addDish2Meal() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String dishName = arguments[0];
		String mealName = arguments[1];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().addDish2Meal(dishName, mealName);
			
			System.out.println("Dish " + dishName+" has been added to the meal "+mealName);

		} catch (NameNotFoundException e){e.printError();
		} catch (DishTypeErrorException e) {e.printError();
		}
	}
	
	/**
	 * showMeal "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void showMeal() throws SyntaxErrorException, PermissionException {
		if (arguments.length<1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().showMeal(mealName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * saveMeal "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void saveMeal() throws SyntaxErrorException, PermissionException {
		if (arguments.length<1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().saveMeal(mealName);
			
			System.out.println("Meal" + mealName+" has been saved and added to the meal-menu of "+restaurant.getName());
		} catch (NameNotFoundException | DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * setSpecialOffer "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void setSpecialOffer() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length<1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getRestaurantService().setSpecialOffer(mealName);
			
			System.out.println("Meal " + mealName+" saved as a special-offer of "+restaurant.getName());
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}
	
	/**
	 * removeFromSpecialOffer "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void removeFromSpecialOffer() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length<1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		restaurant.getRestaurantService().removeSpecialOffer(mealName);
		System.out.println("Meal " + mealName+" removed from the list of special-offers of "+restaurant.getName());

	}

	/**
	 * createOrder "restaurantName" "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void createOrder() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		String restaurantName = arguments[0];
		String orderName = arguments[1];
		Customer customer = (Customer)user;
		customer.getCustomerService().createOrder(restaurantName, orderName);
		
		System.out.println("Order " + orderName+" added to the shopping cart.");

	}

	/**
	 * addItem2Order "orderName" "itemName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void addItem2Order() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		String orderName = arguments[0];
		String itemName = arguments[1];
		Customer customer = (Customer)user;
		try {
			customer.getCustomerService().addItem2Order(orderName, itemName);
			
			System.out.println("Item " + itemName+" added to the order "+orderName);

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}

	/**
	 * endOrder "orderName" "date".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void endOrder() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=2){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		String orderName = arguments[0];
		String date = arguments[1];
		Customer customer = (Customer)user;
		try {
			customer.getCustomerService().endOrder(orderName, date);
			
			System.out.println("Order " + orderName+" finalised at "+date+ " and you paid for it.");
		} catch (NameNotFoundException e){e.printError();
		} catch (ParseException e) {e.printStackTrace();
		}
	}

	/**
	 * onDuty "".
	 *
	 * @throws PermissionException the permission exception
	 */
	public void onDuty() throws PermissionException {
		// TODO Auto-generated method stub
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		Courier courier = (Courier)user;
		courier.getCourierService().turnOnDuty();
		System.out.println("Current state set as on-duty.");
		
	}

	/**
	 * 	 offDuty "".
	 *
	 * @throws PermissionException the permission exception
	 */
	public void offDuty() throws PermissionException {
		// TODO Auto-generated method stub
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		Courier courier = (Courier)user;
		courier.getCourierService().turnOffDuty();
		System.out.println("Current state set as off-duty.");

	}

	/**
	 *  acceptCall "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void acceptCall() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		String orderName = arguments[0];
		Courier courier = (Courier)user;
		courier.getCourierService().acceptCall(orderName);
		System.out.println("courier "+courier.getName()+" accepts to take the order.");
	}
	
	/**
	 * Refuse call.
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void refuseCall() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length!=1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		String orderName = arguments[0];
		Courier courier = (Courier)user;
		System.out.println("courier "+courier.getName()+" refuses to take the order. A new courier is being assigned.");
		courier.getCourierService().refuseCall(orderName);
	}


	/**
	 * findDeliverer "orderName".
	 */
	public void findDeliverer() {
		// TODO Auto-generated method stub
	}

	/**
	 * setDeliveryPolicy  "delpolicy".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void setDeliveryPolicy() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length<1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String deliveryPolicy = arguments[0];
		Manager manager = (Manager)user;
		manager.getManagerService().setDeliveryPolicy(deliveryPolicy);
	}

	/**
	 * setProfitPolicy  "ProfitPolicyName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void setProfitPolicy() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length<1){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String ProfitPolicyName = arguments[0];
		Manager manager = (Manager)user;
		manager.getManagerService().setTargetProfitPolicy(ProfitPolicyName);
	}

	/**
	 * associateCard "userName" "cardType".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	public void associateCard() throws SyntaxErrorException, PermissionException {
		// TODO Auto-generated method stub
		if (arguments.length<2){
			throw new SyntaxErrorException();
		}
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String userName = arguments[0];
		String cardType = arguments[1];
		Manager manager = (Manager)user;
		try {
			manager.getManagerService().associateCard(userName, cardType);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printError();
		}

	}
	
	/**
	 * showCourierDeliveries "".
	 *
	 * @throws PermissionException the permission exception
	 */
	public void showCourierDeliveries() throws PermissionException {
		// TODO Auto-generated method stub
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		manager.getManagerService().showCourierDesc();
	}

	/**
	 * showRestaurantTop "".
	 *
	 * @throws PermissionException the permission exception
	 */
	public void showRestaurantTop() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		// TODO Auto-generated method stub
		Manager manager = (Manager)user;
		manager.getManagerService().showRestaurantDesc();
	}

	/**
	 * showCustomers.
	 *
	 * @throws PermissionException the permission exception
	 */
	public void showCustomers() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		// TODO Auto-generated method stub
		Manager manager = (Manager)user;
		manager.getManagerService().displayUsersOfAssignedType("customer");
	}

	/**
	 * showMenuItem "restaurant-name".
	 *
	 * @throws PermissionException the permission exception
	 */
	public void showMenuItem() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String restaurant_name = arguments[0];
		Manager manager = (Manager)user;
		try {
			Restaurant restaurant = (Restaurant) manager.getManagerService().selectUser(restaurant_name);
			restaurant.getRestaurantService().displayAllMenu();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
		
	}

	/**
	 * showTotalProfit "".
	 *
	 * @throws PermissionException the permission exception
	 */
	public void showTotalProfit() throws PermissionException {
		// TODO Auto-generated method stub
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		try{
		if (arguments==null){
			String startingDate = "01/01/2017";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String endingDate = sdf.format(new Date());
			System.out.println("Total profit : "+manager.getManagerService().getTotalProfit(startingDate, endingDate)+" euros.");
		}
		else if (arguments.length==2){
			String startingDate = arguments[0];
			String endingDate = arguments[1];
			System.out.println("Total profit : "+manager.getManagerService().getTotalProfit(startingDate, endingDate)+" euros.");
		}
		}
		catch (ParseException e){
			System.out.println("DATE FORMAT ERROR : please enter date formated as dd/MM/YYYY");
		}
	}

	/**
	 * runtest "testScenario1.txt"
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	public void runTest() throws SyntaxErrorException {
		if (arguments.length<1) throw new SyntaxErrorException();
		String testScenarioN = arguments[0];
		String[] parts = testScenarioN.split("\\.");
		String testScenarioNoutput = parts[0]+"outut"+parts[1];
		CustomPrintStream.setOutputStream(testScenarioNoutput);
		
		CommandProcessor c = new CommandProcessor();
		
		File file = new File(testScenarioN);
		Scanner s;
		try {
			s = new Scanner(file);
			String rawInput = "";
			while (s.hasNextLine()){
				rawInput = s.nextLine();
				c.processCommand(rawInput);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Help.
	 */
	public void help() {
		// TODO Auto-generated method stub
		
	}
}