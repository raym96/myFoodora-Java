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
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;


/**
 * The Class RestaurantTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class RestaurantTest {

	/** The restaurant. */
	private static Restaurant restaurant = null;
	
	/**
	 * Test restaurant.
	 */
	@BeforeClass
	public static void testRestaurant() {
		restaurant = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		assertNotNull(restaurant);
		assertNotNull(restaurant.getService());
		assertNotNull(restaurant.getMenu());
		assertNotNull(restaurant.getHalfMealMenu());
		assertNotNull(restaurant.getHalfMealMenu());
		assertNotNull(restaurant.getHistory());
	}
	
	/**
	 * Test observe observable.
	 */
	@Test
	@Ignore
	public void testObserveObservable() {
	}

	/**
	 * Test update.
	 */
	@Test
	public void testUpdate() {
		String str = "testUpdate() message";
		restaurant.update(str);
	}

	/**
	 * Test observe observable object.
	 */
	@Test
	public void testObserveObservableObject() {
		String str = "testObserveObservableObject() message";
		restaurant.observe(MyFoodora.getInstance(), str);
	}
	
	/**
	 * Test add to history.
	 */
	@Test
	public void testAddToHistory() {
		Menu menu = new Menu();
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		fm1.setRestaurant(r);
		Order Order = new Order(c, r);
		Order.addItem(fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr);
		
		restaurant.addToHistory(Order);
		assertTrue(restaurant.getHistory().getOrders().size() > 0);
		System.out.println(restaurant.getHistory());
	}


	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		int hashCode = restaurant.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	/**
	 * Test log in.
	 */
	@Test
	public void testLogIn() {
		restaurant.logIn();
		assertTrue(restaurant.isLogStatus());
	}

	/**
	 * Test log out.
	 */
	@Test
	public void testLogOut() {
		restaurant.logOut();
		assertFalse(restaurant.isLogStatus());
	}

	/**
	 * Test turn on notification.
	 */
	@Test
	public void testTurnOnNotification() {
		restaurant.turnOnNotification();
		assertTrue(restaurant.isNotified());
	}

	/**
	 * Test turn off notification.
	 */
	@Test
	public void testTurnOffNotification() {
		restaurant.turnOffNotification();
		assertFalse(restaurant.isNotified());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		Restaurant copy = restaurant;
		assertTrue(copy.equals(restaurant));
	}

	/**
	 * Test refresh message board.
	 */
	@AfterClass
	public static void testRefreshMessageBoard() {
		restaurant.refreshMessageBoard();
	}
	
	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(restaurant);
	}
}
