package test.restaurant;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DishNotFoundException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import restaurant.Dessert;
import restaurant.MainDish;
import restaurant.Menu;
import restaurant.Starter;

public class MenuTest {

	private static Menu menu;
	
	@BeforeClass
	public static void testMenu() {
		menu = new Menu();
		assertNotNull(menu);
		assertNotNull(menu.getMaindishes());
		assertNotNull(menu.getDesserts());
		assertNotNull(menu.getStarters());
	}
	
	@Test
	public void testInitMenu() {
		menu.initMenu();
		assertTrue(menu.getDesserts().size() > 0);
		assertTrue(menu.getMaindishes().size() > 0);
		assertTrue(menu.getStarters().size() > 0);
	}

	@Test
	public void testGetDishes() {
		assertNotNull(menu.getDishes());
	}
	
	@Test
	public void testHasDish() {
		menu.initMenu();
		assertTrue(menu.hasDish(menu.getDishes().get(0).getDishName()));
		assertTrue(menu.hasDish(menu.getDishes().get(1).getDishName()));
		assertTrue(menu.hasDish(menu.getDishes().get(2).getDishName()));
	}

	@Test
	public void testAddDish() {
		MainDish m = new MainDish("Pork ribs", "standard", 9.1);
		Starter s = new Starter("Japanese soup", "gluten-free", 3.5);
		Dessert d = new Dessert("Nougat", "gluten-free", 2.2);
		menu.addDish(d);
		menu.addDish(s);
		menu.addDish(m);
		assertTrue(menu.hasDish(m.getDishName()));
		assertTrue(menu.hasDish(s.getDishName()));
		assertTrue(menu.hasDish(d.getDishName()));
	}

	@Test
	public void testRemoveDish() {
		MainDish m = new MainDish("Pork ribs 222222222", "standard", 9.1);
		menu.addDish(m);
		assertTrue(menu.hasDish(m.getDishName()));
		try {
			menu.removeDish(m.getDishName());
		} catch (DishNotFoundException e) {
			e.printStackTrace();
		}
		assertFalse(menu.hasDish(m.getDishName()));
	}

	
	@Test
	public void testDisplay() {
		menu.display();
	}

}
