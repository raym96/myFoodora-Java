package service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.*;
import model.myfoodora.DeliveryPolicy;
import model.myfoodora.History;
import model.myfoodora.MyFoodora;
import model.myfoodora.SpecialOffer;
import model.myfoodora.SpecialOfferBoard;
import model.myfoodora.TargetProfitPolicy;
import model.myfoodora.TargetProfit_DeliveryCost;
import model.myfoodora.TargetProfit_Markup;
import model.myfoodora.TargetProfit_ServiceFee;
import model.restaurant.*;
import model.user.*;
import service.MyFoodoraService;

public class MyFoodoraServiceImpl implements MyFoodoraService{
	private MyFoodora myfoodora;

	//[[used by Manager]]
	public void addUser(User user){
		myfoodora.getUsers().add(user);
	}
	
	public void removeUser(User user){
		myfoodora.getUsers().remove(user);
	}
	
	public void activateUser(User user){
		myfoodora.getActiveUsers().add(user);
	}
	
	public void disactivateUser(User user){
		myfoodora.getActiveUsers().remove(user);
	}
	
	
	@Override
	public User selectUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getTotalIncome(Date date1, Date date2){
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(date1, date2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalIncome = 0;
		for (Order order:list){
			totalIncome += order.accept(visitor);
		}
		return totalIncome;
	}
	
	public double getTotalProfit(Date date1, Date date2){
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(date1, date2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalProfit = 0;
		for (Order order:list){
			totalProfit += order.accept(visitor)*myfoodora.getMarkup_percentage()+myfoodora.getService_fee()-myfoodora.getDelivery_cost();
		}
		return totalProfit;
	}
	
	public double getAverageIncomePerCustomer(Date date1, Date date2){
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(date1, date2);
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
		int number_of_orders = myfoodora.getHistory().getOrderBetween(aMonthAgo, new Date()).size();
		
		double newValue  = myfoodora.getTargetprofitpolicy().meetTargetProfit(targetProfit, lastIncome, myfoodora.getDelivery_cost(), myfoodora.getService_fee(), myfoodora.getMarkup_percentage(), number_of_orders);
		
		if (myfoodora.getTargetprofitpolicy() instanceof TargetProfit_DeliveryCost){
			myfoodora.setDelivery_cost(newValue);
		}
		if (myfoodora.getTargetprofitpolicy() instanceof TargetProfit_Markup){
			myfoodora.setMarkup_percentage(newValue);
		}
		if (myfoodora.getTargetprofitpolicy() instanceof TargetProfit_ServiceFee){
			myfoodora.setService_fee(newValue);
		}
	}
	
	
	public Courier parse(Order order){
		return myfoodora.getDeliverypolicy().parse(order, myfoodora.getActivecouriers());
	}
	

	//[[[Used by Customer]]]
	public void addStandardMealOrder(Customer c,Restaurant r, String mealType, String mealName){
		c.getCustomerService().addStandardMealOrder(r,  mealType,  mealName);
	}
	
	public void addSpecialMealOrder(Customer c,Restaurant r, String mealType, String mealName){
		c.getCustomerService().addSpecialMealOrder(r,  mealType,  mealName);
	}
	
	
	public void addAlaCarteOrder(Customer c,Restaurant r, String dishName){
		c.getCustomerService().addAlaCarteOrder(r, dishName);
	}
	
	public double calculatePrice(Customer c){
		return c.calculatePrice();
	}
	
	//Important method, to be completed
	public void pay(Customer c){
		c.pay();
		//Send the orders into history before clearing the shopping cart
		for (Order order:c.getShoppingCart().getOrders()){
			order.getRestaurant().addToHistory(order);
			addToHistory(order);
			System.out.println("Courier" + parse(order).getName() +" has been assigned to this order");
		}
		c.clearShoppingCart();
	}
	
	public void addToHistory(Order order){
		myfoodora.getHistory().addOrder(order);
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
		myfoodora.getSpecialofferboard().register(customer);
	}
	
	public void turnOffNotification(Customer customer){
		customer.turnOffNotification();
		myfoodora.getSpecialofferboard().unregister(customer);
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
		myfoodora.getSpecialofferboard().addSpecialOffer(new SpecialOffer(restaurant, meal));
	}
	public void removeSpecialOffer(Restaurant restaurant, Meal meal){
		restaurant.removeSpecialMeal(meal.getName());
		myfoodora.getSpecialofferboard().removeSpecialOffer(new SpecialOffer(restaurant,meal));
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
		myfoodora.getActivecouriers().add(c);
	}
	
	public void setOffDuty(Courier c){
		c.turnOffDuty();
		myfoodora.getActivecouriers().remove(c);
	}

	public void displayUsers() {
		// TODO Auto-generated method stub
		myfoodora.displayUsers();
	}
	public void displayActiveUsers(){
		myfoodora.displayActiveUsers();
	}
}