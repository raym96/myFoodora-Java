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
import system.ShoppingCart;
import system.SpecialMealOrder;
import system.StandardMealOrder;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;

public class ShoppingCartTest {

	private static ShoppingCart shoppingCart = null;
	private static Menu menu = new Menu();
	
	@BeforeClass
	public static void testShoppingCart() {
		menu.initMenu();
		
		shoppingCart = new ShoppingCart();
		assertNotNull(shoppingCart);
		assertNotNull(shoppingCart.getOrders());
	}

	@Test
	public void testAddOrder() {

		StandardMealOrder standardMealOrder = null;	
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		standardMealOrder = new StandardMealOrder(c, r, fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		standardMealOrder.setCourier(cr);
		
		SpecialMealOrder specialMealOrder = null;
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new SpecialMealOrder(c2, r2, fm2);
		Courier cr2 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr2);
		
		AlaCarteOrder alaCarteOrder = null;
		Restaurant r3 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c3 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Dish d = menu.getDishes().get(0);
		alaCarteOrder = new AlaCarteOrder(c3, r3, d);
		Courier cr3 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		alaCarteOrder.setCourier(cr3);
		
		shoppingCart.addOrder(standardMealOrder);
		shoppingCart.addOrder(specialMealOrder);
		shoppingCart.addOrder(alaCarteOrder);
		
		assertTrue(shoppingCart.getOrders().contains(standardMealOrder));
		assertTrue(shoppingCart.getOrders().contains(specialMealOrder));
		assertTrue(shoppingCart.getOrders().contains(alaCarteOrder));
	}

	@Test
	public void testRemoveOrder() {
		StandardMealOrder standardMealOrder = null;	
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		standardMealOrder = new StandardMealOrder(c, r, fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		standardMealOrder.setCourier(cr);
		
		SpecialMealOrder specialMealOrder = null;
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new SpecialMealOrder(c2, r2, fm2);
		Courier cr2 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr2);
		
		AlaCarteOrder alaCarteOrder = null;
		Restaurant r3 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c3 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Dish d = menu.getDishes().get(0);
		alaCarteOrder = new AlaCarteOrder(c3, r3, d);
		Courier cr3 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		alaCarteOrder.setCourier(cr3);
		
		shoppingCart.addOrder(standardMealOrder);
		shoppingCart.addOrder(specialMealOrder);
		shoppingCart.addOrder(alaCarteOrder);
		
		assertTrue(shoppingCart.getOrders().contains(standardMealOrder));
		assertTrue(shoppingCart.getOrders().contains(specialMealOrder));
		assertTrue(shoppingCart.getOrders().contains(alaCarteOrder));
		
		shoppingCart.removeOrder(shoppingCart.getOrders().get(0));
		assertFalse(shoppingCart.getOrders().contains(standardMealOrder));
	}

	@Test
	public void testCalculatePrice() {
		StandardMealOrder standardMealOrder = null;	
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		standardMealOrder = new StandardMealOrder(c, r, fm1);
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		standardMealOrder.setCourier(cr);
		
		SpecialMealOrder specialMealOrder = null;
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		specialMealOrder = new SpecialMealOrder(c2, r2, fm2);
		Courier cr2 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		specialMealOrder.setCourier(cr2);
		
		AlaCarteOrder alaCarteOrder = null;
		Restaurant r3 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c3 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Dish d = menu.getDishes().get(0);
		alaCarteOrder = new AlaCarteOrder(c3, r3, d);
		Courier cr3 = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		alaCarteOrder.setCourier(cr3);
		
		shoppingCart.addOrder(standardMealOrder);
		shoppingCart.addOrder(specialMealOrder);
		shoppingCart.addOrder(alaCarteOrder);
		
		double price = shoppingCart.getTotalPrice();
		System.out.println(price);
	}


	@AfterClass
	public static void testToString() {
		System.out.println(shoppingCart);
	}

}
