package policies;

import java.util.List;

public class TargetProfit_Markup implements TargetProfitPolicy {
	// target profit = last month income * markup + last month order*(service fee - delivery cost)

	@Override
	public double meetTargetProfit(double targetProfit, double lastIncome, double delivery_cost, double service_fee, double markup_percentage, int number_of_orders) {
		// TODO Auto-generated method stub
		double ret = 0;
		ret = (targetProfit - number_of_orders*(service_fee - delivery_cost))/lastIncome;
		return ret;
	}

}
