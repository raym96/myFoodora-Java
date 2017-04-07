package model.restaurant;

import exceptions.MealNotFoundException;

public class SpecialMealFactory extends MealFactory {
	public SpecialMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}
	
	@Override
	public Meal createMeal(String mealName) throws MealNotFoundException{
		// TODO Auto-generated method stub
		for (Meal meal : this.mealmenu.getMeals()){
			if (meal.getName().equalsIgnoreCase(mealName)){
				return new SpecialMeal(meal);
			}
		}
		throw new MealNotFoundException(mealName);
	}

}
