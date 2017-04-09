/*
 * 
 */
package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import system.AddressPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressPointTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class AddressPointTest {

	/** The p 1. */
	private static AddressPoint p1 = null;
	
	/** The p 2. */
	private static AddressPoint p2 = null;
	
	/**
	 * Test address point double double.
	 */
	@BeforeClass
	public static void testAddressPointDoubleDouble() {
		p1 = new AddressPoint(2.0, 3.5);
		assertNotNull(p1);
	}

	/**
	 * Test address point string.
	 */
	@BeforeClass
	public static void testAddressPointString() {
		p2 = new AddressPoint("3.5,8.9");
		assertNotNull(p2);
	}

	/**
	 * Test calculate distance.
	 */
	@Test
	public void testCalculateDistance() {
		double d1 = p1.calculateDistance(p2);
		System.out.println(d1);
	}

	
	/**
	 * Test to string.
	 */
	@AfterClass
	public static void testToString() {
		System.out.println(p1);
		System.out.println(p2);
	}
}
