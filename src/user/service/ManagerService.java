/*
 * 
 */
package user.service;

import java.text.ParseException;
import java.util.Date;

import exceptions.NameNotFoundException;
import policies.DeliveryPolicy;
import policies.TargetProfitPolicy;
import user.model.Courier;
import user.model.Restaurant;
import user.model.User;


/**
 * The Interface ManagerService. 
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface ManagerService extends UserService{
	
	
	/**
	 * Adds any kind of user to the system.
	 *
	 * @param user the user
	 */
	public void addUser(User user);
	
	/**
	 * Removes any kind of user from the system.
	 *
	 * @param user the user
	 * @throws NameNotFoundException 
	 */
	public void removeUser(String username) throws NameNotFoundException;
	
	/**
	 * Activate any kind of user.
	 *
	 * @param user the user
	 * @throws NameNotFoundException the user not found exception
	 */
	public void activateUser(User user) throws NameNotFoundException;
	
	/**
	 * Disactivate any kind of user.
	 *
	 * @param user the user
	 * @throws NameNotFoundException the user not found exception
	 */
	public void disactivateUser(User user) throws NameNotFoundException;
	
	/**
	 * Sets the service free.
	 *
	 * @param service_fee the new service free
	 */
	public void setServiceFree(double service_fee);
	
	/**
	 * Sets the mark up pencentage.
	 *
	 * @param markup_percentage the new mark up pencentage
	 */
	public void setMarkUpPencentage(double markup_percentage);
	
	/**
	 * Sets the delivery cost.
	 *
	 * @param delivery_cost the new delivery cost
	 */
	public void setDeliveryCost(double delivery_cost);
	
	/**
	 * Gets the total income over a time period.
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the total income
	 * @throws ParseException the parse exception
	 */
	public double getTotalIncome(String stringDate1, String stringDate2) throws ParseException;
	
	/**
	 * Gets the total profit over a time period.
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the total profit
	 * @throws ParseException the parse exception
	 */
	public double getTotalProfit(String stringDate1, String stringDate2) throws ParseException;
	
	/**
	 * Computing the average income per customer over a time period.
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the average income per customer
	 * @throws ParseException the parse exception
	 */
	public double getAverageIncomePerCustomer(String stringDate1, String stringDate2) throws ParseException;
	
	/**
	 * Sets the target profit policy.
	 *
	 * @param profitPolicyName the new target profit policy
	 */
	public void setTargetProfitPolicy(String profitPolicyName);
	
	/**
	 * determining either the service-fee and/or markup percentage and/or the delivery-
	 * cost so to meet a target-prot (see target prot policies below).
	 *
	 * @param targetProfit the target profit
	 */
	public void determineParam2MeetTargetProfit(double targetProfit);
	
	/**
	 * Determines the best selling restaurant.
	 *
	 * @return the best restaurant
	 */
	public void showRestaurantDesc(); 
	
	/**
	 * Determines the worst selling restaurant.
	 *
	 * @return the worst restaurant
	 */
	public void showRestaurantAsc(); 
	
	/**
	 * Gets the most active courier of the fleet.
	 *
	 * @return the best courier
	 */
	public void showCourierDesc(); 
	
	/**
	 * Gets the least active courier of the fleet.
	 *
	 * @return the worst courier
	 */
	public void showCourierAsc();
	
	/**
	 * setting the current delivery-policy used by MyFoodora to determine which courier
	 * is assigned to deliver an order placed by a customer.
	 *
	 * @param deliveryPolicy the new delivery policy
	 */
	public void setDeliveryPolicy(String deliveryPolicy); 
	
	
	// ##. extra tool method

	public User selectUser(String username) throws NameNotFoundException;
	
	/**
	 * Associate card.
	 *
	 * @param username the username
	 * @param cardType the card type
	 * @throws NameNotFoundException the name not found exception
	 */
	public void associateCard(String username, String cardType) throws NameNotFoundException;
	
}
