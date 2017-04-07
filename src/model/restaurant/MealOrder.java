package model.restaurant;

import model.customer.ShoppingCartVisitor;
import model.users.Customer;
import model.users.Restaurant;

public abstract class MealOrder extends Order {
	protected Meal meal;
	private String mealCategory;
	
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

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public Meal getMeal(){
		return meal;
	}
	
	
	public String getMealType() {
		return mealCategory;
	}

	public String getName() {
		return meal.getName();
	}
	
	@Override
	public abstract double accept(ShoppingCartVisitor visitor);
	
}
