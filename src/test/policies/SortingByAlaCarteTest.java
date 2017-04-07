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
import system.AlaCarteOrder;
import system.Order;
import system.SpecialMealOrder;
import system.StandardMealOrder;
import user.Courier;
import user.Customer;
import user.Restaurant;

public class SortingByAlaCarteTest {

	private static SortingByAlaCarte sortingByAlaCarte = null;
	private static ArrayList<Order> history = new ArrayList<Order>();

	private static HashMap<HalfMeal, Integer> map = null;
	
	private static Dish d1 = null;
	private static Dish	d2 = null;
	
	@BeforeClass
	public static void testSortingByRestaurant(){
		sortingByAlaCarte = new SortingByAlaCarte();
		assertNotNull(sortingByAlaCarte);
	}
	
	@BeforeClass
	public static void init(){
		Menu menu = new Menu();
		menu.initMenu();
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		d1 = menu.getDishes().get(0);
		d2 = menu.getDishes().get(1);
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		
		AlaCarteOrder alaCarteOrder1 = new AlaCarteOrder(c, r, d1);
		alaCarteOrder1.setCourier(cr);
		
		AlaCarteOrder alaCarteOrder2 = new AlaCarteOrder(c, r, d1);
		alaCarteOrder2.setCourier(cr);
		
		AlaCarteOrder alaCarteOrder3 = new AlaCarteOrder(c, r, d1);
		alaCarteOrder3.setCourier(cr);
		
		AlaCarteOrder alaCarteOrder4 = new AlaCarteOrder(c, r, d1);
		alaCarteOrder4.setCourier(cr);
		
		AlaCarteOrder alaCarteOrder5 = new AlaCarteOrder(c, r, d2);
		alaCarteOrder5.setCourier(cr);
		
		AlaCarteOrder alaCarteOrder6 = new AlaCarteOrder(c, r, d2);
		alaCarteOrder6.setCourier(cr);
		

		history.add(alaCarteOrder1);
		history.add(alaCarteOrder2);
		history.add(alaCarteOrder3);
		history.add(alaCarteOrder4);
		history.add(alaCarteOrder5);
		history.add(alaCarteOrder6);
		
		System.out.println("orders = ");
		System.out.println(history);
	}
	
	
	@Before
	public void testCountOccurence() {
		map = sortingByAlaCarte.countOccurence(history);
		assertTrue(map.containsKey(d1));
		assertTrue(map.containsKey(d2));
		assertTrue(map.get(d1) == 4);
		assertTrue(map.get(d2) == 2);
	}

	@Test
	public void testSortByValues() {
		HashMap<Restaurant, Integer> result = sortingByAlaCarte.sortByValues(map);
		System.out.println("---testSortByValues()---");
		System.out.println(result);
	}

	@Test
	public void testSortByValuesReversed() {
		HashMap<Restaurant, Integer> result = sortingByAlaCarte.sortByValuesReversed(map);
		System.out.println("---testSortByValuesReversed()---");
		System.out.println(result);
	}

	@AfterClass
	public static void testDisplayAscending() {
		System.out.println("---testDisplayAscending---");
		sortingByAlaCarte.displayAscending(history);
	}

	@AfterClass
	public static void testDisplayDescending() {
		System.out.println("---testDisplayDescending---");
		sortingByAlaCarte.displayDescending(history);
	}

}
