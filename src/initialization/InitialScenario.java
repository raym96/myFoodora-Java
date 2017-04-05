package initialization;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import exceptions.DishNotFoundException;
import exceptions.MealNotFoundException;
import model.users.*;
import model.myfoodora.History;
import model.restaurant.*;

public class InitialScenario {
	
	//Load .ini file data into the MyFoodora System
	public static void load(String filename) {
		MyFoodora myfoodora = MyFoodora.getInstance();
		ArrayList<User> users = new ArrayList<User>();
		try{
			users.addAll(loadManager(filename));
			users.addAll(loadRestaurant(filename));
			users.addAll(loadCustomer(filename));	
			users.addAll(loadCourier(filename));
			myfoodora.setUsers(users); //add them to myfoodora
			myfoodora.setActiveUsers(users); //activate them
			myfoodora.setHistory(loadHistory(filename)); //initialize history by adding orders
		}catch(IOException e){
			System.out.println(filename+" not found.");
		}
		System.out.println("\nThe initial file "+filename+" successfully loaded into the system.");
		System.out.println("-------------------------------------------------------------\n");
	}

	public static ArrayList<Manager> loadManager(String filename) throws IOException{
		String name;
		String surname;
		String username;
		
		ArrayList<Manager> users = new ArrayList<Manager>();
		Ini ini = new Ini(new File(filename));
		
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
	public static ArrayList<Customer> loadCustomer(String filename) throws IOException {
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
    	
	public static ArrayList<Courier> loadCourier(String filename) throws IOException {
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
	public static ArrayList<Restaurant> loadRestaurant(String filename) throws IOException {
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
    	
    	System.out.println("Initializing the menus:");
    	loadMenu(users, ini); //SET THE DISH MENU
    	loadMealMenu(users, ini); //SET THE MEAL MENU
    	return users;
	}
	
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
				dishName2 = meal.get("dish2");
				//add the meal and promotes it to special-meals
				restaurant.getRestaurantService().addMeal(mealName, dishName1, dishName2);
				restaurant.getRestaurantService().addSpecialMeal(mealName);
			}
		}

	}
	
	//Initialize history by adding random orders to restaurants
	public static History loadHistory(String filename) throws IOException{
		History history= new History();
		Ini ini = new Ini(new File(filename));
		
		//relative to dates
		Random random = new Random();

		
		Ini.Section orders;
		Ini.Section order;
		
		String customer_username;
		String restaurant_username;
		String courier_username;
		String ordername;
		String orderType;
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
				if (orderType.equals("Special-meal")){
					neworder = new SpecialMealOrder(customer, restaurant, restaurant.getMealFactory(orderType).createMeal(ordername));
				}
				if (orderType.equals("A-la-carte")){
					neworder = new AlaCarteOrder(customer, restaurant, restaurant.getDishFactory().createDish(ordername));
				}
				else{
					neworder = new StandardMealOrder(customer, restaurant, restaurant.getMealFactory(orderType).createMeal(ordername));
				}
				//Assign courier
				neworder.setAssigned(true);
				neworder.setCourier(courier);
				courier.addDeliveryTask(neworder); //add the order to courier's delivery history
				
				//Set random date
				String s = "2017."+random.nextInt(4)+"."+random.nextInt(28);
				DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
				Date date;
				date = format.parse(s);
				neworder.setDate(date);
				
				restaurant.getHistory().addOrder(neworder);
				history.addOrder(neworder);
			}catch (ParseException e){
			}catch (MealNotFoundException e){
			}catch (DishNotFoundException e){}	
		}
		return history;
	}
}
