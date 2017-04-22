/*
 * 
 */
package user.service.impl;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.NameNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.NameNotFoundException;
import exceptions.NameAlreadyExistsException;
import policies.SortingByAlaCarte;
import policies.SortingByCriteria;
import policies.SortingByHalfMeal;
import restaurant.Dessert;
import restaurant.Dish;
import restaurant.FullMeal;
import restaurant.HalfMeal;
import restaurant.MainDish;
import restaurant.Meal;
import restaurant.MealFactory;
import restaurant.MealMenu;
import restaurant.Starter;
import system.*;
import user.model.Restaurant;
import user.service.RestaurantService;


/**
 * The Class RestaurantServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class RestaurantServiceImpl implements RestaurantService {

	/** The restaurant. */
	private  Restaurant restaurant;
	
	/**
	 * Instantiates a new restaurant service impl.
	 *
	 * @param restaurant the restaurant
	 */
	public RestaurantServiceImpl(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	
	// 1. editing the restaurant menu (adding/removing items)
	// add/remove a dish to the menu
	//add/remove a dish to the menu
	
		/* (non-Javadoc)
	 * @see user.service.RestaurantService#addDish(restaurant.Dish)
	 */
	@Override
	public void addDish(Dish dish) {
		// TODO Auto-generated method stub
		//add/remove a dish to the menu
		try{
			restaurant.getMenu().addDish(dish);
		} catch (NameAlreadyExistsException e){
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addDish(java.lang.String, java.lang.String, java.lang.String, double)
	 */
	@Override
	public void addDish(String dishName, String dishCategory, String foodCategory, double unitPrice) {
		// TODO Auto-generated method stub
		Dish dish = null;
		if (dishCategory.equalsIgnoreCase("starter")) dish = new Starter(dishName,foodCategory,unitPrice);
		if (dishCategory.equalsIgnoreCase("main")) dish = new MainDish(dishName,foodCategory,unitPrice);
		if (dishCategory.equalsIgnoreCase("dessert")) dish = new Dessert(dishName,foodCategory,unitPrice);
		addDish(dish);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#removeDish(java.lang.String)
	 */
	@Override
	public void removeDish(String dishName) {
		// TODO Auto-generated method stub
		try {
			restaurant.getMenu().removeDish(dishName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#hasDish(java.lang.String)
	 */
	@Override
	public boolean hasDish(String dishName) {
		// TODO Auto-generated method stub
		return restaurant.getMenu().hasDish(dishName);
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#createDish(java.lang.String)
	 */
	//create an instance of Dish
	@Override
	public Dish createFactoryDish(String dishName) {
		// TODO Auto-generated method stub
		//create an instance of Dish
		try{ 
			Dish dish = restaurant.getDishFactory().createDish(dishName);
			return dish;	
		}
		catch (NameNotFoundException e){
		}
		return new Starter("","",0);
		

		}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#createMeal(java.lang.String)
	 */
	@Override
	public void createMeal(String mealName) throws NameAlreadyExistsException {
		// TODO Auto-generated method stub
		Meal meal = new Meal(mealName);
		restaurant.getMealMenu().addMeal(meal);
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addDish2Meal(java.lang.String, java.lang.String)
	 */
	@Override
	public void addDish2Meal(String dishName, String mealName) throws NameNotFoundException, DishTypeErrorException{
		Dish dish = restaurant.getMenu().getDish(dishName);
		Meal meal = restaurant.getMealMenu().getMeal(mealName);
		meal.addDish(dish);
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showMeal(java.lang.String)
	 */
	@Override
	public void showMeal(String mealName) throws NameNotFoundException{
		Meal meal = restaurant.getMealMenu().getMeal(mealName);
		System.out.println("The dishes of the meal <"+mealName+"> are :");
		for (Dish dish : meal.getDishes()){
			System.out.println(dish);
		}
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#saveMeal(java.lang.String)
	 */
	@Override
	public void saveMeal(String mealName) throws NameNotFoundException, DishTypeErrorException{
		Meal meal = restaurant.getMealMenu().getMeal(mealName);
		if (meal.getDishes().size()==2){
			HalfMeal halfmeal = new HalfMeal(meal);
			halfmeal.setSaved(true);
			restaurant.getMealMenu().removeMeal(mealName);
			try {
				restaurant.getMealMenu().addMeal(halfmeal);
				System.out.println("Formula <"+meal.getName()+">" + " added to the meal-menu of "+restaurant.getName());
			} catch (NameAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (meal.getDishes().size()==3){
			FullMeal fullmeal = new FullMeal(meal);
			fullmeal.setSaved(true);
			restaurant.getMealMenu().removeMeal(mealName);
			try {
				restaurant.getMealMenu().addMeal(fullmeal);
				System.out.println("Formula <"+meal.getName()+">" + " added to the meal-menu of "+restaurant.getName());
			} catch (NameAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			throw new DishTypeErrorException();
		}
		
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addMeal(restaurant.Meal)
	 */
	@Override
	public void addMeal(Meal meal) {
		// TODO Auto-generated method stub
		try{
		if (meal instanceof HalfMeal){
			restaurant.getMealMenu().addMeal(meal);
		}
		if (meal instanceof FullMeal){
			restaurant.getMealMenu().addMeal(meal);
		}
		System.out.println("Formula <"+meal.getName()+">" + " added to the meal-menu of "+restaurant.getName());
		}catch(NameAlreadyExistsException e){
			e.printStackTrace();
		}
	}
		
		
		
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addMeal(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addMeal(String mealname, String dishname1, String dishname2) {
		// TODO Auto-generated method stub
		//Add  a meal to the meal menu.
		//Error occurs when dish name is not recognized or when dish types don't match
		//Half-meal
		try{
			if (!(restaurant.getMenu().hasDish(dishname1))){
				throw new NameNotFoundException(dishname1);
			}
			if (!(restaurant.getMenu().hasDish(dishname2))){
				throw new NameNotFoundException(dishname2);
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
			restaurant.getMealMenu().addMeal(meal);
			System.out.println("Formula <"+meal.getName()+">" + " added to the meal-menu of "+restaurant.getName());
		}
		else{
			throw new DishTypeErrorException();
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addMeal(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addMeal(String mealname, String startername, String maindishname, String dessertname) {
		// TODO Auto-generated method stub

		
		//Full-meal
		try{
			if (!(restaurant.getMenu().hasDish(startername))){
				throw new NameNotFoundException(startername);
			}
			if (!(restaurant.getMenu().hasDish(maindishname))){
				throw new NameNotFoundException(maindishname);
			}
			if (!(restaurant.getMenu().hasDish(dessertname))){
				throw new NameNotFoundException(dessertname);
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
				this.restaurant.getMealMenu().addMeal(meal);
				System.out.println("Formula <"+meal.getName()+">" +" added to the meal-menu of "+restaurant.getName());
			}
			else {
				throw new DishTypeErrorException();
			}
			}
			catch (Exception e){
				e.printStackTrace();
			}
	
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#createMeal(java.lang.String, java.lang.String)
	 */
	//create an instance of Meal
	@Override
	public Meal createFactoryMeal(String mealCategory, String mealName) {
		// TODO Auto-generated method stub
		//create an instance of Meal
		try{
			MealFactory mealFactory = restaurant.getMealFactory(mealCategory);
			Meal meal = mealFactory.createMeal(mealName);	
			System.out.println("Formula "+mealName + " successfully created");
			return meal;	
		}
		catch (NameNotFoundException e){
		}
		return new HalfMeal("problem");
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#removeMeal(java.lang.String)
	 */
	//Remove a meal from the meal menu
	@Override
	public void removeMeal(String mealName) throws NameNotFoundException {
		// TODO Auto-generated method stub
		//Remove a meal from the meal menu
		MealMenu mealmenu = restaurant.getMealMenu();
		if (!(mealmenu.hasMeal(mealName))){
			throw new NameNotFoundException(mealName);
		}
		if (mealmenu.hasMeal(mealName)){
			mealmenu.removeMeal(mealName);
			System.out.println("Half-meal <" + mealName + "> successfully removed from the meal-menu");
		}
	}

	

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addSpecialMeal(java.lang.String)
	 */
	//throw exception if meal name is not recognized
	@Override
	public void setSpecialOffer(String mealName) throws NameNotFoundException {
		// TODO Auto-generated method stub
		//throw exception if meal name is not recognized
		int count=0;
		for (Iterator<Meal> iter1 = restaurant.getMealMenu().getMeals().iterator();iter1.hasNext();){
			Meal meal = iter1.next();
			if (meal.getName().equalsIgnoreCase(mealName)){
				iter1.remove();
				meal.setSpecial(true);
				try {
					restaurant.getSpecialmealmenu().addMeal(meal);
				} catch (NameAlreadyExistsException e) {
					e.printStackTrace();
				}
				count++;
			}
		}
		if (count==0){
			throw new NameNotFoundException(mealName);
		}
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#removeSpecialMeal(java.lang.String)
	 */
	@Override
	public void removeSpecialOffer(String mealName) {
		// TODO Auto-generated method stub
		for (Iterator<Meal> iter = restaurant.getSpecialmealmenu().getMeals().iterator();iter.hasNext();){
			Meal sm = iter.next();
			if (sm.getName().equalsIgnoreCase(mealName)){
				iter.remove();
				sm.setSpecial(false);
				restaurant.getRestaurantService().addMeal(sm);
			}
		
		}
	}

	// 3. establishing the generic discount factor (default 5%) to apply when computing
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#setGenericDiscountFactor(double)
	 */
	// a meal price
	@Override
	public void setGenericDiscountFactor(double generic_discount_factor) {
		// TODO Auto-generated method stub
		restaurant.setGDF(generic_discount_factor);
	}

	// 4. establishing the special discount factor (default 10%) to apply to the meal-of-
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#setSpecialDiscountFactor(double)
	 */
	// the-week special offer.
	@Override
	public void setSpecialDiscountFactor(double special_discount_factor) {
		// TODO Auto-generated method stub
		restaurant.setSDF(special_discount_factor);
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayMostOrderedHalfMeal()
	 */
	// 5. sorting of shipped orders with respect to different criteria (see below)
	@Override
	public void DisplayMostOrderedHalfMeal() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByHalfMeal();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped half-meals");
		s.displayDescending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayLeastOrderedHalfMeal()
	 */
	@Override
	public void DisplayLeastOrderedHalfMeal() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByHalfMeal();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped half-meals");
		s.displayAscending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayMostOrderedAlaCarte()
	 */
	@Override
	public void DisplayMostOrderedAlaCarte() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByAlaCarte();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped a-la-carte dishes");
		s.displayDescending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}
		
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayLeastOrderedAlaCarte()
	 */
	@Override
	public void DisplayLeastOrderedAlaCarte() {
		// TODO Auto-generated method stub
		SortingByCriteria s = new SortingByAlaCarte();
		System.out.println("\nDisplaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped a-la-carte dishes");
		s.displayAscending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addToHistory(system.Order)
	 */
	// EXTRA TOOLS
	@Override
	public void addToHistory(Order order) {
		// TODO Auto-generated method stub
		restaurant.addToHistory(order);
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#displayMenu()
	 */
	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		restaurant.getMenu().display();
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#displayMealMenu()
	 */
	@Override
	public void displayMealMenu() {
		// TODO Auto-generated method stub
		System.out.println("\n"+"[Meal menu]");
		restaurant.getMealMenu().display();
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#displaySpecialMenu()
	 */
	@Override
	public void displaySpecialMenu(){
		// TODO Auto-generated method stub
		System.out.println("\n[Special Offers]");
		restaurant.getSpecialmealmenu().display();
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#displayAllMenu()
	 */
	@Override
	public void displayAllMenu(){
		System.out.println("\n-----["+(restaurant.getName().toUpperCase()+"]-----"));
		displayMenu();
		displayMealMenu();
		displaySpecialMenu();
	}



	


}
