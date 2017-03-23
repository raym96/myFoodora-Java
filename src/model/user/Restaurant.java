package model.user;

import java.util.*;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
import model.customer.Observable;
import model.myfoodora.History;
import model.restaurant.*;
import service.RestaurantService;
import service.impl.RestaurantServiceImpl;

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
		
		restaurantService = new RestaurantServiceImpl(this);
	}
	
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	
	public DishFactory getDishFactory(){
		return new DishFactory(this.menu);
	}
	
	public MealFactory getMealFactory(String mealType){
		if(mealType.equals("Full_meal")){
			return new FullMealFactory(mealmenu);
		}else if(mealType.equals("Half_meal")){
			return new HalfMealFactory(mealmenu);
		}
		return null;
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

	public SpecialMealMenu getSpecialmealmenu() {
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
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return super.toString() + ", <Restaurant> name=" + name + ", address=" + address + ".\n";
	}

	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}

	
}


	
