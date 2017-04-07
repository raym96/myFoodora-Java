package test.model.user;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import model.users.Manager;
import model.users.MyFoodora;
import service.ManagerService;

public class ManagerTest {

	private static Manager manager = null;
	private static ManagerService managerService = null;
	
	@BeforeClass
	public static void testManager() {
		manager = new Manager("He", "Xiaoan", "hxa");
		managerService = manager.getManagerService();
		
		assertNotNull(manager);
		assertNotNull(managerService);
		assertNotNull(manager.getMyfoodoraService());
	}
	
	@Test
	@Ignore
	public void testObserveObservable() {
	}

	@Test
	public void testUpdate() {
		String str = "testUpdate() message";
		manager.update(str);
	}

	@Test
	public void testObserveObservableObject() {
		String str = "testObserveObservableObject() message";
		manager.observe(MyFoodora.getInstance(), str);
	}

	@Test
	public void testHashCode() {
		int hashCode = manager.hashCode();
		System.out.println("hashCode = " + hashCode);
	}

	@Test
	public void testLogIn() {
		manager.logIn();
		assertTrue(manager.isLogStatus());
	}

	@Test
	public void testLogOut() {
		manager.logOut();
		assertFalse(manager.isLogStatus());
	}

	@Test
	public void testTurnOnNotification() {
		manager.turnOnNotification();
		assertTrue(manager.isNotified());
	}

	@Test
	public void testTurnOffNotification() {
		manager.turnOffNotification();
		assertFalse(manager.isNotified());
	}

	@Test
	public void testEqualsObject() {
		Manager copy = manager;
		assertTrue(copy.equals(manager));
	}

	@AfterClass
	public static void testToString() {
		System.out.println(manager);
	}
	
	@AfterClass
	public static void testRefreshMessageBoard() {
		manager.refreshMessageBoard();
	}
}
