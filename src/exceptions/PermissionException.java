package exceptions;

public class PermissionException extends Exception {
	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;
	
	private String name;
	
	/**
	 * Instantiates a new PermissionException.
	 *
	 * @param name the  name
	 */
	public PermissionException(String name){
		this.name = name;
	}
	
	public void printError(){
		System.out.println("PERMISSION ERROR : You must be logged as a "
				+ name.toLowerCase()+" to execute this command.");
	}
}
