/*
 * 
 */
package user.model;

import java.util.*;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
import restaurant.*;
import system.AddressPoint;
import system.ConcreteShoppingCartVisitor;
import system.History;
import system.Message;
import system.Observable;
import system.Order;
import system.SpecialOfferBoard;
import user.service.RestaurantService;
import user.service.impl.RestaurantServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class Restaurant.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Restaurant extends User{

	/** The name. */
	private String name;
	
	/** The address. */
	private AddressPoint address;
	
	/** The generic discount factor. */
	private double generic_discount_factor = 0.05;
	
	/** The special discount factor. */
	private double special_discount_factor = 0.1;
	
	/** The gdf changed. */
	private boolean gdf_changed;
	
	/** The sdf changed. */
	private boolean sdf_changed; // bserver pattern with mealmenus which adapt their factor
	
	/** The menu. */
	private Menu menu;
	
	/** The halfmealmenu. */
	private MealMenu halfmealmenu;
	
	/** The fullmealmenu. */
	private MealMenu fullmealmenu;
	
	/** The specialmealmenu. */
	private MealMenu specialmealmenu;
	
	/** The history. */
	private History history;
	
	/** The restaurant service. */
	private RestaurantService restaurantService;
	
	/**
	 * Instantiates a new restaurant.
	 *
	 * @param name the name
	 * @param username the username
	 * @param address the address
	 */
	//Constructor
	public Restaurant(String name, String username, AddressPoint address) {
		super(username);
		this.name = name;
		this.address = address;
		menu = new Menu();
		halfmealmenu = new MealMenu(generic_discount_factor);
		fullmealmenu = new MealMenu(generic_discount_factor);
		specialmealmenu = new MealMenu(special_discount_factor);
		history = new History();
		
		gdf_changed = false;
		sdf_changed = false;
		
		restaurantService = new RestaurantServiceImpl(this);
		
		//INITIALIZE FOR TESTS
//		menu = InitDishMenu.init("src/txt files/menu.txt");
//		setMealMenu(InitMealMenu.init("src/txt files/mealmenu.txt"));
	}
	

	/**
	 * Gets the restaurant service.
	 *
	 * @return the restaurant service
	 */
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	
	/**
	 * Gets the dish factory.
	 *
	 * @return the dish factory
	 */
	public DishFactory getDishFactory(){
		return new DishFactory(this.menu);
	}
	
	/**
	 * Gets the meal factory.
	 *
	 * @param mealCategory the meal category
	 * @return the meal factory
	 */
	public MealFactory getMealFactory(String mealCategory){
		if(mealCategory.equalsIgnoreCase("Full-meal")){
			return new FullMealFactory(fullmealmenu);
		}else if(mealCategory.equalsIgnoreCase("Half-meal")){
			return new HalfMealFactory(halfmealmenu);
		}else if(mealCategory.equalsIgnoreCase("Special-meal")){
			return new HalfMealFactory(specialmealmenu);
		}
		return null;
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * Gets the half meal menu.
	 *
	 * @return the half meal menu
	 */
	public MealMenu getHalfMealMenu() {
		return halfmealmenu;
	}
	
	/**
	 * Gets the full meal menu.
	 *
	 * @return the full meal menu
	 */
	public MealMenu getFullMealMenu(){
		return fullmealmenu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu the new menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Sets the meal menu.
	 *
	 * @param mealmenus the new meal menu
	 */
	//USEFUL FOR TESTS
	public void setMealMenu(ArrayList<MealMenu> mealmenus){
		this.halfmealmenu = mealmenus.get(0);
		this.fullmealmenu = mealmenus.get(1);
		this.specialmealmenu = mealmenus.get(2);
	}

	/**
	 * Sets the half mealmenu.
	 *
	 * @param mealmenu the new half mealmenu
	 */
	public void setHalfMealmenu(MealMenu mealmenu) {
		this.halfmealmenu = mealmenu;
	}
	
	/**
	 * Sets the full mealmenu.
	 *
	 * @param mealmenu the new full mealmenu
	 */
	public void setFullMealmenu(MealMenu mealmenu){
		this.fullmealmenu=mealmenu;
	}

	/**
	 * Sets the special mealmenu.
	 *
	 * @param mealmenu the new special mealmenu
	 */
	public void setSpecialMealmenu(MealMenu mealmenu){
		this.specialmealmenu = mealmenu;
	}
	
	/**
	 * Gets the generic discount factor.
	 *
	 * @return the generic discount factor
	 */
	public double getGeneric_discount_factor() {
		return generic_discount_factor;
	}


	/**
	 * Sets the generic discount factor.
	 *
	 * @param generic_discount_factor the new generic discount factor
	 */
	public void setGeneric_discount_factor(double generic_discount_factor) {
		this.generic_discount_factor = generic_discount_factor;
	}


	/**
	 * Gets the special discount factor.
	 *
	 * @return the special discount factor
	 */
	public double getSpecial_discount_factor() {
		return special_discount_factor;
	}


	/**
	 * Sets the special discount factor.
	 *
	 * @param special_discount_factor the new special discount factor
	 */
	public void setSpecial_discount_factor(double special_discount_factor) {
		this.special_discount_factor = special_discount_factor;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public AddressPoint getAddress(){
		return address;
	}

	/**
	 * Checks if is gdf changed.
	 *
	 * @return true, if is gdf changed
	 */
	public boolean isGdf_changed() {
		return gdf_changed;
	}


	/**
	 * Checks if is sdf changed.
	 *
	 * @return true, if is sdf changed
	 */
	public boolean isSdf_changed() {
		return sdf_changed;
	}


	/**
	 * Gets the specialmealmenu.
	 *
	 * @return the specialmealmenu
	 */
	public MealMenu getSpecialmealmenu() {
		return specialmealmenu;
	}

	/**
	 * Adds the to history.
	 *
	 * @param order the order
	 */
	public void addToHistory(Order order){
		history.addOrder(order);
	}
	
	/**
	 * Gets the income.
	 *
	 * @return the income
	 */
	public double getIncome(){
		double income = 0;
		for (Order o:history.getOrders()){
			income+=o.getPrice();
		}
		return Math.floor(income*100)/100;
	}
	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	public History getHistory() {
		// TODO Auto-generated method stub
		return history;
	}


	/* (non-Javadoc)
	 * @see user.model.User#toString()
	 */
	@Override
	public String toString() {
		return  "<Restaurant> "+username+"; name = "+name+"; address="+address;
	}


	/**
	 * Sets the sdf.
	 *
	 * @param sdf the new sdf
	 */
	public void setSDF(double sdf) {
		// TODO Auto-generated method stub
		this.special_discount_factor = sdf;
		this.sdf_changed = true;
	}

	/**
	 * Sets the gdf.
	 *
	 * @param gdf the new gdf
	 */
	public void setGDF(double gdf) {
		// TODO Auto-generated method stub
		this.generic_discount_factor = gdf;
		this.gdf_changed = true;
	}

	/**
	 * Update price.
	 */
	public void updatePrice(){ 
		//Update the discount factor of the menus and therefore the price of the meals
		if (gdf_changed){
			this.fullmealmenu.setDiscountFactor(generic_discount_factor);
			this.halfmealmenu.setDiscountFactor(generic_discount_factor);
			gdf_changed = false;
		}
		if (sdf_changed){
			this.specialmealmenu.setDiscountFactor(special_discount_factor);
			sdf_changed = false;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see user.model.User#update(java.lang.Object)
	 */
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		super.update(o);
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable)
	 */
	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable, java.lang.Object)
	 */
	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	
}


	
