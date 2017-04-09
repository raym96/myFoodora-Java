package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import system.AddressPoint;
import system.Message;
import system.MessageBoard;
import user.model.Customer;
import user.model.MyFoodora;

public class MessageBoardTest {

	private static MessageBoard msgb1 = null;
	private static MessageBoard msgb2 = null;
	
	@BeforeClass
	public static void testMessageBoardUser() {
		Customer customer = new Customer("Guan", "Yu", "customer_2", new AddressPoint("101.0,101.0"), "guanyu@gmail.com", "+33 1 01 01 02 02");
		msgb2 = new MessageBoard(customer);
		assertNotNull(msgb2);
		assertNotNull(msgb2.getMessages());
	}

	@BeforeClass
	public static void testMessageBoardMyFoodora() {
		msgb1 = new MessageBoard(MyFoodora.getInstance());
		assertNotNull(msgb1);
		assertNotNull(msgb1.getMessages());
	}

	@Test
	public void testAddMessage() {
		Message msg1 = new Message("testAddMessage() of myFoodora");
		Message msg2 = new Message("testAddMessage() of user");
		
		msgb1.addMessage(msg1);
		msgb2.addMessage(msg2);
		
		assertTrue(msgb1.getMessages().size() > 0);
		assertTrue(msgb2.getMessages().size() > 0);
	}

	@AfterClass
	public static void testDisplayAllmsgs() {
		System.out.println(msgb1);
		System.out.println(msgb2);
	}

}
