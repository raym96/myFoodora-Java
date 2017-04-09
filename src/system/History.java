/*
 * 
 */
package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import restaurant.*;


/**
 * The Class History.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class History {
	
	/** The orders. */
	private ArrayList<Order> orders;
	
	/**
	 * Instantiates a new history.
	 */
	public History() {
		super();
		orders = new ArrayList<Order>();
	}

	/**
	 * Adds the order.
	 *
	 * @param order the order
	 */
	public void addOrder(Order order){
		orders.add(order);
	}
	
	/**
	 * Gets the order between.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the order between
	 */
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
	
	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(ArrayList<Order> orders){
		this.orders = orders;
	}
	
	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		System.out.println("\n[COMMAND HISTORY]");
		String str = "";
		for (Order order:orders){
			str+=order+"\n";
		}
		return str;
	}
	
	/**
	 * Gets the orders of.
	 *
	 * @param restaurant_username the restaurant username
	 * @return the orders of
	 */
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
