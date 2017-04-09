/*
 * 
 */
package system;

import restaurant.Meal;
import user.model.Customer;
import user.model.Restaurant;


/**
 * The Class StandardMealOrder.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class StandardMealOrder extends MealOrder {
	
	/**
	 * Instantiates a new standard meal order.
	 *
	 * @param customer the customer
	 * @param restaurant the restaurant
	 * @param meal the meal
	 */
	public StandardMealOrder(Customer customer, Restaurant restaurant, Meal meal) {
		super(customer, restaurant, meal);
		this.price = accept(new ConcreteShoppingCartVisitor());
	}

	
	/* (non-Javadoc)
	 * @see system.MealOrder#accept(system.ShoppingCartVisitor)
	 */
	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		this.price = visitor.visit(this);
		return price;
	}
}
