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

public class InitHistory {
	public static History init(String filename){		
		History history = new History();
		
		String customer_username;
		String restaurant_username;
		String ordername;
		String orderType;
		Customer customer = null;
		Restaurant restaurant = null;
		Order order = null;
		
		File file = new File(filename);
		try{
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				customer_username = s.nextLine();
				restaurant_username = s.nextLine();
				orderType = s.nextLine();
				ordername = s.nextLine();
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
				if (orderType.equals("Special-meal")){
					order = new SpecialMealOrder(customer, restaurant, restaurant.getMealFactory(orderType).createMeal(ordername));
					restaurant.getHistory().addOrder(order);
					history.addOrder(order);
				}
				if (orderType.equals("A-la-carte")){
					order = new AlaCarteOrder(customer, restaurant, restaurant.getDishFactory().createDish(ordername));
					history.addOrder(order);
					restaurant.getHistory().addOrder(order);
				}
				else{
					order = new StandardMealOrder(customer, restaurant, restaurant.getMealFactory(orderType).createMeal(ordername));
					history.addOrder(order);
					restaurant.getHistory().addOrder(order);
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch (MealNotFoundException e){}
		catch (DishNotFoundException e){}
		return history;
	}
	//generate random history, to be copy-pasted in menu.txt
	public static void main(String[] args) {
		Menu menu = InitDishMenu.init("src/txt files/menu.txt");
		Random random = new Random();
		for (int i = 0;i<100;i++){
			System.out.println("----------");
			System.out.println("customer_"+(random.nextInt(7)+1));
			System.out.println("restaurant_"+(random.nextInt(7)+1));
			if (random.nextInt()%3==0){
				System.out.println("Half-meal");
				System.out.println("hm"+(random.nextInt(5)+1));
			}
			else if (random.nextInt()%3==1){
				System.out.println("Full-meal");
				System.out.println("fm"+(random.nextInt(5)+1));
			}
			else {
				System.out.println("A-la-carte");
				System.out.println(menu.getDishes().get(random.nextInt(menu.getDishes().size())).getDishName());
			}
		}
	}
}
