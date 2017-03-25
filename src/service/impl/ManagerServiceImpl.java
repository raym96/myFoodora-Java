package service.impl;

import java.util.ArrayList;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.ConcreteShoppingCartVisitor;
import model.customer.ShoppingCartVisitor;
import model.myfoodora.DeliveryPolicy;
import model.restaurant.Order;
import model.user.Courier;
import model.user.Manager;
import model.user.MyFoodora;
import model.user.Restaurant;
import model.user.User;
import service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private Manager manager;
	
	public ManagerServiceImpl(Manager manager) {
		super();
		this.manager = manager;
	}

	// 1. add/remove any kind of user (restaurant, customers and/or couriers) to/from the system.
	public void addUser(User user){
		MyFoodora.getInstance().addUser(user);
	}
	
	public void removeUser(User user){
		MyFoodora.getInstance().removeUser(user);
	}
	
	// 2. activate/disactivate any kind of user (restaurant, customers and/or couriers) of the system
	public void activateUser(User user) throws UserNotFoundException{
		MyFoodora.getInstance().activateUser(user);
	}
	
	public void disactivateUser(User user){
		MyFoodora.getInstance().disactivateUser(user);
		
	}

	// 3. changing the service-fee percentage and/or the markup percentage (\percentage de marge") and/or the delivery-cost
	@Override
	public synchronized void changeServicefee(double service_fee) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setService_fee(service_fee);
	}

	@Override
	public synchronized void changeMarkupPercentage(double markup_percentage) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setService_fee(markup_percentage);
	}

	@Override
	public synchronized void changeDeliverycost(double delivery_cost) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setService_fee(delivery_cost);
	}

	
	// 4. computing the total income and/or prot over a time period
	@Override
	public double getTotalIncome(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().getTotalIncome(date1, date2);
	}

	@Override
	public double getTotalProfit(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().getTotalProfit(date1, date2);
	}


	// 5. computing the average income per customer (i.e., the total income divided by
	// the number of customers that placed at least one command) a time period
	
	@Override
	public double getAverageIncomePerCustomer(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().getAverageIncomePerCustomer(date1, date2);
	}
	
	// 6. determining either the service-fee and/or markup percentage and/or the delivery-
		//cost so to meet a target-prot (see target prot policies below)
	@Override
	public void determineParam2MeetTargetProfit(double targetProfit) {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().applyTargetProfitPolicy(targetProfit);
	}

	// 7. determining the most/least selling restaurant

	@Override
	public Restaurant getBestRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant getWorstRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	// 8. determining the most/least active courier of the fleet
	@Override
	public Courier getBestCourier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Courier getWorstCourier() {
		// TODO Auto-generated method stub
		return null;
	}

	// 9. setting the current delivery-policy used by MyFoodora to determine which courier
	// is assigned to deliver an order placed by a customer
	@Override
	public void setDeliveryPolicy(DeliveryPolicy deliverypolicy) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setDeliveryPolicy(deliverypolicy);
	}
	
	
	// ##. extra tool method
	@Override
	public void displayUsers() {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().displayUsers();
	}

	@Override
	public void displayActiveUsers() {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().displayActiveUsers();
	}

	@Override
	public User selectUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().selectUser(username);
	}


}
