/*
 * 
 */
package test.user;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import user.model.Manager;
import user.model.MyFoodora;
import user.service.ManagerService;


/**
 * The Class ManagerTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ManagerTest {

	/** The manager. */
	private static Manager manager = null;
	
	/** The manager service. */
	private static ManagerService managerService = null;
	
	/**
	 * Test manager.
	 */
	@BeforeClass
	public static void testManager() {
		manager = new Manager("He", "Xiaoan", "hxa","password");
		managerService = manager.getService();
		
		assertNotNull(manager);
		assertNotNull(managerService);
		assertNotNull(manager.getService());
	}
	
	/**
	 * Test observe observable.
	 */
	@Test
	@Ignore
	public void testObserveObservable() {
	}


	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		int hashCode = manager.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	/**
	 * Test log in.
	 */
	@Test
	public void testLogIn() {
		manager.logIn();
		assertTrue(manager.isLogStatus());
	}

	/**
	 * Test log out.
	 */
	@Test
	public void testLogOut() {
		manager.logOut();
		assertFalse(manager.isLogStatus());
	}

	/**
	 * Test turn on notification.
	 */
	@Test
	public void testTurnOnNotification() {
		manager.turnOnNotification();
		assertTrue(manager.isNotified());
	}

	/**
	 * Test turn off notification.
	 */
	@Test
	public void testTurnOffNotification() {
		manager.turnOffNotification();
		assertFalse(manager.isNotified());
	}

	/**
	 * Test equals object.
	 */
	@Test
	public void testEqualsObject() {
		Manager copy = manager;
		assertTrue(copy.equals(manager));
	}

	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(manager);
	}
	

}
