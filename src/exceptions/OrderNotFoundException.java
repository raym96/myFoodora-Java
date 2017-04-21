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
	public OrderNotFoundException(String orderName){
		System.out.println("[OrderNotFoundException]:: The order " + orderName+" couldn't be identified.");
	}
}
