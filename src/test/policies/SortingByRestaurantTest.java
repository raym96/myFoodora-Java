/*
 * 
 */
package test.policies;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import policies.SortingByRestaurant;
import restaurant.Dish;
import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.AlaCarteOrder;
import system.Order;
import system.SpecialMealOrder;
import system.StandardMealOrder;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class SortingByRestaurantTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SortingByRestaurantTest {

	/** The sorting by restaurant. */
	private static SortingByRestaurant sortingByRestaurant = null;
	
	/** The history. */
	private static ArrayList<Order> history = new ArrayList<Order>();
	
	/** The r. */
	private static Restaurant r = null;
	
	/** The r 2. */
	private static Restaurant r2 = null;
	
	/** The map. */
	private static HashMap<Restaurant, Integer> map = null;
	
	/**
	 * Test sorting by restaurant.
	 */
	@BeforeClass
	public static void testSortingByRestaurant(){
		sortingByRestaurant = new SortingByRestaurant();
		assertNotNull(sortingByRestaurant);
	}
	
	/**
	 * Inits the.
	 */
	@BeforeClass
	public static void init(){
		Menu menu = new Menu();
		menu.initMenu();
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		
		r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM1", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		StandardMealOrder standardMealOrder1 = new StandardMealOrder(c, r, fm1);
		standardMealOrder1.setCourier(cr);
		
		r2 = new Restaurant("Chinese Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c2 = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		StandardMealOrder standardMealOrder2 = new StandardMealOrder(c2, r2, fm2);
		standardMealOrder2.setCourier(cr);
	
		FullMeal fm3 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		SpecialMealOrder specialMealOrder1 = new SpecialMealOrder(c, r, fm3);
		specialMealOrder1.setCourier(cr);
		
	
		FullMeal fm4 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		SpecialMealOrder specialMealOrder2 = new SpecialMealOrder(c2, r2, fm4);
		specialMealOrder2.setCourier(cr);
		
		Dish d = menu.getDishes().get(0);
		AlaCarteOrder alaCarteOrder1 = new AlaCarteOrder(c, r, d);
		alaCarteOrder1.setCourier(cr);
		
		Dish d2 = menu.getDishes().get(1);
		AlaCarteOrder alaCarteOrder2 = new AlaCarteOrder(c, r, d2);
		alaCarteOrder2.setCourier(cr);
		
		history.add(standardMealOrder1);
		history.add(standardMealOrder2);
		history.add(specialMealOrder1);
		history.add(specialMealOrder2);
		history.add(alaCarteOrder1);
		history.add(alaCarteOrder2);
		
		System.out.println("orders = ");
		for (Order order : history){
			System.out.println(order);
		}
	}
	
	
	/**
	 * Test count occurence.
	 */
	@Before
	public void testCountOccurence() {
		map = sortingByRestaurant.countOccurence(history);
		assertTrue(map.containsKey(r));
		assertTrue(map.containsKey(r2));
		assertTrue(map.get(r) == 4);
		assertTrue(map.get(r2) == 2);
	}

	/**
	 * Test sort by values.
	 */
	@Test
	public void testSortByValues() {
		HashMap<Restaurant, Integer> result = sortingByRestaurant.sortByValues(map);
		System.out.println("---testSortByValues()---");
		System.out.println(result);
	}

	/**
	 * Test sort by values reversed.
	 */
	@Test
	public void testSortByValuesReversed() {
		HashMap<Restaurant, Integer> result = sortingByRestaurant.sortByValuesReversed(map);
		System.out.println("---testSortByValuesReversed()---");
		System.out.println(result);
	}

	/**
	 * Test display ascending.
	 */
	@AfterClass
	public static void testDisplayAscending() {
		System.out.println("---testDisplayAscending---");
		sortingByRestaurant.displayAscending(history);
	}

	/**
	 * Test display descending.
	 */
	@AfterClass
	public static void testDisplayDescending() {
		System.out.println("---testDisplayDescending---");
		sortingByRestaurant.displayDescending(history);
	}
}
