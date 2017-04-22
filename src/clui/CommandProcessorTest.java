package clui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.model.*;
import user.service.*;
import user.service.impl.*;

/**
 * The Class CommandProcessorTest.
 */
public class CommandProcessorTest {
	
	/** The c. */
	CommandProcessor c;
	
	/** The m. */
	MyFoodoraService m;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		//initial scenario
		InitialScenario.load("my_foodora.ini");
		c = new CommandProcessor();
		m = new MyFoodoraServiceImpl();
	}

	/**
	 * Test login.
	 */
	@Test
	public void testLogin() {
		c.processCommand("login ceo 123456789");
	}

	/**
	 * Test logout.
	 */
	@Test
	public void testLogout() {
		c.processCommand("login ceo 123456789");
		c.processCommand("logout");
	}

	/**
	 * Test register restaurant.
	 */
	@Test
	public void testRegisterRestaurant() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		MyFoodora.getInstance().displayUsers();
	}

	/**
	 * Test register customer.
	 */
	@Test
	public void testRegisterCustomer() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerCustomer francois hollande fhollande 2,7 password");
		MyFoodora.getInstance().displayUsers();
	}

	/**
	 * Test register courier.
	 */
	@Test
	public void testRegisterCourier() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerCourier nicolas sarkozy nsarkozy 2,7 password");
		MyFoodora.getInstance().displayUsers();
	}

	/**
	 * Test add dish restaurant menu.
	 */
	@Test
	public void testAddDishRestaurantMenu() {
		c.processCommand("login ceo 123456789");
		c.processCommand("registerRestaurant BonheurDAntony 1,1 restaurant_1 password");
		c.processCommand("logout");
		
		c.processCommand("login restaurant_1 password");
		c.processCommand("addDishRestaurantMenu chicken main standard 3.3");
		c.processCommand("addDishRestaurantMenu salad starter standard 3.3");
		c.processCommand("addDishRestaurantMenu ice-cream dessert standard 3.3");
		
		Restaurant restaurant = (Restaurant)m.selectUser("restaurant_1");
		restaurant.getRestaurantService().displayAllMenu();
	}

	/**
	 * Test create meal.
	 */
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

	/**
	 * Test add dish 2 meal.
	 */
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

	/**
	 * Test show meal.
	 */
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

	/**
	 * Test save meal.
	 */
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

	/**
	 * Test set special offer.
	 */
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

	/**
	 * Test remove from special offer.
	 */
	@Test
	public void testRemoveFromSpecialOffer() {
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
		
		c.processCommand("removeFromSpecialOffer iLoveChicken");
	}

	/**
	 * Test create order.
	 */
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

	/**
	 * Test add item 2 order.
	 */
	@Test
	public void testAddItem2Order() {
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
		c.processCommand("createOrder restaurant_1 ViveLaFrance ");
		
		c.processCommand("addItem2Order ViveLaFrance chicken");
		c.processCommand("addItem2Order ViveLaFrance iLoveChicken");
		
		Customer customer = (Customer)m.selectUser("fhollande");
		System.out.println(customer.getShoppingCart());
	}

	/**
	 * Test end order.
	 */
	@Test
	public void testEndOrder() {
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
		c.processCommand("createOrder restaurant_1 ViveLaFrance ");
		
		c.processCommand("addItem2Order ViveLaFrance chicken");
		c.processCommand("addItem2Order ViveLaFrance iLoveChicken");
		
		c.processCommand("endOrder ViveLaFrance 25/04/2017");
	}

	/**
	 * Test on duty.
	 */
	@Test
	public void testOnDuty() {
		c.processCommand("login hclinton password");
		c.processCommand("onduty");
	}

	/**
	 * Test off duty.
	 */
	@Test
	public void testOffDuty() {
		c.processCommand("login hclinton password");
		c.processCommand("offduty");
	}

	/**
	 * Test find deliverer.
	 */
	@Test
	public void testFindDeliverer() {
		fail("Not yet implemented");
	}

	/**
	 * Test set delivery policy.
	 */
	@Test
	public void testSetDeliveryPolicy() {
		fail("Not yet implemented");
	}

	/**
	 * Test set profit policy.
	 */
	@Test
	public void testSetProfitPolicy() {
		fail("Not yet implemented");
	}

	/**
	 * Test associate card.
	 */
	@Test
	public void testAssociateCard() {
		fail("Not yet implemented");
	}

	/**
	 * Test show courier deliveries.
	 */
	@Test
	public void testShowCourierDeliveries() {
		fail("Not yet implemented");
	}

	/**
	 * Test show restaurant top.
	 */
	@Test
	public void testShowRestaurantTop() {
		fail("Not yet implemented");
	}

	/**
	 * Test show customers.
	 */
	@Test
	public void testShowCustomers() {
		fail("Not yet implemented");
	}

	/**
	 * Test show menu item.
	 */
	@Test
	public void testShowMenuItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test show total profit.
	 */
	@Test
	public void testShowTotalProfit() {
		fail("Not yet implemented");
	}

	/**
	 * Test run test.
	 */
	@Test
	public void testRunTest() {
		fail("Not yet implemented");
	}

	/**
	 * Test help.
	 */
	@Test
	public void testHelp() {
		fail("Not yet implemented");
	}

}
