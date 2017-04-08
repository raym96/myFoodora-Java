package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import restaurant.*;

public class History {
	private ArrayList<Order> orders;
	
	public History() {
		super();
		orders = new ArrayList<Order>();
	}

	public void addOrder(Order order){
		orders.add(order);
	}
	
	public ArrayList<Order> getOrderBetween(Date date1, Date date2){
		//returns all orders made between date 1 and date 2
		ArrayList<Order >list = new ArrayList<Order>();
		for (Order order:orders){
			if (order.getDate().compareTo(date1)>=0 && order.getDate().compareTo(date2)<=0){
				list.add(order);
			}
		}
		return list;
	}
	
	public void setOrders(ArrayList<Order> orders){
		this.orders = orders;
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
	@Override
	public String toString(){
		System.out.println("\n[COMMAND HISTORY]");
		String str = "";
		for (Order order:orders){
			str+=order+"\n";
		}
		return str;
	}
	
	public ArrayList<Order> getOrdersOf(String restaurant_username){
		ArrayList<Order> ret = new ArrayList<Order>();
		for (Order order:orders){
			if (order.getRestaurant().getUsername().equals(restaurant_username)){
				ret.add(order);
			}
		}
		return ret;
	}
}
