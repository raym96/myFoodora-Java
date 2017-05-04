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
public interface RestaurantService extends UserService{

	/**
	 * Adds the dish.
	 *
	 * @param dishName the dish name
	 * @param dishCategory the dish category
	 * @param foodCategory the food category
	 * @param unitPrice the unit price
	 * @throws NameAlreadyExistsException 
	 */
	public void addDish(String dishName, String dishCategory, String foodCategory, double unitPrice) throws NameAlreadyExistsException;

	/**
	 * Creates the meal.
	 *
	 * @param mealName the meal name
	 * @throws NameAlreadyExistsException the name already exists exception
	 */
	public void createMeal(String mealName) throws NameAlreadyExistsException;
	
	/**
	 * Adds the dish 2 meal.
	 *
	 * @param dishName the dish name
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 * @throws DishTypeErrorException the dish type error exception
	 */
	void addDish2Meal(String dishName, String mealName) throws NameNotFoundException, DishTypeErrorException;

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

	
	/**
	 * Removes a dish from the restaurant menu	 *.
	 *
	 * @param dishName the dish name
	 * @throws NameNotFoundException 
	 */
	public void removeDish(String dishName) throws NameNotFoundException;
	
	/**
	 * Removes a meal from the meal menu.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 */
	//Remove a meal from the meal menu
	public void removeMeal(String mealName) throws NameNotFoundException;

	/**
	 * Checks for dish.
	 *
	 * @param dishName the dish name
	 * @return true, if successful
	 */
	public boolean hasDish(String dishName);
	
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
	 * Creates the dish from a dish factory.
	 *
	 * @param dishName the dish name
	 * @return the dish
	 */
	//create an instance of Dish
	public Dish createFactoryDish(String dishName);
	
	
	/**
	 * Promotes an existing meal to the meal-of-the-week meal-menu.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException the name not found exception
	 */
	public void setSpecialOffer(String mealName) throws NameNotFoundException;	
	
	/**
	 * Removes a meal-of-the-week and places it in the regular meal-menu.
	 *
	 * @param mealName the meal name
	 * @throws NameNotFoundException 
	 */
	public void removeSpecialOffer(String mealName) throws NameNotFoundException;

	/**
	 * establishing the generic discount factor (default 5%) to apply when computing.
	 *
	 * @param generic_discount_factor the new generic discount factor
	 */
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
	public void displayMostOrderedHalfMeal();
	
	/**
	 * Display least ordered half meal.
	 */
	public void displayLeastOrderedHalfMeal();
	
	/**
	 * Display most ordered a la carte.
	 */
	public void displayMostOrderedAlaCarte();
	
	/**
	 * Display least ordered a la carte.
	 */
	public void displayLeastOrderedAlaCarte();
	
	
	// #. extra tool methods

	/**
	 * Adds the to history.
	 *
	 * @param order the order
	 */
	public void addToHistory(Order order);
	
	public void showInfo();
	
	public void showHistory();
	

	public void showDishes() ;


	public void showMeals() ;

	public void showDiscountFactors();
	
	public void showSpecialOffers();
	
	public void showTotalIncome();
	

	public void showMenu();

	
	
}
