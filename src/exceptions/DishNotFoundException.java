package exceptions;

public class DishNotFoundException extends Exception{
	public static final long serialVersionUID = 1L;
	public DishNotFoundException(String dishName){
		System.out.println("[DishNotFoundException]:: The dish '" + dishName + "' is not found");
	}
}
