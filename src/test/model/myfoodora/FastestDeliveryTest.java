package test.model.myfoodora;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.UserNotFoundException;
import model.myfoodora.FairOccupationDelivery;
import model.myfoodora.FastestDelivery;
import model.restaurant.FullMeal;
import model.restaurant.Menu;
import model.restaurant.StandardMealOrder;
import model.users.AddressPoint;
import model.users.Courier;
import model.users.Customer;
import model.users.MyFoodora;
import model.users.Restaurant;

public class FastestDeliveryTest {

	private FastestDelivery fastestDelivery = new FastestDelivery();
	
	@Test
	public void testParse() throws UserNotFoundException {
		MyFoodora myFoodora = MyFoodora.getInstance();
		
		Courier cr2 = new Courier("Clinton", "Hillary", "courier_2", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Courier cr3 = new Courier("Clinton", "Hillary", "courier_3", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		Courier cr4 = new Courier("Clinton", "Hillary", "courier_4", new AddressPoint("5.5,12.4"), "+33 6 29 04 59 20");
		myFoodora.register(cr2);
		myFoodora.register(cr3);
		myFoodora.register(cr4);
		myFoodora.activateUser(cr2);
		myFoodora.activateUser(cr3);
		myFoodora.activateUser(cr4);
		
		Menu menu = new Menu();
		menu.initMenu();
		Restaurant r = new Restaurant("French Restaurant", "restaurant_1", new AddressPoint(1.0,1.0));
		Customer c = new Customer("Liu", "Bei", "customer_1", new AddressPoint(100.0,100.0), "liubei@gmail.com", "+33 1 01 01 02 01");
		FullMeal fm1 = new FullMeal("FM2", menu.getStarters().get(0), menu.getMaindishes().get(0), menu.getDesserts().get(0));
		StandardMealOrder standardMealOrder = new StandardMealOrder(c, r, fm1);
		
		ArrayList<Courier> activecouriers = myFoodora.getActivecouriers();
		
		Courier courier = fastestDelivery.parse(standardMealOrder, activecouriers);
		assertNotNull(courier);
		System.out.println(courier);
		
	}

}
