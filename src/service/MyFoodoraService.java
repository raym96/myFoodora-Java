package service;

import java.util.ArrayList;
import java.util.Date;

import model.myfoodora.*;
import model.restaurant.*;
import model.users.*;

public interface MyFoodoraService {
		
	/** myfoodora's specific services **/
	// 1. setting of the service-fee, the markup percentage (\percentage de marge") and the
	// delivery cost values
	public void setServiceFee(double service_fee);
	public void setMarkUpPercentage(double markup_percentage);
	public void setDeliveryCost(double delivery_cost);
	
	// 2. allocating of a courier to an order placed by a customer (by application of the current
	// delivery policy, see below details of supported policies)
	public void parse(Order order, ArrayList<Courier> availablecouriers);

	// 3. notifying users that gave consensus to receive special oers notications, of a new
	// special offer set by a restaurant
	public void notifyAll(SpecialOffer specialoffer);
	
	// 4. computing the total income (i.e. the sum of all completed orders) as well as the total
	// profit of the system, knowing that the the prot of a single order is given by:
	// profit for one order = order_price * markup_percentage + service_fee - delivery cost
	public double getTotalIncome(Date date1, Date date2);
	public double getTotalProfit(Date date1, Date date2);
	public double getAverageIncomePerCustomer(Date date1, Date date2);

	// 5. chose the target prot policy (see below) used to optimise the profit-related-
	// parameters (service-fee, markup percentage, and the delivery cost)
	public void applyTargetProfitPolicy(double targetProfit);
	

	
	/** myfoodora's basic services provided to User's specific operations rely on **/

	public User selectUser(String username);
//	void askAgree2customers(String ask); <- what is this ?
	
	public ArrayList<User> getUsersOfAssignedType(String userType);

	public void assignManager(Manager manager);
	
	public void askAgree2customers(String ask);
	
	//returns history of myfoodora for manager-service
	public History getHistory();
}
