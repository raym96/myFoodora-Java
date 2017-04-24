/*
 * 
 */
package system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import exceptions.NameNotFoundException;
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
	 * Checks if has order name orderName.
	 *
	 * @param orderName the order name
	 * @return true, if successful
	 */
	public boolean hasOrder(String orderName){
		for (Order order:orders){
			if (order.getName().equalsIgnoreCase(orderName)){
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Gets the order.
	 *
	 * @param orderName the order name
	 * @return the order
	 * @throws NameNotFoundException the name not found exception
	 */
	public Order getOrder(String orderName) throws NameNotFoundException{
		for (Order order:orders){
			if (order.getName().equalsIgnoreCase(orderName)){
				return order;
			}
		}
		throw new NameNotFoundException(orderName);
	}
	
	/**
	 * Gets the orders between 2 dates
	 *
	 * @param stringDate1 the string date 1
	 * @param stringDate2 the string date 2
	 * @return the order between
	 * @throws ParseException the parse exception
	 */
	public ArrayList<Order> getOrderBetween(String stringDate1, String stringDate2) throws ParseException{
		//returns all orders made between date 1 and date 2
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse(stringDate1);
		Date date2 = sdf.parse(stringDate2);
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
		String str = "\n[COMMAND HISTORY]\n\n";
		for (Order order:orders){
			str+=order+"\n\n";
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
