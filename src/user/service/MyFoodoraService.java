/*
 * 
 */
package user.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import exceptions.LoginErrorException;
import exceptions.NameNotFoundException;
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
	 * (by application of the currentdelivery policy, see below details of supported policies).
	 *
	 * @param order the order
	 * @param availablecouriers the available couriers
	 */
	public void findDeliverer(Order order, ArrayList<Courier> availablecouriers);

	/**
	 * notifying users that gave consensus to receive special offers notifcations, of a new
	 * 	 special offer set by a restaurant.
	 *
	 * @param specialoffer the specialoffer
	 */

	public void notifyAllObservers();
	
	/**
	 * computing the total income (i.e. the sum of all completed orders) over a period
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the total income
	 * @throws ParseException the parse exception
	 */
	public double getTotalIncome(String stringDate1, String stringDate2) throws ParseException;
	
	/**
	 * 	 computing profit of the system, knowing that the the profit of a single order is given by:
	 * profit for one order = order_price * markup_percentage + service_fee - delivery cost.
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the total profit
	 * @throws ParseException the parse exception
	 */
	public double getTotalProfit(String stringDate1, String stringDate2) throws ParseException;
	
	/**
	 * Gets the average income per customer over a period.
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the average income per customer
	 * @throws ParseException the parse exception
	 */
	public double getAverageIncomePerCustomer(String stringDate1, String stringDate2) throws ParseException;

	// 5. chose the target prot policy (see below) used to optimise the profit-related-
	/**
	 * Apply target profit policy.
	 *
	 * @param targetProfit the target profit
	 */
	public void applyTargetProfitPolicy(double targetProfit);
	
	/**
	 *  Select a user by his username.
	 *
	 * @param username the username
	 * @return the user
	 * @throws NameNotFoundException 
	 */

	public User selectUser(String username) throws NameNotFoundException;
	
	/**
	 * Gets the users of assigned type.
	 *
	 * @param userType the user type
	 * @return the users of assigned type
	 */
	public ArrayList<User> getUsersOfAssignedType(String userType);
	
	/**
	 * Login.
	 *
	 * @param username the username
	 * @param password the password
	 * @throws LoginErrorException the login error exception
	 */
	public void login(String username, String password) throws LoginErrorException;
	
	public void showUsersOfAssignedType(String userType);
	
	/**
	 * Display users.
	 */
	public void showUsers();
	/**
	 * Display active users.
	 */
	public void showActiveUsers();
	
	/**
	 * Display menus of all restaurants.
	 */
	public void showRestaurantMenus();
	

	public void showHistory();
	
	public void showSystemValues();
	
	public void showPolicies();
}
