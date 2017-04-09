package test.user;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenario;
import policies.DeliveryPolicy;
import policies.FairOccupationDeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.TargetProfit_DeliveryCost;
import restaurant.Dish;
import restaurant.HalfMeal;
import restaurant.Meal;
import restaurant.Menu;
import system.AddressPoint;
import system.AlaCarteOrder;
import system.Order;
import system.SpecialOffer;
import system.StandardMealOrder;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;

public class MyFoodoraServiceTest {

	MyFoodora myfoodora;
	MyFoodoraService myfoodora_service;
	

	static Date startingdate = null;
	
	@Before
	public void setUpBefore() throws Exception {
		InitialScenario.load("scenario_test_services.ini");	
		
		myfoodora = MyFoodora.getInstance();
		myfoodora_service = new MyFoodoraServiceImpl();
		
		//starting date for computing revenue between 2 dates
		String s = "2016.01.01";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		startingdate = format.parse(s);

	}

	@Test
	public void testSetServiceFee() {
		System.out.println("-----testSetServiceFee-----");
		myfoodora_service.setServiceFee(1);
		assertTrue(myfoodora.getService_fee()==1);
		
	}

	@Test
	public void testSetMarkUpPercentage() {
		System.out.println("-----testSetMarkUpPercentage-----");
		myfoodora_service.setMarkUpPercentage(0.01);
		assertTrue(myfoodora.getMarkup_percentage()==0.01);
	}

	@Test
	public void testSetDeliveryCost() {
		System.out.println("-----testSetDeliveryCost-----");
		myfoodora_service.setDeliveryCost(0.5);
		assertTrue(myfoodora.getDelivery_cost()==0.5);
	}

	@Test
	public void testParse() {
		System.out.println("-----testParse-----");
		Customer customer = (Customer)myfoodora_service.selectUser("customer_1");
		Restaurant restaurant = (Restaurant)myfoodora_service.selectUser("restaurant_1");
		Meal meal = restaurant.getFullMealMenu().getMeals().get(0);
		Order order = new StandardMealOrder(customer,restaurant,meal);
		
		System.out.println("restaurant position = " + restaurant.getAddress());
		for (Courier c : myfoodora.getAvailableCouriers()){
			System.out.println("Courier <"+c.getName()+"> position ="+c.getPosition()+" has count: " + c.getCount());
		}
		
		//Should give the order to courier_1
		System.out.println("FastDeliveryPolicy:");
		myfoodora.setDeliveryPolicy(new FastestDeliveryPolicy());
		myfoodora_service.parse(order, myfoodora.getAvailableCouriers());
		
		//verify that the courier given by the algorithm of delivery policy (tested in its own test file) received the order
		Courier courier_1 = new FastestDeliveryPolicy().parse(order, myfoodora.getAvailableCouriers());
		assertTrue(courier_1.getWaitingOrders().contains(order));
		
		//Should give the order to courier_2
		System.out.println("FairOccupationDelivery");
		myfoodora.setDeliveryPolicy(new FairOccupationDeliveryPolicy());
		myfoodora_service.parse(order, myfoodora.getAvailableCouriers());
		
		//verify that the courier given by the algorithm of delivery policy (tested in its own test file) received the order
		Courier courier_2 = new FairOccupationDeliveryPolicy().parse(order, myfoodora.getAvailableCouriers());
		assertTrue(courier_2.getWaitingOrders().contains(order));
		
	}

	@Test
	public void testNotifyAll() {
		System.out.println("-----testNotifyAll-----");
		Restaurant r = (Restaurant)myfoodora_service.selectUser("restaurant_1");
		Meal supermeal = new HalfMeal("supertest",r.getMenu().getStarters().get(0),r.getMenu().getMaindishes().get(0));
		
		//customer c agrees to be notified
		Customer c = (Customer)myfoodora_service.selectUser("customer_1");
		c.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		
		myfoodora_service.notifyAll(supermeal);
		
		//verify that the new special offer appears on the board of customer c
		assertTrue(c.getSpecialoffers().contains(supermeal));
	}

	@Test
	public void testGetTotalIncome() throws ParseException {
		//Starting date for calculating the income/profit
		System.out.println("-----testGetTotalIncome-----");
		System.out.println(myfoodora.getHistory());
		System.out.println(myfoodora_service.getTotalIncome(startingdate, new Date()));
	}

	@Test
	public void testGetTotalProfit() throws ParseException {
		//Starting date for calculating the income/profit
		System.out.println("-----testGetTotalProfit-----");
		System.out.println(myfoodora.getHistory());
		System.out.println(myfoodora_service.getTotalProfit(startingdate, new Date()));
	}

	@Test
	public void testGetAverageIncomePerCustomer() throws ParseException {
		System.out.println("-----testGetAverageIncomePerCustomer-----");
		System.out.println(myfoodora.getHistory());
		System.out.println(myfoodora_service.getAverageIncomePerCustomer(startingdate, new Date()));
	}

	@Test
	public void testApplyTargetProfitPolicy() {
		System.out.println("-----testApplyTargetProfitPolicy-----");
		
		System.out.println("TargetProfit_DeliveryCost:");
		myfoodora.setTargetprofitpolicy(new TargetProfit_DeliveryCost(myfoodora));
		
		System.out.println("Delivery_Cost=" + myfoodora.getDelivery_cost());
		System.out.println("Service_fee="+myfoodora.getService_fee());
		System.out.println("Markup_percentage="+myfoodora.getMarkup_percentage());

		System.out.println("applying target profit policy");
		myfoodora_service.applyTargetProfitPolicy(5);
		
		System.out.println("Delivery_Cost=" + myfoodora.getDelivery_cost());
		System.out.println("Service_fee="+myfoodora.getService_fee());
		System.out.println("Markup_percentage="+myfoodora.getMarkup_percentage());
		
	}

	@Test
	public void testSelectUser() {
		System.out.println("-----testSelectUser-----");
		Customer c = new Customer("","","test",new AddressPoint(0,0),"","");
		myfoodora.addUser(c);
		User user = myfoodora_service.selectUser("test");
		assertEquals(c,user);
	}

	@Test
	public void testGetUsersOfAssignedType() {
		System.out.println("-----testGetUsersOfAssignedType-----");
		for (User u:myfoodora_service.getUsersOfAssignedType("Restaurant")){
			if (!(u instanceof Restaurant)){
				fail();
			}
		}
	}

	@Test
	public void testAskAgree2customers() {
		System.out.println("-----testAskAgree2customers-----");
		myfoodora_service.askAgree2customers("Do you agree ?");
	}

	@Test
	public void testGetHistory() {
		System.out.println("-----testGetHistory-----");
		System.out.println(myfoodora_service.getHistory());
	}

}
