/*
 * 
 */
package restaurant;

import exceptions.MealNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating HalfMeal objects.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class HalfMealFactory extends MealFactory {

	/**
	 * Instantiates a new half meal factory.
	 *
	 * @param mealmenu the mealmenu
	 */
	public HalfMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}
	
	/* (non-Javadoc)
	 * @see restaurant.MealFactory#createMeal(java.lang.String)
	 */
	@Override
	public HalfMeal createMeal(String mealName) throws MealNotFoundException{
		// TODO Auto-generated method stub
		for (Meal halfmeal : this.mealmenu.getMeals()){
			if (halfmeal.getName().equalsIgnoreCase(mealName)){
				return new HalfMeal(halfmeal);
			}
		}
		throw new MealNotFoundException(mealName);
	}

}
