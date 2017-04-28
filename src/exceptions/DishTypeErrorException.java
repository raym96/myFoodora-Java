/*
 * 
 */
package exceptions;

//Occurs when we try to create a Full-Meal with 2 starters for example

/**
 * The Class DishTypeErrorException. Thrown when the dish type is wrong when creating
 * a meal, for example when a restaurant tries to add 2 starters to a half-meal.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class DishTypeErrorException extends Exception {
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	

	/**
	 * Prints the error.
	 */
	public void printError() {
		System.out.println("DISH TYPE ERROR: A meal can have at most 1 dessert, 1 main & 1 dessert. Furthermore,"
				+ "a half-meal must have a least 1 main-dish.");
	}
	
	

}
