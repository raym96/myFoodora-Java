package model.restaurant;

import java.util.ArrayList;

public class SpecialMealMenu {
	private ArrayList<Meal> specialmeals;
	
	public SpecialMealMenu(){
		specialmeals = new ArrayList<Meal>();
	}
	public void addMeal(Meal meal){
		specialmeals.add(meal);
	}
	
	public ArrayList<Meal> getMeals(){
		return specialmeals;
	}
/*	public void addMeal(String mealName){
		for (Meal meal : this.mealmenu.getHalfMealMenu()){
			if (meal.getName()==mealName){
				specialmeals.add(meal);
			}
		}
		for (Meal meal : this.mealmenu.getFullMealMenu()){
			if (meal.getName()==mealName){
				specialmeals.add(meal);
			}
		}
	}*/
}
