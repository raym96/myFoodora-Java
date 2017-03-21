package service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.*;
import model.restaurant.*;
import model.user.*;
import system.DeliveryPolicy;
import system.History;
import system.SpecialOffer;
import system.SpecialOfferBoard;
import system.TargetProfitPolicy;
import system.TargetProfit_DeliveryCost;
import system.TargetProfit_Markup;
import system.TargetProfit_ServiceFee;

public class MyFoodora{
	
	public ArrayList<User> users;
	public ArrayList<User> activeUsers;
	public ArrayList<Courier> activecouriers;
	
	public SpecialOfferBoard specialofferboard;
	
	private double service_fee;
	private double markup_percentage;
	private double delivery_cost;	
	
	private TargetProfitPolicy targetprofitpolicy;
	private DeliveryPolicy deliverypolicy;

	private History history;
	
	private double balance;
	
	//[[used by Manager]]
	public void addUser(User user){
		users.add(user);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}
	
	public void activateUser(User user){
		activeUsers.add(user);
	}
	
	public void disactivateUser(User user){
		activeUsers.remove(user);
	}
	
	public double getService_fee() {
		return service_fee;
	}
	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}
	public double getMarkup_percentage() {
		return markup_percentage;
	}
	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}
	public double getDelivery_cost() {
		return delivery_cost;
	}
	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}
	
	public double getTotalIncome(Date date1, Date date2){
		ArrayList<Order> list = history.getOrderBetween(date1, date2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalIncome = 0;
		for (Order order:list){
			totalIncome += order.accept(visitor);
		}
		return totalIncome;
	}
	
	public double getTotalProfit(Date date1, Date date2){
		ArrayList<Order> list = history.getOrderBetween(date1, date2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalProfit = 0;
		for (Order order:list){
			totalProfit += order.accept(visitor)*markup_percentage+service_fee-delivery_cost;
		}
		return totalProfit;
	}
	
	public double getAverageIncomePerCustomer(Date date1, Date date2){
		ArrayList<Order> list = history.getOrderBetween(date1, date2);
		ArrayList<Customer> customerlist = null;
		double avgIncome = 0;
		for (Order order:list){
			//count the number of different customers over the time period
			if (!(customerlist.contains(order.getCustomer()))){
				customerlist.add(order.getCustomer());
			}
		}
		if (customerlist.size()==0){
			//no order has been made by any customer over the time period
			return 0;
		}
		else{
			return (getTotalIncome(date1, date2)/customerlist.size());
		}
	}
	
	//Ugly code, to be completed
	public void applyTargetProfitPolicy(double targetProfit){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date aMonthAgo = cal.getTime();
		double lastIncome = getTotalIncome(aMonthAgo, new Date());
		int number_of_orders = history.getOrderBetween(aMonthAgo, new Date()).size();
		
		double newValue  = targetprofitpolicy.meetTargetProfit(targetProfit, lastIncome, getDelivery_cost(), getService_fee(), getMarkup_percentage(), number_of_orders);
		
		if (targetprofitpolicy instanceof TargetProfit_DeliveryCost){
			setDelivery_cost(newValue);
		}
		if (targetprofitpolicy instanceof TargetProfit_Markup){
			setMarkup_percentage(newValue);
		}
		if (targetprofitpolicy instanceof TargetProfit_ServiceFee){
			setService_fee(newValue);
		}
	}
	
	public void setDeliveryPolicy(DeliveryPolicy d){
		deliverypolicy = d;
	}
	
	public Courier parse(Order order){
		return deliverypolicy.parse(order, activecouriers);
	}
	
	public ArrayList<User> displayUsers(){
		return users;
	}
	
	public ArrayList<User> displayActiveUsers(){
		return activeUsers;
	}
	
	public User selectUser(String username) throws UserNotFoundException{
		User target = null;
		for(User user : users){
			if(user.getUsername()==username){
				target = user;
				return target;
			}
		}
		throw new UserNotFoundException(username);
	}

	//[[[Used by Customer]]]
	public void addStandardMealOrder(Customer c,Restaurant r, String mealType, String mealName){
		c.addStandardMealOrder(r,  mealType,  mealName);
	}
	
	public void addSpecialMealOrder(Customer c,Restaurant r, String mealType, String mealName){
		c.addSpecialMealOrder(r,  mealType,  mealName);
	}
	
	
	public void addAlaCarteOrder(Customer c,Restaurant r, String dishName){
		c.addAlaCarteOrder(r, dishName);
	}
	
	public double calculatePrice(Customer c){
		return c.calculatePrice();
	}
	
	//Important method, to be completed
	public void pay(Customer c){
		c.pay();
		balance += c.calculatePrice();
		//Send the orders into history before clearing the shopping cart
		for (Order order:c.getShoppingCart().getOrders()){
			order.getRestaurant().addToHistory(order);
			addToHistory(order);
			System.out.println(parse(order).getName() +" has been assigned to this order");
		}
		c.clearShoppingCart();
	}
	
	public void addToHistory(Order order){
		history.addOrder(order);
	}
	
	
	public void registerCard(Customer c, String cardType){
		c.registerCard(cardType);
	}
	
	public void unregisterCard(Customer c){
		c.unregisterCard();
	}
	
	public void getCardPoints(Customer c){
		if (c.getCard() instanceof PointCard){
			PointCard p = (PointCard) c.getCard();
			System.out.println(p.getPoints());
		}
		else{
			System.out.println("You don't have a Point Fidelity Card");
		}
	}
	
	
	public void turnOnNotification(Customer customer){
		customer.turnOnNotification();
		specialofferboard.register(customer);
	}
	
	public void turnOffNotification(Customer customer){
		customer.turnOffNotification();
		specialofferboard.unregister(customer);
	}

	//[[[Used by Restaurant]]]
	public void addDish(Restaurant restaurant, Dish dish){
		restaurant.addDish(dish);
	}
	public void removeDish(Restaurant restaurant, String dishName){
		restaurant.removeDish(dishName);
	}
	
	//add halfmeal
	public void addMeal(Restaurant restaurant, String mealname, String dishname1, String dishname2){
		restaurant.addMeal(mealname, dishname1, dishname2);
	}
	//add fullmeal
	public void addMeal(Restaurant restaurant, String mealname, String dishname1, String dishname2, String dishname3){
		restaurant.addMeal(mealname, dishname1, dishname2, dishname3);
	}
	
	public void removeMeal(Restaurant restaurant, String mealname){
		restaurant.removeMeal(mealname);
	}
	
	public void addSpecialOffer(Restaurant restaurant, Meal meal){
		restaurant.addSpecialMeal(meal.getName());
		specialofferboard.addSpecialOffer(new SpecialOffer(restaurant, meal));
	}
	public void removeSpecialOffer(Restaurant restaurant, Meal meal){
		restaurant.removeSpecialMeal(meal.getName());
		specialofferboard.removeSpecialOffer(new SpecialOffer(restaurant,meal));
	}
	
	public void setGDF(Restaurant r, double gdf){
		r.setGeneric_discount_factor(gdf);
	}
	public void setSDF(Restaurant r, double sdf){
		r.setSpecial_discount_factor(sdf);
	}
	
	public void DisplayMostOrderedHalfMeal(Restaurant r){
		r.DisplayMostOrderedHalfMeal();
	}
	
	public void DisplayLeastOrderedHalfMeal(Restaurant r){
		r.DisplayLeastOrderedHalfMeal();
	}
	
	public void DisplayMostOrderedAlaCarte(Restaurant r){
		r.DisplayMostOrderedAlaCarte();
	}
	
	public void DisplayLeastOrderedAlaCarte(Restaurant r){
		r.DisplayLeastOrderedAlaCarte();
	}
	
	//[[[Used by Courier]]]
	
	//to be completed
	public void setOnDuty(Courier c){
		c.turnOnDuty();
		activecouriers.add(c);
	}
	
	public void setOffDuty(Courier c){
		c.turnOffDuty();
		activecouriers.remove(c);
	}
}
