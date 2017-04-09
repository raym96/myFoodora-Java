/*
 * 
 */
package user.service;

import java.util.ArrayList;
import java.util.Date;

import restaurant.*;
import system.*;
import user.model.Courier;
import user.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface MyFoodoraService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface MyFoodoraService {
		
	/**
	 *  myfoodora's specific services *.
	 *
	 * @param service_fee the new service fee
	 */
	// 1. setting of the service-fee, the markup percentage (\percentage de marge") and the
	// delivery cost values
	public void setServiceFee(double service_fee);
	
	/**
	 * Sets the mark up percentage.
	 *
	 * @param markup_percentage the new mark up percentage
	 */
	public void setMarkUpPercentage(double markup_percentage);
	
	/**
	 * Sets the delivery cost.
	 *
	 * @param delivery_cost the new delivery cost
	 */
	public void setDeliveryCost(double delivery_cost);
	
	// 2. allocating of a courier to an order placed by a customer (by application of the current
	/**
	 * Parses the.
	 *
	 * @param order the order
	 * @param availablecouriers the availablecouriers
	 */
	// delivery policy, see below details of supported policies)
	public void parse(Order order, ArrayList<Courier> availablecouriers);

	// 3. notifying users that gave consensus to receive special oers notications, of a new
	/**
	 * Notify all.
	 *
	 * @param specialoffer the specialoffer
	 */
	// special offer set by a restaurant
	public void notifyAll(Meal specialoffer);
	
	// 4. computing the total income (i.e. the sum of all completed orders) as well as the total
	// profit of the system, knowing that the the prot of a single order is given by:
	/**
	 * Gets the total income.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the total income
	 */
	// profit for one order = order_price * markup_percentage + service_fee - delivery cost
	public double getTotalIncome(Date date1, Date date2);
	
	/**
	 * Gets the total profit.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the total profit
	 */
	public double getTotalProfit(Date date1, Date date2);
	
	/**
	 * Gets the average income per customer.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the average income per customer
	 */
	public double getAverageIncomePerCustomer(Date date1, Date date2);

	// 5. chose the target prot policy (see below) used to optimise the profit-related-
	/**
	 * Apply target profit policy.
	 *
	 * @param targetProfit the target profit
	 */
	// parameters (service-fee, markup percentage, and the delivery cost)
	public void applyTargetProfitPolicy(double targetProfit);
	

	
	/**
	 *  myfoodora's basic services provided to User's specific operations rely on *.
	 *
	 * @param username the username
	 * @return the user
	 */

	public User selectUser(String username);
	
	/**
	 * Gets the users of assigned type.
	 *
	 * @param userType the user type
	 * @return the users of assigned type
	 */
	public ArrayList<User> getUsersOfAssignedType(String userType);
	
	/**
	 * Ask agree 2 customers.
	 *
	 * @param ask the ask
	 */
	public void askAgree2customers(String ask);
	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	//returns history of myfoodora for manager-service
	public History getHistory();
}
