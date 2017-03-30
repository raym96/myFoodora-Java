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
	
	//ONLY FOR TESTS
	public HalfMeal(String name, Menu menu, String dishName1, String dishName2) {
		super(name);
		Dish dish1 = null;
		Dish dish2 = null;
		for (Dish dish:menu.getDishes()){
			if (dish.getDishName().equals(dishName1)) {dish1 = dish;}
			if (dish.getDishName().equals(dishName2)) {dish2 = dish;}
			}
		this.addDish(dish1);
		this.addDish(dish2);
		this.refreshMealType();
	}
	
	
}