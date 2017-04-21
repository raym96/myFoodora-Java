/*
 * 
 */
package restaurant;

import exceptions.DishTypeErrorException;

/**
 * The Class HalfMeal.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class HalfMeal extends Meal {
	
	/**
	 * Instantiates a new half meal.
	 *
	 * @param name the name
	 */
	public HalfMeal(String name){
		super(name);
	}
	
	/**
	 * Instantiates a new half meal.
	 *
	 * @param name the name
	 * @param dish1 the dish 1
	 * @param dish2 the dish 2
	 */
	public HalfMeal(String name, Dish dish1, Dish dish2) {
		super(name);
		// TODO Auto-generated constructor stub
		try {
			this.addDish(dish1);
			this.addDish(dish2);
		} catch (DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.refreshMealType();
	}
	
	
	/**
	 * Instantiates a new half meal.
	 *
	 * @param halfmeal the halfmeal
	 */
	//Copy constructor, useful for creating meals
	public HalfMeal(Meal halfmeal){
		super(halfmeal.getName());
		this.dishes = halfmeal.getDishes();
		this.restaurant = halfmeal.getRestaurant();
	}
	
	/**
	 * Instantiates a new half meal.
	 *
	 * @param name the name
	 * @param menu the menu
	 * @param dishName1 the dish name 1
	 * @param dishName2 the dish name 2
	 */
	//ONLY FOR TESTS
	public HalfMeal(String name, Menu menu, String dishName1, String dishName2) {
		super(name);
		Dish dish1 = null;
		Dish dish2 = null;
		for (Dish dish:menu.getDishes()){
			if (dish.getDishName().equals(dishName1)) {dish1 = dish;}
			if (dish.getDishName().equals(dishName2)) {dish2 = dish;}
			}
		try {
			this.addDish(dish1);
			this.addDish(dish2);
		} catch (DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.refreshMealType();
	}
	
	
	
}