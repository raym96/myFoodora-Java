/*
 * 
 */
package exceptions;

/**
 * The Class NameAlreadyExistsException. Thrown when creating a user/meal/dish with
 * an already existing username/mealname/dishname.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class NameAlreadyExistsException extends Exception {
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	String name;
	
	/**
	 * Instantiates a new name already exists exception.
	 *
	 * @param name the name
	 */
	public NameAlreadyExistsException(String name){
		this.name = name;
	}
	
	public void printError(){
		System.out.println("NAME ALREADY TAKEN : \""+name+"\" already exists.");
	}
}
