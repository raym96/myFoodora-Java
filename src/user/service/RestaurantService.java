/*
 * 
 */
package user.service;

import java.util.ArrayList;

import exceptions.NameNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.NameNotFoundException;
import exceptions.NameAlreadyExistsException;
import restaurant.Dish;
import restaurant.Meal;
import system.Order;


/**
 * The Interface RestaurantService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface RestaurantService {

	/**
	 * Adds a dish to the restaurant menu.
	 *
	 * @param dish the dish
	 */
	// add/remove a dish to the menu
	public void addDish(Dish dish);
	
	/**
	 * Adds the dish.
	 *
	 * @param dishName the dish name
	 * @param dishCategory the dish category
	 * @param foodCategory the food category
	 * @param unitPrice the unit price
	 */
	public void addDish(String dishName, String dishCategory, String foodCategory, double unitPrice);

	
	/**
	 * Removes a dish from the restaurant menu	 *.
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
	
	/**
	 * Add  an existing meal object to the meal menu.
	 Error occurs when dish name is not recognized or when dish types don't match/

	 *
	 * @param meal the meal
	 */
	public void addMeal(Meal meal);
	
	/**
	 * Create a half-meal from the dishes of the dish menu and add it to the meal menu
	 * Exception thrown if dish categories don't match.
	 * @param mealname the mealname
	 * @param dishname1 the dishname 1
	 * @param dishname2 the dishname 2
	 */
	public void addMeal(String mealname, String dishname1, String dishname2);
	
	/**
	 * Create a full-meal from the dishes of the dish menu and add it to the meal menu
	 * Exception thrown if dish categories don't match.
	 * @param mealname the mealname
	 * @param startername the startername
	 * @param maindishname the maindishname
	 * @param dessertname the dessertname
	 */
	public void addMeal(String mealname, String startername, String maindishname,String dessertname);
	
	/**
	 * Creates a half-meal/full-meal from a factory.
	 *
	 * @param mealType the meal type
	 * @param mealName the meal name
	 * @return the meal
	 */
	//create an instance of Meal
	public Meal createFactoryMeal(String mealType, String mealName);	
	
	/**
	 * Creates the meal.
	 *
	 * @param mealName the meal name
	 * @throws NameAlreadyExistsException the name already exists exception
	 */
	//create an empty meal
	public void createMeal(String mealName) throws NameAlreadyExistsException;
	
	/**
	 * Creates the dish from a dish factory.
	 *
	 * @param dishName the dish name
	 * @return the dish
	 */
	//create an instance of Dish
	public Dish createFactoryDish(String dishName);
	
	/**
	 * Removes a meal from the meal menu.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 */
	//Remove a meal from the meal menu
	public void removeMeal(String mealName) throws NameNotFoundException;
	
	/**
	 * Promotes an existing meal to the meal-of-the-week meal-menu.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 */
	//throw exception if meal name is not recognized
	public void setSpecialOffer(String mealName) throws NameNotFoundException;	
	
	/**
	 * Removes a meal-of-the-week and places it in the regular meal-menu.
	 *
	 * @param mealName the meal name
	 */
	public void removeSpecialOffer(String mealName);

	/**
	 * establishing the generic discount factor (default 5%) to apply when computing.
	 *
	 * @param generic_discount_factor the new generic discount factor
	 */
	// a meal price
	public void setGenericDiscountFactor(double generic_discount_factor);
	
	/**
	 *  establishing the special discount factor (default 10%) to apply to the meal-of-week.
	 *
	 * @param special_discount_factor the new special discount factor
	 */
	public void setSpecialDiscountFactor(double special_discount_factor);
	
	/**
	 * Display most ordered half meal.
	 */
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
	
	
	// #. extra tool methods

	/**
	 * Adds the to history.
	 *
	 * @param order the order
	 */
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

	/**
	 * Adds the dish 2 meal.
	 *
	 * @param dishName the dish name
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 * @throws NameNotFoundException the name not found exception
	 * @throws DishTypeErrorException the dish type error exception
	 */
	void addDish2Meal(String dishName, String mealName) throws NameNotFoundException, NameNotFoundException, DishTypeErrorException;

	/**
	 * Show meal.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 */
	void showMeal(String mealName) throws NameNotFoundException;

	/**
	 * Save meal.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 * @throws DishTypeErrorException the dish type error exception
	 */
	void saveMeal(String mealName) throws NameNotFoundException, DishTypeErrorException;


	
}
