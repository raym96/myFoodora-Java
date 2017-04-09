/*
 * 
 */
package system;

import restaurant.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface ShoppingCartVisitor.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface ShoppingCartVisitor {
	
	/**
	 * Visit.
	 *
	 * @param mealOrder the meal order
	 * @return the double
	 */
	double visit(SpecialMealOrder mealOrder);
	
	/**
	 * Visit.
	 *
	 * @param order the order
	 * @return the double
	 */
	double visit(StandardMealOrder order);
	
	/**
	 * Visit.
	 *
	 * @param dish the dish
	 * @return the double
	 */
	double visit(AlaCarteOrder dish);
}
