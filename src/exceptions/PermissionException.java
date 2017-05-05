package exceptions;

/**
 * The Class PermissionException.
 * Thrown when a user of CLUI tries to use a command without the right permission.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class PermissionException extends Exception {
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new PermissionException.
	 *
	 * @param name the  name
	 */
	public PermissionException(String name){
		this.name = name;
	}
	
	/**
	 * Prints the error.
	 */
	public void printError(){
		System.out.println("PERMISSION ERROR : You must be logged as a "
				+ name.toLowerCase()+" to execute this command.");
	}
}
