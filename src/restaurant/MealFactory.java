/*
 * 
 */
package restaurant;

import java.util.ArrayList;

import exceptions.MealNotFoundException;


/**
 * A factory for creating Meal objects.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class MealFactory {
	
	/** The mealmenu. */
	//needs to know what's in the meal-menu to create meals
	protected MealMenu mealmenu;
	
	/**
	 * Gets the mealmenu.
	 *
	 * @return the mealmenu
	 */
	public MealMenu getMealmenu() {
		return mealmenu;
	}

	/**
	 * Creates a new Meal object.
	 *
	 * @param mealName the meal name
	 * @return the meal
	 * @throws MealNotFoundException the meal not found exception
	 */
	public abstract Meal createMeal(String mealName) throws MealNotFoundException;
}
