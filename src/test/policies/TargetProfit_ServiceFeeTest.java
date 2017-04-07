package test.model.myfoodora;

import static org.junit.Assert.*;

import org.junit.Test;

import model.myfoodora.TargetProfit_DeliveryCost;

public class TargetProfit_ServiceFeeTest {

	@Test
	public void testMeetTargetProfit() {
		System.out.println(new TargetProfit_DeliveryCost().meetTargetProfit(1.0, 2.0, 3.0, 4.0, 5.0, 7));
	}


}
