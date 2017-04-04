package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import exceptions.DishNotFoundException;
import exceptions.MealNotFoundException;
import model.myfoodora.History;
import model.restaurant.*;
import model.restaurant.Order;
import model.restaurant.StandardMealOrder;
import model.users.*;


/**To be used after the initialization of restaurants, customers, and couriers
 * 
 * Add a list of orders (can be randomly generated) to the history of MyFoodora, the history
 * of its restaurants as well as the history of delivered orders of the couriers
 * 
 * @author Ray
 *
 */

public class InitHistory {
	public static void init(String filename){		
		History history = new History();
		
		String customer_username;
		String restaurant_username;
		String courier_username;
		String ordername;
		String orderType;
		Customer customer = null;
		Restaurant restaurant = null;
		Courier courier = null;
		
		Order order = null;
		
		File file = new File(filename);
		try{
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				customer_username = s.nextLine();
				restaurant_username = s.nextLine();
				orderType = s.nextLine();
				ordername = s.nextLine();
				courier_username = s.nextLine();
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
					order = new SpecialMealOrder(customer, restaurant, restaurant.getMealFactory(orderType).createMeal(ordername));
				}
				if (orderType.equals("A-la-carte")){
					order = new AlaCarteOrder(customer, restaurant, restaurant.getDishFactory().createDish(ordername));
				}
				else{
					order = new StandardMealOrder(customer, restaurant, restaurant.getMealFactory(orderType).createMeal(ordername));
				}
				restaurant.getHistory().addOrder(order); //add the order to the restaurant's history
				MyFoodora.getInstance().getHistory().addOrder(order); //add the order to MyFoodra's general history
				
				order.setAssigned(true);
				order.setCourier(courier);
				courier.addDeliveryTask(order); //add the order to courier's delivery history
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch (MealNotFoundException e){}
		catch (DishNotFoundException e){}
	}
	//generate random history, to be copy-pasted in menu.txt
	public static void main(String[] args) {
		Random random = new Random();
		try{
		ArrayList<Restaurant> restaurants = InitFile.initRestaurant("init.ini");
		ArrayList<Customer> customers = InitFile.initCustomer("init.ini");
		for (int i = 0;i<100;i++){
			System.out.println("[Order/"+(i+1)+"]");
			int c = random.nextInt(customers.size());
			int r = random.nextInt(restaurants.size());
			System.out.println("customer="+customers.get(c).getUsername());
			System.out.println("restaurant="+restaurants.get(r).getUsername());
			if (random.nextInt()%3==0){
				System.out.println("category=Half-meal");
				System.out.println("name=hm"+(random.nextInt(4)+1));
			}
			else if (random.nextInt()%3==1){
				System.out.println("category=Full-meal");
				System.out.println("name=fm"+(random.nextInt(4)+1));
			}
			else {
				System.out.println("category=A-la-carte");
				Menu menu = restaurants.get(r).getMenu();
				System.out.println("name="+menu.getDishes().get(random.nextInt(menu.getDishes().size())).getDishName());
			}
			System.out.println("courier=courier_"+(random.nextInt(4)+1));
			System.out.println();
		}
		}catch(IOException e){}
	}
}
