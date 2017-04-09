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
import system.AlaCarteOrder;
import system.Order;
import system.SpecialMealOrder;
import system.StandardMealOrder;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;

public class SortingByHalfMealTest {

	private static SortingByHalfMeal sortingByHalfMeal = null;
	private static ArrayList<Order> history = new ArrayList<Order>();

	private static HashMap<HalfMeal, Integer> map = null;
	
	private static HalfMeal hm1 = null;
	private static HalfMeal hm2 = null;
	
	@BeforeClass
	public static void testSortingByRestaurant(){
		sortingByHalfMeal = new SortingByHalfMeal();
		assertNotNull(sortingByHalfMeal);
	}
	
	@BeforeClass
	public static void init(){
		Menu menu = new Menu();
		menu.initMenu();
		
		Courier cr = new Courier("Sanders", "Bernie", "courier_3", new AddressPoint(1.0,3.1), "+33 8 30 10 93 29");
		hm1 = new HalfMeal("HM2", menu.getStarters().get(0), menu.getMaindishes().get(0));
		hm2 = new HalfMeal("HM2", menu.getStarters().get(1), menu.getMaindishes().get(1));
		
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		
		StandardMealOrder standardMealOrder1 = new StandardMealOrder(c, r, hm1);
		standardMealOrder1.setCourier(cr);
		
		StandardMealOrder standardMealOrder2 = new StandardMealOrder(c, r, hm2);
		standardMealOrder2.setCourier(cr);
		
		StandardMealOrder standardMealOrder3 = new StandardMealOrder(c, r, hm1);
		standardMealOrder3.setCourier(cr);
		
		StandardMealOrder standardMealOrder4 = new StandardMealOrder(c, r, hm1);
		standardMealOrder4.setCourier(cr);
		
		SpecialMealOrder specialMealOrder1 = new SpecialMealOrder(c, r, hm1);
		specialMealOrder1.setCourier(cr);
		
		SpecialMealOrder specialMealOrder2 = new SpecialMealOrder(c, r, hm1);
		specialMealOrder2.setCourier(cr);
		

		history.add(standardMealOrder1);
		history.add(standardMealOrder2);
		history.add(standardMealOrder3);
		history.add(standardMealOrder4);
		history.add(specialMealOrder1);
		history.add(specialMealOrder2);
		
		System.out.println("orders = ");
		System.out.println(history);
	}
	
	
	@Before
	public void testCountOccurence() {
		map = sortingByHalfMeal.countOccurence(history);
		assertTrue(map.containsKey(hm1));
		assertTrue(map.containsKey(hm2));
		assertTrue(map.get(hm1) == 3);
		assertTrue(map.get(hm2) == 1);
	}

	@Test
	public void testSortByValues() {
		HashMap<Restaurant, Integer> result = sortingByHalfMeal.sortByValues(map);
		System.out.println("---testSortByValues()---");
		System.out.println(result);
	}

	@Test
	public void testSortByValuesReversed() {
		HashMap<Restaurant, Integer> result = sortingByHalfMeal.sortByValuesReversed(map);
		System.out.println("---testSortByValuesReversed()---");
		System.out.println(result);
	}

	@AfterClass
	public static void testDisplayAscending() {
		System.out.println("---testDisplayAscending---");
		sortingByHalfMeal.displayAscending(history);
	}

	@AfterClass
	public static void testDisplayDescending() {
		System.out.println("---testDisplayDescending---");
		sortingByHalfMeal.displayDescending(history);
	}

}
