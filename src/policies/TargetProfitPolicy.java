package policies;

public interface TargetProfitPolicy {
	//so the total profit = last month income * markup + last month order*(service fee - delivery cost)
	
	public void meetTargetProfit(double targetProfit);
}
