package exceptions;

/**
 * The Class SyntaxErrorException.
 */
public class SyntaxErrorException extends Exception {
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	int sizeArgument;

	public SyntaxErrorException(){
		sizeArgument = 0;
	}
	
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
