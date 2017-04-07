package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import system.Message;

public class MessageTest {

	private static Message msg1 = null;
	private static Message msg2 = null;
	
	@BeforeClass
	public static void testMessageString() {
		msg1 = new Message("testMessageString()");
	}

	@BeforeClass
	public static void testMessageStringString() {
		msg2 = new Message("poster", "testMessageStringString()");
	}
	
	@Test
	public void f(){
		
	}

	@AfterClass
	public static void testToString() {
		System.out.println(msg1);
		System.out.println(msg2);
	}

}
