package test.model.user;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import model.restaurant.FullMeal;
import model.restaurant.Menu;
import model.restaurant.StandardMealOrder;
import model.users.AddressPoint;
import model.users.Courier;
import model.users.Customer;
import model.users.Manager;
import model.users.MyFoodora;
import model.users.Restaurant;

public class CourierTest {

	private static Courier courier = null;
	
	@BeforeClass
	public static void testCourier() {
		courier = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		
		assertNotNull(courier);
		assertNotNull(courier.getCourierService());
		assertNotNull(courier.getAllDeliveryTask());
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
	
	@Test
	public void testAddDeliveryTask() {
		Menu menu = new Menu();
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		StandardMealOrder standardMealOrder = new StandardMealOrder(c, r, fm1);
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		standardMealOrder.setCourier(cr);
		
		courier.addDeliveryTask(standardMealOrder);
		
		assertTrue(courier.getAllDeliveryTask().size() > 0);
		assertTrue(courier.getCount() > 0);
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
