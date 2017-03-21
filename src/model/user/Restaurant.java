package model.user;

import java.util.*;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
import model.restaurant.*;
import service.RestaurantService;
import system.History;

public class Restaurant extends User{

	private String name;
	private AddressPoint address;
	private double generic_discount_factor = 0.05;
	private double special_discount_factor = 0.1;
	
	private Menu menu;
	private MealMenu mealmenu;
	private SpecialMealMenu specialmealmenu;
	
	private History history;
	
	private RestaurantService restaurantService;
	
	//Constructor
	public Restaurant(String name, String username, AddressPoint address) {
		super(username);
		this.name = name;
		this.address = address;
		menu = new Menu();
		mealmenu = new MealMenu();
		specialmealmenu = new SpecialMealMenu();
		history = new History();
	}
	
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	
	//Add  a meal to the meal menu.
	//Error occurs when dish name is not recognized or when dish types don't match
	//Half-meal
	public void addMeal(String mealname, String dishname1, String dishname2){
		try{
			if (!(menu.hasDish(dishname1))){
				throw new DishNotFoundException(dishname1);
			}
			if (!(menu.hasDish(dishname2))){
				throw new DishNotFoundException(dishname2);
			}
		double md_count=0;
		double st_ds_count=0;
		Meal meal = new HalfMeal(mealname);
		for (Dish starter:menu.getStarters()){
			if (starter.getDishName()==dishname1 || starter.getDishName()==dishname2){
				meal.addDish(starter);
				st_ds_count++;
			}
		}
		for (Dish maindish:menu.getMaindishes()){
			if (maindish.getDishName()==dishname1 || maindish.getDishName()==dishname2){
				meal.addDish(maindish);
				md_count++;
			}
		}
		for (Dish dessert:menu.getDesserts()){
			if (dessert.getDishName()==dishname1 || dessert.getDishName()==dishname2){
				meal.addDish(dessert);
				st_ds_count++;
			}
		}
		if (md_count==1 && st_ds_count==1){
			meal.refreshMealType();
			this.mealmenu.addMeal(meal);
			System.out.println("Formula " +mealname + " successfully added to the meal-menu");
		}
		else{
			throw new DishTypeErrorException("half meal");
		}
		}
		catch (DishNotFoundException e){}
		catch (DishTypeErrorException e){}
	}
	//Full-meal
	public void addMeal(String mealname, String startername, String maindishname,String dessertname){
		try{
			if (!(menu.hasDish(startername))){
				throw new DishNotFoundException(startername);
			}
			if (!(menu.hasDish(maindishname))){
				throw new DishNotFoundException(maindishname);
			}
			if (!(menu.hasDish(dessertname))){
				throw new DishNotFoundException(dessertname);
			}
			Meal meal = new FullMeal(mealname);
			for (Dish starter:menu.getStarters()){
				if (starter.getDishName()==startername){
					meal.addDish(starter);
				}
			}
			for (Dish maindish:menu.getMaindishes()){
				if (maindish.getDishName()==maindishname){
					meal.addDish(maindish);
				}
			}
			for (Dish dessert:menu.getDesserts()){
				if (dessert.getDishName()==dessertname){
					meal.addDish(dessert);
				}
			}
			if (meal.getDishes().size()==3){
				meal.refreshMealType();
				this.mealmenu.addMeal(meal);
				System.out.println("Formula " +meal.getMealType()+" "+mealname + " successfully added to the meal-menu");
			}
			else {
				throw new DishTypeErrorException("full meal");
			}
			}
			catch (DishTypeErrorException e){}
			catch (DishNotFoundException e){}
		}
	//Remove a meal from the meal menu
	public void removeMeal(String mealName){
		this.mealmenu.removeMeal(mealName);
		System.out.println(mealName + " successfully removed from the meal-menu");
	}
	
	//add/remove a dish to the menu
	public void addDish(Dish dish){
		this.menu.addDish(dish);
		System.out.println(dish.getDishName() + " successfully added to the menu");
	}
	
	public void removeDish(String dishName){
		this.menu.removeDish(dishName);
		System.out.println(dishName + " successfully removed from the menu");

	}
	
	//create an instance of Meal
	public Meal createMeal(String mealType, String mealName){
		try{
			Meal meal = getMealFactory(mealType).createMeal(mealName);	
			System.out.println("Formula "+mealName + " successfully created");
			return meal;	
		}
		catch (MealNotFoundException e){
		}
		return new HalfMeal("problem");
	}
	
	public MealFactory getMealFactory(String mealType){
		
		MealFactory mealFactory = null;
		
		switch (mealType) {	
			case "half meal":
				mealFactory = new HalfMealFactory(this.mealmenu);
				break;
			case "full meal":
				mealFactory = new FullMealFactory(this.mealmenu);
				break;
			default:
				break;
		}
		return mealFactory;
	}

