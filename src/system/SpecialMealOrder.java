/*
 * 
 */
package system;

import restaurant.Meal;
import user.model.Customer;
import user.model.Restaurant;

/**
 * The Class SpecialMealOrder.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SpecialMealOrder extends MealOrder{
	
	/**
	 * Instantiates a new special meal order.
	 *
	 * @param customer the customer
	 * @param restaurant the restaurant
	 * @param meal the meal
	 */
	public SpecialMealOrder(Customer customer,Restaurant restaurant, Meal meal) {
		super(customer, restaurant, meal);
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
