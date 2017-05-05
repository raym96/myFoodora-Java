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
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.RestaurantService;
import user.view.RestaurantView;


/**
 * The Class RestaurantServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class RestaurantServiceImpl implements RestaurantService {

	/** The restaurant. */
	private  Restaurant restaurant;
	
	/** The r. */
	private RestaurantView r;
	
	/**
	 * Instantiates a new restaurant service impl.
	 *
	 * @param restaurant the restaurant
	 */
	public RestaurantServiceImpl(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
		this.r = new RestaurantView(restaurant);
	}
	
	// 1. editing the restaurant menu (adding/removing items)
	// add/remove a dish to the menu
	//add/remove a dish to the menu
	

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addDish(java.lang.String, java.lang.String, java.lang.String, double)
	 */
	@Override
	public void addDish(String dishName, String dishCategory, String foodCategory, double unitPrice) throws NameAlreadyExistsException {
		// TODO Auto-generated method stub
		Dish dish = null;
		if (dishCategory.equalsIgnoreCase("starter")) dish = new Starter(dishName,foodCategory,unitPrice);
		if (dishCategory.equalsIgnoreCase("main")) dish = new MainDish(dishName,foodCategory,unitPrice);
		if (dishCategory.equalsIgnoreCase("dessert")) dish = new Dessert(dishName,foodCategory,unitPrice);
		restaurant.getMenu().addDish(dish);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#removeDish(java.lang.String)
	 */
	@Override
	public void removeDish(String dishName) throws NameNotFoundException  {
		restaurant.getMenu().removeDish(dishName);
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
			if (!(meal.getDishes().get(0) instanceof MainDish || meal.getDishes().get(1) instanceof MainDish)){
				throw new DishTypeErrorException();
			}
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
			System.out.println(mealName + " successfully removed from the meal-menu");
		}
	}

	

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#addSpecialMeal(java.lang.String)
	 */
	//throw exception if meal name is not recognized
	@Override
	public void setSpecialOffer(String mealName) throws NameNotFoundException {
		MealMenu mealmenu = restaurant.getMealMenu();
		Meal meal = mealmenu.getMeal(mealName);
		mealmenu.removeMeal(mealName);
		try {
			meal.setSpecial(true);
			restaurant.getSpecialmealmenu().addMeal(meal);
			MyFoodora.getInstance().getSpecialOfferBoard().addSpecialOffer(meal);
		} catch (NameAlreadyExistsException e) {
			e.printError();
		}
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#removeSpecialMeal(java.lang.String)
	 */
	@Override
	public void removeSpecialOffer(String mealName) throws NameNotFoundException {
		// TODO Auto-generated method stub
		MealMenu specialmealmenu = restaurant.getSpecialmealmenu();
		Meal meal = specialmealmenu.getMeal(mealName);
		specialmealmenu.removeMeal(mealName);
		try {
			meal.setSpecial(false);
			restaurant.getMealMenu().addMeal(meal);
			MyFoodora.getInstance().getSpecialOfferBoard().removeSpecialOffer(meal);
		} catch (NameAlreadyExistsException e1) {
			e1.printError();
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
	public void displayMostOrderedHalfMeal() {
		// TODO Auto-generated method stub
		SortingByCriteria sortingcriteria = new SortingByHalfMeal();
		System.out.println("Displaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped half-meals");
		sortingcriteria.displayDescending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayLeastOrderedHalfMeal()
	 */
	@Override
	public void displayLeastOrderedHalfMeal() {
		// TODO Auto-generated method stub
		SortingByCriteria sortingcriteria = new SortingByHalfMeal();
		System.out.println("Displaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped half-meals (ascending order)");
		sortingcriteria.displayAscending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayMostOrderedAlaCarte()
	 */
	@Override
	public void displayMostOrderedAlaCarte() {
		// TODO Auto-generated method stub
		SortingByCriteria sortingcriteria = new SortingByAlaCarte();
		System.out.println("Displaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped a-la-carte dishes");
		sortingcriteria.displayDescending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
	}
		
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#DisplayLeastOrderedAlaCarte()
	 */
	@Override
	public void displayLeastOrderedAlaCarte() {
		// TODO Auto-generated method stub
		SortingByCriteria sortingcriteria = new SortingByAlaCarte();
		System.out.println("Displaying all menu items of "+restaurant.getName()+" sorted w.r.t the number of shipped a-la-carte dishes (ascending order)");
		sortingcriteria.displayAscending(restaurant.getHistory().getOrdersOf(restaurant.getUsername()));
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
	 * @see user.service.RestaurantService#showInfo()
	 */
	@Override
	public void showInfo(){
		r.showInfo();
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showHistory()
	 */
	@Override
	public void showHistory(){
		r.showHistory();
	}
	

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showDishes()
	 */
	@Override
	public void showDishes() {
		// TODO Auto-generated method stub
		r.showDishes();
	}


	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showMeals()
	 */
	@Override
	public void showMeals() {
		// TODO Auto-generated method stub
		r.showMeals();
	}

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showDiscountFactors()
	 */
	@Override
	public void showDiscountFactors(){
		r.showDiscountFactors();
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showSpecialOffers()
	 */
	@Override
	public void showSpecialOffers(){
		// TODO Auto-generated method stub
		r.showSpecialOffers();
	}
	
	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showTotalIncome()
	 */
	@Override
	public void showTotalIncome(){
		r.showTotalIncome();
	}
	

	/* (non-Javadoc)
	 * @see user.service.RestaurantService#showMenu()
	 */
	@Override
	public void showMenu(){
		r.showMenu();
	}


}
