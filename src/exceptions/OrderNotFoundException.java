/*
 * 
 */
package exceptions;

import system.Order;

/**
 * The Class OrderNotFoundException. Thrown when a order is not recognized.
 * 
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
