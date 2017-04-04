package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import model.users.*;
import model.restaurant.*;

public class InitFile {

	public static ArrayList<Customer> initCustomer(String filename) throws IOException {
		String name;
		String surname;
		String username;
		String email;
		String phone;
		AddressPoint address;
		
		ArrayList<Customer> users = new ArrayList<Customer>();
		Ini ini = new Ini(new File(filename));

    	Ini.Section customers;
    	Ini.Section customer;
    	
    	customers = ini.get("Customer");
    	for (String id:customers.childrenNames()){
    		customer = customers.getChild(id);
    		name = customer.get("name");
    		surname = customer.get("surname");
    		username = customer.get("username");
			address = new AddressPoint(customer.get("address"));
			phone = customer.get("phone");
			email = customer.get("email");
			Customer c = new Customer(name,surname,username,address,email,phone);
			users.add(c);
    	}
    	return users;
	}
    	
    	//COURIERS
	public static ArrayList<Courier> initCourier(String filename) throws IOException {
		String name;
		String surname;
		String username;
		String phone;
		AddressPoint address;
		
		ArrayList<Courier> users = new ArrayList<Courier>();
		Ini ini = new Ini(new File(filename));
		
    	Ini.Section couriers;
    	Ini.Section courier;
    	
    	couriers = ini.get("Courier");
    	for (String id:couriers.childrenNames()){
    		courier = couriers.getChild(id);
    		name = courier.get("name");
    		surname = courier.get("surname");
    		username = courier.get("username");
			address = new AddressPoint(courier.get("position"));
			phone = courier.get("phone");
			Courier c = new Courier(name,surname,username,address,phone);
			users.add(c);
    	}
    	return users;
	}
	
	//initialize restaurants and set up their menu & meal-menu
	public static ArrayList<Restaurant> initRestaurant(String filename) throws IOException {
		String name;
		String username;
		AddressPoint address;
		
		ArrayList<Restaurant> users = new ArrayList<Restaurant>();
		Ini ini = new Ini(new File(filename));
		
       	Ini.Section restaurants;
    	Ini.Section restaurant;
    	
    	restaurants = ini.get("Restaurant");
    	for (String id:restaurants.childrenNames()){
    		restaurant = restaurants.getChild(id);
    		name = restaurant.get("name");
    		username = restaurant.get("username");
			address = new AddressPoint(restaurant.get("address"));
			Restaurant r = new Restaurant(name,username,address);
    		users.add(r);
    	}
    	
    	initMenu(users, ini);
    	initMealMenu(users, ini);
    	return users;
	}
	
	//add dishes to the menus of predefined restaurants
	public static void initMenu(ArrayList<Restaurant> restaurants, Ini ini) throws IOException {
		
		Ini.Section dishes;
		Ini.Section dish;
	
		String restaurant_username;
		Restaurant restaurant = null;
		
		String name;
		String dishCategory; //starter main-dish dessert
		String dishType; //vegetarian, gluten-free, standard
		double price;
	
		
		Menu menu = new Menu();
		dishes = ini.get("Dish");
		for (String id:dishes.childrenNames()){
			dish = dishes.getChild(id);
			
			restaurant_username = dish.get("restaurant");
			for (Restaurant r : restaurants){
				if (r.getUsername().equals(restaurant_username)){
					restaurant = r;
					break;
				}
			} // get the right restaurant
			
			name = dish.get("name");
			dishCategory = dish.get("category");
			dishType = dish.get("type");
			price = Double.parseDouble(dish.get("price"));
			
			if (dishCategory.equals("Starter")){
				restaurant.getMenu().addDish(new Starter(name,dishType,price));
			}
			if (dishCategory.equals("Main-dish")){
				restaurant.getMenu().addDish(new MainDish(name,dishType,price));
			}
			if (dishCategory.equals("Dessert")){
				restaurant.getMenu().addDish(new Dessert(name,dishType,price));
			}
		}
	}
	
	//Add meal menus to predefined restaurants
	public static void initMealMenu(ArrayList<Restaurant> restaurants, Ini ini) throws InvalidFileFormatException, IOException{	
		String restaurant_username;
		Restaurant restaurant = null;
		
		String mealName;
		String mealCategory;
		String dishName1;
		String dishName2;
		String dishName3;
		
		Ini.Section meals;
		Ini.Section meal;
		
		meals = ini.get("Meal");
		for (String id : meals.childrenNames()){
			meal = meals.getChild(id);
			
			restaurant_username = meal.get("restaurant");
			for (Restaurant r : restaurants){
				if (r.getUsername().equals(restaurant_username)){
					restaurant = r;
					break;
				}
			} // get the right restaurant
			
			
			mealName = meal.get("name");
			mealCategory = meal.get("category");
			if (mealCategory.equals("Half-meal")){
				dishName1 = meal.get("dish1");
				dishName2 = meal.get("dish2");
				restaurant.getRestaurantService().addMeal(mealName, dishName1, dishName2);
			}
			if (mealCategory.equals("Full-meal")){
				dishName1 = meal.get("dish1");
				dishName2 = meal.get("dish2");
				dishName3 = meal.get("dish3");
				restaurant.getRestaurantService().addMeal(mealName, dishName1, dishName2, dishName3);
			}
			if (mealCategory.equals("Special-offer")){
				dishName1 = meal.get("dish1");
				dishName2 = meal.get("dish1");
				//add the meal and promotes it to special-meals
				restaurant.getRestaurantService().addMeal(mealName, dishName1, dishName2);
				restaurant.getRestaurantService().addSpecialMeal(mealName);
			}
		}

	}
	
	public static void InitUser(String filename) {
		MyFoodora myfoodora = MyFoodora.getInstance();
		ArrayList<User> users = new ArrayList<User>();
		try{
			users.addAll(initRestaurant(filename));
			users.addAll(initCustomer(filename));	
			users.addAll(initCourier(filename));
		}catch(IOException e){}
		myfoodora.setUsers(users); //add them to myfoodora
		myfoodora.setActiveUsers(users); //activating them
	}
}
