package exceptions;

public class DishNotFoundException extends Exception{
	public static final long serialVersionUID = 1L;
	public DishNotFoundException(String dishName){
		System.out.println("The dish name '" + dishName + "' is not recognized");
	}
}
