package exceptions;

/**
 * The Class SyntaxErrorException.
 */
public class SyntaxErrorException extends Exception {
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	

	/**
	 * Prints the error.
	 */
	public void printError() {
		 System.out.println("SYNTAX ERROR: Unknown command / "
		 		+ "missing arguments. Please check your spelling. "
		 		+ "For more information, enter \"help\".");
	}
}
