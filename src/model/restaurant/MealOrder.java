package model.restaurant;

import model.customer.ShoppingCartVisitor;
import model.users.Customer;
import model.users.Restaurant;

public abstract class MealOrder extends Order {
	protected Meal meal;
	private String mealType;
	
	public MealOrder(Customer customer, Restaurant restaurant, Meal meal) {
		super(customer, restaurant);
		this.meal = meal;
		if (meal instanceof HalfMeal){
			mealType = "half meal";
		}
		if (meal instanceof FullMeal){
			mealType = "full meal";
		}
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public Meal getMeal(){
		return meal;
	}
	
	
	public String getMealType() {
		return mealType;
	}

	public String getName() {
		return meal.getName();
	}
	
	@Override
	public String toString(){
		String str = "";
		if (this instanceof SpecialMealOrder){
		str+= "<SpecialMealOrder> ";
		}
		else if (this instanceof StandardMealOrder){
			str+="<StandardMealOrder> ";
		}
		str+=this.meal.getName()+"|"+customer.getUsername()+ "|" +restaurant.getUsername()+"|"+ date;
		return str;
	}

	@Override
	public abstract double accept(ShoppingCartVisitor visitor);
	
}
