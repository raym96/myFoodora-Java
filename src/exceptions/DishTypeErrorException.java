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
	String errortype;
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new dish type error exception.
	 *
	 * @param errortype the errortype
	 */
	public DishTypeErrorException(String errortype){
		this.errortype = errortype;
	}

	public String getErrortype() {
		return errortype;
	}
	
	

}
