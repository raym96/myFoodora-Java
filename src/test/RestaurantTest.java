package test;

import static org.junit.Assert.*;

import model.restaurant.Dish;
import model.user.*;

import org.junit.Test;

public class RestaurantTest {

	private Restaurant restaurant;
	@Test
	public void testOfInitiateRestaurant() {

		System.out.println("\n------------- testOfInitiateRestaurant --------------");
		
		restaurant = new Restaurant(1, "Beijing restaurant", "restaurant 1", new AddressPoint(1.0,1.0));	
		
		System.out.println("Menu :: ");
		for(Dish item:restaurant.getMenu().getItems()){
					
			System.out.println(item.toString());
		}
	}
	
	@Test
	public void testOfCreateAndRemoveDifferentMeals(){
		
		System.out.println("\n------------- testOfCreateAndRemoveDifferentMeals --------------");
		
		testOfInitiateRestaurant();
		
		restaurant.creatingMeal("standard meal", "standard 1");
		restaurant.creatingMeal("standard meal", "standard 2");
		restaurant.creatingMeal("standard meal", "standard 3");
		restaurant.creatingMeal("vegetarian meal", "vegetarian 1");
		restaurant.creatingMeal("vegetarian meal", "vegetarian 2");
		restaurant.creatingMeal("vegetarian meal", "vegetarian 3");
		restaurant.creatingMeal("gluten-free meal", "gluten-free 1");
		restaurant.creatingMeal("gluten-free meal", "gluten-free 2");
		restaurant.creatingMeal("gluten-free meal", "gluten-free 3");
		restaurant.creatingMeal("half meal", "half 1");
		restaurant.creatingMeal("half meal", "half 2");
		restaurant.creatingMeal("half meal", "half 3");
		restaurant.creatingMeal("full meal", "full 1");
		restaurant.creatingMeal("full meal", "full 2");
		restaurant.creatingMeal("full meal", "full 3");
		
		System.out.println("\nMeals :: ");
		for(int i=0; i<restaurant.getMeals().size(); i++){
			System.out.println("Meal " + (i+1));
			System.out.println(restaurant.getMeals().get(i).toString());
		}
		
		restaurant.removingMeal("standard 2");
		restaurant.removingMeal("vegetarian 1");
		restaurant.removingMeal("gluten-free 1");
		restaurant.removingMeal("half 2");
		restaurant.removingMeal("full 3");
		
		System.out.println("\nMeals :: ");
		for(int i=0; i<restaurant.getMeals().size(); i++){
			System.out.println("Meal " + (i+1));
			System.out.println(restaurant.getMeals().get(i).toString());
		}
	}
	
	@Test
	public void testOfCreateAndRemoveItemsOfMenu(){
		
		System.out.println("\n------------- testOfCreateAndRemoveItemsOfMenu --------------");
		
		testOfInitiateRestaurant();
		
		restaurant.addingItem(new Dish("itemB", "vegetarian", 2.3));
		restaurant.addingItem(new Dish("itemC", "vegetarian", 2.3));
		restaurant.addingItem(new Dish("itemD", "vegetarian", 2.3));
		
		System.out.println("Menu :: ");
		for(Dish item:restaurant.getMenu().getItems()){
					
			System.out.println(item.toString());
		}
		
		restaurant.removingItem("itemB");
		restaurant.removingItem("itemD");
		
		System.out.println("Menu :: ");
		for(Dish item:restaurant.getMenu().getItems()){
					
			System.out.println(item.toString());
		}
	}

}
