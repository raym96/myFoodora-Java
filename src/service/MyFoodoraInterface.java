package service;

import exceptions.UserNotFoundException;
import model.restaurant.Dish;
import model.restaurant.Meal;
import model.user.Customer;
import model.user.Restaurant;
import model.user.User;

public interface MyFoodoraInterface {
	
	public User selectUser(String username) throws UserNotFoundException;
	
	//[[used by Manager]]
	public void addUser(User user);
	public void removeUser(User user);
	public void activateUser(User user) throws UserNotFoundException;
	public void disactivateUser(User user);
	public void dispalyUsers();
	public void displayActiveUsers();
	
	//[[[Used by Customer]]]
	public void addMealOrder(Customer c,Restaurant r, String mealType, String mealName);
	public void addAlaCarteOrder(Customer c,Restaurant r, String dishName);
	public double calculatePrice(Customer c);
	public void pay(Customer c);
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
	
	//[[[Used by Courier]]]
	
		//to be completed
	
}
