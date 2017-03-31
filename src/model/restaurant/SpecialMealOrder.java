package model.restaurant;

import model.customer.ShoppingCartVisitor;
import model.user.Customer;
import model.user.Restaurant;

public class SpecialMealOrder extends MealOrder{
	public SpecialMealOrder(Customer customer,Restaurant restaurant, Meal meal) {
		super(customer, restaurant, meal);
		}

	
	@Override
	public String toString() {
		return "<SpecialMealOrder> "+meal.getName()+"|"+customer.getUsername()+ "|" +restaurant.getUsername()+"|"+ date;

	}


	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		this.price = visitor.visit(this);
		return price;
	}
	
}
