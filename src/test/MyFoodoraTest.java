//package test;
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import exceptions.UserNotFoundException;
//import model.restaurant.Dessert;
//import model.restaurant.MainDish;
//import model.restaurant.Starter;
//import model.user.*;
//import service.ManagerService;
//import service.MyFoodoraService;
//import service.RestaurantService;
//
//public class MyFoodoraTest {
//
//	// number of methods in interface of restaurant service
//	private final int totalTestCount = 24; 
//	// count of test
//	private int testCounter = 0;
//	
//	private Manager manager;
//	private ManagerService managerService;
//		
//	public void testValid(){
//		testCounter++;
//	}
//	
//	/*
//	 * test of manager's operation on myFoodora platform
//	 */
//	@Before
//	public void management() throws UserNotFoundException{
//		
//		System.out.println("\n----------------------- manager operations -----------------------");
//	
//		manager = new Manager("HE", "Xiaoan", "HXA");
//		managerService = manager.getManagerService();
//		
//		User restaurant_1 = new Restaurant("Beijing restaurant", "restaurant No 1", new AddressPoint(1.0, 1.0));
//		User restaurant_2 = new Restaurant("Nanjing restaurant", "restaurant No 2", new AddressPoint(3.0, 3.0));
//		User customer_1 = new Customer("Zhang", "San", "customer No 1", new AddressPoint(2.0, 2.0), "zhangsan@gmail.com", "+330101010101");
//		User customer_2 = new Customer("Li", "Si", "customer No 2", new AddressPoint(3.0, 3.0), "lisi@gmail.com", "+330101010102");
//		User courier_1 = new Courier("Wang", "Wu", "courier No 1", new AddressPoint(4.0, 4.0), "+330101010103");
//		User courier_2 = new Courier("Zhao", "Liu", "courier No 2", new AddressPoint(5.0, 5.0), "+330101010104");
//	
//		managerService.addUser(courier_1);
//		managerService.addUser(courier_2);
//		managerService.addUser(restaurant_1);
//		managerService.addUser(restaurant_2);
//		managerService.addUser(customer_1);
//		managerService.addUser(customer_2);
//		testValid();
//		managerService.displayUsers();
//		testValid();
//		
//		managerService.removeUser(courier_2);
//		managerService.removeUser(customer_2);
//		managerService.removeUser(restaurant_1);
//		testValid();
//		managerService.displayUsers();
//		
//		managerService.activateUser(courier_1);
//		managerService.activateUser(customer_1);
//		managerService.activateUser(restaurant_2);
//		testValid();
//		managerService.displayActiveUsers();
//		testValid();
//		
//		managerService.disactivateUser(courier_1);
//		managerService.disactivateUser(customer_1);
//		testValid();
//		managerService.displayActiveUsers();
//	}
//	
//	/*
//	 * test of operations about restaurant on myFoodora platform
//	 */
//	@Test
//	public void operationsOnRestaurant() throws UserNotFoundException{
//		
//		System.out.println("\n----------------------- Operations On Restaurant -----------------------");
//
//		Restaurant restaurant = (Restaurant)managerService.selectUser("restaurant No 2");
//		MyFoodoraService myFoodora = manager.getMyfoodoraService();
//		
//		User customer_1 = new Customer("Zhang", "San", "customer No 1", new AddressPoint(2.0, 2.0), "zhangsan@gmail.com", "+330101010101");
//		User customer_2 = new Customer("Li", "Si", "customer No 2", new AddressPoint(3.0, 3.0), "lisi@gmail.com", "+330101010102");
//		myFoodora.turnOnNotification((Customer)customer_1);
//		myFoodora.turnOnNotification((Customer)customer_2);
//		testValid();
//
//		myFoodora.addDish(restaurant, new Starter("starter_1", "standard",  1.5));
//		myFoodora.addDish(restaurant, new MainDish("mainDish_1", "standard", 5.0));
//		myFoodora.addDish(restaurant, new Dessert("dessert_1", "standard", 2.1));
//		testValid();
//		restaurant.getRestaurantService().displayMenu();
//		
//		myFoodora.removeDish(restaurant, "starter_1");
//		testValid();
//		restaurant.getRestaurantService().displayMenu();
//		
//		myFoodora.addMeal(restaurant, "new meal 1", "foie gras", "poulet");
//		myFoodora.addMeal(restaurant, "new meal 3", "foie gras", "poisson", "glace");
//		testValid();
//		restaurant.getRestaurantService().displayMealMenu();
//		
//		myFoodora.removeMeal(restaurant, "new meal 1");
//		testValid();
//		restaurant.getRestaurantService().displayMealMenu();
//		
//		myFoodora.addSpecialOffer(restaurant, restaurant.getMealMenu().getHalfMealMenu().get(0));
//		testValid();
//		restaurant.getRestaurantService().displaySpecialMenu();
//		
//		myFoodora.removeSpecialOffer(restaurant, restaurant.getMealMenu().getHalfMealMenu().get(0));
//		testValid();
//		myFoodora.turnOffNotification((Customer)customer_2);
//		testValid();
//		restaurant.getRestaurantService().displaySpecialMenu();
//		
//		myFoodora.setGDF(restaurant, 0.02);
//		testValid();
//		myFoodora.setSDF(restaurant, 0.2);
//		testValid();
//	}
//	
//	@Test
//	public void operationsOnCustomer(){
//		
//		System.out.println("\n----------------------- Operations On Customer -----------------------");
//		
//		MyFoodoraService myFoodora = manager.getMyfoodoraService();
//		User customer_1 = new Customer("Zhang", "San", "customer No 1", new AddressPoint(2.0, 2.0), "zhangsan@gmail.com", "+330101010101");
//		User customer_2 = new Customer("Li", "Si", "customer No 2", new AddressPoint(3.0, 3.0), "lisi@gmail.com", "+330101010102");
//
//		myFoodora.registerCard((Customer)customer_1, "lottery");
//		myFoodora.registerCard((Customer)customer_2, "point");
//		testValid();
//		
//		
//		
//		myFoodora.unregisterCard((Customer)customer_1);
//		testValid();
//	}
//	
//	@After
//	public void testResult(){
//		double coverage = (double)testCounter/(double)totalTestCount;
//		
//		System.out.println("\n----------------------- Test Result -----------------------");
//		System.out.println("test number=" + testCounter + ", total number=" + totalTestCount);
//		System.out.println("test coverage=" + coverage);
//	}
//}
