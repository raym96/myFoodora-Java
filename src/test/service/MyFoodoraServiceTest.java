package test.service;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenario;
import model.myfoodora.FairOccupationDelivery;
import model.myfoodora.FastestDelivery;
import model.myfoodora.SpecialOffer;
import model.restaurant.Meal;
import model.restaurant.Order;
import model.restaurant.SpecialMeal;
import model.restaurant.StandardMealOrder;
import model.users.Courier;
import model.users.Customer;
import model.users.MyFoodora;
import model.users.Restaurant;
import model.users.User;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class MyFoodoraServiceTest {

	MyFoodora myfoodora = MyFoodora.getInstance();
	MyFoodoraService m = new MyFoodoraServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitialScenario.load("init.ini");		
	}

	@Test
	public void testSetServiceFee() {
		m.setServiceFee(1);
		assertTrue(myfoodora.getService_fee()==1);
	}

	@Test
	public void testSetMarkUpPercentage() {
		m.setMarkUpPercentage(0.01);
		assertTrue(myfoodora.getMarkup_percentage()==1);
	}

	@Test
	public void testSetDeliveryCost() {
		m.setDeliveryCost(0.5);
		assertTrue(myfoodora.getDelivery_cost()==0.5);
	}

	@Test
	public void testParse() {
		Customer customer = (Customer)m.selectUser("customer_1");
		Restaurant restaurant = (Restaurant)m.selectUser("restaurant_1");
		Meal meal = restaurant.getHalfMealMenu().getMeals().get(0);
		Order order = new StandardMealOrder(customer,restaurant,meal);
		
		System.out.println("restaurant position = " + restaurant.getAddress());
		for (Courier c : myfoodora.getAvailableCouriers()){
			System.out.println("Courier <"+c.getName()+"> position ="+c.getPosition()+" has count: " + c.getCount());
		}
		
		myfoodora.setDeliveryPolicy(new FastestDelivery());
		m.parse(order, myfoodora.getAvailableCouriers());
		Courier courier = new FastestDelivery().parse(order, myfoodora.getAvailableCouriers());
		
		//verify that the courier given by the algorithm of delivery policy (tested in its own test file) received the order
		assertTrue(courier.getWaitingOrders().contains(order));
	}

	@Test
	public void testNotifyAll() {
		Restaurant r = (Restaurant)m.selectUser("restaurant_1");
		Meal supermeal = new SpecialMeal("supertest",r.getMenu().getStarters().get(0),r.getMenu().getMaindishes().get(0));
		SpecialOffer specialoffer = new SpecialOffer(r,supermeal);
		
		//customer c agrees to be notified
		Customer c = (Customer)m.selectUser("customer_1");
		c.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		
		m.notifyAll(specialoffer);
		
		//verify that the new special offer appears on the board of customer c
		assertTrue(c.getSpecialoffers().contains(specialoffer));
	}

	@Test
	public void testGetTotalIncome() throws ParseException {
		String s = "2016.01.01";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Date date = format.parse(s);
		System.out.println(m.getTotalIncome(date, new Date()));
	}

	@Test
	public void testGetTotalProfit() throws ParseException {
		String s = "2016.01.01";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Date date = format.parse(s);
		System.out.println(m.getTotalProfit(date, new Date()));
	}

	@Test
	public void testGetAverageIncomePerCustomer() throws ParseException {
		String s = "2016.01.01";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Date date = format.parse(s);
		System.out.println(m.getAverageIncomePerCustomer(date, new Date()));
	}

	@Test
	public void testApplyTargetProfitPolicy() {
		m.applyTargetProfitPolicy(1000);
	}

	@Test
	public void testSelectUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsersOfAssignedType() {
		fail("Not yet implemented");
	}

	@Test
	public void testAskAgree2customers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHistory() {
		fail("Not yet implemented");
	}

}
