package user;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
import policies.SortingByAlaCarte;
import policies.SortingByCriteria;
import policies.SortingByHalfMeal;
import restaurant.Dish;
import restaurant.FullMeal;
import restaurant.HalfMeal;
import restaurant.Meal;
import restaurant.MealFactory;
import restaurant.MealMenu;
import restaurant.Starter;
import system.*;

public class RestaurantServiceImpl implements RestaurantService {

	private  Restaurant restaurant;
	
	public RestaurantServiceImpl(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	
	// 1. editing the restaurant menu (adding/removing items)
	// add/remove a dish to the menu
	//add/remove a dish to the menu
	
		@Override
		public void addDish(Dish dish) {
			// TODO Auto-generated method stub
			//add/remove a dish to the menu
			restaurant.getMenu().addDish(dish);
			System.out.println(dish + " added to the menu");	
		}

		@Override
		public void removeDish(String dishName) {
			// TODO Auto-generated method stub
			try {
				restaurant.getMenu().removeDish(dishName);
				System.out.println(dishName + " removed from the menu");
			} catch (DishNotFoundException e) {
				e.printStackTrace();
			}	
		}
		
		@Override
		public boolean hasDish(String dishName) {
			// TODO Auto-generated method stub
			return restaurant.getMenu().hasDish(dishName);
		}

		//create an instance of Dish
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
	
	// 2. creating/removing different meals (half or full meal, vegetarian, gluten-free
	// and/or standard meals).
	@Override
	public void addMeal(Meal meal) {
		// TODO Auto-generated method stub
		if (meal instanceof HalfMeal){
			restaurant.getHalfMealMenu().addMeal(meal);
		}
		if (meal instanceof FullMeal){
			restaurant.getFullMealMenu().addMeal(meal);
		}
		System.out.println("Formula <"+meal.getName()+">" + " added to the meal-menu of "+restaurant.getName());
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
			if (starter.getDishName().equalsIgnoreCase(dishname1) || starter.getDishName().equalsIgnoreCase(dishname2)){
				meal.addDish(starter);
				st_ds_count++;
			}
		}
		for (Dish maindish:restaurant.getMenu().getMaindishes()){
			if (maindish.getDishName().equalsIgnoreCase(dishname1) || maindish.getDishName().equalsIgnoreCase(dishname2)){
				meal.addDish(maindish);
				md_count++;
			}
		}
		for (Dish dessert:restaurant.getMenu().getDesserts()){
			if (dessert.getDishName().equalsIgnoreCase(dishname1) || dessert.getDishName().equalsIgnoreCase(dishname2)){
				meal.addDish(dessert);
				st_ds_count++;
			}
		}
		if (md_count==1 && st_ds_count==1){
			meal.refreshMealType();
			restaurant.getHalfMealMenu().addMeal(meal);
			System.out.println("Formula <"+meal.getName()+">" + " added to the meal-menu of "+restaurant.getName());
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
				if (starter.getDishName().equalsIgnoreCase(startername)){
					meal.addDish(starter);
				}
			}
			for (Dish maindish:restaurant.getMenu().getMaindishes()){
				if (maindish.getDishName().equalsIgnoreCase(maindishname)){
					meal.addDish(maindish);
				}
			}
			for (Dish dessert:restaurant.getMenu().getDesserts()){
				if (dessert.getDishName().equalsIgnoreCase(dessertname)){
					meal.addDish(dessert);
				}
			}
			if (meal.getDishes().size()==3){
				meal.refreshMealType();
				this.restaurant.getFullMealMenu().addMeal(meal);
				System.out.println("Formula <"+meal.getName()+">" +" added to the meal-menu of "+restaurant.getName());
			}
			else {
				throw new DishTypeErrorException("full meal");
			}
			}
			catch (DishTypeErrorException e){}
			catch (DishNotFoundException e){}
	
	}

	//create an instance of Meal
	@Override
	public Meal createMeal(String mealCategory, String mealName) {
		// TODO Auto-generated method stub
		//create an instance of Meal
		try{
			MealFactory mealFactory = restaurant.getMealFactory(mealCategory);
			Meal meal = mealFactory.createMeal(mealName);	
			System.out.println("Formula "+mealName + " successfully created");
			return meal;	
		}
		catch (MealNotFoundException e){
		}
		return new HalfMeal("problem");
	}

	//Remove a meal from the meal menu
	@Override
	public void removeMeal(String mealName) {
		// TODO Auto-generated method stub
		//Remove a meal from the meal menu
		MealMenu halfmeals = restaurant.getHalfMealMenu();
		MealMenu fullmeals = restaurant.getFullMealMenu();
		try{
			if (halfmeals.hasMeal(mealName)){
				halfmeals.removeMeal(mealName);
				System.out.println(mealName + " successfully removed from the meal-menu");
			}
			if (fullmeals.hasMeal(mealName)){
				fullmeals.removeMeal(mealName);
				System.out.println(mealName + " successfully removed from the meal-menu");
			}
			else{
				throw new MealNotFoundException(mealName);
			}
		} catch (MealNotFoundException e){
			e.printStackTrace();
		}
	}

	

	//throw exception if meal name is not recognized
	@Override
	public void addSpecialMeal(String mealName) {
		// TODO Auto-generated method stub
		//throw exception if meal name is not recognized
		try{
			int count=0;
			for (Iterator<Meal> iter1 = restaurant.getHalfMealMenu().getMeals().iterator();iter1.hasNext();){
				Meal hm = iter1.next();
				if (hm.getName().equalsIgnoreCase(mealName)){
					iter1.remove();
					restaurant.getSpecialmealmenu().addMeal(hm);
					System.out.println(mealName + " has been added to the special-offer menu");
					count++;
				}
			}
			for (Iterator<Meal> iter2 = restaurant.getFullMealMenu().getMeals().iterator();iter2.hasNext();){
				Meal fm = iter2.next();
				if (fm.getName().equalsIgnoreCase(mealName)){
					iter2.remove();
					restaurant.getSpecialmealmenu().addMeal(fm);
					System.out.println(mealName + " has been added to the special-offer menu");
					count++;
				}
			}
			if (count==0){
				throw new MealNotFoundException(mealName);
			}
		}catch (MealNotFoundException e){}
	}

	@Override
	public void removeSpecialMeal(String mealName) {
		// TODO Auto-generated method stub
		for (Iterator<Meal> iter = restaurant.getSpecialmealmenu().getMeals().iterator();iter.hasNext();){
			Meal sm = iter.next();
			if (sm.getName().equalsIgnoreCase(mealName)){
				iter.remove();
				restaurant.getRestaurantService().addMeal(sm);
				System.out.println(mealName+" has been removed from the special-offer menu");
			}
		
		}
	}

	// 3. establishing the generic discount factor (default 5%) to apply when computing
	// a meal price
	@Override
	public void setGenericDiscountFactor(double generic_discount_factor) {
		// TODO Auto-generated method stub
		restaurant.setGDF(generic_discount_factor);
		restaurant.updatePrice();
	}

	// 4. establishing the special discount factor (default 10%) to apply to the meal-of-
	// the-week special offer.
	@Override
	public void setSpecialDiscountFactor(double special_discount_factor) {
		// TODO Auto-generated method stub
		restaurant.setSDF(special_discount_factor);
		restaurant.updatePrice();
	}

	// 5. sorting of shipped orders with respect to different criteria (see below)
	@Override
	public void DisplayMostOrderedHalfMeal() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByHalfMeal();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped half-meals");
		s.displayDescending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}

	@Override
	public void DisplayLeastOrderedHalfMeal() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByHalfMeal();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped half-meals");
		s.displayAscending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}

	@Override
	public void DisplayMostOrderedAlaCarte() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByAlaCarte();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped a-la-carte dishes");
		s.displayDescending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}
		
	@Override
	public void DisplayLeastOrderedAlaCarte() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByAlaCarte();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped a-la-carte dishes");
		s.displayAscending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}
	
	// EXTRA TOOLS
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
		System.out.println("\n"+"----- Meal menu -----");
		System.out.println("\nHalf-Meals:");
		restaurant.getHalfMealMenu().display();
		System.out.println("\nFull-Meals:");
		restaurant.getFullMealMenu().display();
	}

	@Override
	public void displaySpecialMenu(){
		// TODO Auto-generated method stub
		System.out.println("\n----- Special Offers -----\n");
		restaurant.getSpecialmealmenu().display();
	}
	
	@Override
	public void displayAllMenu(){
		System.out.println("\n-----["+(restaurant.getName().toUpperCase()+"]-----"));
		displayMenu();
		displayMealMenu();
		displaySpecialMenu();
	}

	


}
