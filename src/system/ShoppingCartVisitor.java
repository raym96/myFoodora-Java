/*
 * 
 */
package system;

import restaurant.*;


/**
 * The Interface ShoppingCartVisitor.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface ShoppingCartVisitor {
	
	/**
	 * Visit a meal and applies the right discount_factor
	 *
	 * @param mealOrder the meal order
	 * @return the double
	 */
	double visit(Meal meal);
	
	/**
	 * Visit a dish
	 *
	 * @param order the order
	 * @return the double
	 */
	double visit(Dish dish);
	
	double visit(Order order);
	
}
