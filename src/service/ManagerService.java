package service;

import java.util.Date;

import exceptions.UserNotFoundException;
import model.user.Courier;
import model.user.Restaurant;
import model.user.User;

public interface ManagerService {

	// manager can perform these operations
	
	
	// 1. add/remove any kind of user (restaurant, customers and/or couriers) to/from the system.
	public void addUser(User user);
	
	public void removeUser(User user);
	
	// 2. activate/disactivate any kind of user (restaurant, customers and/or couriers) of the system
	public void activateUser(User user) throws UserNotFoundException;
	
	public void disactivateUser(User user);
	
	// 3. changing the service-fee percentage and/or the markup percentage (\percentage de marge") and/or the delivery-cost
	public void changeServicefee(double service_fee);
	public void changeMarkupPercentage(double markup_percentage);
	public void changeDeliverycost(double delivery_cost);
	
	// 4. computing the total income and/or prot over a time period
	public double getTotalIncome(Date date1, Date date2);
	public double getTotalProfit(Date date1, Date date2);
	
	// 5. computing the average income per customer (i.e., the total income divided by
	// the number of customers that placed at least one command) a time period
	public double getAverageIncomePerCustomer(Date date1, Date date2);
	
	// 6. determining either the service-fee and/or markup percentage and/or the delivery-
	//cost so to meet a target-prot (see target prot policies below)
	public void determineParam2MeetTargetProfit(double targetProfit);
	
	// 7. determining the most/least selling restaurant
	public Restaurant getBestRestaurant(); // to be completed
	public Restaurant getWorstRestaurant(); // to be completed
	
	// 8. determining the most/least active courier of the fleet
	public Courier getBestCourier(); // to be completed
	public Courier getWorstCourier(); // to be completed 
	
	// 9. setting the current delivery-policy used by MyFoodora to determine which courier
	// is assigned to deliver an order placed by a customer
	public void settingDeliveryPolicy(); // to be completed
	
	// ##. extra tool method
	public void displayUsers();
	public void displayActiveUsers();
	public User selectUser(String username) throws UserNotFoundException;
}
