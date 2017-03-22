package service;

import model.restaurant.Dish;
import model.restaurant.DishFactory;
import model.restaurant.Meal;
import model.restaurant.Order;

public interface RestaurantService {

	//Add  a meal to the meal menu.
	//Error occurs when dish name is not recognized or when dish types don't match
	//Half-meal
	public void addMeal(String mealname, String dishname1, String dishname2);
	
	//Full-meal
	public void addMeal(String mealname, String startername, String maindishname,String dessertname);
	
	//create an instance of Meal
	public Meal createMeal(String mealType, String mealName);
	
	//Remove a meal from the meal menu
	public void removeMeal(String mealName);
	
	//add/remove a dish to the menu
	public void addDish(Dish dish);
	
	public void removeDish(String dishName);
	
	public boolean hasDish(String dishName);
	
	//create an instance of Dish
	public Dish createDish(String dishName);
	
	//throw exception if meal name is not recognized
	public void addSpecialMeal(String mealName);
	
	public void removeSpecialMeal(String mealName);

	public void addToHistory(Order order);
	
	public void displayMenu();
	
	public void displayMealMenu();
	
	public void displaySpecialMenu();
}
