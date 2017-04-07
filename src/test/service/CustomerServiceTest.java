package test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenario;
import model.customer.LotteryCard;
import model.customer.PointCard;
import model.customer.StandardCard;
import model.myfoodora.History;
import model.restaurant.Dish;
import model.restaurant.Meal;
import model.restaurant.MealOrder;
import model.restaurant.Order;
import model.restaurant.SpecialMealOrder;
import model.users.Customer;
import model.users.MyFoodora;
import model.users.Restaurant;
import service.CustomerService;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class CustomerServiceTest {

	MyFoodora myfoodora;
	MyFoodoraService m;
	
	Customer customer;
	CustomerService c;
	
	Restaurant r;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		InitialScenario.load("scenario_test_services.ini");
		myfoodora = MyFoodora.getInstance();
		m = new MyFoodoraServiceImpl();
		
		customer = (Customer)m.selectUser("customer_1");
		c = customer.getCustomerService();
		r = (Restaurant)m.selectUser("restaurant_1");
	}



	@Test
	public void testAddSpecialMealOrder() {
		System.out.println("-------testAddSpecialMealOrder------");
		Meal meal = r.getSpecialmealmenu().getMeals().get(0);
		c.addSpecialMealOrder(r, meal.getName());
	
		//verify that the last item in the shopping cart of the customer is the new meal
		System.out.println(customer.getShoppingCart());
	}

	@Test
	public void testAddStandardMealOrder() {
		System.out.println("------testAddStandardMealOrder------");
		Meal meal = r.getHalfMealMenu().getMeals().get(0);
		c.addStandardMealOrder(r, meal.getName(), "Half-meal");
		
		//verify that the last item in the shopping cart of the customer is the new meal
		System.out.println(customer.getShoppingCart());
	}

	@Test
	public void testAddAlaCarteOrder() {
		System.out.println("------testAddAlaCarteOrder------");
		Dish dish = r.getMenu().getDishes().get(0);
		c.addAlaCarteOrder(r, dish.getDishName());
		
		//verify that the last item in the shopping cart of the customer is the new meal
		System.out.println(customer.getShoppingCart());
	}


	@Test
	public void testPay() {
		System.out.println("------testPay------");
		c.pay();
	}

	@Test
	public void testRegisterCard() {
		System.out.println("------testRegisterCard------");
		c.registerCard("PointCard");
		assertTrue(customer.getCard() instanceof PointCard);
		c.registerCard("LotteryCard");
		assertTrue(customer.getCard() instanceof LotteryCard);
		
		c.registerCard("UnexistantCard");
		
	}

	@Test
	public void testUnregisterCard() {
		System.out.println("------testUnregisterCard------");
		c.registerCard("PointCard");
		c.unregisterCard();
		assertTrue(customer.getCard() instanceof StandardCard);
	}

	@Test
	public void testGetHistory() {
		System.out.println("------testGetHistory------");
		System.out.println(c.getHistory());
		assertTrue(c.getHistory() instanceof History);
	}

	@Test
	public void testGetPoints() {
		System.out.println("------testGetPoints-------");
		//clear shopping cart, add a new order
		customer.getShoppingCart().clear();
		Meal meal = r.getSpecialmealmenu().getMeals().get(0);
		c.addSpecialMealOrder(r, meal.getName());
		
		//set the card to a point card
		c.registerCard("PointCard");
		
		//pay
		c.pay();
		
		System.out.println(customer.getUsername() + " has "+c.getPoints()+" points.");
	}

	@Test
	public void testGiveConsensusBeNotifiedSpecialOffers() {
		System.out.println("------testGiveConsensusBeNotifiedSpecialOffers------");
		//verify that customer has been added to the list of special offer observers
		c.giveConsensusBeNotifiedSpecialOffers();
		assertTrue(MyFoodora.getInstance().getSpecialOfferObserver().contains(customer));
	}

	@Test
	public void testRemoveConsensusBeNotifiedSpecialOffers() {
		System.out.println("------testRemoveConsensusBeNotifiedSpecialOffers------");
		c.removeConsensusBeNotifiedSpecialOffers();
		assertFalse(MyFoodora.getInstance().getSpecialOfferObserver().contains(customer));
	}

}
