/*
 * 
 */
package restaurant;

import exceptions.DishTypeErrorException;

/**
 * The Class FullMeal.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class FullMeal extends Meal {
	
	/**
	 * Instantiates a new full meal.
	 *
	 * @param name the name
	 */
	public FullMeal(String name){
		super(name);
	}
	
	/**
	 * Instantiates a new full meal.
	 *
	 * @param name the name
	 * @param dish1 the dish 1
	 * @param dish2 the dish 2
	 * @param dish3 the dish 3
	 */
	public FullMeal(String name, Dish dish1, Dish dish2, Dish dish3) {
		super(name);
		// TODO Auto-generated constructor stub
		try {
			this.addDish(dish1);
			this.addDish(dish2);
			this.addDish(dish3);
		} catch (DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.refreshMealType();
	}
	

	/**
	 * Instantiates a new full meal by copying the argument.
	 *
	 * @param fullmeal the fullmeal
	 */
	//Copy constructor, useful for creating meals
	public FullMeal(Meal fullmeal){
		super(fullmeal.getName());
		this.dishes = fullmeal.getDishes();
		this.mealType = fullmeal.getMealType();
		this.restaurant=fullmeal.getRestaurant();
	}
	
	/**
	 * Instantiates a new full meal.
	 *
	 * @param name the name
	 * @param menu the menu
	 * @param dishName1 the dish name 1
	 * @param dishName2 the dish name 2
	 * @param dishName3 the dish name 3
	 */
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
		try {
			this.addDish(dish1);
			this.addDish(dish2);
			this.addDish(dish3);
		} catch (DishTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.refreshMealType();
	}
	

}
