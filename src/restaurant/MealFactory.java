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
public class MealFactory {
	
	/** The mealmenu. */
	//needs to know what's in the meal-menu to create meals
	protected MealMenu mealmenu;
	
	public MealFactory(MealMenu mealmenu) {
		this.mealmenu = mealmenu;
	}



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
	public Meal createMeal(String mealName) throws MealNotFoundException{
		// TODO Auto-generated method stub
		for (Meal meal : mealmenu.getMeals()){
			if (meal.getName().equalsIgnoreCase(mealName)){
				if (meal instanceof HalfMeal){
					return new HalfMeal(meal);
				}
				if (meal instanceof FullMeal){
					return new FullMeal(meal);
				}
			}
		}
		throw new MealNotFoundException(mealName);
	}
}
