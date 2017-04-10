/*
 * 
 */
package user.service;

import java.util.Date;

import exceptions.UserNotFoundException;
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
public interface ManagerService {
	
	
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
	 */
	public void removeUser(User user);
	
	/**
	 * Activate any kind of user.
	 *
	 * @param user the user
	 * @throws UserNotFoundException the user not found exception
	 */
	public void activateUser(User user) throws UserNotFoundException;
	
	/**
	 * Disactivate any kind of user.
	 *
	 * @param user the user
	 * @throws UserNotFoundException the user not found exception
	 */
	public void disactivateUser(User user) throws UserNotFoundException;
	
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
	 * @param date1 the starting date
	 * @param date2 the end date
	 * @return the total income
	 */
	public double getTotalIncome(Date date1, Date date2);
	
	/**
	 * Gets the total profit over a time period.
	 *
	 * @param date1 the starting date
	 * @param date2 the end date
	 * @return the total profit
	 */
	public double getTotalProfit(Date date1, Date date2);
	
	/**
	 * Computing the average income per customer over a time period.
	 *
	 * @param date1 the starting date
	 * @param date2 the end date
	 * @return the average income per customer
	 */
	public double getAverageIncomePerCustomer(Date date1, Date date2);
	
	/**
	 * Sets the target profit policy.
	 *
	 * @param t the new target profit policy
	 */
	public void setTargetProfitPolicy(TargetProfitPolicy t);
	
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
	public Restaurant getBestRestaurant(); // to be completed
	
	/**
	 * Determines the worst selling restaurant.
	 *
	 * @return the worst restaurant
	 */
	public Restaurant getWorstRestaurant(); 
	
	/**
	 * Gets the most active courier of the fleet.
	 *
	 * @return the best courier
	 */
	public Courier getBestCourier(); 
	
	/**
	 * Gets the least active courier of the fleet.
	 *
	 * @return the worst courier
	 */
	public Courier getWorstCourier();
	
	/**
	 * setting the current delivery-policy used by MyFoodora to determine which courier
	 * is assigned to deliver an order placed by a customer.
	 *
	 * @param deliverypolicy the new delivery policy
	 */
	public void setDeliveryPolicy(DeliveryPolicy deliverypolicy); 
	
	
	// ##. extra tool method

	/**
	 * Display users.
	 */
	public void displayUsers();
	
	/**
	 * Display active users.
	 */
	public void displayActiveUsers();
	
	/**
	 * Select user by username.
	 *
	 * @param username the username
	 * @return the user
	 * @throws UserNotFoundException the user not found exception
	 */
	public User selectUser(String username) throws UserNotFoundException;
	
	/**
	 * Display users of assigned type.
	 *
	 * @param userType the user type
	 */
	public void displayUsersOfAssignedType(String userType);
	
}
