package model.users;

import java.util.*;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
import model.myfoodora.History;
import model.myfoodora.Message;
import model.myfoodora.SpecialOfferBoard;
import model.restaurant.*;
import service.RestaurantService;
import service.impl.RestaurantServiceImpl;

public class Restaurant extends User{

	private String name;
	private AddressPoint address;
	
	
	private double generic_discount_factor = 0.05;
	private double special_discount_factor = 0.1;
	private boolean gdf_changed;
	private boolean sdf_changed; //small observer pattern with mealmenus
	
	private Menu menu;
	private MealMenu halfmealmenu;
	private MealMenu fullmealmenu;
	private MealMenu specialmealmenu;
	
	private History history;
	
	private RestaurantService restaurantService;
	
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
	

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	
	public DishFactory getDishFactory(){
		return new DishFactory(this.menu);
	}
	
	public MealFactory getMealFactory(String mealType){
		if(mealType.equalsIgnoreCase("Full-meal")){
			return new FullMealFactory(fullmealmenu);
		}else if(mealType.equalsIgnoreCase("Half-meal")){
			return new HalfMealFactory(halfmealmenu);
		}else if(mealType.equalsIgnoreCase("Special-meal")){
			return new HalfMealFactory(specialmealmenu);
		}
		return null;
	}

	public Menu getMenu() {
		return menu;
	}

	public MealMenu getHalfMealMenu() {
		return halfmealmenu;
	}
	public MealMenu getFullMealMenu(){
		return fullmealmenu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	//USEFUL FOR TESTS
	public void setMealMenu(ArrayList<MealMenu> mealmenus){
		this.halfmealmenu = mealmenus.get(0);
		this.fullmealmenu = mealmenus.get(1);
		this.specialmealmenu = mealmenus.get(2);
	}

	public void setHalfMealmenu(MealMenu mealmenu) {
		this.halfmealmenu = mealmenu;
	}
	
	public void setFullMealmenu(MealMenu mealmenu){
		this.fullmealmenu=mealmenu;
	}

	public void setSpecialMealmenu(MealMenu mealmenu){
		this.specialmealmenu = mealmenu;
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

	public boolean isGdf_changed() {
		return gdf_changed;
	}


	public boolean isSdf_changed() {
		return sdf_changed;
	}


	public MealMenu getSpecialmealmenu() {
		return specialmealmenu;
	}

	public void addToHistory(Order order){
		history.addOrder(order);
	}
	
	public History getHistory() {
		// TODO Auto-generated method stub
		return history;
	}


	@Override
	public String toString() {
		return  "<Restaurant> "+username+"; name = "+name+"; address="+address;
	}


	public void setSDF(double sdf) {
		// TODO Auto-generated method stub
		this.special_discount_factor = sdf;
		this.sdf_changed = true;
	}

	public void setGDF(double gdf) {
		// TODO Auto-generated method stub
		this.generic_discount_factor = gdf;
		this.gdf_changed = true;
	}

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
	
	
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		super.update(o);
	}

	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}


	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	
}


	
