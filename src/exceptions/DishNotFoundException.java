/*
 * 
 */
package exceptions;

// TODO: Auto-generated Javadoc
/**
 *
 * The Class DishNotFoundException.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class DishNotFoundException extends Exception{
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new dish not found exception.
	 *
	 * @param dishName the dish name
	 */
	public DishNotFoundException(String dishName){
		System.out.println("[DishNotFoundException]:: The dish '" + dishName + "' is not found");
	}
}
