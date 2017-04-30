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
import system.Order;
import system.ShoppingCart;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class ShoppingCartTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ShoppingCartTest {

	/** The shopping cart. */
	private static ShoppingCart shoppingCart = null;
	
	/** The menu. */
	private static Menu menu = new Menu();
	
	/**
	 * Test shopping cart.
	 */
	@BeforeClass
	public static void testShoppingCart() {
		menu.initMenu();
		
		shoppingCart = new ShoppingCart();
		assertNotNull(shoppingCart);
		assertNotNull(shoppingCart.getOrders());
	}

	/**
	 * Test add order.
	 */
	@Test
	public void testAddOrder() {

		Order Order = null;	
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		Order = new Order(c, r, "myorder"); Order.addItem(fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr);
		
		Order specialMealOrder = null;
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new Order(c2,r2,"myorder2"); specialMealOrder.addItem(fm2);
		Courier cr2 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr2);
		
		Order order = null;
		Restaurant r3 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c3 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		Dish d = menu.getDishes().get(0);
		Order = new Order(c3, r3, "myorder3"); Order.addItem(d);
		Courier cr3 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr3);
		
		shoppingCart.addOrder(Order);
		shoppingCart.addOrder(specialMealOrder);
		shoppingCart.addOrder(Order);
		
		assertTrue(shoppingCart.getOrders().contains(Order));
		assertTrue(shoppingCart.getOrders().contains(specialMealOrder));
		assertTrue(shoppingCart.getOrders().contains(Order));
	}

	/**
	 * Test remove order.
	 */
	@Test
	public void testRemoveOrder() {
		Order Order = null;	
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		Order = new Order(c, r, "myorder"); Order.addItem(fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr);
		
		Order specialMealOrder = null;
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new Order(c2,r2,"myorder2"); specialMealOrder.addItem(fm2);
		Courier cr2 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr2);
		
		Order order = null;
		Restaurant r3 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c3 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		Dish d = menu.getDishes().get(0);
		Order = new Order(c3, r3, "myorder3"); Order.addItem(d);
		Courier cr3 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr3);
		
		shoppingCart.addOrder(Order);
		shoppingCart.addOrder(specialMealOrder);
		shoppingCart.addOrder(Order);
		
		assertTrue(shoppingCart.getOrders().contains(Order));
		assertTrue(shoppingCart.getOrders().contains(specialMealOrder));
		assertTrue(shoppingCart.getOrders().contains(Order));
		
		shoppingCart.removeOrder(shoppingCart.getOrders().get(0));
		assertFalse(shoppingCart.getOrders().contains(Order));
	}

	/**
	 * Test calculate price.
	 */
	@Test
	public void testCalculatePrice() {
		Order Order = null;	
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		Order = new Order(c, r, "myorder"); Order.addItem(fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr);
		
		Order specialMealOrder = null;
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new Order(c2,r2,"myorder2"); specialMealOrder.addItem(fm2);
		Courier cr2 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr2);
		
		Order order = null;
		Restaurant r3 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c3 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		Dish d = menu.getDishes().get(0);
		Order = new Order(c3, r3, "myorder3"); Order.addItem(d);
		Courier cr3 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		Order.setCourier(cr3);
		
		shoppingCart.addOrder(Order);
		shoppingCart.addOrder(specialMealOrder);
		shoppingCart.addOrder(Order);
		
		double price = shoppingCart.getTotalPrice();
		System.out.println(price);
	}


	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(shoppingCart);
	}

}
