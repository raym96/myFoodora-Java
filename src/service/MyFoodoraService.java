package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.ConcreteShoppingCartVisitor;
import model.customer.PointCard;
import model.customer.ShoppingCartVisitor;
import model.myfoodora.DeliveryTask;
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
	
	// 3. computing the total income (i.e. the sum of all completed orders) as well as the total
	// profit of the system, knowing that the the prot of a single order is given by:
	// profit for one order = order_price * markup_percentage + service_fee - delivery cost
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
	
	public void askAgree2customers(String ask);
	
	public void displayUsersOfAssignedType(String userType);
	
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
	
	// 1. setting of the service-fee, the markup percentage (\percentage de marge") and the
	// delivery cost values
	public void setGDF(Restaurant r, double gdf);
	public void setSDF(Restaurant r, double sdf);
	
	public void DisplayMostOrderedHalfMeal(Restaurant r);
	
	public void DisplayLeastOrderedHalfMeal(Restaurant r);
	
	public void DisplayMostOrderedAlaCarte(Restaurant r);
	public void DisplayLeastOrderedAlaCarte(Restaurant r);
	
	//[[[Used by Courier]]]
	public void setOnDuty(Courier c);
	public void setOffDuty(Courier c);
	
	// 2. allocating of a courier to an order placed by a customer (by application of the current
	// delivery policy, see below details of supported policies)
	public void allocateDeliveryTask(Courier c);
	public void updateCurrentDeliveryTask(DeliveryTask task);

	// 3. notifying users that gave consensus to receive special oers notications, of a new
	// special offer set by a restaurant
	
	// 4. chose the target prot policy (see below) used to optimise the profit-related-
	// parameters (service-fee, markup percentage, and the delivery cost)
	
	public User selectUser(String username);
}
