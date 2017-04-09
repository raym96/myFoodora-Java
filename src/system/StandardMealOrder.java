package system;

import restaurant.Meal;
import user.model.Customer;
import user.model.Restaurant;

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
