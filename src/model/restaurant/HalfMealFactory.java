package model.restaurant;

import exceptions.MealNotFoundException;

public class HalfMealFactory extends MealFactory {

	public HalfMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}
	
	@Override
	public HalfMeal createMeal(String mealName) throws MealNotFoundException{
		// TODO Auto-generated method stub
		for (HalfMeal halfmeal : this.mealmenu.getHalfMealMenu()){
			if (halfmeal.getName()==mealName){
				return new HalfMeal(halfmeal);
			}
		}
		throw new MealNotFoundException(mealName);
	}

}
