package service.impl;

import java.util.Iterator;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
import model.restaurant.Dish;
import model.restaurant.FullMeal;
import model.restaurant.HalfMeal;
import model.restaurant.Meal;
import model.restaurant.MealFactory;
import model.restaurant.Order;
import model.restaurant.Starter;
import model.user.Restaurant;
import service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {

	private  Restaurant restaurant;
	
	public RestaurantServiceImpl(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	@Override
	public void addMeal(String mealname, String dishname1, String dishname2) {
		// TODO Auto-generated method stub
		//Add  a meal to the meal menu.
		//Error occurs when dish name is not recognized or when dish types don't match
		//Half-meal
		try{
			if (!(restaurant.getMenu().hasDish(dishname1))){
				throw new DishNotFoundException(dishname1);
			}
			if (!(restaurant.getMenu().hasDish(dishname2))){
				throw new DishNotFoundException(dishname2);
			}
		double md_count=0;
		double st_ds_count=0;
		Meal meal = new HalfMeal(mealname);
		for (Dish starter:restaurant.getMenu().getStarters()){
			if (starter.getDishName()==dishname1 || starter.getDishName()==dishname2){
				meal.addDish(starter);
				st_ds_count++;
			}
		}
		for (Dish maindish:restaurant.getMenu().getMaindishes()){
			if (maindish.getDishName()==dishname1 || maindish.getDishName()==dishname2){
				meal.addDish(maindish);
				md_count++;
			}
		}
		for (Dish dessert:restaurant.getMenu().getDesserts()){
			if (dessert.getDishName()==dishname1 || dessert.getDishName()==dishname2){
				meal.addDish(dessert);
				st_ds_count++;
			}
		}
		if (md_count==1 && st_ds_count==1){
			meal.refreshMealType();
			restaurant.getMealMenu().addMeal(meal);
			System.out.println("Formula " +mealname + " successfully added to the meal-menu");
		}
		else{
			throw new DishTypeErrorException("half meal");
		}
		}
		catch (DishNotFoundException e){}
		catch (DishTypeErrorException e){}
	}

	@Override
	public void addMeal(String mealname, String startername, String maindishname, String dessertname) {
		// TODO Auto-generated method stub

		
		//Full-meal
		try{
			if (!(restaurant.getMenu().hasDish(startername))){
				throw new DishNotFoundException(startername);
			}
			if (!(restaurant.getMenu().hasDish(maindishname))){
				throw new DishNotFoundException(maindishname);
			}
			if (!(restaurant.getMenu().hasDish(dessertname))){
				throw new DishNotFoundException(dessertname);
			}
			Meal meal = new FullMeal(mealname);
			for (Dish starter:restaurant.getMenu().getStarters()){
				if (starter.getDishName()==startername){
					meal.addDish(starter);
				}
			}
			for (Dish maindish:restaurant.getMenu().getMaindishes()){
				if (maindish.getDishName()==maindishname){
					meal.addDish(maindish);
				}
			}
			for (Dish dessert:restaurant.getMenu().getDesserts()){
				if (dessert.getDishName()==dessertname){
					meal.addDish(dessert);
				}
			}
			if (meal.getDishes().size()==3){
				meal.refreshMealType();
				this.restaurant.getMealMenu().addMeal(meal);
				System.out.println("Formula " +meal.getMealType()+" "+mealname + " successfully added to the meal-menu");
			}
			else {
				throw new DishTypeErrorException("full meal");
			}
			}
			catch (DishTypeErrorException e){}
			catch (DishNotFoundException e){}
	
	}

	@Override
	public Meal createMeal(String mealType, String mealName) {
		// TODO Auto-generated method stub
		//create an instance of Meal
		try{
			MealFactory mealFactory = null;
			Meal meal = mealFactory.createMeal(mealName);	
			System.out.println("Formula "+mealName + " successfully created");
			return meal;	
		}
		catch (MealNotFoundException e){
		}
		return new HalfMeal("problem");
	}

	@Override
	public void removeMeal(String mealName) {
		// TODO Auto-generated method stub
		//Remove a meal from the meal menu
		restaurant.getMealMenu().removeMeal(mealName);
		System.out.println(mealName + " successfully removed from the meal-menu");
	}

	@Override
	public void addDish(Dish dish) {
		// TODO Auto-generated method stub
		//add/remove a dish to the menu
		restaurant.getMenu().addDish(dish);
		System.out.println(dish.getDishName() + " successfully added to the menu");	
	}

	@Override
	public void removeDish(String dishName) {
		// TODO Auto-generated method stub
		restaurant.getMenu().removeDish(dishName);
		System.out.println(dishName + " successfully removed from the menu");

	}

	@Override
	public Dish createDish(String dishName) {
		// TODO Auto-generated method stub
		//create an instance of Dish
		try{ 
			Dish dish = restaurant.getDishFactory().createDish(dishName);
			System.out.println("Dish "+dishName + " successfully created");
			return dish;	
		}
		catch (DishNotFoundException e){
		}
		return new Starter("","",0);
		

	}

	@Override
	public void addSpecialMeal(String mealName) {
		// TODO Auto-generated method stub
		//throw exception if meal name is not recognized
		try{
			int count=0;
			for (Iterator<HalfMeal> iter = restaurant.getMealMenu().getHalfMealMenu().iterator();iter.hasNext();){
				Meal hm = iter.next();
				if (hm.getName()==mealName){
					restaurant.getSpecialmealmenu().addMeal(hm);
					System.out.println(mealName + " has been added to the special-offer menu");
					count++;
				}
			}
			for (Iterator<FullMeal> iter = restaurant.getMealMenu().getFullMealMenu().iterator();iter.hasNext();){
				Meal fm = iter.next();
				if (fm.getName()==mealName){
					restaurant.getSpecialmealmenu().addMeal(fm);
					System.out.println(mealName + " has been added to the special-offer menu");
					count++;
				}
			if (count==0){
				throw new MealNotFoundException(mealName);
			}
			}
		}catch (MealNotFoundException e){}
	}

	@Override
	public void removeSpecialMeal(String mealName) {
		// TODO Auto-generated method stub
		for (Iterator<Meal> iter = restaurant.getSpecialmealmenu().getMeals().iterator();iter.hasNext();){
			Meal sm = iter.next();
			if (sm.getName()==mealName){
				iter.remove();
				System.out.println(mealName+" has been removed from the special-offer menu");
			}
		
		}
	}

	@Override
	public void addToHistory(Order order) {
		// TODO Auto-generated method stub
		restaurant.addToHistory(order);
	}

	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		restaurant.getMenu().display();
	}

	@Override
	public void displayMealMenu() {
		// TODO Auto-generated method stub
		System.out.println("difference");
		System.out.println("Meal menu:");
		System.out.println("Half-Meals: " + restaurant.getMealMenu().getHalfMealMenu());
		System.out.println("Full-Meals: " + restaurant.getMealMenu().getFullMealMenu());
	}

	@Override
	public void displaySpecialMenu(){
		// TODO Auto-generated method stub
		System.out.println("Special-offers: " + restaurant.getSpecialmealmenu().getMeals() );
	}

	@Override
	public boolean hasDish(String dishName) {
		// TODO Auto-generated method stub
		return restaurant.getMenu().hasDish(dishName);
	}

}
