package model.restaurant;

import java.text.DecimalFormat;
import java.util.ArrayList;

import exceptions.MealNotFoundException;
import model.users.Restaurant;

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
		double price = meal.getRawprice()*(1-discount_factor);
		meal.setPrice(Math.floor(price*100)/100);
		meals.add(meal);
	}
	
	public void removeMeal(String mealName) throws MealNotFoundException{
		int count = 0;
		for(int i=0; i<meals.size(); i++){
			if( meals.get(i).getName().equalsIgnoreCase(mealName)){
				meals.remove(i);
				count++;
			}
		}
		if (count==0){
			throw new MealNotFoundException(mealName);
		}
	}
	
	public boolean hasMeal(String mealName){
		for (Meal m:getMeals()){
			if (m.getName().equalsIgnoreCase(mealName)){
				return true;
			}
		}
		return false;
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


	public double getDiscount_factor() {
		return discount_factor;
	}


	public void setDiscount_factor(double discount_factor) {
		this.discount_factor = discount_factor;
	}
	
	
}
