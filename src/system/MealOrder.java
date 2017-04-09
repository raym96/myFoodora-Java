/*
 * 
 */
package system;

import restaurant.FullMeal;
import restaurant.HalfMeal;
import restaurant.Meal;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class MealOrder.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class MealOrder extends Order {
	
	/** The meal. */
	protected Meal meal;
	
	/** The meal category. */
	private String mealCategory;
	
	/**
	 * Instantiates a new meal order.
	 *
	 * @param customer the customer
	 * @param restaurant the restaurant
	 * @param meal the meal
	 */
	public MealOrder(Customer customer, Restaurant restaurant, Meal meal) {
		super(customer, restaurant);
		this.meal = meal;
		if (meal instanceof HalfMeal){
			mealCategory = "Half-meal";
		}
		if (meal instanceof FullMeal){
			mealCategory = "Full-meal";
		}
	}

	/* (non-Javadoc)
	 * @see system.Order#getRestaurant()
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	/**
	 * Gets the meal.
	 *
	 * @return the meal
	 */
	public Meal getMeal(){
		return meal;
	}
	
	
	/**
	 * Gets the meal type.
	 *
	 * @return the meal type
	 */
	public String getMealType() {
		return mealCategory;
	}

	/* (non-Javadoc)
	 * @see system.Order#getName()
	 */
	public String getName() {
		return meal.getName();
	}
	
	/* (non-Javadoc)
	 * @see system.Order#accept(system.ShoppingCartVisitor)
	 */
	@Override
	public abstract double accept(ShoppingCartVisitor visitor);
	
}
