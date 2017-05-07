package clui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import system.Order;
import user.model.*;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;

/**
 * The Class CommandProcessor.
 * @author He Xiaoan
 * @author Ji Raymond
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
	 * Start.
	 */
	public void start(){
 		System.out.println("Welcome to MyFoodora. Please login/register before executing any command."
				+ " For more information, enter \"help\".");
		System.out.println("To exit system, enter \"#end\"\n");
		
		Scanner s = new Scanner(System.in);
		String rawInput = s.nextLine();
		while (!(rawInput.equalsIgnoreCase("#end"))){
			System.out.println(rawInput);
			this.processCommand(rawInput);
			System.out.println();
			rawInput = s.nextLine();
		}
		System.out.println("Session ended. Goodbye. Press F11 to launch again.");
		s.close();
 	}
	
 	/**
	  * Process command.
	  *
	  * @param rawInput the raw input
	  */
 	public void processCommand(String rawInput){
 		Scanner s = new Scanner(rawInput);
		while (s.hasNextLine()){
			String lineInput = s.nextLine();
			processLine(lineInput);
		}
		s.close();
 	}
 	
 
	/**
	 * Process command.
	 *
	 * @param lineInput the raw input
	 */
 	public void processLine(String lineInput){
		 Command command = new Command(lineInput);
		 cmd = command.getCommand();
		 arguments = command.getArguments();
		 
		 try{
			 switch (cmd.toLowerCase()){
			 //without logging in
			 case "login":
				 login();
				 break;
			 case "register":
				 register();
				 break;
			 case "runtest":
				 runTest();
				 break;
			 case "help":
				 help();
				 break;
			// ALL USERS
			 case "logout":
				 logout();
				 break;
			 case "unregister":
				 unregister();
				 break;
			 case "showinfo":
				 showInfo();
				 break;
			 case "showrestaurantmenus":
				 showRestaurantMenus();
				 break;
			 case "shownewmessages":
				 showNewMessages();
				 break;
			 case "showmessages":
				 showAllMessages();
				 break;
			 case "showhistory":
				 showHistory();
				 break;
			// MANAGER
			 case "registermanager":
				 registerManager();
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
			 case "removeuser":
				 removeUser();
				 break;
			 case "setservicefee":
				 setServiceFee();
				 break;
			 case "setdeliverycost":
				 setDeliveryCost();
				 break;
			 case "setmarkuppercentage":
				 setMarkUpPercentage();
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
			 case "showtotalincome":
				 showTotalIncome();
				 break;
			 case "showaverageincome":
				 showAverageIncomePerCustomer();
				 break;
			 case "applyprofitpolicy":
				 applyTargetProfitPolicy();
				 break;
			 case "showusers":
				 showUsers();
				 break;
			 case "showactiveusers":
				 showActiveUsers();
				 break;
			 case "showpolicies":
				 showPolicies();
				 break;
			 case "showsystemvalues":
				 showSystemValues();
				 break;
			// RESTAURANT
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
			 case "removemeal":
				 removeMeal();
				 break;
			 case "removedish":
				 removeDish();
				 break;
			 case "setspecialoffer":
				 setSpecialOffer();
				 break;
			 case "removefromspecialoffer":
				 removeFromSpecialOffer();
				 break;
			 case "setgenericdiscountfactor":
				 setGenericDiscountFactor();
				 break;
			 case "setspecialdiscountfactor":
				 setSpecialDiscountFactor();
				 break;
			 case "showhalfmeal":
				 showHalfMeal();
				 break;
			 case "showalacarte":
				 showAlaCarte();
				 break;
			 case "showmenu":
				 showMyMenu();
				 break;
			 case "showdiscountfactor":
				 showDiscountFactor();
				 break;
			 case "finddeliverer":
				 findDeliverer();
				 break;
			 case "showincome":
				 showIncome();
				 break;
			//CUSTOMER
			 case "createorder":
				 createOrder();
				 break;
			 case "additem2order":
				 addItem2Order();
				 break;
			 case "endorder":
				 endOrder();
				 break;
			 case "registercard":
				 registerCard();
				 break;
			 case "unregistercard":
				 unregisterCard();
				 break;
			 case "showpoints":
				 showPoints();
				 break;
			 case "showshoppingcart":
				 showShoppingCart();
				 break;
			 case "showspecialoffers":
				 showSpecialOffers();
				 break;
			 case "turnonnotification":
				 turnOnNotification();
				 break;
			 case "turnoffnotification":
				 turnOffNotification();
				 break;
			//COURIER
			 case "setonduty":
				 setOnDuty();
				 break;
			 case "setoffduty":
				 setOffDuty();
				 break;
			 case "changeposition":
				 changePosition();
				 break;
			 case "acceptcall":
				 acceptCall();
				 break;
			 case "refusecall":
				 refuseCall();
				 break;
			 case "showwaiting":
				 showWaitingOrders();
				 break;
			 case "showcount":
				 showCount();
				 break;
			 default: 
				 throw new SyntaxErrorException();
			 }
		 }
		 catch (SyntaxErrorException e){
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
	private void login() throws SyntaxErrorException{
		if (!(user==null)){
			System.out.println("You already logged on as an user. Please log out first.");
			return;
		}
		if (arguments.length<2)throw new SyntaxErrorException(2);
		String username = arguments[0];
		String password = arguments[1];
		MyFoodoraService m = MyFoodora.getInstance().getService();
		try{
			m.login(username,password);
			user = m.selectUser(username);
			if (user instanceof Restaurant){
				((Restaurant)user).getService().showMenu();
			}
			if (user instanceof Customer){
				MyFoodora.getInstance().getService().showUsersOfAssignedType("Restaurant");
			}
			
			System.out.println("Welcome on MyFoodora, user <"+username+">. ");
			if (user.getMessageBoard().getHasUnreadMessages()){
				System.out.println("You have new messages.");
			}
			if (user instanceof Courier){
				Courier courier = (Courier)user;
				if (courier.getWaitingOrders().size()!=0){
					System.out.println("You have new waiting delivery tasks.");
				}
			}
			System.out.println("Please enter a command. Enter \"help\" "
					+ "to get the list of available commands.");
		}
		catch (LoginErrorException e){
			e.printError();
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * logout <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void logout() throws PermissionException {
		if (user == null) throw new PermissionException("user");
		user.logOut();
		System.out.println("Goodbye <"+user.getUsername()+">. We hope to see you again on MyFoodora.");
		user = null;
	}
	

	/**
	 * register <>.
	 */
	private void register() {
		if (!(user == null)){
			System.out.println("You already logged on as an user. Please log out first.");
			return;
		}
		
		Scanner scan = new Scanner(System.in);
		User newUser = null;
		
		System.out.println("You want to register an acount of which type ? \n"
				+ "1. restaurant   2. customer  3. courier");	
		String userTypeString = scan.nextLine();
		int userType = Integer.parseInt(userTypeString);
		
		
		System.out.println("Please choose your username : \n"
				+"username = ");
		String username = scan.nextLine();
		while (MyFoodora.getInstance().hasUser(username)){
			System.out.println("Username "+username+" already exists. Please choose another username.\n"
					+ "username = ");
			username = scan.nextLine();
		}
		
		
		System.out.println("Please choose your password : \n"
				+"password = ");
		String password = scan.nextLine();
		
	
		System.out.println("Please enter your address/position: example 2.0,1.5 \n"
				+"address/position = ");
		String addressString = scan.nextLine();
		AddressPoint address = new AddressPoint(addressString);
		
		if (userType==1){
			System.out.println("Enter your name");
			String name = scan.nextLine();
			newUser = new Restaurant(name,username,address,password);
		}
		if (userType==2||userType==3){
			System.out.println("Enter your firstname");
			String firstname = scan.nextLine();
			System.out.println("Enter your lastname");
			String lastname = scan.nextLine();
			if (userType==2) newUser = new Customer(firstname,lastname,username,address,password);
			if (userType==3) newUser = new Courier(firstname,lastname,username,address,password);
		}
		
		String email="";
		String phone="";
		System.out.println("Please enter your contact info. Enter # when you have finished.");
		System.out.println("Enter contact info type :  email or phone ? ");
		String contactType = scan.nextLine();
		while (!(contactType.equals("#"))){
			if (contactType.equalsIgnoreCase("email")){
				System.out.println("Enter your email address");
				email = scan.nextLine();
				newUser.setEmail(email);
			}
			if (contactType.equalsIgnoreCase("phone")){
				System.out.println("Enter your phone number");
				phone = scan.nextLine();
				newUser.setPhone(phone);
			}
			System.out.println("Please enter your contact info. Enter # when you have finished.");
			System.out.println("Enter contact info type :  email or phone ? ");
			contactType = scan.nextLine();
		}
		
		if (userType==2){
			System.out.println("Do you agree to be notified about the special offer ? It's no by default");
			if (scan.nextLine().equalsIgnoreCase("yes")){
				((Customer)newUser).getService().giveConsensusBeNotifiedSpecialOffers();
			}
			System.out.println("Select the contact to be used to send the offers: email or phone.");
			scan.nextLine();
		}
		
		if (userType==3){
			System.out.println("Do you want to set your current duty status as on-duty ? By default it's off-duty. If you do enter \"yes\".");
			if (scan.nextLine().equalsIgnoreCase("yes")){
				((Courier)newUser).getService().turnOnDuty();
			}
		}

		newUser.getService().showInfo();
		System.out.println("If these informations are correct, please enter \"yes\" to save this account.");
		if (scan.nextLine().equalsIgnoreCase("yes")){
			MyFoodora.getInstance().addUser(newUser);
			System.out.println("You have been registered on MyFoodora.");
			System.out.println("You may now login with your username & password.");
		}
		else{
			System.out.println("Session closed.");
		}
	}

	/**
	 * unregister <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void unregister() throws PermissionException {
		if (user == null) throw new PermissionException("user");
		Scanner s = new Scanner(System.in);
		System.out.println("Are your sure ? It is not reversible and your account will be deleted forever. Enter yes to confirm, anything else to cancel.");
		if (s.nextLine().equalsIgnoreCase("yes")){
			MyFoodora.getInstance().removeUser(user);
			System.out.println("You deleted your account on MyFoodora.");
			logout();
		}
		else{
			System.out.println("Operation cancelled.");
		}
	}
	
	
	/**
	 * Show restaurant menus.
	 */
	private void showRestaurantMenus() {
		MyFoodora.getInstance().getService().showRestaurantMenus();
	}
	
	
	
	/**
	 * Show all messages.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showAllMessages() throws PermissionException {
		if (user == null) throw new PermissionException("user");
		user.getMessageBoard().displayAllmsgs();
	}

	/**
	 * Show new messages.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showNewMessages() throws PermissionException {
		if (user == null) throw new PermissionException("user");
		user.getMessageBoard().displayUnreadMessages();
	}

	/**
	 * displayinfo<>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showInfo() throws PermissionException {
		if (user==null) throw new PermissionException("user");
		user.getService().showInfo();
	}
	
	/**
	 * showHistory <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showHistory() throws PermissionException {
		if (user == null) throw new PermissionException("user");
		user.getService().showHistory();
	}
	
	
	
	/**
	 * registerManager "firstname" "lastname" "username" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void registerManager() throws SyntaxErrorException, PermissionException{
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<3) throw new SyntaxErrorException(2);

		String firstname = arguments[0];
		String lastname = arguments[1];
		String userName = arguments[2];
		String password = arguments[3];
		Manager manager = (Manager)user;
		try {
			manager.getService().addUser(new Manager(firstname,lastname,userName,password));
			System.out.println("Manager <"+userName+"> has been registered on myfoodora.");
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}
	
	/**
	 * registerRestaurant "name" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void registerRestaurant() throws SyntaxErrorException, PermissionException{
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<4) throw new SyntaxErrorException(4);

		String name = arguments[0];
		String userName = arguments[1];
		String[] parts = arguments[2].split(",");
		AddressPoint address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
		String password = arguments[3];
		Manager manager = (Manager)user;
		try {
			manager.getService().addUser(new Restaurant(name,userName,address,password));
			System.out.println("Restaurant <"+userName+"> has been registered on myfoodora.");
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}
	
	/**
	 * registerCustomer "firstname" "lastname" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void registerCustomer() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<5) throw new SyntaxErrorException(5);

		String firstName = arguments[0];
		String lastName = arguments[1];
		String userName = arguments[2];
		String[] parts = arguments[3].split(",");
		AddressPoint address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[0]));
		String password = arguments[4];
		Manager manager = (Manager)user;
		try {
			manager.getService().addUser(new Customer(firstName,lastName,userName,address,password));
			System.out.println("Customer <"+userName+"> has been registered on myfoodora.");		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}

	/**
	 * registerCourier "firstname" "lastname" "username" "address" "password".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void registerCourier() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<5) throw new SyntaxErrorException(5);

		String firstName = arguments[0];
		String lastName = arguments[1];
		String userName = arguments[2];
		String[] parts = arguments[3].split(",");
		AddressPoint position = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
		String password = arguments[4];
		Manager manager = (Manager)user;
		try {
			manager.getService().addUser(new Courier(firstName,lastName,userName,position,password));
			System.out.println("Courier <"+userName+"> has been registered on myfoodora.");
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printError();
		}
	}

	/**
	 * removeUser "username".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void removeUser() throws PermissionException, SyntaxErrorException {
		if (arguments.length<1) throw new SyntaxErrorException(1);
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String username = arguments[0];
		Manager manager = (Manager)user;
		try {
			manager.getService().removeUser(username);
			System.out.println("User account <"+username+"> deleted. User removed from MyFoodora.");
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * setMarkUpPercentage "markup_percentage".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void setMarkUpPercentage() throws PermissionException, SyntaxErrorException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		Manager manager = (Manager)user;
		Double markUp_percentage = Double.parseDouble(arguments[0]);
		manager.getService().setMarkUpPencentage(markUp_percentage);
		System.out.println("Mark up percentage updated.");
	}
	
	/**
	 * setDeliveryCost "delivery_cost".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void setDeliveryCost() throws PermissionException, SyntaxErrorException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		Manager manager = (Manager)user;
		Double delivery_cost = Double.parseDouble(arguments[0]);
		manager.getService().setDeliveryCost(delivery_cost);
		System.out.println("Delivery cost updated");
	}
	
	/**
	 * setServiceFee "setservice_fee".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void setServiceFee() throws PermissionException, SyntaxErrorException {
		if (arguments.length<1) throw new SyntaxErrorException(1);
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		Double service_fee = Double.parseDouble(arguments[0]);
		manager.getService().setServiceFree(service_fee);
		System.out.println("Service fee updated");
	}
	
	/**S
	 * setDeliveryPolicy  "delpolicy".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void setDeliveryPolicy() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String deliveryPolicy = arguments[0];
		Manager manager = (Manager)user;
		try {
			manager.getService().setDeliveryPolicy(deliveryPolicy);
			System.out.println("Delivery policy set as "+deliveryPolicy + " policy.");
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * setProfitPolicy  "ProfitPolicyName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void setProfitPolicy() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String ProfitPolicyName = arguments[0];
		Manager manager = (Manager)user;
		manager.getService().setTargetProfitPolicy(ProfitPolicyName);
		System.out.println("Target profit policy set as "+ProfitPolicyName + " policy.");
	}

	
	/**
	 * associateCard "userName" "cardType".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void associateCard() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<2) throw new SyntaxErrorException();
		String userName = arguments[0];
		String cardType = arguments[1];
		Manager manager = (Manager)user;
		try {
			manager.getService().associateCard(userName, cardType);
			System.out.println("Card <"+cardType+"> associated to user <"+userName+">.");
		} catch (NameNotFoundException e) {
			e.printError();
		}

	}
	
	/**
	 * showCourierDeliveries "".
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showCourierDeliveries() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;

		if (arguments.length==0 || arguments[0].equalsIgnoreCase("desc")){
			manager.getService().showCourierDesc();
		}
		else if (arguments[0].equalsIgnoreCase("asc")){
			manager.getService().showCourierAsc();
		}
		
	}

	/**
	 * showRestaurantTop "".
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showRestaurantTop() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		
		if (arguments.length==0 || arguments[0].equalsIgnoreCase("desc")){
			manager.getService().showRestaurantDesc();
		}
		else if (arguments[0].equalsIgnoreCase("asc")){
			manager.getService().showRestaurantAsc();
		}
	}

	/**
	 * showTotalProfit "".
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showTotalProfit() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		try{
		if (arguments.length==0){
			String startingDate = "01/01/2017";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String endingDate = sdf.format(new Date());
			System.out.println("Total profit : "+manager.getService().getTotalProfit(startingDate, endingDate)+" euros.");
		}
		else if (arguments.length==2){
			String startingDate = arguments[0];
			String endingDate = arguments[1];
			System.out.println("Total profit between "+startingDate+" and "+endingDate+": "
					+manager.getService().getTotalProfit(startingDate, endingDate)+" euros.");
		}
		}
		catch (ParseException e){
			System.out.println("DATE FORMAT ERROR : please enter date formated as dd/MM/YYYY");
		}
	}

	/**
	 * showTotalIncome <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showTotalIncome() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		try{
		if (arguments.length==0){
			String startingDate = "01/01/2017";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String endingDate = sdf.format(new Date());
			System.out.println("Total income : "+manager.getService().getTotalIncome(startingDate, endingDate)+" euros.");
		}
		else if (arguments.length==2){
			String startingDate = arguments[0];
			String endingDate = arguments[1];
			System.out.println("Total income between "+startingDate+" and "+endingDate+": "
					+manager.getService().getTotalIncome(startingDate, endingDate)+" euros.");
		}
		}
		catch (ParseException e){
			System.out.println("DATE FORMAT ERROR : please enter date formated as dd/MM/YYYY");
		}
		
	}

	/**
	 * showAverageIncomePerCustomer <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showAverageIncomePerCustomer() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		Manager manager = (Manager)user;
		try{
		if (arguments.length==0){
			String startingDate = "01/01/2017";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String endingDate = sdf.format(new Date());
			System.out.println("Average income per customer : "+manager.getService().getAverageIncomePerCustomer(startingDate, endingDate)+" euros.");
		}
		else if (arguments.length==2){
			String startingDate = arguments[0];
			String endingDate = arguments[1];
			System.out.println("Average income per customer between "+startingDate+" and "+endingDate+": "
					+manager.getService().getAverageIncomePerCustomer(startingDate, endingDate)+" euros.");
		}
		}
		catch (ParseException e){
			System.out.println("DATE FORMAT ERROR : please enter date formated as dd/MM/YYYY");
		}
	}

	/**
	 * applyTargetProfitPolicy "profit".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void applyTargetProfitPolicy() throws PermissionException, SyntaxErrorException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		Manager manager = (Manager)user;
		Double targetProfit = Double.parseDouble(arguments[0]);
		manager.getService().determineParam2MeetTargetProfit(targetProfit);;
		System.out.println("The target profit policy has been applied, system values updated to meet a profit of "+targetProfit+" euros.");
		MyFoodora.getInstance().getService().showSystemValues();
	}

	/**
	 * showUsers "".
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showUsers() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		if (arguments.length==0){
			MyFoodora.getInstance().getService().showUsers();
		}
		else{
			String userType = arguments[0];
			MyFoodora.getInstance().getService().showUsersOfAssignedType(userType);
		}
	}

	/**
	 * showCustomers.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showCustomers() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		MyFoodora.getInstance().getService().showUsersOfAssignedType("customer");
	}

	/**
	 * showMenuItem "restaurant-name".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void showMenuItem() throws PermissionException, SyntaxErrorException {
		if (arguments.length<1) throw new SyntaxErrorException(1);
		if (!(user instanceof Manager)) throw new PermissionException("manager");
		String restaurant_name = arguments[0];
		Manager manager = (Manager)user;
		try {
			Restaurant restaurant = (Restaurant) manager.getService().selectUser(restaurant_name);
			restaurant.getService().showMenu();
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * Show active users.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showActiveUsers() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("Manager");
		MyFoodora.getInstance().getService().showActiveUsers();
	}

	/**
	 * Show policies.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showPolicies() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("Manager");
		MyFoodora.getInstance().getService().showPolicies();
	}

	/**
	 * Show system values.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showSystemValues() throws PermissionException {
		if (!(user instanceof Manager)) throw new PermissionException("Manager");
		MyFoodora.getInstance().getService().showSystemValues();
	}

	

	/**
	 * addDishRestaurantMenu  "dishName" "dishCategory" "foodCategory" "unitPrice".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void addDishRestaurantMenu() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length!=4) throw new SyntaxErrorException(4);
		String dishName = arguments[0];
		String dishCategory = arguments[1];
		String foodCategory = arguments[2];
		double unitPrice = Double.parseDouble(arguments[3]);
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().addDish(dishName,dishCategory,foodCategory,unitPrice);
			System.out.println("<"+dishName+"> has been added to the menu of <"+user.getUsername()+">.");
		} catch (NameAlreadyExistsException e) {
			e.printError();
		}
	}
	
	/**
	 * createMeal "mealName" .
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void createMeal() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().createMeal(mealName);
			
			System.out.println("Meal <" + mealName+"> has been created.");

		} catch (NameAlreadyExistsException e) {
			e.printError();
		}
	}

	/**
	 * addDish2Meal "dishName" "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void addDish2Meal() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length!=2) throw new SyntaxErrorException(2);
		String dishName = arguments[0];
		String mealName = arguments[1];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().addDish2Meal(dishName, mealName);
			
			System.out.println("Dish <" + dishName+"> has been added to the meal <"+mealName+">.");

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
	private void showMeal() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=1) throw new SyntaxErrorException(1);
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().showMeal(mealName);
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}


	/**
	 * saveMeal "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void saveMeal() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().saveMeal(mealName);
			
			System.out.println("Meal <" + mealName+"> has been saved and added to the meal-menu of <"+restaurant.getName()+">.");
		} catch (NameNotFoundException e){e.printError();
		} catch (DishTypeErrorException e){
			e.printError();
		}
	}

	
	/**
	 * removeDish "dishName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void removeDish() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String dishName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().removeDish(dishName);
			System.out.println("Dish <" + dishName+"> removed from the menu of <"+restaurant.getName()+">.");
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}
	
	/**
	 * removeMeal "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void removeMeal() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().removeMeal(mealName);
			System.out.println("Meal <" + mealName+"> removed from the meal-menu of <"+restaurant.getName()+">.");
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * setSpecialOffer "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void setSpecialOffer() throws SyntaxErrorException, PermissionException {
		if (arguments.length!=1) throw new SyntaxErrorException(1);
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().setSpecialOffer(mealName);
			
			System.out.println("Meal " + mealName+" saved as a special-offer of "+restaurant.getName());
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * removeFromSpecialOffer "mealName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void removeFromSpecialOffer() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String mealName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			restaurant.getService().removeSpecialOffer(mealName);
			System.out.println("Meal <" + mealName+"> removed from the list of special-offers of <"+restaurant.getName()+">.");
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * setGenericDiscountFactor "generic_discount_factor".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void setGenericDiscountFactor() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);
		
		double generic_discount_factor = Double.parseDouble(arguments[0]);
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().setGenericDiscountFactor(generic_discount_factor);
		System.out.println("Generic discount factor updated.");
	}
	
	/**
	 * setSpecialDiscountFactor "special_discount_factor".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void setSpecialDiscountFactor() throws PermissionException, SyntaxErrorException {
		if (arguments.length!=1) throw new SyntaxErrorException(1);
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		double special_discount_factor = Double.parseDouble(arguments[0]);
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().setSpecialDiscountFactor(special_discount_factor);
		System.out.println("Special discount factor updated.");
	}
	
	/**
	 * showHalfMeal <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showHalfMeal() throws PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().displayMostOrderedHalfMeal();
	}
	
	/**
	 * ShowALaCarte <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showAlaCarte() throws PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().displayMostOrderedAlaCarte();

	}
	
	/**
	 * displayMenu <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showMyMenu() throws PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().showMenu();
	}

	/**
	 * Show discount factor.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showDiscountFactor() throws PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().showDiscountFactors();
	}
	
	/**
	 * Show income.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showIncome() throws PermissionException{
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		Restaurant restaurant = (Restaurant)user;
		restaurant.getService().showTotalIncome();
	}


	/**
	 * findDeliverer "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void findDeliverer() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Restaurant)) throw new PermissionException("restaurant");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String orderName = arguments[0];
		Restaurant restaurant = (Restaurant)user;
		try {
			Order order = restaurant.getHistory().getOrder(orderName);
			MyFoodora myfoodora = MyFoodora.getInstance();
			ArrayList<Courier> availablecouriers = myfoodora.getAvailableCouriers();
			MyFoodora.getInstance().getService().findDeliverer(order, availablecouriers);
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}
	/**
	 * createOrder "restaurantName" "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void createOrder() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		if (arguments.length<2) throw new SyntaxErrorException(2);

		String restaurantName = arguments[0];
		String orderName = arguments[1];
		Customer customer = (Customer)user;
		try {
			customer.getService().createOrder(restaurantName, orderName);
			System.out.println("Order <" + orderName+"> added to your shopping cart.");
		} catch (NameAlreadyExistsException e) {
			e.printError();
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * addItem2Order "itemName" "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void addItem2Order() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		if (arguments.length<2) throw new SyntaxErrorException(2);
		String orderName = arguments[1];
		String itemName = arguments[0];
		Customer customer = (Customer)user;
		try {
			customer.getService().addItem2Order(orderName, itemName);
			
			System.out.println("Item <" + itemName+"> added to the order <"+orderName+">.");

		} catch (NameNotFoundException e) {
			e.printError();
		}
	}

	/**
	 * endOrder "orderName" "date" or endOrder "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void endOrder() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		Customer customer = (Customer)user;

		String orderName = arguments[0];
		
		String dateString = null;
		try {
			if (arguments.length==1){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dateString = sdf.format(new Date());
				
			}
			if (arguments.length==2){
				dateString = arguments[1];
			}
			Order order = customer.getShoppingCart().getOrder(orderName);
			System.out.println(order);
			customer.getService().endOrder(orderName, dateString);
			System.out.println("Order <" + orderName+"> finalised on "+dateString+ " and you paid for it.");
		} catch (NameNotFoundException e){e.printError();
		} catch (ParseException e) {e.printStackTrace();
		}
	}
	
	
	/**
	 * registerCard "cardType".
	 *
	 * @throws PermissionException the permission exception
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void registerCard() throws PermissionException, SyntaxErrorException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		if (arguments.length<1) throw new SyntaxErrorException(1);
		String cardType = arguments[0];
		Customer customer = (Customer)user;
		try {
			customer.getService().registerCard(cardType);
			System.out.println("You subscribed to a new fidelity plan. Fidelity card set as <"+cardType+"> card.");
		} catch (NameNotFoundException e) {
			e.printError();
		}
	}
	
	/**
	 * unregisterCard <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void unregisterCard() throws PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		Customer customer = (Customer)user;
		customer.getService().unregisterCard();
		System.out.println("You unsubscribed from fidelity plan. Fidelity card reset as standard card.");
	}
	
	
	
	/**
	 * showPoints <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showPoints() throws PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		Customer customer = (Customer)user;
		customer.getService().showPoints();
	}
	
	/**
	 * Show shopping cart.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showShoppingCart() throws PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		Customer customer = (Customer)user;
		customer.getService().showShoppingCart();
		
	}


	
	/**
	 * Show special offers.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showSpecialOffers() throws PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		Customer customer = (Customer)user;
		customer.getService().showSpecialOffers();
	}

	
	/**
	 * turnOnNotification <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void turnOnNotification() throws PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		Customer customer = (Customer)user;
		customer.getService().giveConsensusBeNotifiedSpecialOffers();
		System.out.println("You agreed to be notified of new special-offers.");
	}
	
	/**
	 * turnOffNotification <>.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void turnOffNotification() throws PermissionException {
		if (!(user instanceof Customer)) throw new PermissionException("customer");
		Customer customer = (Customer)user;
		customer.getService().removeConsensusBeNotifiedSpecialOffers();
		System.out.println("You refused to be notified of new special-offers.");
	}

	/**
	 * onDuty "".
	 *
	 * @throws PermissionException the permission exception
	 */
	private void setOnDuty() throws PermissionException {
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		Courier courier = (Courier)user;
		courier.getService().turnOnDuty();
		System.out.println("Current state set as on-duty.");
		
	}

	/**
	 * 	 offDuty "".
	 *
	 * @throws PermissionException the permission exception
	 */
	private void setOffDuty() throws PermissionException {
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		Courier courier = (Courier)user;
		courier.getService().turnOffDuty();
		System.out.println("Current state set as off-duty.");

	}
	

 	/**
	  * changePosition "newposition".
	  *
	  * @throws PermissionException the permission exception
	  * @throws SyntaxErrorException the syntax error exception
	  */
	private void changePosition() throws PermissionException, SyntaxErrorException {
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		if (arguments.length<1) throw new SyntaxErrorException(1);
		String newposition = arguments[0];
		Courier courier = (Courier)user;
		courier.getService().changePosition(newposition);
		System.out.println("Position updated.");
	}

	/**
	 *  acceptCall "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void acceptCall() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		if (arguments.length<1) throw new SyntaxErrorException(1);
		String orderName = arguments[0];
		Courier courier = (Courier)user;
		courier.getService().acceptCall(orderName);
		System.out.println("courier "+courier.getFullName()+" <"+courier.getUsername()+"> accepts to take the order.");
	}
	
	/**
	 * refuseCall "orderName".
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 * @throws PermissionException the permission exception
	 */
	private void refuseCall() throws SyntaxErrorException, PermissionException {
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		if (arguments.length<1) throw new SyntaxErrorException(1);

		String orderName = arguments[0];
		Courier courier = (Courier)user;
		System.out.println("courier <"+courier.getUsername()+"> refuses to take the order. A new courier is being assigned.");
		courier.getService().refuseCall(orderName);
	}

	/**
	 * Show waiting orders.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showWaitingOrders() throws PermissionException {
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		Courier courier = (Courier)user;
		courier.getService().showWaitingOrders();
	}



	/**
	 * Show count.
	 *
	 * @throws PermissionException the permission exception
	 */
	private void showCount() throws PermissionException {
		// TODO Auto-generated method stub
		if (!(user instanceof Courier)) throw new PermissionException("courier");
		Courier courier = (Courier)user;
		courier.getService().showCount();
	}

	/**
	 * runtest "testScenario1.txt"
	 *
	 * @throws SyntaxErrorException the syntax error exception
	 */
	private void runTest() throws SyntaxErrorException {
		if (arguments.length<1) throw new SyntaxErrorException();
		
		String testScenarioN = arguments[0];
		String[] parts = testScenarioN.split("\\.");
		String testScenarioNoutput = parts[0]+"output."+parts[1];
		CustomPrintStream.setOutPutFile("testScenario/"+testScenarioNoutput);

		CommandProcessor c = new CommandProcessor();
		
		File file = new File("testScenario/"+testScenarioN);
		Scanner s;
		try {
			MyFoodora.reset();
			InitialScenario.load("my_foodora.ini");
			System.out.println("----------------------------------------------------------------------------------------------------------------");
			System.out.println("                                   "+testScenarioN.toUpperCase()+"                                    ");
			System.out.println("----------------------------------------------------------------------------------------------------------------");
			s = new Scanner(file);
			String rawInput = "";
			while (s.hasNextLine()){
				rawInput = s.nextLine();
				System.out.println(rawInput);
				c.processCommand(rawInput);
				System.out.println();	
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Help.
	 */
	private void help() {
		File file;
		if (user instanceof Customer){
			file = new File("help/Customer.txt");
		}
		else if (user instanceof Restaurant){
			file = new File("help/Restaurant.txt");
		} else if (user instanceof Courier){
			file = new File("help/Courier.txt");
		} else if (user instanceof Manager){
			file = new File("help/Manager.txt");
		} else{
			file = new File("help/LoginRegister.txt");
		}
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()){
				System.out.println(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

}