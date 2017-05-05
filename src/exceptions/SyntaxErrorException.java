package exceptions;

/**
 * The Class SyntaxErrorException.
 * Thrown when a CLUI command is not recognized / has missing arguments.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SyntaxErrorException extends Exception {
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/** The size argument. */
	int sizeArgument;

	/**
	 * Instantiates a new syntax error exception.
	 */
	public SyntaxErrorException(){
		sizeArgument = 0;
	}
	
	/**
	 * Instantiates a new syntax error exception.
	 *
	 * @param sizeArgument the size argument
	 */
	public SyntaxErrorException(int sizeArgument){
		this.sizeArgument = sizeArgument;
	}

	/**
	 * Prints the error.
	 */
	public void printError() {
		if (sizeArgument==0){
		 System.out.println("SYNTAX ERROR: Unknown command"
		 		+ ". Please check your spelling. "
		 		+ "For more information, enter \"help\".");
		}
		if (sizeArgument>0){
			 System.out.println("SYNTAX ERROR: Missing arguments. "
			 		+ "You must have at least "+sizeArgument+" arguments for this command."
				 		+ " For more information, enter \"help\".");
		}
	}
}
