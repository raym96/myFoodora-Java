package model.myfoodora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import model.restaurant.*;
import model.user.Restaurant;

public class History {
	ArrayList<Order> orders;
	
	public void addOrder(Order order){
		orders.add(order);
	}
	
	public ArrayList<Order> getOrderBetween(Date date1, Date date2){
		//returns all orders made between date 1 and date 2
		ArrayList<Order >list = new ArrayList<Order>();
		for (Order order:orders){
			if (order.getDate().compareTo(date1)>0 && order.getDate().compareTo(date2)<0){
				list.add(order);
			}
		}
		return list;
	}
	
	public void DisplayHistory(){
		System.out.println(orders);
	}
	
	public void DisplayMostOrderedHalfMeal(Restaurant r){
		Map<Meal, Integer> tm  = new TreeMap(); //key = meal, value = occurrence, can be sorted
		for (Order order: orders){
			if  (order.getRestaurant()==r && order instanceof StandardMealOrder){
				if (((StandardMealOrder)order).getMealType()=="half meal"){
					int occurrences = Collections.frequency(orders, order);
					tm.put(((MealOrder) order).getMeal(), occurrences);
				}
			}
       for (Map.Entry<Meal, Integer> entry : tm.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
		}
		}
	}
	
	public void DisplayLeastOrderedHalfMeal(Restaurant r){
		Map<Meal, Integer> tm  = new TreeMap<Meal,Integer>(Collections.reverseOrder()); //key = meal, value = occurrence, can be sorted
		for (Order order: orders){
			if  (order.getRestaurant()==r && order instanceof StandardMealOrder){
				if (((StandardMealOrder)order).getMealType()=="half meal"){
					int occurrences = Collections.frequency(orders, order);
					tm.put(((MealOrder) order).getMeal(), occurrences);
				}
			}
       for (Map.Entry<Meal, Integer> entry : tm.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
		}
		}
	}
	
	public void DisplayMostOrderedAlaCarte(Restaurant r){
		Map<Meal, Integer> tm  = new TreeMap(); //key = meal, value = occurrence, can be sorted
		for (Order order: orders){
			if  (order.getRestaurant()==r && order instanceof AlaCarteOrder){
					int occurrences = Collections.frequency(orders, order);
					tm.put(((MealOrder) order).getMeal(), occurrences);
				}
			}
		for (Map.Entry<Meal, Integer> entry : tm.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
		}
	}
	
	public void DisplayLeastOrderedAlaCarte(Restaurant r){
		Map<Meal, Integer> tm  = new TreeMap<Meal,Integer>(Collections.reverseOrder()); //key = meal, value = occurrence, can be sorted
		for (Order order: orders){
			if  (order.getRestaurant()==r && order instanceof AlaCarteOrder){
					int occurrences = Collections.frequency(orders, order);
					tm.put(((MealOrder) order).getMeal(), occurrences);
				}
			}
		for (Map.Entry<Meal, Integer> entry : tm.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
		}
	}
}
