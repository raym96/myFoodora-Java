/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * Use case scenario
 * The following use case scenario describe examples of how the MyFoodora should function.
 *
 * @author He Xiaoan
 * @author Ji Raymond
 */


//* Login user
//	1. a user wants to login
//	2. the user inserts username and password
//	3. the system handles the login and presents to the user the available operations ac-
//	cording to his role
//*/

public class LoginUserTest {

	/** The myfoodora. */
	private MyFoodora myfoodora;
	
	/** The myfoodora service. */
	private MyFoodoraService myfoodora_service;
	
	/** The manager service director. */
	private ManagerService managerService_director;

	
	/**
	 * Test startup scenario.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
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

	/**
	 * Test of login user.
	 */
	@Test
	public void testOfLoginUser(){
		System.out.println("----------------------- Login User -----------------------");
	
		// the default password of all users is "password" now
		System.out.println("--- Login in  ---");
		Scanner s = new Scanner(System.in);
		
		System.out.println("username = ");
		s.nextLine();
		String username = "customer_1";
		System.out.println(username);
		System.out.println("password = ");
		s.nextLine();
		String password = "password";
		System.out.println("password");
			
		User user = null;
		for(User u : myfoodora.getUsers()){
			if( username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword()) ){
				user = u;
				user.logIn();
				System.out.println("user "+user+" logged on myfoodora.");
				break;
			}
		}
		s.close();
		assertTrue(myfoodora.getActiveUsers().contains(user));
	}


}
