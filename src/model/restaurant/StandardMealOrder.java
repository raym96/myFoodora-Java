package model.restaurant;

import model.customer.ConcreteShoppingCartVisitor;
import model.customer.ShoppingCartVisitor;
import model.users.Customer;
import model.users.Restaurant;

public class StandardMealOrder extends MealOrder {
	public StandardMealOrder(Customer customer, Restaurant restaurant, Meal meal) {
		super(customer, restaurant, meal);
		this.price = accept(new ConcreteShoppingCartVisitor());
	}

	
	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		this.price = visitor.visit(this);
		return price;
	}
}
