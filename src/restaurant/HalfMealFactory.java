package restaurant;

import exceptions.MealNotFoundException;

public class HalfMealFactory extends MealFactory {

	public HalfMealFactory(MealMenu mealmenu){
		this.mealmenu = mealmenu;
	}
	
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
