package test.user;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.UserNotFoundException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import policies.FairOccupationDeliveryPolicy;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.StandardMealOrder;
import user.Courier;
import user.Customer;
import user.Manager;
import user.MyFoodora;
import user.Restaurant;
import user.User;

public class MyFoodoraTest {

	private static MyFoodora myFoodora = MyFoodora.getInstance();
	private static Customer c = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
	private static Courier cr = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
	private static Restaurant r = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
	private static Manager m = new Manager("He", "Xiaoan", "hxa");
	
	@BeforeClass
	public static void init(){
		MyFoodora myFoodora2 = MyFoodora.getInstance();
		assertEquals(myFoodora2, myFoodora);
		
		assertNotNull(myFoodora);
		assertNotNull(myFoodora.getUsers());
		assertNotNull(myFoodora.getActiveUsers());
		assertNotNull(myFoodora.getCouriers());
		assertNotNull(myFoodora.getActivecouriers());
		assertNotNull(myFoodora.getSpecialOfferObserver());
		assertNotNull(myFoodora.getMessageBoard());
		assertNotNull(myFoodora.getSpecialofferboard());
		assertNotNull(myFoodora.getHistory());
		
	}
	
	@Test
	public void testAddSpecialOfferObserver() {
		myFoodora.addSpecialOfferObserver(c);
		assertTrue(myFoodora.getSpecialOfferObserver().contains(c));
	}

	@Test
	public void testRemoveSpecialOfferObserver() {
		myFoodora.removeSpecialOfferObserver(c);
		assertFalse(myFoodora.getSpecialOfferObserver().contains(c));
	}

	@Test
	public void testParse() throws UserNotFoundException {
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Courier cr3 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Courier cr4 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		myFoodora.register(cr2);
		myFoodora.register(cr3);
		myFoodora.register(cr4);
		myFoodora.activateUser(cr2);
		myFoodora.activateUser(cr3);
		myFoodora.activateUser(cr4);
		
		Menu menu = new Menu();
		menu.initMenu();
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		StandardMealOrder standardMealOrder = new StandardMealOrder(c, r, fm1);
	
		myFoodora.setDeliveryPolicy(new FairOccupationDeliveryPolicy());
		Courier theCourier = myFoodora.parse(standardMealOrder);
		System.out.println(theCourier);
	} 

	
	@Test
	public void testAddUser() {
		Customer c2 = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		myFoodora.addUser(r2);
		myFoodora.addUser(c2);
		myFoodora.addUser(cr2);
		assertTrue(myFoodora.getUsers().contains(c2));
		assertTrue(myFoodora.getUsers().contains(r2));
		assertTrue(myFoodora.getUsers().contains(cr2));
	}

	@Test
	public void testRemoveUser() {
		Customer c2 = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		myFoodora.addUser(r2);
		myFoodora.addUser(c2);
		myFoodora.addUser(cr2);
		assertTrue(myFoodora.getUsers().contains(c2));
		assertTrue(myFoodora.getUsers().contains(r2));
		assertTrue(myFoodora.getUsers().contains(cr2));
		
		myFoodora.removeUser(r2);
		myFoodora.removeUser(c2);
		myFoodora.removeUser(cr2);
		assertFalse(myFoodora.getUsers().contains(r2));
		assertFalse(myFoodora.getUsers().contains(c2));
		assertFalse(myFoodora.getUsers().contains(cr2));
	}

	@Test
	public void testActivateUser() throws UserNotFoundException {
		Customer c2 = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		myFoodora.register(r2);
		myFoodora.register(c2);
		myFoodora.register(cr2);
		myFoodora.activateUser(r2);
		myFoodora.activateUser(c2);
		myFoodora.activateUser(cr2);
		assertTrue(myFoodora.getActiveUsers().contains(c2));
		assertTrue(myFoodora.getActiveUsers().contains(r2));
		assertTrue(myFoodora.getActiveUsers().contains(cr2));
		assertTrue(r2.isActivated());
		assertTrue(c2.isActivated());
		assertTrue(cr2.isActivated());
	}

