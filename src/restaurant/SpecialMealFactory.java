/*
 * 
 */
package restaurant;

import exceptions.MealNotFoundException;


/**
 * A factory for creating Meal-of-the-weeks which can be halfmeal or fullmeal.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SpecialMealFactory extends MealFactory {
	
	/**
	 * Instantiates a new special meal factory.
	 *
	 * @param mealmenu the mealmenu
	 */
	public SpecialMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}
	
	/* (non-Javadoc)
	 * @see restaurant.MealFactory#createMeal(java.lang.String)
	 */
	@Override
	public Meal createMeal(String mealName) throws MealNotFoundException{
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
		throw new MealNotFoundException(mealName);
	}

}
