/*
 * 
 */
package exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class NameAlreadyExistsException.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class NameAlreadyExistsException extends Exception {
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new name already exists exception.
	 *
	 * @param name the name
	 */
	public NameAlreadyExistsException(String name){
		System.out.println("[NameAlreadyExistsException]::'"+name+"' already exists.");
	}
}
