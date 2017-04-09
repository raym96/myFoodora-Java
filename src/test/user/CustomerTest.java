package test.user;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.SpecialMealOrder;
import system.SpecialOffer;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.CustomerService;

public class CustomerTest {

	private static Customer customer = null;
	private static CustomerService customerService = null;
	
	@BeforeClass
	public static void testCustomer() {
		customer = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		customerService = customer.getCustomerService();
		
		assertNotNull(customer);
		assertNotNull(customerService);
		assertNotNull(customer.getSpecialoffers());
	}
	
	@Test
	@Ignore
	public void testObserveObservable() {
	}

	@Test
	public void testUpdate() {
		String str = "testUpdate() message";
		customer.update(str);
	}

	@Test
	public void testObserveObservableObject() {
		String str = "testObserveObservableObject() message";
		customer.observe(MyFoodora.getInstance(), str);
	}

	@Test
	public void testAddSpecialOffer() {
		Menu menu = new Menu();
		menu.initMenu();
		
		FullMeal fm = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		
		r.getRestaurantService().addMeal(fm);
		r.getRestaurantService().addSpecialMeal("FM2");
		
		customer.addSpecialOffer(fm);
		
		assertTrue(customer.getSpecialoffers().size() > 0);
	}

	@Test
	public void testHashCode() {
		int hashCode = customer.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	@Test
	public void testLogIn() {
		customer.logIn();
		assertTrue(customer.isLogStatus());
	}

	@Test
	public void testLogOut() {
		customer.logOut();
		assertFalse(customer.isLogStatus());
	}

	@Test
	public void testTurnOnNotification() {
		customer.turnOnNotification();
		assertTrue(customer.isNotified());
	}

	@Test
	public void testTurnOffNotification() {
		customer.turnOffNotification();
		assertFalse(customer.isNotified());
	}

	@Test
	public void testEqualsObject() {
		Customer copy = customer;
		assertTrue(copy.equals(customer));
	}

	@AfterClass
	public static void testRefreshMessageBoard() {
		customer.refreshMessageBoard();
	}
	
	@AfterClass
	public static void testToString() {
		System.out.println(customer);
	}
}
