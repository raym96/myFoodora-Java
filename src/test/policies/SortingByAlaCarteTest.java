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

import policies.SortingByAlaCarte;
import policies.SortingByHalfMeal;
import restaurant.Dish;
import restaurant.HalfMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.Order;
import system.Order;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class SortingByAlaCarteTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SortingByAlaCarteTest {

	/** The sorting by ala carte. */
	private static SortingByAlaCarte sortingByAlaCarte = null;
	
	/** The history. */
	private static ArrayList<Order> history = new ArrayList<Order>();

	/** The map. */
	private static HashMap<HalfMeal, Integer> map = null;
	
	/** The d 1. */
	private static Dish d1 = null;
	
	/** The d 2. */
	private static Dish	d2 = null;
	
	/**
	 * Test sorting by restaurant.
	 */
	@BeforeClass
	public static void testSortingByRestaurant(){
		sortingByAlaCarte = new SortingByAlaCarte();
		assertNotNull(sortingByAlaCarte);
	}
	
	/**
	 * Inits the.
	 */
	@BeforeClass
	public static void init(){
		Menu menu = new Menu();
		menu.initMenu();
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "password");
		d1 = menu.getDishes().get(0);
		d2 = menu.getDishes().get(1);
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0),"password");
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "password");
		
		Order Order1 = new Order(c, r, "order1");
		Order1.addItem(d1);
		Order1.setCourier(cr);
		
		Order Order2 = new Order(c, r, "order2");
		Order2.addItem(d1);
		Order2.setCourier(cr);
		
		Order Order3 = new Order(c, r, "order3");
		Order3.addItem(d1);
		Order3.setCourier(cr);
		
		Order Order4 = new Order(c, r, "order4");
		Order4.addItem(d1);
		Order4.setCourier(cr);
		
		Order Order5 = new Order(c, r, "order5");
		Order5.addItem(d2);
		Order5.setCourier(cr);
		
		Order Order6 = new Order(c, r, "order6");
		Order6.addItem(d2);
		Order6.setCourier(cr);
		

		history.add(Order1);
		history.add(Order2);
		history.add(Order3);
		history.add(Order4);
		history.add(Order5);
		history.add(Order6);
		
		System.out.println("orders = ");
		System.out.println(history);
	}
	
	
	/**
	 * Test count occurence.
	 */
	@Before
	public void testCountOccurence() {
		map = sortingByAlaCarte.countOccurence(history);
		assertTrue(map.containsKey(d1));
		assertTrue(map.containsKey(d2));
		assertTrue(map.get(d1) == 4);
		assertTrue(map.get(d2) == 2);
	}

	/**
	 * Test sort by values.
	 */
	@Test
	public void testSortByValues() {
		HashMap<Restaurant, Integer> result = sortingByAlaCarte.sortByValues(map);
		System.out.println("---testSortByValues()---");
		System.out.println(result);
	}

	/**
	 * Test sort by values reversed.
	 */
	@Test
	public void testSortByValuesReversed() {
		HashMap<Restaurant, Integer> result = sortingByAlaCarte.sortByValuesReversed(map);
		System.out.println("---testSortByValuesReversed()---");
		System.out.println(result);
	}

	/**
	 * Test display ascending.
	 */
	@AfterClass
	public static void testDisplayAscending() {
		System.out.println("---testDisplayAscending---");
		sortingByAlaCarte.displayAscending(history);
	}

	/**
	 * Test display descending.
	 */
	@AfterClass
	public static void testDisplayDescending() {
		System.out.println("---testDisplayDescending---");
		sortingByAlaCarte.displayDescending(history);
	}

}
