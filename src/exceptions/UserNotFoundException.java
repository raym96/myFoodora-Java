/*
 * 
 */
package exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class UserNotFoundException.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class UserNotFoundException extends Exception{
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param username the username
	 */
	public UserNotFoundException(String username){
		System.out.println("[UserNotFoundException] :: The user '" + username + "' is not found");
	}
	
}
