/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;

import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.CustomerService;


/**
 * The Class CustomerTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerTest {

	/** The customer. */
	private static Customer customer = null;
	
	/** The customer service. */
	private static CustomerService customerService = null;
	
	/**
	 * Test customer.
	 */
	@BeforeClass
	public static void testCustomer() {
		customer = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "password");
		customerService = customer.getService();
		
		assertNotNull(customer);
		assertNotNull(customerService);
		assertNotNull(customer.getSpecialoffers());
	}
	
	/**
	 * Test observe observable.
	 */
	@Test
	@Ignore
	public void testObserveObservable() {
	}
\

	/**
	 * Test add special offer.
	 */
	@Test
	public void testAddSpecialOffer() {
		Menu menu = new Menu();
		menu.initMenu();
		
		FullMeal fm = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		
		r.getService().addMeal(fm);
		r.getService().setSpecialOffer("FM2");
		
		customer.addSpecialOffer(fm);
		
		assertTrue(customer.getSpecialoffers().size() > 0);
	}

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		int hashCode = customer.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	/**
	 * Test log in.
	 */
	@Test
	public void testLogIn() {
		customer.logIn();
		assertTrue(customer.isLogStatus());
	}

	/**
	 * Test log out.
	 */
	@Test
	public void testLogOut() {
		customer.logOut();
		assertFalse(customer.isLogStatus());
	}

	/**
	 * Test turn on notification.
	 */
	@Test
	public void testTurnOnNotification() {
		customer.turnOnNotification();
		assertTrue(customer.isNotified());
	}

	/**
	 * Test turn off notification.
	 */
	@Test
	public void testTurnOffNotification() {
		customer.turnOffNotification();
		assertFalse(customer.isNotified());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		Customer copy = customer;
		assertTrue(copy.equals(customer));
	}


	
	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(customer);
	}
}
