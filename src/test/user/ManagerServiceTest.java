/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.NameNotFoundException;
import initialization.InitialScenarioOld;
import policies.FastestDeliveryPolicy;
import system.AddressPoint;
import user.model.Courier;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.ManagerService;


/**
 * The Class ManagerServiceTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ManagerServiceTest {

	/** The manager. */
	Manager manager;
	
	/** The manager service. */
	ManagerService manager_service;
	
	/** The startingdate. */
	static Date startingdate;
	
	/**
	 * Sets the up before.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		InitialScenarioOld.load("scenario_test_services.ini");
		
		manager = new Manager("test","test","test");
		manager_service = manager.getManagerService();
		
		
		String s = "2016.01.01";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		startingdate = format.parse(s);
	}


	/**
	 * Test add user.
	 */
	@Test
	public void testAddUser() {
		System.out.println("-----testAddUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");
		manager_service.addUser(user_test);
		
		//verify that the user is in the user list of myfoodora
		assertTrue(MyFoodora.getInstance().getUsers().contains(user_test));
	}

	/**
	 * Test remove user.
	 */
	@Test
	public void testRemoveUser() {
		System.out.println("-----testRemoveUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");
		
		manager_service.addUser(user_test);
		assertTrue(MyFoodora.getInstance().getUsers().contains(user_test));

		//verify that the user is no more in the user list of myfoodora
		manager_service.removeUser(user_test);
		assertFalse(MyFoodora.getInstance().getUsers().contains(user_test));
	}

	/**
	 * Test activate user.
	 *
	 * @throws NameNotFoundException the user not found exception
	 */
	@Test
	public void testActivateUser() throws NameNotFoundException {
		System.out.println("-----testActivateUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");

		manager_service.addUser(user_test);
		manager_service.activateUser(user_test);
		//verify that the user is in the active user list of myfoodora
		assertTrue(MyFoodora.getInstance().getActiveUsers().contains(user_test));
		
		
	}

	/**
	 * Test disactivate user.
	 *
	 * @throws NameNotFoundException the user not found exception
	 */
	@Test
	public void testDisactivateUser() throws NameNotFoundException{
		System.out.println("-----testDisactivateUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");

		manager_service.addUser(user_test);
		manager_service.activateUser(user_test);
		assertTrue(MyFoodora.getInstance().getActiveUsers().contains(user_test));

		//verify that the user is no more in the active user list of myfoodora
		manager_service.disactivateUser(user_test);
		assertFalse(MyFoodora.getInstance().getActiveUsers().contains(user_test));
	}
	

	/**
	 * Test set servicefee.
	 */
	@Test
	public void testSetServicefee() {
		System.out.println("-----testSetServiceFee-----");
		manager_service.setServiceFree(1);
		
		assertTrue(MyFoodora.getInstance().getService_fee()==1);
	}

	/**
	 * Test set markup percentage.
	 */
	@Test
	public void testSetMarkupPercentage() {
		System.out.println("-----testMarkUpPencentage-----");
		manager_service.setMarkUpPencentage(0.01);
		
		assertTrue(MyFoodora.getInstance().getMarkup_percentage()==0.01);

	}

	/**
	 * Test set deliverycost.
	 */
	@Test
	public void testSetDeliverycost() {
		System.out.println("-----testSetServiceFee-----");
		manager_service.setDeliveryCost(1);
		
		assertTrue(MyFoodora.getInstance().getDelivery_cost()==1);
	}

	/**
	 * Test get total income.
	 */
	@Test
	public void testGetTotalIncome() {
		System.out.println("-----testGetTotalIncom-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		System.out.println(manager_service.showTotalIncome(startingdate, new Date()));
		
	}

	/**
	 * Test get total profit.
	 */
	@Test
	public void testGetTotalProfit() {
		System.out.println("-----testGetTotalProfit-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		System.out.println(manager_service.showTotalProfit(startingdate, new Date()));
	}

	/**
	 * Test get average income per customer.
	 */
	@Test
	public void testGetAverageIncomePerCustomer() {
		System.out.println("-----testGetAverageIncomePerCustomer-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		System.out.println(manager_service.showAverageIncomePerCustomer(startingdate, new Date()));
	}

	/**
	 * Test determine param 2 meet target profit.
	 */
	@Test
	public void testDetermineParam2MeetTargetProfit() {
		//already tested in MyFoodoraServiceTest.java
	}

	/**
	 * Test get best restaurant.
	 */
	@Test
	public void testGetBestRestaurant() {
		System.out.println("-----testBestRestaurant-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("restaurant")){
			Restaurant r = (Restaurant)u;
			System.out.println("The total income of restaurant <"+r.getName()+"> is "+r.getIncome());
		}
		manager_service.showRestaurantDesc();
	}

	/**
	 * Test get worst restaurant.
	 */
	@Test
	public void testGetWorstRestaurant() {
		System.out.println("-----testGetWorstRestaurant-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("restaurant")){
			Restaurant r = (Restaurant)u;
			System.out.println("The total income of restaurant <"+r.getName()+"> is "+r.getIncome());
		}
		manager_service.showRestaurantAsc();
	}

	/**
	 * Test get best courier.
	 */
	@Test
	public void testGetBestCourier() {
		System.out.println("-----testBestCourier-----");
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("COURIER")){
			Courier c = (Courier)u;
			System.out.println("Courier <"+c.getName()+"> + has count: " + c.getCount());
		}
		
		manager_service.showCourierDesc();
	}

	/**
	 * Test get worst courier.
	 */
	@Test
	public void testGetWorstCourier() {
		System.out.println("-----testWorstCourier-----");
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("COURIER")){
			Courier c = (Courier)u;
			System.out.println("Courier <"+c.getName()+"> + has count: " + c.getCount());
		}
		
		manager_service.showCourierAsc();
	}

	/**
	 * Test set delivery policy.
	 */
	@Test
	public void testSetDeliveryPolicy() {
		System.out.println("-----testSetDeliveryPolicy-----");
		manager_service.setDeliveryPolicy(new FastestDeliveryPolicy());
		
		assertTrue(MyFoodora.getInstance().getDeliverypolicy() instanceof FastestDeliveryPolicy);
	}

	/**
	 * Test display users.
	 */
	@Test
	public void testDisplayUsers() {
		System.out.println("------testDisplayUsers-----");
		manager_service.displayUsers();
	}

	/**
	 * Test display active users.
	 */
	@Test
	public void testDisplayActiveUsers() {
		System.out.println("------testDisplayActiveUsers-----");
		manager_service.displayActiveUsers();
	}

	/**
	 * Test select user.
	 *
	 * @throws NameNotFoundException the user not found exception
	 */
	@Test
	public void testSelectUser() throws NameNotFoundException{
		System.out.println("------testSelectUser-----");
	
		Courier user_test = new Courier("test","test","user_test", new AddressPoint(0,0),"+06 00 00 00 00");
		manager_service.addUser(user_test);
		
		User user = manager_service.selectUser("user_test");
		
		assertEquals(user,user_test);
	}

	/**
	 * Test display users of assigned type.
	 */
	@Test
	public void testDisplayUsersOfAssignedType() {
		System.out.println("-----DisplayUsersOfAssignedType-----");
		System.out.println("example : m.displayUsersOfAssignedType(restaurant)");
		manager_service.displayUsersOfAssignedType("Restaurant");
	}

}
