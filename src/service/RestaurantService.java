package service;

import model.restaurant.Dish;
import model.restaurant.DishFactory;
import model.restaurant.Meal;
import model.restaurant.Order;

public interface RestaurantService {

	// restaurant can perform thest operations : 

	// 1. editing the restaurant menu (adding/removing items)
	// add/remove a dish to the menu
	public void addDish(Dish dish);
	public void removeDish(String dishName);
	public boolean hasDish(String dishName);
	
	// 2. creating/removing dierent meals (half or full meal, vegetarian, gluten-free
	// and/or standard meals).
	// Add  a meal to the meal menu.
	// Error occurs when dish name is not recognized or when dish types don't match
	// Half-meal
	public void addMeal(String mealname, String dishname1, String dishname2);
	//Full-meal
	public void addMeal(String mealname, String startername, String maindishname,String dessertname);
	//create an instance of Meal
	public Meal createMeal(String mealType, String mealName);	
	//Remove a meal from the meal menu
	public void removeMeal(String mealName);
	//create an instance of Dish
	public Dish createDish(String dishName);	
	//throw exception if meal name is not recognized
	public void addSpecialMeal(String mealName);	
	public void removeSpecialMeal(String mealName);

	// 3. establishing the generic discount factor (default 5%) to apply when computing
	// a meal price
	public void settingGenericDiscountFactor(double generic_discount_factor);
	
	// 4. establishing the special discount factor (default 10%) to apply to the meal-of-
	// the-week special offer.
	public void settingSpecialDiscountFactor(double special_discount_factor);
	
	// 5. sorting of shipped orders with respect to dierent criteria (see below)
	public void sortingShippedOrders();    // to be completed
	
	
	// #. extra tool methods
	public void addToHistory(Order order);
	
	public void displayMenu();
	
	public void displayMealMenu();
	
	public void displaySpecialMenu();
	
	public void DisplayMostOrderedHalfMeal();
	
	public void DisplayLeastOrderedHalfMeal();
	
	public void DisplayMostOrderedAlaCarte();
	
	public void DisplayLeastOrderedAlaCarte();
}
