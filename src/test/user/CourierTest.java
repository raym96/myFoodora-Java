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
 * The Class CourierTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CourierTest {

	/** The courier. */
	private static Courier courier = null;
	
	/** The standard meal order 1. */
	private Order Order1 = null;
	
	/** The standard meal order 2. */
	private Order Order2 = null;
	
	/**
	 * Test courier.
	 */
	@BeforeClass
	public static void testCourier() {
		courier = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		
		assertNotNull(courier);
		assertNotNull(courier.getCourierService());
		assertNotNull(courier.getDeliveredOrders());
		assertNotNull(courier.getWaitingOrders());
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
		courier.update(str);
	}

	/**
	 * Test observe observable object.
	 */
	@Test
	public void testObserveObservableObject() {
		String str = "testObserveObservableObject() message";
		courier.observe(MyFoodora.getInstance(), str);
	}
	
/**
 * Test add waiting order.
 */
	@Test
	public void testAddWaitingOrder() {
		
		Menu menu = new Menu();
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(1), menu.getMaindishes().get(1), menu.getDesserts().get(1));
		fm1.setRestaurant(r);
		fm2.setRestaurant(r);
		Order1 = new Order(c, r);
		Order1.addItem(fm1);
		Order1.setCourier(cr);
		Order2 = new Order(c,r);
		Order2.addItem(fm2);
		Order2.setCourier(cr);

		
		courier.setWaitingOrders(new ArrayList<Order>());
		
		courier.addWaitingOrder(Order1);
		courier.addWaitingOrder(Order2);
		
		assertTrue(courier.getWaitingOrders().contains(Order1));
		assertTrue(courier.getWaitingOrders().contains(Order2));
	}
	
	/**
	 * Test refuse waiting order.
	 *
	 * @throws NameNotFoundException the order not found exception
	 */
	@Test(expected = NameNotFoundException.class)
	public void testRefuseWaitingOrder() throws NameNotFoundException{
		
		testAddWaitingOrder();
		courier.refuseWaitingOrder(Order1);
		assertFalse(courier.getWaitingOrders().contains(Order1));
		
		courier.refuseWaitingOrder(Order1);
	}

	/**
	 * Accept waiting order.
	 *
	 * @throws NameNotFoundException the order not found exception
	 */
	@Test(expected = NameNotFoundException.class)
	public void acceptWaitingOrder() throws NameNotFoundException{
		
		testAddWaitingOrder();
		courier.acceptWaitingOrder(Order2);
		assertFalse(courier.getWaitingOrders().contains(Order2));
		assertTrue(courier.getDeliveredOrders().contains(Order2));
		
		courier.acceptWaitingOrder(Order2);
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		int hashCode = courier.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	/**
	 * Test log in.
	 */
	@Test
	public void testLogIn() {
		courier.logIn();
		assertTrue(courier.isLogStatus());
	}

	/**
	 * Test log out.
	 */
	@Test
	public void testLogOut() {
		courier.logOut();
		assertFalse(courier.isLogStatus());
	}

	/**
	 * Test turn on notification.
	 */
	@Test
	public void testTurnOnNotification() {
		courier.turnOnNotification();
		assertTrue(courier.isNotified());
	}

	/**
	 * Test turn off notification.
	 */
	@Test
	public void testTurnOffNotification() {
		courier.turnOffNotification();
		assertFalse(courier.isNotified());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		Courier copy = courier;
		assertTrue(copy.equals(courier));
	}

	/**
	 * Test refresh message board.
	 */
	@AfterClass
	public static void testRefreshMessageBoard() {
		courier.refreshMessageBoard();
	}
	
	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(courier);
	}

}
