package initialization;

import java.util.ArrayList;

import model.myfoodora.History;
import model.restaurant.*;
import model.restaurant.Order;
import model.restaurant.StandardMealOrder;
import model.user.*;

public class InitHistory {
	public static History init(){
		History history = new History();
		
		ArrayList<Restaurant> restaurants = InitRestaurant.init("src/txt files/restaurant.txt");
		ArrayList<Customer> customers = InitCustomer.init("src/txt files/customer.txt");
		ArrayList<MealMenu> meals = InitMealMenu.init("src/txt files/mealmenu.txt");

		ArrayList<Order> orders = new ArrayList<Order>();

		for (Restaurant r : restaurants){
			for (Customer c: customers){
				Order order1 = new StandardMealOrder(c,r,meals.get(0).getMeals().get(0));
				Order order2 = new StandardMealOrder(c,r,meals.get(1).getMeals().get(0));
				Order order3 = new SpecialMealOrder(c,r,meals.get(2).getMeals().get(0));
				orders.add(order1);
				orders.add(order2);
				orders.add(order3);
			}
		}
		history.setOrders(orders);
		return history;
	}
//	public static void main(String[] args) {
//		init().displayAllOrders();
//	}
}
