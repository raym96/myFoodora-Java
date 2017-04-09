/*
 * 
 */
package restaurant;

import exceptions.MealNotFoundException;


/**
 * A factory for creating FullMeal objects.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class FullMealFactory extends MealFactory {

	/**
	 * Instantiates a new full meal factory.
	 *
	 * @param mealmenu the mealmenu
	 */
	public FullMealFactory(MealMenu mealmenu){
		this.mealmenu=mealmenu;
	}
	
	/* (non-Javadoc)
	 * @see restaurant.MealFactory#createMeal(java.lang.String)
	 */
	@Override
	public FullMeal createMeal(String mealName) throws MealNotFoundException{
		// TODO Auto-generated method stub
		for (Meal fullmeal : mealmenu.getMeals()){
			if (fullmeal.getName().equalsIgnoreCase(mealName)){
				return new FullMeal(fullmeal);
			}
		}
		throw new MealNotFoundException(mealName);
	}
}
