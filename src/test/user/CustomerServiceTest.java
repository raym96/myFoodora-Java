/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenarioOld;
import policies.LotteryCard;
import policies.PointCard;
import policies.StandardCard;
import restaurant.Dish;
import restaurant.Meal;
import system.History;
import system.Order;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.CustomerService;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;


/**
 * The Class CustomerServiceTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerServiceTest {

	/** The myfoodora. */
	MyFoodora myfoodora;
	
	/** The myfoodora service. */
	MyFoodoraService myfoodora_service;
	
	/** The customer. */
	Customer customer;
	
	/** The customer service. */
	CustomerService customer_service;
	
	/** The restaurant. */
	Restaurant restaurant;
	
	/** The restaurant 2. */
	Restaurant restaurant2;
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		InitialScenarioOld.load("scenario_test_services.ini");
		myfoodora = MyFoodora.getInstance();
		myfoodora_service = MyFoodora.getInstance().getService();
		
		customer = (Customer)myfoodora_service.selectUser("customer_1");
		customer_service = customer.getService();
		restaurant = (Restaurant)myfoodora_service.selectUser("restaurant_1");
		restaurant2 = (Restaurant)myfoodora_service.selectUser("restaurant_2");
	}



	/**
	 * Test add special meal order.
	 */
	@Test
	public void testCommandSpecialMeal() {
		System.out.println("-------testCommandSpecialMeal------");
		Meal meal = restaurant.getSpecialmealmenu().getMeals().get(0);
		customer_service.commandSpecialMeal(restaurant, meal.getName());
	
		//verify that the last item in the shopping cart of the customer is the new meal
		System.out.println(customer.getShoppingCart());
	}

	/**
	 * Test add standard meal order.
	 */
	@Test
	public void testCommandRegularMeal() {
		System.out.println("------testCommandRegularMeal------");
		Meal meal = restaurant.getHalfMealMenu().getMeals().get(0);
		Meal meal2 = restaurant.getFullMealMenu().getMeals().get(0);
		customer_service.commandRegularMeal(restaurant, meal.getName(), "Half-meal");
		customer_service.commandRegularMeal(restaurant, meal2.getName(), "Full-meal");
		customer_service.commandRegularMeal(restaurant2, meal2.getName(), "Full-meal");
		
		//verify that the last item in the shopping cart of the customer is the new meal
		System.out.println(customer.getShoppingCart());
	}

	/**
	 * Test add ala carte order.
	 */
	@Test
	public void testCommandAlaCarte() {
		System.out.println("------testCommandAlaCarte------");
		Dish dish1 = restaurant.getMenu().getDishes().get(0);
		Dish dish2 = restaurant.getMenu().getDishes().get(1);
		Dish dish3 = restaurant2.getMenu().getDishes().get(0);
		
		customer_service.commandAlaCarte(restaurant, dish1.getDishName());
		customer_service.commandAlaCarte(restaurant, dish2.getDishName());
		customer_service.commandAlaCarte(restaurant2, dish3.getDishName());

		//verify that the last item in the shopping cart of the customer is the new meal
		System.out.println(customer.getShoppingCart());
	}


	/**
	 * Test pay.
	 */
	@Test
	public void testPay() {
		System.out.println("------testPay------");
		Dish dish = restaurant.getMenu().getDishes().get(0);
		Meal meal = restaurant.getHalfMealMenu().getMeals().get(0);
		Meal specialmeal = restaurant.getSpecialmealmenu().getMeals().get(0);
		customer_service.commandAlaCarte(restaurant, dish.getDishName());
		customer_service.commandRegularMeal(restaurant, meal.getName(), "Half-meal");
		customer_service.commandSpecialMeal(restaurant, specialmeal.getName());
		
		System.out.println(customer.getShoppingCart());
		customer_service.endOrder();
	}

	/**
	 * Test register card.
	 */
	@Test
	public void testRegisterCard() {
		System.out.println("------testRegisterCard------");
		customer_service.registerCard("PointCard");
		assertTrue(customer.getCard() instanceof PointCard);
		customer_service.registerCard("LotteryCard");
		assertTrue(customer.getCard() instanceof LotteryCard);
		
		customer_service.registerCard("UnexistantCard");
		
	}

	/**
	 * Test unregister card.
	 */
	@Test
	public void testUnregisterCard() {
		System.out.println("------testUnregisterCard------");
		customer_service.registerCard("PointCard");
		customer_service.unregisterCard();
		assertTrue(customer.getCard() instanceof StandardCard);
	}

	/**
	 * Test get history.
	 */
	@Test
	public void testGetHistory() {
		System.out.println("------testGetHistory------");
		System.out.println(customer_service.getHistory());
		assertTrue(customer_service.getHistory() instanceof History);
	}

	/**
	 * Test get points.
	 */
	@Test
	public void testGetPoints() {
		System.out.println("------testGetPoints-------");
		//clear shopping cart, add a new order
		customer.getShoppingCart().clear();
		Meal meal = restaurant.getSpecialmealmenu().getMeals().get(0);
		customer_service.commandSpecialMeal(restaurant, meal.getName());
		
		//set the card to a point card
		customer_service.registerCard("PointCard");
		
		//pay
		customer_service.endOrder();
		
		System.out.println(customer.getUsername() + " has "+customer_service.getPoints()+" points.");
	}

	/**
	 * Test give consensus be notified special offers.
	 */
	@Test
	public void testGiveConsensusBeNotifiedSpecialOffers() {
		System.out.println("------testGiveConsensusBeNotifiedSpecialOffers------");
		//verify that customer has been added to the list of special offer observers
		customer_service.giveConsensusBeNotifiedSpecialOffers();
		assertTrue(MyFoodora.getInstance().getSpecialOfferObserver().contains(customer));
	}

	/**
	 * Test remove consensus be notified special offers.
	 */
	@Test
	public void testRemoveConsensusBeNotifiedSpecialOffers() {
		System.out.println("------testRemoveConsensusBeNotifiedSpecialOffers------");
		customer_service.removeConsensusBeNotifiedSpecialOffers();
		assertFalse(MyFoodora.getInstance().getSpecialOfferObserver().contains(customer));
	}

}
