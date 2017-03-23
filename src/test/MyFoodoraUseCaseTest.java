package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.UserNotFoundException;
import model.myfoodora.*;
import model.user.*;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class MyFoodoraUseCaseTest {

	/**
	 * Use case scenario
	 * The following use case scenario describe examples of how the MyFoodora should function.
	 * @throws UserNotFoundException 
	 **/
	private MyFoodora myFoodora = MyFoodora.getInstance();
	private MyFoodoraService commonMyFoodoraService = new MyFoodoraServiceImpl();
	
	/*
	 * Startup scenario
		1. the system loads all registered users: at least 2 manager (the CEO and his deputy
			i.e. ajoint), 5 restaurants and 2 couriers, 7 customers, 4 full-meals per restaurant...
		2. the system sends alerts to the customers that agreed to be notified of special offers
	 */
	@Test
	public void testStartupScenario() throws UserNotFoundException {
		
		System.out.println("----------------------- Startup scenario -----------------------");
	
		// managers 
		Manager ceo = new Manager("Ji", "raym", "raym");
		Manager director = new Manager("He", "xiaoan", "hxa");
		// restaurants
		Restaurant restaurant_1 = new Restaurant("Beijing Restaurant", "restaurant_1", new AddressPoint(1.0, 1.0));
		Restaurant restaurant_2 = new Restaurant("Paris Restaurant", "restaurant_2", new AddressPoint(2.0, 2.0));
		Restaurant restaurant_3 = new Restaurant("New York Restaurant", "restaurant_3", new AddressPoint(3.0, 3.0));
		Restaurant restaurant_4 = new Restaurant("Mosco Restaurant", "restaurant_4", new AddressPoint(4.0, 4.0));
		Restaurant restaurant_5 = new Restaurant("Tokyo Restaurant", "restaurant_5", new AddressPoint(5.0, 5.0));
		// couriers
		Courier courier_1 = new Courier("Zhang", "David", "courier_1", new AddressPoint(10.0, 10.0), "+33 1 01 01 01 01 01");
		Courier courier_2 = new Courier("Chen", "Vincent", "courier_2", new AddressPoint(11.0, 11.0), "+33 1 01 01 01 01 02");
		// customers
		Customer customer_1 = new Customer("Zhang", "San", "customer_1", new AddressPoint(100.0, 100.0), "zhangsan@gmail.com", "+33 1 01 01 02 01");
		Customer customer_2 = new Customer("Li", "Si", "customer_2", new AddressPoint(101.0, 101.0), "lisi@gmail.com", "+33 1 01 01 02 02");
		Customer customer_3 = new Customer("Wang", "Wu", "customer_3", new AddressPoint(102.0, 102.0), "wangwu@gmail.com", "+33 1 01 01 02 03");
		Customer customer_4 = new Customer("Zhao", "Liu", "customer_4", new AddressPoint(103.0, 103.0), "zhaoliu@gmail.com", "+33 1 01 01 02 04");
		Customer customer_5 = new Customer("Chen", "Qi", "customer_5", new AddressPoint(104.0, 104.0), "chenqi@gmail.com", "+33 1 01 01 02 05");
		Customer customer_6 = new Customer("Liang", "Ba", "customer_6", new AddressPoint(105.0, 105.0), "liangba@gmail.com", "+33 1 01 01 02 06");
		Customer customer_7 = new Customer("Chu", "Jiu", "customer_7", new AddressPoint(106.0, 106.0), "chujiu@gmail.com", "+33 1 01 01 02 07");

		// registering
		ceo.registerOnFoodora();
		director.registerOnFoodora();
		restaurant_1.registerOnFoodora();
		restaurant_2.registerOnFoodora();
		restaurant_3.registerOnFoodora();
		restaurant_4.registerOnFoodora();
		restaurant_5.registerOnFoodora();
		courier_1.registerOnFoodora();
		courier_2.registerOnFoodora();
		customer_1.registerOnFoodora();
		customer_2.registerOnFoodora();
		customer_3.registerOnFoodora();
		customer_4.registerOnFoodora();
		customer_5.registerOnFoodora();
		customer_6.registerOnFoodora();
		customer_7.registerOnFoodora();
		// activiting
		commonMyFoodoraService.activateUser(ceo);
		commonMyFoodoraService.activateUser(director);
		commonMyFoodoraService.activateUser(restaurant_1);
		commonMyFoodoraService.activateUser(restaurant_2);
		commonMyFoodoraService.activateUser(restaurant_3);
		commonMyFoodoraService.activateUser(restaurant_4);
		commonMyFoodoraService.activateUser(restaurant_5);
		commonMyFoodoraService.activateUser(courier_1);
		commonMyFoodoraService.activateUser(courier_2);
		commonMyFoodoraService.activateUser(customer_1);
		commonMyFoodoraService.activateUser(customer_2);
		commonMyFoodoraService.activateUser(customer_3);
		commonMyFoodoraService.activateUser(customer_4);
		commonMyFoodoraService.activateUser(customer_5);
		commonMyFoodoraService.activateUser(customer_6);
		commonMyFoodoraService.activateUser(customer_7);
		
		commonMyFoodoraService.displayUsers();
		commonMyFoodoraService.displayActiveUsers();
		
		// send alerts to customers
		commonMyFoodoraService.askAgree2customers("Are you agree to be notified of special offers ?");
		customer_1.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_2.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_3.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_4.getCustomerService().giveConsensusBeNotifiedSpecialOffers();
		customer_5.getCustomerService().removeConsensusBeNotifiedSpecialOffers();
		customer_6.getCustomerService().removeConsensusBeNotifiedSpecialOffers();
		customer_7.getCustomerService().removeConsensusBeNotifiedSpecialOffers();
	}

}
