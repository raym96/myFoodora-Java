package user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import exceptions.UserNotFoundException;
import policies.DeliveryPolicy;
import policies.SortingByCriteria;
import policies.SortingByRestaurant;
import policies.TargetProfitPolicy;
import system.ConcreteShoppingCartVisitor;
import system.Order;
import system.ShoppingCartVisitor;
import user.model.Courier;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private Manager manager;
	
	public ManagerServiceImpl(Manager manager) {
		super();
		this.manager = manager;
	}

	// 1. add/remove any kind of user (restaurant, customers and/or couriers) to/from the system.
	@Override
	public void addUser(User user){
		MyFoodora.getInstance().register(user);
	}
	@Override
	public void removeUser(User user){
		MyFoodora.getInstance().unregister(user);
	}
	
	// 2. activate/disactivate any kind of user (restaurant, customers and/or couriers) of the system
	@Override
	public void activateUser(User user) throws UserNotFoundException{
		MyFoodora.getInstance().activateUser(user);
	}
	
	@Override
	public void disactivateUser(User user) throws UserNotFoundException{
		MyFoodora.getInstance().disactivateUser(user);
		
	}

	// 3. changing the service-fee percentage and/or the markup percentage (\percentage de marge") and/or the delivery-cost
	@Override
	public synchronized void setServiceFree(double service_fee) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setService_fee(service_fee);
	}

	@Override
	public synchronized void setMarkUpPencentage(double markup_percentage) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setMarkup_percentage(markup_percentage);
	}

	@Override
	public synchronized void setDeliveryCost(double delivery_cost) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setDelivery_cost(delivery_cost);
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
	public void setTargetProfitPolicy(TargetProfitPolicy t){
		MyFoodora.getInstance().setTargetprofitpolicy(t);
	}

	@Override
	public void determineParam2MeetTargetProfit(double targetProfit) {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().applyTargetProfitPolicy(targetProfit);
	}

	// 7. determining the most/least selling restaurant

	@Override
	public Restaurant getBestRestaurant() {
		// TODO Auto-generated method stub
		double income = 0;
		Restaurant restaurant = null;
		ArrayList<User> restaurant_users =  manager.getMyfoodoraService().getUsersOfAssignedType("RESTAURANT");
		for (User u : restaurant_users){
			Restaurant r = (Restaurant)u;
			double rincome = r.getIncome(); //the number of commands passed at restaurant r is the length of its history
			if (rincome>income){
				income = rincome;
				restaurant = r;
			}
		}
		System.out.println("\nThe best selling restaurant is:");
		System.out.println(restaurant.getName());
		return restaurant;
		
	}

	@Override
	public Restaurant getWorstRestaurant() {
		// TODO Auto-generated method stub
		double income = Integer.MAX_VALUE;
		Restaurant restaurant = null;
		ArrayList<User> restaurant_users =  manager.getMyfoodoraService().getUsersOfAssignedType("RESTAURANT");
		for (User u : restaurant_users){
			Restaurant r = (Restaurant)u;
			double rincome = r.getIncome(); //the number of commands passed at restaurant r is the length of its history
			if (rincome<income){
				income = rincome;
				restaurant = r;
			}
		}
		System.out.println("\nThe worst selling restaurant is:");
		System.out.println(restaurant.getName());
		return restaurant;
	}


	// 8. determining the most/least active courier of the fleet
	@Override
	public Courier getBestCourier() {
		// TODO Auto-generated method stub
		int count = 0;
		Courier courier = null;
		ArrayList<User> courier_users =  manager.getMyfoodoraService().getUsersOfAssignedType("COURIER");
		for (User u : courier_users){
			Courier c = (Courier)u;
			int ccount = c.getCount(); //the number of commands passed at restaurant r is the length of its history
			if (ccount>count){
				count = ccount;
				courier = c;
			}
		}
		System.out.println("\nThe most active courier is:");
		System.out.println(courier.getName());
		return courier;
	}

	@Override
	public Courier getWorstCourier() {
		// TODO Auto-generated method stub
		int count = Integer.MAX_VALUE;
		Courier courier = null;
		ArrayList<User> courier_users =  manager.getMyfoodoraService().getUsersOfAssignedType("COURIER");
		for (User u : courier_users){
			Courier c = (Courier)u;
			int ccount = c.getCount(); //the number of commands passed at restaurant r is the length of its history
			if (ccount<count){
				count = ccount;
				courier = c;
			}
		}
		System.out.println("\nThe least active courier is:");
		System.out.println(courier.getName());
		return courier;
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

	public void displayUsersOfAssignedType(String userType){
		ArrayList<User> users = MyFoodora.getInstance().getUsersOfAssignedType(userType);
		System.out.println("--- " + userType + " ---");
		System.out.println(users);
	}
}
