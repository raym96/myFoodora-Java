package test.usecase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import exceptions.UserNotFoundException;

import initialization.InitialScenario;
import restaurant.*;
import system.*;
import user.model.*;
import user.service.ManagerService;
import user.service.MyFoodoraService;
import user.service.impl.ManagerServiceImpl;
import user.service.impl.MyFoodoraServiceImpl;

/**
 * Use case scenario
 * The following use case scenario describe examples of how the MyFoodora should function.
 * @throws UserNotFoundException 
 * 
 * @author Ray
 * @author Hxa
 **/


/*
 * Startup scenario
	1. the system loads all registered users: at least 2 manager (the CEO and his deputy
		i.e. adjoint), 5 restaurants and 2 couriers, 7 customers, 4 full-meals per restaurant...
	2. the system sends alerts to the customers that agreed to be notified of special offers
 */

public class RegisterAUserTest {

	private MyFoodora myfoodora;
	private MyFoodoraService myfoodora_service;
	private ManagerService managerService_director;

	
	@Before
	public void testStartupScenario() throws UserNotFoundException {
		
		System.out.println("----------------------- Startup scenario -----------------------");
		InitialScenario.load("init.ini");
		
		myfoodora = MyFoodora.getInstance();
		myfoodora_service = new MyFoodoraServiceImpl();
		managerService_director = new ManagerServiceImpl(new Manager("test","myfoodora","usecase"));
	
		// send alerts to customers
		myfoodora_service.askAgree2customers("Do you agree to be notified of special offers ? By default it is no.");
	}

	/*
	 * Register a user
		1. a user start using the system because she wants to register
		2. the user inserts his first-name, his last-name, his username, his address, his birth-
		date...
		3. the user starts inserting a contact info with the type and the value (e.g. email, phone)
		鈥�	 the user repeats step 3 since he ends to inserts his contact info
		4. if the user is a customer she sets the agreement about the special offer contact (by
		default it is no)
		5. the user is a customer selects the contact to be used to send the offers (by default it
		is the e-mail if exists)
		6. if the user is a courier he sets his current duty status (default on-duty)
		7. the user specify to save the account
	 */
	
	@Test
	public void testOfRegisterUser() throws UserNotFoundException{
		
		System.out.println("----------------------- Register a User -----------------------");
		
		User user = null;
		Scanner s = new Scanner(System.in);
		
		System.out.println("You want to register an acount of which type ? \n"
				+ "1. customer   2. restaurant  3. courier");	
		String userTypeString = s.nextLine();
		int userType = Integer.parseInt(userTypeString);
		System.out.println("Please input your personal infos : \n"
				+ "First name = ");
		s.nextLine();
		String firstname = "test";
		System.out.println("last name = ");
		s.nextLine();
		String lastname = "test";
		System.out.println("username = ");
		s.nextLine();
		String username = "test";
		System.out.println("address (format 'x,y')=");
		s.nextLine();
		AddressPoint address = new AddressPoint(1,1);
		System.out.println("email = ");
		s.nextLine();
		String email = "test";
		System.out.println("phone = ");
		s.nextLine();
		String phone = "test";
		
		switch (userType) {
		case 1:
			System.out.println("customer");
			System.out.println("birthdate (dd/MM/yyyy) = ");
			s.nextLine();
			String birthdate = "test";
			user = new Customer(firstname, lastname, username, address, email, phone);
			System.out.println("Do you agree to be notified of special offers ? By default it is no. Y/N");
			String agree = s.nextLine();
			if(agree=="Y"){
				((Customer)user).getCustomerService().giveConsensusBeNotifiedSpecialOffers();
			}else if(agree=="N"){
				((Customer)user).getCustomerService().removeConsensusBeNotifiedSpecialOffers();
			}
			System.out.println("Please select the contact to be used to send the offers. By default it is the e-mail if exist. 1: email 2:SMS ");
			String choice = s.nextLine();
			while (!((choice.equals("1")) || (choice.equals("2")))){
				System.out.println("Please select the contact to be used to send the offers. By default it is the e-mail if exist. 1: email 2:SMS ");
			}
			break;

		case 2:
			user = new Restaurant(firstname,username,address);
			break;
		case 3:
			user = new Courier(firstname, lastname, username, address, phone);
			System.out.println("Please sets your current duty status. By default off-duty. Y:on-duty N: off-duty");
			String status = s.nextLine();
			if(status=="Y"){
				((Courier)user).getCourierService().turnOnDuty();
			}else if(status=="N"){
				((Courier)user).getCourierService().turnOffDuty();
			}
			break;
		default:
			break;
		}
		
		System.out.println("Save ? Y/N");
		String specify = s.nextLine();
		System.out.println(user);
		if(specify.toUpperCase().equals("Y")){
			managerService_director.addUser(user);
			managerService_director.activateUser(user);
			managerService_director.displayActiveUsers();
			System.out.println("You have been registered on MyFoodora.");
			System.out.println("Session closed.");
			assertTrue(myfoodora.getUsers().contains(user));
		}else if (specify.toUpperCase().equals("N")){
			System.out.println("Session closed.");
		}
		s.nextLine();
		s.close();
	}
}