	//create an instance of Dish
	public Dish createDish(String dishName){
		try{ 
			Dish dish = getDishFactory().createDish(dishName);
			System.out.println("Dish "+dishName + " successfully created");
			return dish;	
		}
		catch (DishNotFoundException e){
		}
		return new Starter("","",0);
	}
	public DishFactory getDishFactory(){
		return new DishFactory(this.menu);
	}
	


	public Menu getMenu() {
		return menu;
	}

	
	public MealMenu getMealMenu() {
		return mealmenu;
	}
	

	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public void setMealmenu(MealMenu mealmenu) {
		this.mealmenu = mealmenu;
	}


	public double getGeneric_discount_factor() {
		return generic_discount_factor;
	}


	public void setGeneric_discount_factor(double generic_discount_factor) {
		this.generic_discount_factor = generic_discount_factor;
	}


	public double getSpecial_discount_factor() {
		return special_discount_factor;
	}


	public void setSpecial_discount_factor(double special_discount_factor) {
		this.special_discount_factor = special_discount_factor;
	}
	
	public AddressPoint getAddress(){
		return address;
	}
	
	
	//throw exception if meal name is not recognized
	public void addSpecialMeal(String mealName){
		try{
			int count=0;
			for (Iterator<HalfMeal> iter = mealmenu.getHalfMealMenu().iterator();iter.hasNext();){
				Meal hm = iter.next();
				if (hm.getName()==mealName){
					specialmealmenu.addMeal(hm);
					System.out.println(mealName + " has been added to the special-offer menu");
					count++;
				}
			}
			for (Iterator<FullMeal> iter = mealmenu.getFullMealMenu().iterator();iter.hasNext();){
				Meal fm = iter.next();
				if (fm.getName()==mealName){
					specialmealmenu.addMeal(fm);
					System.out.println(mealName + " has been added to the special-offer menu");
					count++;
				}
			if (count==0){
				throw new MealNotFoundException(mealName);
			}
			}
		}catch (MealNotFoundException e){}
	}
	
	
	public void removeSpecialMeal(String mealName){
		for (Iterator<Meal> iter = specialmealmenu.getMeals().iterator();iter.hasNext();){
			Meal sm = iter.next();
			if (sm.getName()==mealName){
				iter.remove();
			}
		System.out.println(mealName+" has been removed from the special-offer menu");
		}
	}
	
	
	
	public SpecialMealMenu getSpecialmealmenu() {
		return specialmealmenu;
	}

	public void addToHistory(Order order){
		history.addOrder(order);
	}

	public void DisplayMenu(){
		System.out.println("Menu:");
		System.out.println("Starters: " +menu.getStarters());
		System.out.println("Main-dishes: " +menu.getMaindishes());
		System.out.println("Desserts: " +menu.getDesserts());
	}
	
	public void DisplayMealMenu(){
		System.out.println("Meal menu:");
		System.out.println("Half-Meals: " +mealmenu.getHalfMealMenu());
		System.out.println("Full-Meals: " +mealmenu.getFullMealMenu());
	}
	
	public void DisplaySpecialMenu(){
		System.out.println("Special-offers: " + specialmealmenu.getMeals() );
	}
	
	public void DisplayMostOrderedHalfMeal(){
		history.DisplayMostOrderedHalfMeal(this);
	}
	
	public void DisplayLeastOrderedHalfMeal(){
		history.DisplayLeastOrderedHalfMeal(this);
	}
	
	public void DisplayMostOrderedAlaCarte(){
		history.DisplayMostOrderedAlaCarte(this);
	}
	
	public void DisplayLeastOrderedAlaCarte(){
		history.DisplayLeastOrderedAlaCarte(this);
	}
	public static void main(String[] args) {
		Restaurant r = new Restaurant("Bonheur d'Antony","bda", new AddressPoint(1,3));
		System.out.println(r.name);
		Starter nems = new Starter("nems","gluten-free",2);
		r.addDish(nems);
		r.DisplayMenu();
		r.DisplayMealMenu();
		r.DisplaySpecialMenu();
		r.addSpecialMeal("Salade-poulet");
		r.DisplayMealMenu();
		r.DisplaySpecialMenu();
		
		r.createMeal("half meal", "Salade-poulet");
		r.createDish("lolol");
		r.addMeal("foie gras-salade", "foie gras", "salade");
		//error because 2 main-dishes
		r.addMeal("poulet-poisson-glace", "poulet", "glace","glace");
		//no error
		r.addMeal("salade-poisson-glace", "salade", "poisson","glace");
		r.addMeal("salade-poisson-glace", "lololol", "poisson","glace");
		Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11,23,11).getTime();
		System.out.println(date);
		}

	public History getHistory() {
		// TODO Auto-generated method stub
		return history;
	}
}


	
