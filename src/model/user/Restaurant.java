package model.user;

import java.util.*;

import exceptions.DishNotFoundException;
import exceptions.DishTypeErrorException;
import exceptions.MealNotFoundException;
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
//	public static void main(String[] args) {
//		Restaurant r = new Restaurant("Bonheur d'Antony","bda", new AddressPoint(1,3));
//		System.out.println(r.name);
//		Starter nems = new Starter("nems","gluten-free",2);
//		r.addDish(nems);
//		r.DisplayMenu();
//		r.DisplayMealMenu();
//		r.DisplaySpecialMenu();
//		r.addSpecialMeal("Salade-poulet");
//		r.DisplayMealMenu();
//		r.DisplaySpecialMenu();
//		
//		r.createMeal("half meal", "Salade-poulet");
//		r.createDish("lolol");
//		r.addMeal("foie gras-salade", "foie gras", "salade");
//		//error because 2 main-dishes
//		r.addMeal("poulet-poisson-glace", "poulet", "glace","glace");
//		//no error
//		r.addMeal("salade-poisson-glace", "salade", "poisson","glace");
//		r.addMeal("salade-poisson-glace", "lololol", "poisson","glace");
//		Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11,23,11).getTime();
//		System.out.println(date);
//		}


}


	
