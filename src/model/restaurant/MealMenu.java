package model.restaurant;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.user.Restaurant;

public class MealMenu {

	private ArrayList<Meal> meals;
	private double discount_factor; //depends on whether it is a special meal menu or standard meal menu
	//simple observer pattern on discount factor
	
	public MealMenu(double discount_factor) {
		super();
		meals = new ArrayList<Meal>();
		this.discount_factor=discount_factor;
	}
	
	
	public void addMeal(Meal meal){
		meals.add(meal);
	}
	
	public void removeMeal(String mealName){
		for(int i=0; i<meals.size(); i++){
			if( meals.get(i).getName() == mealName ){
				meals.remove(i);
			}
		}
	}
	
	public ArrayList<Meal> getMeals(){
		return meals;
	}
	
	public void setDiscountFactor(double discount_factor){
		this.discount_factor=discount_factor;
	}

	
	public void display(){
		for (Meal meal:meals){
			double price = meal.getRawprice()*(1-discount_factor);
			meal.setPrice(Math.floor(price*100)/100); //arrondi
			System.out.println(meal);
		}
	}
}
