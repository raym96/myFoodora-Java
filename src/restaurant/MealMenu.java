/*
 * 
 */
package restaurant;

import java.text.DecimalFormat;
import java.util.ArrayList;

import exceptions.MealNotFoundException;
import exceptions.NameAlreadyExistsException;

// TODO: Auto-generated Javadoc
/**
 * The Class MealMenu.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MealMenu {

	/** The meals. */
	private ArrayList<Meal> meals;
	
	/** The discount factor. */
	private double discount_factor; //depends on whether it is a special meal menu or standard meal menu
	//simple observer pattern on discount factor
	
	/**
	 * Instantiates a new meal menu.
	 *
	 * @param discount_factor the discount factor
	 */
	public MealMenu(double discount_factor) {
		super();
		meals = new ArrayList<Meal>();
		this.discount_factor=discount_factor;
	}
	
	
	/**
	 * Adds the meal.
	 *
	 * @param meal the meal
	 * @throws NameAlreadyExistsException the name already exists exception
	 */
	public void addMeal(Meal meal) throws NameAlreadyExistsException{
		if (hasMeal(meal.getName())){
			throw new NameAlreadyExistsException(meal.getName());
		}
		double price = meal.getRawprice()*(1-discount_factor);
		meal.setPrice(Math.floor(price*100)/100);
		meals.add(meal);
	}
	
	/**
	 * Removes the meal.
	 *
	 * @param mealName the meal name
	 * @throws MealNotFoundException the meal not found exception
	 */
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
	
	/**
	 * Checks for meal.
	 *
	 * @param mealName the meal name
	 * @return true, if successful
	 */
	public boolean hasMeal(String mealName){
		for (Meal m:getMeals()){
			if (m.getName().equalsIgnoreCase(mealName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the meals.
	 *
	 * @return the meals
	 */
	public ArrayList<Meal> getMeals(){
		return meals;
	}
	
	/**
	 * Sets the discount factor.
	 *
	 * @param discount_factor the new discount factor
	 */
	public void setDiscountFactor(double discount_factor){
		this.discount_factor=discount_factor;
	}

	
	/**
	 * Display.
	 */
	public void display(){
		for (Meal meal:meals){
			double price = meal.getRawprice()*(1-discount_factor);
			meal.setPrice(Math.floor(price*100)/100); //arrondi
			System.out.println(meal);
		}
	}


	/**
	 * Gets the discount factor.
	 *
	 * @return the discount factor
	 */
	public double getDiscount_factor() {
		return discount_factor;
	}


	/**
	 * Sets the discount factor.
	 *
	 * @param discount_factor the new discount factor
	 */
	public void setDiscount_factor(double discount_factor) {
		this.discount_factor = discount_factor;
	}
	
}
