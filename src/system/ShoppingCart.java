/*
 * 
 */
package system;

import java.util.ArrayList;

import user.model.Restaurant;


/**
 * The Class ShoppingCart.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ShoppingCart {
	
	/** The orders. */
	private ArrayList<Order> orders;

	
	/**
	 * Instantiates a new shopping cart.
	 */
	public ShoppingCart() {
		super();
		this.orders = new ArrayList<Order>();
	}

	/**
	 * Adds the order.
	 *
	 * @param o the o
	 */
	public void addOrder(Order o){
		orders.add(o);
	}
	
	/**
	 * Removes the order.
	 *
	 * @param o the o
	 */
	public void removeOrder(Order o){
		orders.remove(o);
	}
	
	
	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
	/**
	 * Checks if has restaurant.
	 *
	 * @param restaurant the restaurant
	 * @return true, if successful
	 */
	public boolean hasRestaurant(Restaurant restaurant){
		for (Order order:orders){
			if (order.getRestaurant().equals(restaurant)){
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Gets the order.
	 *
	 * @param restaurant the restaurant
	 * @return the order
	 */
	public Order getOrder(Restaurant restaurant){
		for (Order order:orders){
			if (order.getRestaurant().equals(restaurant)){
				return order;
			}
		}
		return null;
	}
	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public double getTotalPrice(){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalprice =0;
		for (Order order:orders){
			totalprice += order.accept(visitor);
		}
		return totalprice;
	}
	

	
	/**
	 * Contains.
	 *
	 * @param order the order
	 * @return true, if successful
	 */
	public boolean contains(Order order){
		return orders.contains(order);
	}
	
	/**
	 * Clear.
	 */
	public void clear(){
		this.orders = new ArrayList<Order>();
	}
	
	
	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size(){
		return orders.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		for (Order order:orders){
			str += order+"\n\n";
		}
		return "\nSHOPPINGCART : \n\n" + str +"TOTAL PRICE = "+getTotalPrice()+" euros";
	}
	
	
}
