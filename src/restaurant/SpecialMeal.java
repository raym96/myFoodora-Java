package model.restaurant;

public class SpecialMeal extends Meal {
	
	public SpecialMeal(String name){
		super(name);
	}
	public SpecialMeal(String name, Dish dish1, Dish dish2) {
		super(name);
		// TODO Auto-generated constructor stub
		this.addDish(dish1);
		this.addDish(dish2);
		this.refreshMealType();
	}
	
	
	//Copy constructor, useful for creating meals
	public SpecialMeal(Meal specialmeal){
		super(specialmeal.getName());
		this.dishes = specialmeal.getDishes();
	}
}