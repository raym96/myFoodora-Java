/*
 * 
 */
package user.service;

import restaurant.Dish;
import restaurant.Meal;
import system.Order;

// TODO: Auto-generated Javadoc
/**
 * The Interface RestaurantService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface RestaurantService {

	// 1. editing the restaurant menu (adding/removing items)
	/**
	 * Adds the dish.
	 *
	 * @param dish the dish
	 */
	// add/remove a dish to the menu
	public void addDish(Dish dish);
	
	/**
	 * Removes the dish.
	 *
	 * @param dishName the dish name
	 */
	public void removeDish(String dishName);
	
	/**
	 * Checks for dish.
	 *
	 * @param dishName the dish name
	 * @return true, if successful
	 */
	public boolean hasDish(String dishName);
	
	// 2. creating/removing different meals (half or full meal, vegetarian, gluten-free
	// and/or standard meals).
	// Add  a meal to the meal menu.
	// Error occurs when dish name is not recognized or when dish types don't match
	// 2 ways to add a meal : from a existing Meal object or create a new meal
	/**
	 * Adds the meal.
	 *
	 * @param meal the meal
	 */
	// from a existing meal object:
	public void addMeal(Meal meal);
	
	/**
	 * Adds the meal.
	 *
	 * @param mealname the mealname
	 * @param dishname1 the dishname 1
	 * @param dishname2 the dishname 2
	 */
	//Half-meal
	public void addMeal(String mealname, String dishname1, String dishname2);
	
	/**
	 * Adds the meal.
	 *
	 * @param mealname the mealname
	 * @param startername the startername
	 * @param maindishname the maindishname
	 * @param dessertname the dessertname
	 */
	//Full-meal
	public void addMeal(String mealname, String startername, String maindishname,String dessertname);
	
	/**
	 * Creates the meal.
	 *
	 * @param mealType the meal type
	 * @param mealName the meal name
	 * @return the meal
	 */
	//create an instance of Meal
	public Meal createMeal(String mealType, String mealName);	
	
	/**
	 * Removes the meal.
	 *
	 * @param mealName the meal name
	 */
	//Remove a meal from the meal menu
	public void removeMeal(String mealName);
	
	/**
	 * Creates the dish.
	 *
	 * @param dishName the dish name
	 * @return the dish
	 */
	//create an instance of Dish
	public Dish createDish(String dishName);	
	
	/**
	 * Adds the special meal.
	 *
	 * @param mealName the meal name
	 */
	//throw exception if meal name is not recognized
	public void addSpecialMeal(String mealName);	
	
	/**
	 * Removes the special meal.
	 *
	 * @param mealName the meal name
	 */
	public void removeSpecialMeal(String mealName);

	// 3. establishing the generic discount factor (default 5%) to apply when computing
	/**
	 * Sets the generic discount factor.
	 *
	 * @param generic_discount_factor the new generic discount factor
	 */
	// a meal price
	public void setGenericDiscountFactor(double generic_discount_factor);
	
	// 4. establishing the special discount factor (default 10%) to apply to the meal-of-
	/**
	 * Sets the special discount factor.
	 *
	 * @param special_discount_factor the new special discount factor
	 */
	// the-week special offer.
	public void setSpecialDiscountFactor(double special_discount_factor);
	
	/**
	 * Display most ordered half meal.
	 */
	// 5. sorting of shipped orders with respect to different criteria (see below)
	public void DisplayMostOrderedHalfMeal();
	
	/**
	 * Display least ordered half meal.
	 */
	public void DisplayLeastOrderedHalfMeal();
	
	/**
	 * Display most ordered ala carte.
	 */
	public void DisplayMostOrderedAlaCarte();
	
	/**
	 * Display least ordered ala carte.
	 */
	public void DisplayLeastOrderedAlaCarte();
	
	/**
	 * Adds the to history.
	 *
	 * @param order the order
	 */
	// #. extra tool methods
	public void addToHistory(Order order);
	
	/**
	 * Display menu.
	 */
	public void displayMenu();
	
	/**
	 * Display meal menu.
	 */
	public void displayMealMenu();
	
	/**
	 * Display special menu.
	 */
	public void displaySpecialMenu();
	
	/**
	 * Display all menu.
	 */
	public void displayAllMenu();
	
}
