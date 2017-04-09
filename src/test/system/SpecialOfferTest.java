package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import restaurant.FullMeal;
import restaurant.Menu;
import system.AddressPoint;
import system.SpecialOffer;
import user.model.Restaurant;

public class SpecialOfferTest {

	private static SpecialOffer specialOffer = null;
	
	@Test
	public void testSpecialOffer() {
		Restaurant restaurant = new Restaurant("Chinese Restaurant", "restaurant_2", new AddressPoint("2.0,2.0"));
		
		Menu menu = new Menu();
		menu.initMenu();
		FullMeal fm2 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		
		specialOffer = new SpecialOffer(restaurant, fm2);
		assertNotNull(specialOffer);
	}

	@AfterClass
	public static void testToString() {
		System.out.println(specialOffer);
	}

}
