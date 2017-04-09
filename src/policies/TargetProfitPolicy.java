/*
 * 
 */
package policies;

// TODO: Auto-generated Javadoc
/**
 * The Interface TargetProfitPolicy.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface TargetProfitPolicy {
	//so the total profit = last month income * markup + last month order*(service fee - delivery cost)
	
	/**
	 * Meet target profit.
	 *
	 * @param targetProfit the target profit
	 */
	public void meetTargetProfit(double targetProfit);
}
