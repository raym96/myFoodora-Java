package system;

public interface TargetProfitPolicy {
	//so the total profit = last month income * markup + last month order*(service fee - delivery cost)
	
	public double meetTargetProfit(double targetProfit, double lastIncome, double delivery_cost, double service_fee, double markup_percentage, int number_of_orders);
}
