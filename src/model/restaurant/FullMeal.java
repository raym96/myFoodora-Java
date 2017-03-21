package model.restaurant;

public class FullMeal extends Meal {
	
	public FullMeal(String name){
		super(name);
	}
	
	public FullMeal(String name, Dish dish1, Dish dish2, Dish dish3) {
		super(name);
		// TODO Auto-generated constructor stub
		this.addDish(dish1);
		this.addDish(dish2);
		this.addDish(dish3);
		this.refreshMealType();
	}
	

	//Copy constructor, useful for creating meals
	public FullMeal(Meal fullmeal){
		super(fullmeal.getName());
		this.dishes = fullmeal.getDishes();
	}

}
