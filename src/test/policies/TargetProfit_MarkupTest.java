/*
 * 
 */
package test.policies;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import clui.InitialScenario;
import policies.TargetProfitPolicy;
import policies.TargetProfit_DeliveryCost;
import policies.TargetProfit_Markup;
import policies.TargetProfit_ServiceFee;
import user.model.MyFoodora;


/**
 * The Class TargetProfit_MarkupTest.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class TargetProfit_MarkupTest {
	
	/** The myfoodora. */
	MyFoodora myfoodora;
	
	/**
	 * Sets the up before.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		InitialScenario.load("my_foodora.ini");	
		myfoodora = MyFoodora.getInstance();
		System.out.println(myfoodora.getHistory());
	}
	
	/**
	 * Test meet target profit.
	 * @throws ParseException 
	 */
	@Test
	public void testMeetTargetProfit() throws ParseException {
		TargetProfitPolicy targetProfit_Markup =  new TargetProfit_Markup(myfoodora);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date aMonthAgo = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String aMonthAgoString = sdf.format(aMonthAgo);
		String nowString = sdf.format(new Date());

		double lastIncome = myfoodora.getService().getTotalIncome(aMonthAgoString, nowString);
		double delivery_cost = myfoodora.getDelivery_cost();
		double markup_percentage = myfoodora.getMarkup_percentage();
		double service_fee = myfoodora.getService_fee();
		int number_of_orders = myfoodora.getHistory().getOrderBetween(aMonthAgoString, nowString).size();
		
		
		
		System.out.println("Old delivery_Cost=" + delivery_cost);
		System.out.println("Old service_fee="+service_fee);
		System.out.println("Old markup_percentage="+markup_percentage);
		
		// profit = last month income * markup + last month order*(service fee - delivery cost)
		System.out.println("current profit = "+(lastIncome*markup_percentage+number_of_orders*(service_fee-delivery_cost)));
		
		targetProfit_Markup.meetTargetProfit(10);
		markup_percentage = myfoodora.getMarkup_percentage();
		System.out.println("\nNew delivery_Cost=" + delivery_cost);
		System.out.println("New service_fee="+service_fee);
		System.out.println("New markup_percentage="+markup_percentage);
		System.out.println("new profit = "+(lastIncome*markup_percentage+number_of_orders*(service_fee-delivery_cost)));
	}

}
