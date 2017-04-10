/*
 * 
 */
package test.system;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restaurant.Dish;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.ConcreteShoppingCartVisitor;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class ConcreteShoppingCartVisitorTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ConcreteShoppingCartVisitorTest {

	/** The concrete shopping cart visitor. */
	private static ConcreteShoppingCartVisitor concreteShoppingCartVisitor = null;
	
	/** The menu. */
	private static Menu menu = new Menu();
	

	/**
	 * Loads the initial.
	 */
	@BeforeClass
	public static void init(){
		concreteShoppingCartVisitor = new ConcreteShoppingCartVisitor();
		assertNotNull(concreteShoppingCartVisitor);
		menu.initMenu();
	}
	
	/**
	 * Test visit a meal.
	 */
	@Test
	public void testVisitMeal() {
		Restaurant r2 = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		fm2.setRestaurant(r2);
		
		System.out.println(fm2);
		System.out.println(concreteShoppingCartVisitor.visit(fm2));
		
		//if it is special
		fm2.setSpecial(true);
		System.out.println(fm2);
		System.out.println(concreteShoppingCartVisitor.visit(fm2));
	}

	/**
	 * Test visit an order.
	 */
	@Test
	public void testVisitOrder() {
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		Order order = new Order(c,r);
		
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		fm1.setRestaurant(r);
		
		order.addItem(fm1);
		
		System.out.println(order);
		System.out.println(concreteShoppingCartVisitor.visit(order));

		
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		fm2.setRestaurant(r);
		
		order.addItem(fm2);
		
		System.out.println(order);
		System.out.println(concreteShoppingCartVisitor.visit(order));
	}

	/**
	 * Test visit a dish.
	 */
	@Test
	public void testVisitADish() {
		Dish d = menu.getDishes().get(0);
		System.out.println(d);
		
		System.out.println(concreteShoppingCartVisitor.visit(d));
	}

}
