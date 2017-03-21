package exceptions;

public class MealNotFoundException extends Exception{
	public static final long serialVersionUID = 1L;
	public MealNotFoundException(String mealName){
		System.out.println("The meal/dish name '" + mealName + "' is not recognized");
	}
}
