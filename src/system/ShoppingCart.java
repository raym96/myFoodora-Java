/*
 * 
 */
package system;

import java.util.ArrayList;


/**
 * The Class ShoppingCart.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ShoppingCart {
	
	/** The orders. */
	private ArrayList<Order> orders;
	
	/** The totalprice. */
	private double totalprice;
	
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
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		totalprice += o.accept(visitor);
		o.setPrice(o.accept(visitor)); //the price of the order is updated
		orders.add(o);

	}
	
	/**
	 * Removes the order.
	 *
	 * @param o the o
	 */
	public void removeOrder(Order o){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		totalprice -= o.accept(visitor);
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
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public double getTotalPrice(){
		return totalprice;
	}
	
	/**
	 * Sets the total price.
	 *
	 * @param price the new total price
	 */
	public void setTotalPrice(double price){
		this.totalprice = price;
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
			str += order+"\n";
		}
		return "\nShoppingCart : \n" + str +"Total price="+getTotalPrice()+"?";
	}
	
	
}
