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


/**
 * The Interface MyFoodoraService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface MyFoodoraService {
		
	/**
	 * Sets up the the service-fee.
	 *
	 * @param service_fee the new service fee
	 */

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
	
	/**
	 * 	allocating of a courier from a list of available couriers to an order placed by a customer 
	 * (by application of the currentdelivery policy, see below details of supported policies)
	 *
	 * @param order the order
	 * @param availablecouriers the available couriers
	 */
	public void parse(Order order, ArrayList<Courier> availablecouriers);

	/**
	 * notifying users that gave consensus to receive special offers notifcations, of a new
	 *	 special offer set by a restaurant
	 * @param specialoffer the specialoffer
	 */

	public void notifyAll(Meal specialoffer);
	
	/**
	 * computing the total income (i.e. the sum of all completed orders) over a period
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the total income
	 */
	public double getTotalIncome(Date date1, Date date2);
	
	/**
	 * 	 computing profit of the system, knowing that the the profit of a single order is given by:
	 * profit for one order = order_price * markup_percentage + service_fee - delivery cost

	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the total profit
	 */
	public double getTotalProfit(Date date1, Date date2);
	
	/**
	 * Gets the average income per customer over a period
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
	public void applyTargetProfitPolicy(double targetProfit);
	
	/**
	 *  Select a user by his username
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
	 * Sends a message to customers.
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
