package exceptions;

public class MealNotFoundException extends Exception{
	public static final long serialVersionUID = 1L;
	public MealNotFoundException(String mealName){
		System.out.println("[MealNotFoundException]:: The meal '" + mealName + "' is not found");
	}
}
