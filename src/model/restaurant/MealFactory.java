package model.restaurant;

import java.util.ArrayList;

import exceptions.MealNotFoundException;

public abstract class MealFactory {
	//needs to know what's in the meal-menu to create meals
	protected MealMenu mealmenu;
	
	public abstract Meal createMeal(String mealName) throws MealNotFoundException;
}
