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
	
	//ONLY FOR TESTS
	public FullMeal(String name, Menu menu, String dishName1, String dishName2, String dishName3) {
		super(name);
		Dish dish1 = null;
		Dish dish2 = null;
		Dish dish3 = null;
		for (Dish dish:menu.getDishes()){
			if (dish.getDishName().equals(dishName1)) {dish1 = dish;}
			if (dish.getDishName().equals(dishName2)) {dish2 = dish;}
			if (dish.getDishName().equals(dishName3)) {dish3 = dish;}
			}
		this.addDish(dish1);
		this.addDish(dish2);
		this.addDish(dish3);
		this.refreshMealType();
	}
	

}
