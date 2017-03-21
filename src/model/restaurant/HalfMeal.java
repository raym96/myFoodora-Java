package model.restaurant;

public class HalfMeal extends Meal {
	
	public HalfMeal(String name){
		super(name);
	}
	public HalfMeal(String name, Dish dish1, Dish dish2) {
		super(name);
		// TODO Auto-generated constructor stub
		this.addDish(dish1);
		this.addDish(dish2);
		this.refreshMealType();
	}
	
	
	//Copy constructor, useful for creating meals
	public HalfMeal(Meal halfmeal){
		super(halfmeal.getName());
		this.dishes = halfmeal.getDishes();
	}
	
}