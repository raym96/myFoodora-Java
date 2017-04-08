package exceptions;

public class NameAlreadyExistsException extends Exception {
	public static final long serialVersionUID = 1L;
	public NameAlreadyExistsException(String name){
		System.out.println("[NameAlreadyExistsException]::'"+name+"' already exists.");
	}
}
