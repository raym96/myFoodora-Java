/*
 * 
 */
package test.restaurant;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.NameNotFoundException;
import restaurant.Dessert;
import restaurant.DishFactory;
import restaurant.MainDish;
import restaurant.Menu;
import restaurant.Starter;


/**
 * The Class DishFactoryTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class DishFactoryTest {

	/** The dish factory. */
	private static DishFactory dishFactory = null;
	
	/** The menu. */
	private static Menu menu = null;
	
	/**
	 * Test dish factory.
	 */
	@BeforeClass
	public static void testDishFactory() {
		
		menu = new Menu();
		menu.initMenu();
		
		dishFactory = new DishFactory(menu);
		
		assertNotNull(dishFactory);
		
		menu.display();
	}

	/**
	 * Test create dish.
	 *
	 * @throws NameNotFoundException the dish not found exception
	 */
	@Test(expected = NameNotFoundException.class)
	public void testCreateDish() throws NameNotFoundException {
		
		Starter s = menu.getStarters().get(0);
		MainDish m = menu.getMaindishes().get(0);
		Dessert d = menu.getDesserts().get(0);
		
		Starter s2 = (Starter) dishFactory.createDish(s.getDishName());
		MainDish m2 = (MainDish) dishFactory.createDish(m.getDishName());
		Dessert d2 = (Dessert) dishFactory.createDish(d.getDishName());
		
		assertTrue(menu.hasDish(s2.getDishName()));
		assertTrue(menu.hasDish(m2.getDishName()));
		assertTrue(menu.hasDish(d2.getDishName()));
		
		Starter s3 = (Starter) dishFactory.createDish("aaaaa");
		MainDish m3 = (MainDish) dishFactory.createDish("bbbbb");
		Dessert d3 = (Dessert) dishFactory.createDish("ccccc");
		
		assertNull(s3);
		assertNull(m3);
		assertNull(d3);
	}

}
