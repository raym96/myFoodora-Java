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
	 * Visit a special meal order and applies special factor
	 *
	 * @param mealOrder the meal order
	 * @return the double
	 */
	double visit(SpecialMealOrder mealOrder);
	
	/**
	 * Visit a standard meal order and applies generic factor
	 *
	 * @param order the order
	 * @return the double
	 */
	double visit(StandardMealOrder order);
	
	/**
	 * Visit an a la carte order and applies generic factor
	 *
	 * @param dish the dish
	 * @return the double
	 */
	double visit(AlaCarteOrder dish);
}
