/*
 * 
 */
package exceptions;

import system.Order;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderNotFoundException.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class OrderNotFoundException extends Exception {
	
	/**
	 * Instantiates a new order not found exception.
	 *
	 * @param order the order
	 */
	public OrderNotFoundException(Order order){
		System.out.println("[OrderNotFoundException]:: The order " + order+" couldn't be identified.");
	}
}
