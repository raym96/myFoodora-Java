/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.NameNotFoundException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import policies.FairOccupationDeliveryPolicy;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;


/**
 * The Class MyFoodoraTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyFoodoraTest {

	/** The my foodora. */
	private static MyFoodora myFoodora = MyFoodora.getInstance();
	
	/** The c. */
	private static Customer c = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
	
	/** The cr. */
	private static Courier cr = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
	
	/** The r. */
	private static Restaurant r = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
	
	/** The m. */
	private static Manager m = new Manager("He", "Xiaoan", "hxa");
	
	/**
	 * Inits the.
	 */
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
		assertNotNull(myFoodora.getSpecialOfferBoard());
		assertNotNull(myFoodora.getHistory());
		
	}
	
	/**
	 * Test add special offer observer.
	 */
	@Test
	public void testAddSpecialOfferObserver() {
		myFoodora.addSpecialOfferObserver(c);
		assertTrue(myFoodora.getSpecialOfferObserver().contains(c));
	}

	/**
	 * Test remove special offer observer.
	 */
	@Test
	public void testRemoveSpecialOfferObserver() {
		myFoodora.removeSpecialOfferObserver(c);
		assertFalse(myFoodora.getSpecialOfferObserver().contains(c));
	}

	/**
	 * Test parse.
	 *
	 * @throws NameNotFoundException the user not found exception
	 */
	@Test
	public void testParse() throws NameNotFoundException {
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
		Order Order = new Order(c, r);
		Order.addItem(fm1);
	
		myFoodora.setDeliveryPolicy(new FairOccupationDeliveryPolicy());
		Courier theCourier = myFoodora.parse(Order);
		System.out.println(theCourier);
	} 

	
	/**
	 * Test add user.
	 */
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

	/**
	 * Test remove user.
	 */
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

	/**
	 * Test activate user.
	 *
	 * @throws NameNotFoundException the user not found exception
	 */
	@Test
	public void testActivateUser() throws NameNotFoundException {
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

	/**
	 * Test disactivate user.
	 *
	 * @throws NameNotFoundException the user not found exception
	 */
	@Test
	public void testDisactivateUser() throws NameNotFoundException {
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

	/**
	 * Test get users of assigned type.
	 */
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

	/**
	 * Test register.
	 */
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

	/**
	 * Test unregister.
	 */
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

	/**
	 * Test notify all observers.
	 */
	@Test
	@Ignore
	public void testNotifyAllObservers() {

	}

	/**
	 * Test notify all observers object.
	 */
	@Test
	@Ignore
	public void testNotifyAllObserversObject() {
	}

	/**
	 * Test notify observer observer.
	 */
	@Test
	@Ignore
	public void testNotifyObserverObserver() {
	}

	/**
	 * Test notify observer observer object.
	 */
	@Test
	@Ignore
	public void testNotifyObserverObserverObject() {
	}

	/**
	 * Test notify observers array list of observer.
	 */
	@Test
	@Ignore
	public void testNotifyObserversArrayListOfObserver() {
	}

	/**
	 * Test notify observers array list of user object.
	 */
	@Test
	public void testNotifyObserversArrayListOfUserObject() {

		ArrayList<User> customers = myFoodora.getUsersOfAssignedType("CUSTOMER");
		myFoodora.notifyObservers(customers, "testNotifyObserversArrayListOfUserObject() test"); 
	}

	/**
	 * Test display users.
	 */
	@AfterClass
	public static void testDisplayUsers() {
		myFoodora.displayUsers();
	}

	/**
	 * Test display active users.
	 */
	@AfterClass
	public static void testDisplayActiveUsers() {
		myFoodora.displayActiveUsers();
	}

	/**
	 * Test refresh message board.
	 */
	@AfterClass
	public static void testRefreshMessageBoard() {
		myFoodora.refreshMessageBoard();
	}
}
