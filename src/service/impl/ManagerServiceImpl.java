package service.impl;

import java.util.ArrayList;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.ConcreteShoppingCartVisitor;
import model.customer.ShoppingCartVisitor;
import model.myfoodora.MyFoodora;
import model.restaurant.Order;
import model.user.Courier;
import model.user.Manager;
import model.user.Restaurant;
import model.user.User;
import service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private Manager manager;
	
	public ManagerServiceImpl(Manager manager) {
		super();
		this.manager = manager;
	}

	public void addUser(User user){
		manager.getMyfoodoraService().addUser(user);
	}
	
	public void removeUser(User user){
		manager.getMyfoodoraService().removeUser(user);
	}
	
	public void activateUser(User user) throws UserNotFoundException{
		manager.getMyfoodoraService().activateUser(user);
	}
	
	public void disactivateUser(User user){
		manager.getMyfoodoraService().disactivateUser(user);
		
	}

	@Override
	public void displayUsers() {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().displayUsers();
	}

	@Override
	public void displayActiveUsers() {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().displayActiveUsers();
	}

	@Override
	public User selectUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().selectUser(username);
	}

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

	@Override
	public double getAverageIncomePerCustomer(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().getAverageIncomePerCustomer(date1, date2);
	}

	@Override
	public void determineParam2MeetTargetProfit(double targetProfit) {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().applyTargetProfitPolicy(targetProfit);
	}

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

	@Override
	public void settingDeliveryPolicy() {
		// TODO Auto-generated method stub
		
	}
	
}
