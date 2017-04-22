package exceptions;

public class LoginErrorException extends Exception {
	
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	public void printError(){
		System.out.println("LOGIN ERROR: Wrong username/password. Please try again.");
	}
}
