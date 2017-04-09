package system;

import restaurant.Meal;
import user.model.Customer;
import user.model.Restaurant;

public class SpecialMealOrder extends MealOrder{
	public SpecialMealOrder(Customer customer,Restaurant restaurant, Meal meal) {
		super(customer, restaurant, meal);
		}


	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		this.price = visitor.visit(this);
		return price;
	}
	
}
