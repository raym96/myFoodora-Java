/*
 * 
 */
package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.ConcreteShoppingCartVisitor;
import system.SpecialMealOrder;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class SpecialMealOrderTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SpecialMealOrderTest {

	/** The special meal order. */
	private static SpecialMealOrder specialMealOrder = null;
	
	/** The menu. */
	private static Menu menu = new Menu();
	
	/**
	 * Test special meal order.
	 */
	@BeforeClass
	public static void testSpecialMealOrder() {
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new SpecialMealOrder(c, r, fm1);
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr);
		
		assertNotNull(specialMealOrder);
		assertNotNull(specialMealOrder.getCustomer());
		assertNotNull(specialMealOrder.getRestaurant());
		assertNotNull(specialMealOrder.getMeal());
		assertNotNull(specialMealOrder.getCourier());
	}
	
	/**
	 * Test accept.
	 */
	@Test
	public void testAccept() {
		double price = specialMealOrder.accept(new ConcreteShoppingCartVisitor());
		assertTrue(price>0);
		System.out.println("price = " + price);
	}

	

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		int hashCode = specialMealOrder.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	
	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		
		SpecialMealOrder s2 = specialMealOrder;
		assertTrue(s2.equals(specialMealOrder));
	}

	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(specialMealOrder);
	}

}
