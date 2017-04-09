/*
 * 
 */
package policies;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import user.model.MyFoodora;

// TODO: Auto-generated Javadoc
/**
 * The Class TargetProfit_Markup.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class TargetProfit_Markup implements TargetProfitPolicy {
	
	/** The myfoodora. */
	// target profit = last month income * markup + last month order*(service fee - delivery cost)
	MyFoodora myfoodora;

	/**
	 * Instantiates a new target profit markup.
	 *
	 * @param myfoodora the myfoodora
	 */
	public TargetProfit_Markup(MyFoodora myfoodora) {
		// TODO Auto-generated constructor stub
		this.myfoodora = myfoodora;
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
		
		double lastIncome = myfoodora.getMyFoodoraService().getTotalIncome(aMonthAgo, new Date());
		double delivery_cost = myfoodora.getDelivery_cost();
		double service_fee = myfoodora.getService_fee();
		int number_of_orders = myfoodora.getHistory().getOrderBetween(aMonthAgo, new Date()).size();

		double markup_percentage = 0;
		markup_percentage = (targetProfit - number_of_orders*(service_fee - delivery_cost))/lastIncome;
		
		myfoodora.setMarkup_percentage(markup_percentage);
	}

}
