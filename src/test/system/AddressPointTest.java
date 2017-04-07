package test.system;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import system.AddressPoint;

public class AddressPointTest {

	private static AddressPoint p1 = null;
	private static AddressPoint p2 = null;
	
	@BeforeClass
	public static void testAddressPointDoubleDouble() {
		p1 = new AddressPoint(2.0, 3.5);
		assertNotNull(p1);
	}

	@BeforeClass
	public static void testAddressPointString() {
		p2 = new AddressPoint("3.5,8.9");
		assertNotNull(p2);
	}

	@Test
	public void testCalculateDistance() {
		double d1 = p1.calculateDistance(p2);
		System.out.println(d1);
	}

	
	@AfterClass
	public static void testToString() {
		System.out.println(p1);
		System.out.println(p2);
	}
}
