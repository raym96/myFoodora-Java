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
	
	/** The mealmenu. */
	MealMenu mealmenu;
	/**
	 * Instantiates a new special meal factory.
	 *
	 * @param mealmenu the mealmenu
	 */
	public SpecialMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}

	/**
	 * Creates a new SpecialMeal object.
	 *
	 * @param mealName the meal name
	 * @return the meal
	 * @throws NameNotFoundException the name not found exception
	 */
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

	/**
	 * Gets the mealmenu.
	 *
	 * @return the mealmenu
	 */
	public MealMenu getMealmenu() {
		return mealmenu;
	}

	/**
	 * Sets the mealmenu.
	 *
	 * @param mealmenu the new mealmenu
	 */
	public void setMealmenu(MealMenu mealmenu) {
		this.mealmenu = mealmenu;
	}
	
	
}