	@Test
	public void testDisactivateUser() throws UserNotFoundException {
		Customer c2 = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		myFoodora.register(r2);
		myFoodora.register(c2);
		myFoodora.register(cr2);
		
		myFoodora.activateUser(r2);
		myFoodora.activateUser(c2);
		myFoodora.activateUser(cr2);
		assertTrue(myFoodora.getActiveUsers().contains(c2));
		assertTrue(myFoodora.getActiveUsers().contains(r2));
		assertTrue(myFoodora.getActiveUsers().contains(cr2));
		assertTrue(r2.isActivated());
		assertTrue(c2.isActivated());
		assertTrue(cr2.isActivated());
		
		myFoodora.disactivateUser(r2);
		myFoodora.disactivateUser(c2);
		myFoodora.disactivateUser(cr2);
		assertFalse(myFoodora.getActiveUsers().contains(c2));
		assertFalse(myFoodora.getActiveUsers().contains(r2));
		assertFalse(myFoodora.getActiveUsers().contains(cr2));
		assertFalse(r2.isActivated());
		assertFalse(c2.isActivated());
		assertFalse(cr2.isActivated());
	}

	@Test
	public void testGetUsersOfAssignedType() {
		myFoodora.addUser(m);
		myFoodora.addUser(r);
		myFoodora.addUser(c);
		myFoodora.addUser(cr);
		
		ArrayList<User> managers = myFoodora.getUsersOfAssignedType("MANAGER");
		assertTrue(managers.get(0) instanceof Manager);

		ArrayList<User> customers = myFoodora.getUsersOfAssignedType("CUSTOMER");
		assertTrue(customers.get(0) instanceof Customer);
		
		ArrayList<User> couriers = myFoodora.getUsersOfAssignedType("COURIER");
		assertTrue(couriers.get(0) instanceof Courier);
		
		ArrayList<User> restaurants = myFoodora.getUsersOfAssignedType("RESTAURANT");
		assertTrue(restaurants.get(0) instanceof Restaurant);
	}

	@Test
	public void testRegister() {
		Customer c2 = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		myFoodora.register(c2);
		myFoodora.register(r2);
		myFoodora.register(cr2);
		assertTrue(myFoodora.getUsers().contains(c2));
		assertTrue(myFoodora.getUsers().contains(r2));
		assertTrue(myFoodora.getUsers().contains(cr2));
	}

	@Test
	public void testUnregister() {
		Customer c2 = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		myFoodora.register(r2);
		myFoodora.register(c2);
		myFoodora.register(cr2);
		assertTrue(myFoodora.getUsers().contains(c2));
		assertTrue(myFoodora.getUsers().contains(r2));
		assertTrue(myFoodora.getUsers().contains(cr2));
		
		myFoodora.unregister(r2);
		myFoodora.unregister(c2);
		myFoodora.unregister(cr2);
		assertFalse(myFoodora.getUsers().contains(r2));
		assertFalse(myFoodora.getUsers().contains(c2));
		assertFalse(myFoodora.getUsers().contains(cr2));
	}

	@Test
	@Ignore
	public void testNotifyAllObservers() {

	}

	@Test
	@Ignore
	public void testNotifyAllObserversObject() {
	}

	@Test
	@Ignore
	public void testNotifyObserverObserver() {
	}

	@Test
	@Ignore
	public void testNotifyObserverObserverObject() {
	}

	@Test
	@Ignore
	public void testNotifyObserversArrayListOfObserver() {
	}

	@Test
	public void testNotifyObserversArrayListOfUserObject() {

		ArrayList<User> customers = myFoodora.getUsersOfAssignedType("CUSTOMER");
		myFoodora.notifyObservers(customers, "testNotifyObserversArrayListOfUserObject() test"); 
	}

	@AfterClass
	public static void testDisplayUsers() {
		myFoodora.displayUsers();
	}

	@AfterClass
	public static void testDisplayActiveUsers() {
		myFoodora.displayActiveUsers();
	}

	@AfterClass
	public static void testRefreshMessageBoard() {
		myFoodora.refreshMessageBoard();
	}
}
