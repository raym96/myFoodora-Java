package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.ConcreteShoppingCartVisitor;
import model.customer.PointCard;
import model.customer.ShoppingCartVisitor;
import model.myfoodora.MyFoodora;
import model.myfoodora.SpecialOffer;
import model.myfoodora.TargetProfit_DeliveryCost;
import model.myfoodora.TargetProfit_Markup;
import model.myfoodora.TargetProfit_ServiceFee;
import model.restaurant.Dish;
import model.restaurant.Meal;
import model.restaurant.Order;
import model.user.Courier;
import model.user.Customer;
import model.user.Restaurant;
import model.user.User;

public interface MyFoodoraService {
		
	//[[used by Manager]]
	public void addUser(User user);
	public void removeUser(User user);
	public void activateUser(User user) throws UserNotFoundException;
	public void disactivateUser(User user);
	public void displayUsers();
	public void displayActiveUsers();
	
	public double getTotalIncome(Date date1, Date date2);
	public double getTotalProfit(Date date1, Date date2);
	
	public double getAverageIncomePerCustomer(Date date1, Date date2);
	public void applyTargetProfitPolicy(double targetProfit);
	
	
	public Courier parse(Order order);

	//[[[Used by Customer]]]
	public void addStandardMealOrder(Customer c,Restaurant r, String mealType, String mealName);
	public void addSpecialMealOrder(Customer c,Restaurant r, String mealType, String mealName);
	
	
	public void addAlaCarteOrder(Customer c,Restaurant r, String dishName);
	
	public double calculatePrice(Customer c);
	
	//Important method, to be completed
	public void pay(Customer c);
	
	public void addToHistory(Order order);
	
	public void registerCard(Customer c, String cardType);
	public void unregisterCard(Customer c);
	public void getCardPoints(Customer c);
	
	public void turnOnNotification(Customer customer);
	public void turnOffNotification(Customer customer);
	//[[[Used by Restaurant]]]
	public void addDish(Restaurant restaurant, Dish dish);
	public void removeDish(Restaurant restaurant, String dishName);
	//add halfmeal
	public void addMeal(Restaurant restaurant, String mealname, String dishname1, String dishname2);
	//add fullmeal
	public void addMeal(Restaurant restaurant, String mealname, String dishname1, String dishname2, String dishname3);
	
	public void removeMeal(Restaurant restaurant, String mealname);
	
	public void addSpecialOffer(Restaurant restaurant, Meal meal);
	public void removeSpecialOffer(Restaurant restaurant, Meal meal);
	
	public void setGDF(Restaurant r, double gdf);
	public void setSDF(Restaurant r, double sdf);
	
	public void DisplayMostOrderedHalfMeal(Restaurant r);
	
	public void DisplayLeastOrderedHalfMeal(Restaurant r);
	
	public void DisplayMostOrderedAlaCarte(Restaurant r);
	public void DisplayLeastOrderedAlaCarte(Restaurant r);
	
	//[[[Used by Courier]]]
	
	//to be completed
	public void setOnDuty(Courier c);
	
	public void setOffDuty(Courier c);
	public User selectUser(String username);
}
