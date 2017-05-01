/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clui.InitialScenario;
import exceptions.NameNotFoundException;
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
		InitialScenario.load("my_foodora.ini");;
		myfoodora = MyFoodora.getInstance();
		myfoodora_service = MyFoodora.getInstance().getService();
		
		customer = (Customer)myfoodora_service.selectUser("customer_1");
		customer_service = customer.getService();
		restaurant = (Restaurant)myfoodora_service.selectUser("restaurant_1");
		restaurant2 = (Restaurant)myfoodora_service.selectUser("restaurant_2");
	}




	/**
	 * Test pay.
	 */
	@Test
	public void testPay() {
		System.out.println("------testPay------");
		Dish dish = restaurant.getMenu().getDishes().get(0);
		Meal meal = restaurant.getMealMenu().getMeals().get(0);
		Meal specialmeal = restaurant.getSpecialmealmenu().getMeals().get(0);
		customer_service.commandAlaCarte(restaurant, dish.getDishName());
		customer_service.commandRegularMeal(restaurant, meal.getName(), "Half-meal");
		customer_service.commandSpecialMeal(restaurant, specialmeal.getName());
		
		System.out.println(customer.getShoppingCart());
		customer_service.endOrder();
	}

	/**
	 * Test register card.
	 * @throws NameNotFoundException 
	 */
	@Test
	public void testRegisterCard() throws NameNotFoundException {
		System.out.println("------testRegisterCard------");
		customer_service.registerCard("PointCard");
		assertTrue(customer.getCard() instanceof PointCard);
		customer_service.registerCard("LotteryCard");
		assertTrue(customer.getCard() instanceof LotteryCard);
		
		customer_service.registerCard("UnexistantCard");
		
	}

	/**
	 * Test unregister card.
	 * @throws NameNotFoundException 
	 */
	@Test
	public void testUnregisterCard() throws NameNotFoundException {
		System.out.println("------testUnregisterCard------");
		customer_service.registerCard("PointCard");
		customer_service.unregisterCard();
		assertTrue(customer.getCard() instanceof StandardCard);
	}



	/**
	 * Test give consensus be notified special offers.
	 */
	@Test
	public void testGiveConsensusBeNotifiedSpecialOffers() {
		System.out.println("------testGiveConsensusBeNotifiedSpecialOffers------");
		//verify that customer has been added to the list of special offer observers
		customer_service.giveConsensusBeNotifiedSpecialOffers();
		assertTrue(MyFoodora.getInstance().getSpecialOfferBoard().getObservers().contains(customer));
	}

	/**
	 * Test remove consensus be notified special offers.
	 */
	@Test
	public void testRemoveConsensusBeNotifiedSpecialOffers() {
		System.out.println("------testRemoveConsensusBeNotifiedSpecialOffers------");
		customer_service.removeConsensusBeNotifiedSpecialOffers();
		assertFalse(MyFoodora.getInstance().getSpecialOfferBoard().getObservers().contains(customer));
	}

}
