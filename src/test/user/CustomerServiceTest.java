/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clui.InitialScenario;
import exceptions.NameAlreadyExistsException;
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
		
		customer = (Customer)myfoodora_service.selectUser("emacron");
		customer_service = customer.getService();
		restaurant = (Restaurant)myfoodora_service.selectUser("french");
		restaurant2 = (Restaurant)myfoodora_service.selectUser("chinese");
	}




	/**
	 * Test pay.
	 *
	 * @throws NameNotFoundException the name not found exception
	 * @throws NameAlreadyExistsException the name already exists exception
	 * @throws ParseException the parse exception
	 */
	@Test
	public void testPay() throws NameNotFoundException, NameAlreadyExistsException, ParseException {
		System.out.println("------testPay------");
		customer_service.createOrder("french", "myorder");
		customer_service.addItem2Order( "myorder","choucroute");
	
		System.out.println(customer.getShoppingCart());
		customer_service.endOrder("myorder","01/01/2000");
	}

	/**
	 * Test register card.
	 *
	 * @throws NameNotFoundException the name not found exception
	 */
	@Test
	public void testRegisterCard() throws NameNotFoundException {
		System.out.println("------testRegisterCard------");
		customer_service.registerCard("Point");
		assertTrue(customer.getCard() instanceof PointCard);
		customer_service.registerCard("Lottery");
		assertTrue(customer.getCard() instanceof LotteryCard);
				
	}

	/**
	 * Test unregister card.
	 *
	 * @throws NameNotFoundException the name not found exception
	 */
	@Test
	public void testUnregisterCard() throws NameNotFoundException {
		System.out.println("------testUnregisterCard------");
		customer_service.registerCard("Point");
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
