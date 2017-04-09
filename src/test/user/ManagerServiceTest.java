package test.user;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.UserNotFoundException;
import initialization.InitialScenario;
import policies.FastestDeliveryPolicy;
import system.AddressPoint;
import user.model.Courier;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.ManagerService;

public class ManagerServiceTest {

	Manager manager;
	ManagerService manager_service;
	
	static Date startingdate;
	
	@Before
	public void setUpBefore() throws Exception {
		InitialScenario.load("scenario_test_services.ini");
		
		manager = new Manager("test","test","test");
		manager_service = manager.getManagerService();
		
		
		String s = "2016.01.01";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		startingdate = format.parse(s);
	}


	@Test
	public void testAddUser() {
		System.out.println("-----testAddUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");
		manager_service.addUser(user_test);
		
		//verify that the user is in the user list of myfoodora
		assertTrue(MyFoodora.getInstance().getUsers().contains(user_test));
	}

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

	@Test
	public void testActivateUser() throws UserNotFoundException {
		System.out.println("-----testActivateUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");

		manager_service.addUser(user_test);
		manager_service.activateUser(user_test);
		//verify that the user is in the active user list of myfoodora
		assertTrue(MyFoodora.getInstance().getActiveUsers().contains(user_test));
		
		
	}

	@Test
	public void testDisactivateUser() throws UserNotFoundException{
		System.out.println("-----testDisactivateUser-----");
		Courier user_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");

		manager_service.addUser(user_test);
		manager_service.activateUser(user_test);
		assertTrue(MyFoodora.getInstance().getActiveUsers().contains(user_test));

		//verify that the user is no more in the active user list of myfoodora
		manager_service.disactivateUser(user_test);
		assertFalse(MyFoodora.getInstance().getActiveUsers().contains(user_test));
	}
	

	@Test
	public void testSetServicefee() {
		System.out.println("-----testSetServiceFee-----");
		manager_service.setServiceFree(1);
		
		assertTrue(MyFoodora.getInstance().getService_fee()==1);
	}

	@Test
	public void testSetMarkupPercentage() {
		System.out.println("-----testMarkUpPencentage-----");
		manager_service.setMarkUpPencentage(0.01);
		
		assertTrue(MyFoodora.getInstance().getMarkup_percentage()==0.01);

	}

	@Test
	public void testSetDeliverycost() {
		System.out.println("-----testSetServiceFee-----");
		manager_service.setDeliveryCost(1);
		
		assertTrue(MyFoodora.getInstance().getDelivery_cost()==1);
	}

	@Test
	public void testGetTotalIncome() {
		System.out.println("-----testGetTotalIncom-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		System.out.println(manager_service.getTotalIncome(startingdate, new Date()));
		
	}

	@Test
	public void testGetTotalProfit() {
		System.out.println("-----testGetTotalProfit-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		System.out.println(manager_service.getTotalProfit(startingdate, new Date()));
	}

	@Test
	public void testGetAverageIncomePerCustomer() {
		System.out.println("-----testGetAverageIncomePerCustomer-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		System.out.println(manager_service.getAverageIncomePerCustomer(startingdate, new Date()));
	}

	@Test
	public void testDetermineParam2MeetTargetProfit() {
		//already tested in MyFoodoraServiceTest.java
	}

	@Test
	public void testGetBestRestaurant() {
		System.out.println("-----testBestRestaurant-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("restaurant")){
			Restaurant r = (Restaurant)u;
			System.out.println("The total income of restaurant <"+r.getName()+"> is "+r.getIncome());
		}
		manager_service.getBestRestaurant();
	}

	@Test
	public void testGetWorstRestaurant() {
		System.out.println("-----testGetWorstRestaurant-----");
		System.out.println(MyFoodora.getInstance().getHistory());
		
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("restaurant")){
			Restaurant r = (Restaurant)u;
			System.out.println("The total income of restaurant <"+r.getName()+"> is "+r.getIncome());
		}
		manager_service.getWorstRestaurant();
	}

	@Test
	public void testGetBestCourier() {
		System.out.println("-----testBestCourier-----");
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("COURIER")){
			Courier c = (Courier)u;
			System.out.println("Courier <"+c.getName()+"> + has count: " + c.getCount());
		}
		
		manager_service.getBestCourier();
	}

	@Test
	public void testGetWorstCourier() {
		System.out.println("-----testWorstCourier-----");
		for (User u :MyFoodora.getInstance().getUsersOfAssignedType("COURIER")){
			Courier c = (Courier)u;
			System.out.println("Courier <"+c.getName()+"> + has count: " + c.getCount());
		}
		
		manager_service.getWorstCourier();
	}

	@Test
	public void testSetDeliveryPolicy() {
		System.out.println("-----testSetDeliveryPolicy-----");
		manager_service.setDeliveryPolicy(new FastestDeliveryPolicy());
		
		assertTrue(MyFoodora.getInstance().getDeliverypolicy() instanceof FastestDeliveryPolicy);
	}

	@Test
	public void testDisplayUsers() {
		System.out.println("------testDisplayUsers-----");
		manager_service.displayUsers();
	}

	@Test
	public void testDisplayActiveUsers() {
		System.out.println("------testDisplayActiveUsers-----");
		manager_service.displayActiveUsers();
	}

	@Test
	public void testSelectUser() throws UserNotFoundException{
		System.out.println("------testSelectUser-----");
	
		Courier user_test = new Courier("test","test","user_test", new AddressPoint(0,0),"+06 00 00 00 00");
		manager_service.addUser(user_test);
		
		User user = manager_service.selectUser("user_test");
		
		assertEquals(user,user_test);
	}

	@Test
	public void testDisplayUsersOfAssignedType() {
		System.out.println("-----DisplayUsersOfAssignedType-----");
		System.out.println("example : m.displayUsersOfAssignedType(restaurant)");
		manager_service.displayUsersOfAssignedType("Restaurant");
	}

}
