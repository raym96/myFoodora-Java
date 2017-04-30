/*
 * 
 */
package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import system.Message;


/**
 * The Class MessageTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MessageTest {

	/** The msg 1. */
	private static Message msg1 = null;
	
	/** The msg 2. */
	private static Message msg2 = null;
	
	/**
	 * Test message string.
	 */
	@BeforeClass
	public static void testMessageString() {
		msg1 = new Message("testMessageString()");
	}

	/**
	 * Test message string string.
	 */
	@BeforeClass
	public static void testMessageStringString() {
		msg2 = new Message("poster", "testMessageStringString()");
	}
	
	/**
	 * F.
	 */
	@Test
	public void f(){
		
	}

	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(msg1);
		System.out.println(msg2);
	}

}
