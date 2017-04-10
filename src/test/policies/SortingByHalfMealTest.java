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

import policies.SortingByHalfMeal;
import policies.SortingByRestaurant;
import restaurant.Dish;
import restaurant.FullMeal;
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
 * The Class SortingByHalfMealTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SortingByHalfMealTest {

	/** The sorting by half meal. */
	private static SortingByHalfMeal sortingByHalfMeal = null;
	
	/** The history. */
	private static ArrayList<Order> history = new ArrayList<Order>();

	/** The map. */
	private static HashMap<HalfMeal, Integer> map = null;
	
	/** The hm 1. */
	private static HalfMeal hm1 = null;
	
	/** The hm 2. */
	private static HalfMeal hm2 = null;
	
	/**
	 * Test sorting by restaurant.
	 */
	@BeforeClass
	public static void testSortingByRestaurant(){
		sortingByHalfMeal = new SortingByHalfMeal();
		assertNotNull(sortingByHalfMeal);
	}
	
	/**
	 * Inits the.
	 */
	@BeforeClass
	public static void init(){
		Menu menu = new Menu();
		menu.initMenu();
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		hm1 = new HalfMeal("HM2", menu.getStarters().get(0), menu.getMaindishes().get(0));
		hm2 = new HalfMeal("HM2", menu.getStarters().get(1), menu.getMaindishes().get(1));
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		
		Order Order1 = new Order(c, r, hm1);
		Order1.setCourier(cr);
		
		Order Order2 = new Order(c, r, hm2);
		Order2.setCourier(cr);
		
		Order Order3 = new Order(c, r, hm1);
		Order3.setCourier(cr);
		
		Order Order4 = new Order(c, r, hm1);
		Order4.setCourier(cr);
		
		hm1.setSpecial(true);
		Order specialMealOrder1 = new Order(c, r, hm1);
		specialMealOrder1.setCourier(cr);
		
		Order specialMealOrder2 = new Order(c, r, hm1);
		specialMealOrder2.setCourier(cr);
		

		history.add(Order1);
		history.add(Order2);
		history.add(Order3);
		history.add(Order4);
		history.add(specialMealOrder1);
		history.add(specialMealOrder2);
		
		System.out.println("orders = ");
		System.out.println(history);
	}
	
	
	/**
	 * Test count occurence.
	 */
	@Before
	public void testCountOccurence() {
		map = sortingByHalfMeal.countOccurence(history);
		assertTrue(map.containsKey(hm1));
		assertTrue(map.containsKey(hm2));
		assertTrue(map.get(hm1) == 3);
		assertTrue(map.get(hm2) == 1);
	}

	/**
	 * Test sort by values.
	 */
	@Test
	public void testSortByValues() {
		HashMap<Restaurant, Integer> result = sortingByHalfMeal.sortByValues(map);
		System.out.println("---testSortByValues()---");
		System.out.println(result);
	}

	/**
	 * Test sort by values reversed.
	 */
	@Test
	public void testSortByValuesReversed() {
		HashMap<Restaurant, Integer> result = sortingByHalfMeal.sortByValuesReversed(map);
		System.out.println("---testSortByValuesReversed()---");
		System.out.println(result);
	}

	/**
	 * Test display ascending.
	 */
	@AfterClass
	public static void testDisplayAscending() {
		System.out.println("---testDisplayAscending---");
		sortingByHalfMeal.displayAscending(history);
	}

	/**
	 * Test display descending.
	 */
	@AfterClass
	public static void testDisplayDescending() {
		System.out.println("---testDisplayDescending---");
		sortingByHalfMeal.displayDescending(history);
	}

}
