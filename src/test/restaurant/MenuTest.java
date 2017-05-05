/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.NameNotFoundException;
import exceptions.NameAlreadyExistsException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import restaurant.Dessert;
import restaurant.MainDish;
import restaurant.Menu;
import restaurant.Starter;


/**
 * The Class MenuTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MenuTest {

	/** The menu. */
	private static Menu menu;
	
	/**
	 * Test menu.
	 */
	@BeforeClass
	public static void testMenu() {
		menu = new Menu();
		assertNotNull(menu);
		assertNotNull(menu.getMaindishes());
		assertNotNull(menu.getDesserts());
		assertNotNull(menu.getStarters());
	}
	
	/**
	 * Test init menu.
	 */
	@Test
	public void testInitMenu() {
		menu.initMenu();
		assertTrue(menu.getDesserts().size() > 0);
		assertTrue(menu.getMaindishes().size() > 0);
		assertTrue(menu.getStarters().size() > 0);
	}

	/**
	 * Test get dishes.
	 */
	@Test
	public void testGetDishes() {
		assertNotNull(menu.getDishes());
	}
	
	/**
	 * Test has dish.
	 */
	@Test
	public void testHasDish() {
		menu.initMenu();
		assertTrue(menu.hasDish(menu.getDishes().get(0).getDishName()));
		assertTrue(menu.hasDish(menu.getDishes().get(1).getDishName()));
		assertTrue(menu.hasDish(menu.getDishes().get(2).getDishName()));
	}

	/**
	 * Test add dish.
	 */
	@Test
	public void testaddDish() {
		MainDish m = new MainDish("Pork ribs", "standard", 9.1);
		Starter s = new Starter("Japanese soup", "gluten-free", 3.5);
		Dessert d = new Dessert("Nougat", "gluten-free", 2.2);

		try {
			menu.addDish(m);
			menu.addDish(d);
			menu.addDish(s);
		} catch (NameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(menu.hasDish(m.getDishName()));
		assertTrue(menu.hasDish(s.getDishName()));
		assertTrue(menu.hasDish(d.getDishName()));
	}

	/**
	 * Test remove dish.
	 */
	@Test
	public void testRemoveDish() {
		MainDish m = new MainDish("Pork ribs 222222222", "standard", 9.1);
		try {
			menu.addDish(m);
		} catch (NameAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertTrue(menu.hasDish(m.getDishName()));
		try {
			menu.removeDish(m.getDishName());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		assertFalse(menu.hasDish(m.getDishName()));
	}

	
	/**
	 * Test display.
	 */
	@Test
	public void testDisplay() {
		menu.display();
	}

}
