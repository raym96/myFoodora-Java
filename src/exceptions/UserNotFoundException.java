/*
 * 
 */
package exceptions;

/**
 * The Class UserNotFoundException. Thrown when a username is not recognized.
 * 
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
