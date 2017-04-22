/*
 * 
 */
package exceptions;

/**
 *
 * The Class DishNotFoundException. Thrown when a name 
 * (dishName, mealName, orderName...) is not recognized.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class NameNotFoundException extends Exception{
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new NameNotFoundException.
	 *
	 * @param name the  name
	 */
	public NameNotFoundException(String name){
		this.name = name;
	}
	
	/**
	 * Prints the error.
	 */
	public void printError(){
		System.out.println("OBJECT NOT FOUND: The name " + name + " is not recognized");
	}
}
