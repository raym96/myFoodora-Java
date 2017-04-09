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

// TODO: Auto-generated Javadoc
/**
 * The Interface ManagerService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface ManagerService {

	// manager can perform these operations
	
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 */
	// 1. add/remove any kind of user (restaurant, customers and/or couriers) to/from the system.
	public void addUser(User user);
	
	/**
	 * Removes the user.
	 *
	 * @param user the user
	 */
	public void removeUser(User user);
	
	/**
	 * Activate user.
	 *
	 * @param user the user
	 * @throws UserNotFoundException the user not found exception
	 */
	// 2. activate/disactivate any kind of user (restaurant, customers and/or couriers) of the system
	public void activateUser(User user) throws UserNotFoundException;
	
	/**
	 * Disactivate user.
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
	// 3. changing the service-fee percentage and/or the markup percentage (\percentage de marge") and/or the delivery-cost
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
	 * Gets the total income.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the total income
	 */
	// 4. computing the total income and/or prot over a time period
	public double getTotalIncome(Date date1, Date date2);
	
	/**
	 * Gets the total profit.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the total profit
	 */
	public double getTotalProfit(Date date1, Date date2);
	
	// 5. computing the average income per customer (i.e., the total income divided by
	/**
	 * Gets the average income per customer.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the average income per customer
	 */
	// the number of customers that placed at least one command) a time period
	public double getAverageIncomePerCustomer(Date date1, Date date2);
	
	// 6. determining either the service-fee and/or markup percentage and/or the delivery-
	/**
	 * Sets the target profit policy.
	 *
	 * @param t the new target profit policy
	 */
	//cost so to meet a target-prot (see target prot policies below)
	public void setTargetProfitPolicy(TargetProfitPolicy t);
	
	/**
	 * Determine param 2 meet target profit.
	 *
	 * @param targetProfit the target profit
	 */
	public void determineParam2MeetTargetProfit(double targetProfit);
	
	/**
	 * Gets the best restaurant.
	 *
	 * @return the best restaurant
	 */
	// 7. determining the most/least selling restaurant
	public Restaurant getBestRestaurant(); // to be completed
	
	/**
	 * Gets the worst restaurant.
	 *
	 * @return the worst restaurant
	 */
	public Restaurant getWorstRestaurant(); // to be completed
	
	/**
	 * Gets the best courier.
	 *
	 * @return the best courier
	 */
	// 8. determining the most/least active courier of the fleet
	public Courier getBestCourier(); // to be completed
	
	/**
	 * Gets the worst courier.
	 *
	 * @return the worst courier
	 */
	public Courier getWorstCourier(); // to be completed 
	
	// 9. setting the current delivery-policy used by MyFoodora to determine which courier
	/**
	 * Sets the delivery policy.
	 *
	 * @param deliverypolicy the new delivery policy
	 */
	// is assigned to deliver an order placed by a customer
	public void setDeliveryPolicy(DeliveryPolicy deliverypolicy); 
	
	/**
	 * Display users.
	 */
	// ##. extra tool method
	public void displayUsers();
	
	/**
	 * Display active users.
	 */
	public void displayActiveUsers();
	
	/**
	 * Select user.
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
