/*
 * 
 */
package exceptions;

/**
 * The Class MealNotFoundException. Thrown when a mealname is not recognized.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MealNotFoundException extends Exception{
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new meal not found exception.
	 *
	 * @param mealName the meal name
	 */
	public MealNotFoundException(String mealName){
		System.out.println("[MealNotFoundException]:: The meal '" + mealName + "' is not found");
	}
}
