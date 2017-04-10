/*
 * 
 */
package initialization;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import exceptions.DishNotFoundException;
import exceptions.MealNotFoundException;
import exceptions.OrderNotFoundException;
import exceptions.UserNotFoundException;
import restaurant.*;
import system.*;
import user.model.*;

/**
 * The Class InitialScenario. Loads an initial scenario from an ini file.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class InitialScenario {
	
	/**
	 * Load.
	 *
	 * @param filename the filename
	 * @throws UserNotFoundException the user not found exception
	 */
	//Load .ini file data into the MyFoodora System
	public static void load(String filename) throws UserNotFoundException {
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("Loading initial scenario <"+filename+">");
		MyFoodora.reset(); //reset the state of the system
		MyFoodora myfoodora = MyFoodora.getInstance();
		ArrayList<User> users = new ArrayList<User>();
		try{
			Ini ini = new Ini(new File(filename)); //if filename not found, throws new exception
			users.addAll(loadManager(ini));
			users.addAll(loadCustomer(ini));
			users.addAll(loadRestaurant(ini)); //loading the menus at the same time
			for (Courier c:loadCourier(ini)){
				//different treatment for couriers because they are add to the courier list as well
				users.add(c);
				myfoodora.getCouriers().add(c);
			}
			myfoodora.setUsers(users); //add them to myfoodora
			for (User u : users){
				//activate them
				myfoodora.activateUser(u);
			}
			myfoodora.setHistory(loadHistory(ini)); //initialize history by adding orders
			
			myfoodora.displayUsers();
			myfoodora.displayAllMenus();	
//			myfoodora.displayHistory();
			
			System.out.println("The initial file <"+filename+"> successfully loaded into the system.");
			System.out.println("-----------------------------------------------------------------------------------\n");
			}catch(IOException e){
				e.printStackTrace();
		}
	}

	/**
	 * Load manager.
	 *
	 * @param ini the ini
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Manager> loadManager(Ini ini) throws IOException{
		String name;
		String surname;
		String username;
		
		ArrayList<Manager> users = new ArrayList<Manager>();
		
		Ini.Section managers;
		Ini.Section manager;
		
    	managers = ini.get("Manager");
    	for (String id:managers.childrenNames()){
    		manager = managers.getChild(id);
    		name = manager.get("name");
    		surname = manager.get("surname");
    		username = manager.get("username");
			Manager c = new Manager(name,surname,username);
			users.add(c);
    	}
    	return users;
	}
	
	/**
	 * Load customer.
	 *
	 * @param ini the ini
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Customer> loadCustomer(Ini ini) throws IOException {
		String name;
		String surname;
		String username;
		String email;
		String phone;
		AddressPoint address;
		
		ArrayList<Customer> users = new ArrayList<Customer>();

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
    	
	/**
	 * Load courier.
	 *
	 * @param ini the ini
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Courier> loadCourier(Ini ini) throws IOException {
		String name;
		String surname;
		String username;
		String phone;
		AddressPoint address;
		
		ArrayList<Courier> users = new ArrayList<Courier>();
		
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
			c.setOn_duty(true);
			users.add(c);
    	}
    	return users;
	}
	
	/**
	 * Load restaurant.
	 *
	 * @param ini the ini
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//initialize restaurants and set up their menu & meal-menu
	public static ArrayList<Restaurant> loadRestaurant(Ini ini) throws IOException {
		String name;
		String username;
		AddressPoint address;
		
		ArrayList<Restaurant> users = new ArrayList<Restaurant>();
		
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
    	
    	loadMenu(users, ini); //SET THE DISH MENU
    	loadMealMenu(users, ini); //SET THE MEAL MENU
    	return users;
	}
	
	/**
	 * Load menu.
	 *
	 * @param restaurants the restaurants
	 * @param ini the ini
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//add dishes to the menu of predefined restaurants
	public static void loadMenu(ArrayList<Restaurant> restaurants, Ini ini) throws IOException {
		
		Ini.Section dishes;
		Ini.Section dish;
	
		String restaurant_username;
		Restaurant restaurant = null;
		
		String name;
		String dishCategory; //starter main-dish dessert
		String dishType; //vegetarian, gluten-free, standard
		double price;
	
		
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
			try{
				if (dishCategory.equals("Starter")){
					restaurant.getMenu().addDish(new Starter(name,dishType,price));
				}
				if (dishCategory.equals("Main-dish")){
					restaurant.getMenu().addDish(new MainDish(name,dishType,price));
				}
				if (dishCategory.equals("Dessert")){
					restaurant.getMenu().addDish(new Dessert(name,dishType,price));
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Load meal menu.
	 *
	 * @param restaurants the restaurants
	 * @param ini the ini
	 * @throws InvalidFileFormatException the invalid file format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Add meals to the meal-menu of predefined restaurants
	public static void loadMealMenu(ArrayList<Restaurant> restaurants, Ini ini) throws InvalidFileFormatException, IOException{	
		String restaurant_username;
		Restaurant restaurant = null;
		
		String mealName;
		String mealCategory;
		String dishName1;
		String dishName2;
		String dishName3;
		
		Ini.Section meals;
		Ini.Section meal;
		
		Meal newmeal;
		meals = ini.get("Meal");
		try{
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
				newmeal = new HalfMeal(mealName);
				for (Dish d:restaurant.getMenu().getDishes()){
					if (d.getDishName().equalsIgnoreCase(dishName1) || d.getDishName().equalsIgnoreCase(dishName2)){
						newmeal.addDish(d);
					}
				}
				newmeal.refreshMealType();
				restaurant.getHalfMealMenu().addMeal(newmeal);
			}
			if (mealCategory.equals("Full-meal")){
				dishName1 = meal.get("dish1");
				dishName2 = meal.get("dish2");
				dishName3 = meal.get("dish3");
				newmeal = new FullMeal(mealName);
				for (Dish d:restaurant.getMenu().getDishes()){
					if (d.getDishName().equalsIgnoreCase(dishName1) || d.getDishName().equalsIgnoreCase(dishName2) ||d.getDishName().equalsIgnoreCase(dishName3)){
						newmeal.addDish(d);
					}
				}
				newmeal.refreshMealType();	
				restaurant.getFullMealMenu().addMeal(newmeal);
			}
			if (mealCategory.equals("Special-offer")){
				dishName1 = meal.get("dish1");
				dishName2 = meal.get("dish2");
				newmeal = new HalfMeal(mealName);
				for (Dish d:restaurant.getMenu().getDishes()){
					if (d.getDishName().equalsIgnoreCase(dishName1) || d.getDishName().equalsIgnoreCase(dishName2)){
						newmeal.addDish(d);
					}
				}
				newmeal.refreshMealType();
				restaurant.getSpecialmealmenu().addMeal(newmeal);
				
			}

		}
		}catch ( Exception e){
			e.printStackTrace();
		}

	}
	
	/**
	 * Load history.
	 *
	 * @param ini the ini
	 * @return the history
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Initialize history by adding random generated orders to restaurants
	public static History loadHistory(Ini ini) throws IOException{
		History history= new History();
				
		Ini.Section orders;
		Ini.Section order;
		
		String customer_username;
		String restaurant_username;
		String courier_username;
		String ordername;
		String orderType;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd, hh:mm:ss");
		Date date;
		Customer customer = null;
		Restaurant restaurant = null;
		Courier courier = null;
		Order neworder = null;
		
		orders = ini.get("Order");
		for (String id:orders.childrenNames()){
			try{
				order = orders.getChild(id);
				customer_username = order.get("customer");
				restaurant_username = order.get("restaurant");
				orderType = order.get("category");
				ordername = order.get("name");
				courier_username = order.get("courier");
				date = sdf.parse(order.get("date"));
	
				for (User u : MyFoodora.getInstance().getUsers()){
					if (u.getUsername().equals(customer_username)){
						customer = ((Customer)u); 
					}
				}
				for (User u : MyFoodora.getInstance().getUsers()){
					if (u.getUsername().equals(restaurant_username)){
						restaurant = ((Restaurant)u); 
					}
				}
				for (User u : MyFoodora.getInstance().getUsers()){
					if (u.getUsername().equals(courier_username)){
						courier = ((Courier)u); 
					}
				}
				neworder = new Order(customer, restaurant);
				if (orderType.equals("Special-meal")){
					neworder.addItem(restaurant.getMealFactory(orderType).createMeal(ordername));
				}
				if (orderType.equals("A-la-carte")){
					neworder.addItem(restaurant.getDishFactory().createDish(ordername));
				}
				else{
					neworder.addItem(restaurant.getMealFactory(orderType).createMeal(ordername));
				}
				//Assign courier
				neworder.setAssigned(true);
				neworder.setCourier(courier);
				courier.addWaitingOrder(neworder);
				try {
					courier.acceptWaitingOrder(neworder);
				} catch (OrderNotFoundException e) {
					e.printStackTrace();
				} //add the order to courier's delivery history
				
				//Set date
				neworder.setDate(date);
				
				restaurant.addToHistory(neworder);
				history.addOrder(neworder);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return history;
	}
}
