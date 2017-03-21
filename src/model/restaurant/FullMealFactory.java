package model.restaurant;

import exceptions.MealNotFoundException;

public class FullMealFactory extends MealFactory {

	public FullMealFactory(MealMenu mealmenu){
		this.mealmenu=mealmenu;
	}
	
	@Override
	public FullMeal createMeal(String mealName) throws MealNotFoundException{
		// TODO Auto-generated method stub
		for (FullMeal fullmeal : mealmenu.getFullMealMenu()){
			if (fullmeal.getName()==mealName){
				return new FullMeal(fullmeal);
			}
		}
		throw new MealNotFoundException(mealName);
	}
}
