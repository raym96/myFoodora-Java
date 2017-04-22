/*
 * 
 */
package restaurant;

import exceptions.NameNotFoundException;


/**
 * A factory for creating Meal-of-the-weeks which can be halfmeal or fullmeal.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SpecialMealFactory {
	
	MealMenu mealmenu;
	/**
	 * Instantiates a new special meal factory.
	 *
	 * @param mealmenu the mealmenu
	 */
	public SpecialMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}

	public Meal createMeal(String mealName) throws NameNotFoundException{
		// TODO Auto-generated method stub
		for (Meal meal : this.mealmenu.getMeals()){
			if (meal.getName().equalsIgnoreCase(mealName)){
				if (meal instanceof HalfMeal){
					return new HalfMeal(meal);
				}
				if (meal instanceof FullMeal){
					return new FullMeal(meal);
				}
			}
		}
		throw new NameNotFoundException(mealName);
	}

}
