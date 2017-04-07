package test.user;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.OrderNotFoundException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.Order;
import system.StandardMealOrder;
import user.Courier;
import user.Customer;
import user.Manager;
import user.MyFoodora;
import user.Restaurant;

public class CourierTest {

	private static Courier courier = null;
	private StandardMealOrder standardMealOrder1 = null;
	private StandardMealOrder standardMealOrder2 = null;
	
	@BeforeClass
	public static void testCourier() {
		courier = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		
		assertNotNull(courier);
		assertNotNull(courier.getCourierService());
		assertNotNull(courier.getDeliveredOrders());
		assertNotNull(courier.getWaitingOrders());
	}
	
	@Test
	@Ignore
	public void testObserveObservable() {
	}

	@Test
	public void testUpdate() {
		String str = "testUpdate() message";
		courier.update(str);
	}

	@Test
	public void testObserveObservableObject() {
		String str = "testObserveObservableObject() message";
		courier.observe(MyFoodora.getInstance(), str);
	}
	
//	@Test
	public void testAddWaitingOrder() {
		
		Menu menu = new Menu();
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(1), menu.getMaindishes().get(1), menu.getDesserts().get(1));
		standardMealOrder1 = new StandardMealOrder(c, r, fm1);
		standardMealOrder1.setCourier(cr);
		standardMealOrder2 = new StandardMealOrder(c, r, fm2);
		standardMealOrder2.setCourier(cr);

		
		courier.setWaitingOrders(new ArrayList<Order>());
		
		courier.addWaitingOrder(standardMealOrder1);
		courier.addWaitingOrder(standardMealOrder2);
		
		assertTrue(courier.getWaitingOrders().contains(standardMealOrder1));
		assertTrue(courier.getWaitingOrders().contains(standardMealOrder2));
	}
	
	@Test(expected = OrderNotFoundException.class)
	public void testRefuseWaitingOrder() throws OrderNotFoundException{
		
		testAddWaitingOrder();
		courier.refuseWaitingOrder(standardMealOrder1);
		assertFalse(courier.getWaitingOrders().contains(standardMealOrder1));
		
		courier.refuseWaitingOrder(standardMealOrder1);
	}

	@Test(expected = OrderNotFoundException.class)
	public void acceptWaitingOrder() throws OrderNotFoundException{
		
		testAddWaitingOrder();
		courier.acceptWaitingOrder(standardMealOrder2);
		assertFalse(courier.getWaitingOrders().contains(standardMealOrder2));
		assertTrue(courier.getDeliveredOrders().contains(standardMealOrder2));
		
		courier.acceptWaitingOrder(standardMealOrder2);
	}
	
	@Test
	public void testHashCode() {
		int hashCode = courier.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	@Test
	public void testLogIn() {
		courier.logIn();
		assertTrue(courier.isLogStatus());
	}

	@Test
	public void testLogOut() {
		courier.logOut();
		assertFalse(courier.isLogStatus());
	}

	@Test
	public void testTurnOnNotification() {
		courier.turnOnNotification();
		assertTrue(courier.isNotified());
	}

	@Test
	public void testTurnOffNotification() {
		courier.turnOffNotification();
		assertFalse(courier.isNotified());
	}

	@Test
	public void testEqualsObject() {
		Courier copy = courier;
		assertTrue(copy.equals(courier));
	}

	@AfterClass
	public static void testRefreshMessageBoard() {
		courier.refreshMessageBoard();
	}
	
	@AfterClass
	public static void testToString() {
		System.out.println(courier);
	}

}
