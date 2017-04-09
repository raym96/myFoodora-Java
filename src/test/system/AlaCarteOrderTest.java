/*
 * 
 */
package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Dish;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.AlaCarteOrder;
import system.ConcreteShoppingCartVisitor;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class AlaCarteOrderTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class AlaCarteOrderTest {

	/** The ala carte order. */
	private static AlaCarteOrder alaCarteOrder = null;
	
	/** The menu. */
	private static Menu menu = new Menu();
	
	/**
	 * Test ala carte order.
	 */
	@BeforeClass
	public static void testAlaCarteOrder() {
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Dish d = menu.getDishes().get(0);
		alaCarteOrder = new AlaCarteOrder(c, r, d);
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		alaCarteOrder.setCourier(cr);
		
		assertNotNull(alaCarteOrder);
		assertNotNull(alaCarteOrder.getCustomer());
		assertNotNull(alaCarteOrder.getRestaurant());
		assertNotNull(alaCarteOrder.getDish());
		assertNotNull(alaCarteOrder.getCourier());
	}
	
	/**
	 * Test accept.
	 */
	@Test
	public void testAccept() {
		double price = alaCarteOrder.accept(new ConcreteShoppingCartVisitor());
		assertTrue(price>0);
		System.out.println("price = " + price);
	}

	

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		int hashCode = alaCarteOrder.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	
	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		
		AlaCarteOrder a2 = alaCarteOrder;
		assertTrue(a2.equals(alaCarteOrder));
	}

	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(alaCarteOrder);
	}
}
