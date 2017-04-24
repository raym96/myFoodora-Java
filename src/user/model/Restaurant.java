/*
 * 
 */
package user.model;

import java.util.*;

import exceptions.NameNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.NameNotFoundException;
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
	
	/** The menu. */
	private Menu menu;
	
	/** The mealmenu. */
	private MealMenu mealmenu;
	
	/** The special-offer menu. */
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
	 * @param password the password
	 */
	public Restaurant(String name, String username, AddressPoint address,String password) {
		super(username,password);
		this.name = name;
		this.address = address;
		menu = new Menu();
		mealmenu = new MealMenu(this);
		specialmealmenu = new MealMenu(this);
		history = new History();
		restaurantService = new RestaurantServiceImpl(this);
	
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
		if(mealCategory.equalsIgnoreCase("meal")){
			return new MealFactory(mealmenu);
		}else if(mealCategory.equalsIgnoreCase("Special-meal")){
			return new MealFactory(specialmealmenu);
		}
		System.out.println("Meal category non recognized");
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
	 * Gets the meal menu.
	 *
	 * @return the meal menu
	 */
	public MealMenu getMealMenu() {
		return mealmenu;
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
		this.mealmenu = mealmenus.get(0);
		this.specialmealmenu = mealmenus.get(1);
	}

	/**
	 * Sets the mealmenu.
	 *
	 * @param mealmenu the new mealmenu
	 */
	public void setHalfMealmenu(MealMenu mealmenu) {
		this.mealmenu = mealmenu;
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
			income+=o.accept(new ConcreteShoppingCartVisitor());
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
	
	@Override
	public void displayInfo(){
		String output = "";
		output+="<Restaurant> "+username+"; name = "+name+"; address="+address;
		if (email !=null){
			output+="; email = "+email;
		}
		if (phone !=null){
			output+="; phone = "+phone;
		}
		System.out.println(output);
	}


	/**
	 * Sets the sdf.
	 *
	 * @param sdf the new sdf
	 */
	public void setSDF(double sdf) {
		// TODO Auto-generated method stub
		this.special_discount_factor = sdf;
	}

	/**
	 * Sets the gdf.
	 *
	 * @param gdf the new gdf
	 */
	public void setGDF(double gdf) {
		// TODO Auto-generated method stub
		this.generic_discount_factor = gdf;
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


	
