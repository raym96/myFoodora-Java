package exceptions;

/**
 * The Class LoginErrorException.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class LoginErrorException extends Exception {
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Prints the error.
	 */
	public void printError(){
		System.out.println("LOGIN ERROR: Wrong username/password. Please try again.");
	}
}
