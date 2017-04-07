package test.model.myfoodora;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import model.myfoodora.SpecialOffer;
import model.restaurant.FullMeal;
import model.restaurant.Menu;
import model.users.AddressPoint;
import model.users.Restaurant;

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
