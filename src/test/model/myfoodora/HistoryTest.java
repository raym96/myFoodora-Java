package test.model.myfoodora;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.myfoodora.History;
import model.restaurant.FullMeal;
import model.restaurant.Menu;
import model.restaurant.Order;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import model.users.AddressPoint;
import model.users.Courier;
import model.users.Customer;
import model.users.Restaurant;

public class HistoryTest {

	private static History history = null;
	private static Menu menu = new Menu();
	
	private static StandardMealOrder standardMealOrder = null;
	private static SpecialMealOrder specialMealOrder = null;
	
	private static Date date1 = null;
	private static Date date2 = null;
	private static Date date3 = null;
	
	@BeforeClass
	public static void testHistory(){
		history = new History();
		assertNotNull(history);
		assertNotNull(history.getOrders());

	}
	
	@BeforeClass
	public static void init() throws InterruptedException{
		
		menu.initMenu();
		
		date1 = new Date();
		Thread.sleep(1 * 1000);
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		standardMealOrder = new StandardMealOrder(c, r, fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		standardMealOrder.setCourier(cr);
		
		date2 = new Date();
		Thread.sleep(1 * 1000);
		
		Restaurant r2 = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint(1.0,1.0));
		specialMealOrder = new SpecialMealOrder(c, r2, fm1);
		specialMealOrder.setCourier(cr);
		
		date3 = new Date();
	}
	
	@Test
	public void testAddOrder() {
		
		history.addOrder(standardMealOrder);
		history.addOrder(specialMealOrder);
		
		assertTrue(history.getOrders().size() > 0);
	}

	@Test
	public void testGetOrderBetween() {
		history.addOrder(standardMealOrder);
		history.addOrder(specialMealOrder);
		
		ArrayList<Order> theOrders1 = history.getOrderBetween(date1, date2);
		assertNotNull(theOrders1);
		assertTrue(theOrders1.contains(standardMealOrder));
		assertFalse(theOrders1.contains(specialMealOrder));
		
		ArrayList<Order> theOrders2 = history.getOrderBetween(date2, date3);
		assertNotNull(theOrders2);
		assertTrue(theOrders2.contains(specialMealOrder));
		assertFalse(theOrders2.contains(standardMealOrder));
	}

	

	@Test
	public void testGetOrdersOf() {
		history.addOrder(standardMealOrder);
		history.addOrder(specialMealOrder);
		
		ArrayList<Order> theOrders = history.getOrdersOf("restaurant_1");
		assertNotNull(theOrders);
		assertTrue(theOrders.contains(standardMealOrder));
		System.out.println("testGetOrdersOf() --- " + theOrders);
	}
	
	@AfterClass
	public static void testDisplayAllOrders() {
		history.displayAllOrders();
	}

}
