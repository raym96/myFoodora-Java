package clui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.model.*;
import user.service.*;
import user.service.impl.*;

public class CommandProcessorTest {
	CommandProcessor c;
	MyFoodoraService m = new MyFoodoraServiceImpl();

	@Before
	public void setUpBeforeClass() throws Exception {
		//initial scenario
		Manager ceo = new Manager("ji","raymond","ceo","123456789");
		ceo.setActivated(true);
		MyFoodora.getInstance().addUser(ceo);
		
		c = new CommandProcessor();
	}

	@Test
	public void testLogin() {
		c.processCommand("login ceo 123456789");
	}

	@Test
	public void testLogout() {
		c.processCommand("login ceo 123456789");
		c.processCommand("logout");
	}

	@Test
	public void testRegisterRestaurant() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		MyFoodora.getInstance().displayUsers();
	}

	@Test
	public void testRegisterCustomer() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerCustomer francois hollande fhollande 2,7 password");
		MyFoodora.getInstance().displayUsers();
	}

	@Test
	public void testRegisterCourier() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerCourier nicolas sarkozy nsarkozy 2,7 password");
		MyFoodora.getInstance().displayUsers();
	}

	@Test
	public void testAddDishRestaurantMenu() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDishRestaurantMenu salad starter standard 3.3");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");

	}

	@Test
	public void testCreateMeal() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("createMeal iLoveChicken");
		
		Restaurant restaurant = (Restaurant)m.selectUser("restaurant_1");
		restaurant.getRestaurantService().displayAllMenu();
	}

	@Test
	public void testAddDish2Meal() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("createMeal iLoveChicken");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");
		
		c.processCommand("addDish2Meal ice-cream iLoveChicken");
		c.processCommand("addDish2Meal chicken iLoveChicken");
		
		Restaurant restaurant = (Restaurant)m.selectUser("restaurant_1");
		restaurant.getRestaurantService().displayAllMenu();
	}

	@Test
	public void testShowMeal() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("createMeal iLoveChicken");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDish2Meal chicken iLoveChicken");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");
		c.processCommand("addDish2Meal ice-cream iLoveChicken");

		
		c.processCommand("showMeal iLoveChicken");
	}

	@Test
	public void testSaveMeal() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("createMeal iLoveChicken");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDish2Meal chicken iLoveChicken");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");
		c.processCommand("addDish2Meal ice-cream iLoveChicken");

		c.processCommand("saveMeal iLoveChicken");
	}

	@Test
	public void testSetSpecialOffer() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("createMeal iLoveChicken");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDish2Meal chicken iLoveChicken");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");
		c.processCommand("addDish2Meal ice-cream iLoveChicken");
		c.processCommand("saveMeal iLoveChicken");
		
		c.processCommand("setSpecialOffer iLoveChicken");
		
		Restaurant restaurant = (Restaurant)m.selectUser("restaurant_1");
		restaurant.getRestaurantService().displayAllMenu();
	}

	@Test
	public void testRemoveFromSpecialOffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOrder() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("registerCustomer francois hollande fhollande 2,7 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("createMeal iLoveChicken");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDish2Meal chicken iLoveChicken");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");
		c.processCommand("addDish2Meal ice-cream iLoveChicken");
		c.processCommand("saveMeal iLoveChicken");
		c.processCommand("logout");

		
		c.processCommand("login fhollande password");
		c.processCommand("createOrder restaurant_1 iLoveChicken ");
		
		Customer customer = (Customer)m.selectUser("fhollande");
		System.out.println(customer.getShoppingCart());
	}

	@Test
	public void testAddItem2Order() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnDuty() {
		fail("Not yet implemented");
	}

	@Test
	public void testOffDuty() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindDeliverer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDeliveryPolicy() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProfitPolicy() {
		fail("Not yet implemented");
	}

	@Test
	public void testAssociateCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowCourierDeliveries() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowRestaurantTop() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowCustomers() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowMenuItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowTotalProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testHelp() {
		fail("Not yet implemented");
	}

}
