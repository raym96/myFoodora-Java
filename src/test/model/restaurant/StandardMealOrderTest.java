package test.model.restaurant;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.customer.ConcreteShoppingCartVisitor;
import model.restaurant.FullMeal;
import model.restaurant.Menu;
import model.restaurant.StandardMealOrder;
import model.users.AddressPoint;
import model.users.Courier;
import model.users.Customer;
import model.users.Restaurant;

public class StandardMealOrderTest {

	private static StandardMealOrder standardMealOrder = null;
	private static Menu menu = new Menu();
	
	@BeforeClass
	public static void testStandardMealOrder() {
		menu.initMenu();
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		standardMealOrder = new StandardMealOrder(c, r, fm1);
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		standardMealOrder.setCourier(cr);
		
		assertNotNull(standardMealOrder);
		assertNotNull(standardMealOrder.getCustomer());
		assertNotNull(standardMealOrder.getRestaurant());
		assertNotNull(standardMealOrder.getMeal());
		assertNotNull(standardMealOrder.getCourier());
	}
	
	@Test
	public void testAccept() {
		double price = standardMealOrder.accept(new ConcreteShoppingCartVisitor());
		assertTrue(price>0);
		System.out.println("price = " + price);
	}

	

	@Test
	public void testHashCode() {
		int hashCode = standardMealOrder.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	
	@Test
	public void testEqualsObject() {
		
		StandardMealOrder s2 = standardMealOrder;
		assertTrue(s2.equals(standardMealOrder));
	}

	@AfterClass
	public static void testToString() {
		System.out.println(standardMealOrder);
	}

}
