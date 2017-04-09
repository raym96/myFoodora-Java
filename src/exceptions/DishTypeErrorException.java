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
	 * Instantiates a new dish type error exception.
	 *
	 * @param errortype the errortype
	 */
	public DishTypeErrorException(String errortype){
		if (errortype == "full meal"){
			System.out.println("[DishType error]:: A full-meal must have 1 starter, 1 main-dish and 1 dessert !");
		}
		if (errortype == "half meal"){
			System.out.println("[DishType error]:: A half-meal must only have 1 main-dish + 1 starter/dessert !");
		}
	}

}
