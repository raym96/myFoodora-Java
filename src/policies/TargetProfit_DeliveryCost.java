/*
 * 
 */
package policies;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import user.model.MyFoodora;


/**
 * The Class TargetProfit_DeliveryCost.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class TargetProfit_DeliveryCost implements TargetProfitPolicy {
	
	/** The myfoodora. */
	// target profit = last month income * markup + last month order*(service fee - delivery cost)
	MyFoodora myfoodora;
	
	/**
	 * Instantiates a new target profit delivery cost.
	 *
	 * @param myfoodora the myfoodora
	 */
	public TargetProfit_DeliveryCost(MyFoodora myfoodora){
		this.myfoodora=myfoodora;
	}
	
	/* (non-Javadoc)
	 * @see policies.TargetProfitPolicy#meetTargetProfit(double)
	 */
	@Override
	public void meetTargetProfit(double targetProfit){
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date aMonthAgo = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String aMonthAgoString= sdf.format(aMonthAgo);
		String todayString = sdf.format(new Date());
		
		double lastIncome;
		try {
			lastIncome = myfoodora.getMyFoodoraService().getTotalIncome(aMonthAgoString, todayString);
			double service_fee = myfoodora.getService_fee();
			double markup_percentage = myfoodora.getMarkup_percentage();
			int number_of_orders = myfoodora.getHistory().getOrderBetween(aMonthAgoString, todayString).size();
			double delivery_cost = 0;
			delivery_cost = service_fee - (targetProfit - lastIncome*markup_percentage)/number_of_orders ;
			myfoodora.setDelivery_cost(delivery_cost);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
