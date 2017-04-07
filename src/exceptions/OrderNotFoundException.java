package exceptions;

import system.Order;

public class OrderNotFoundException extends Exception {
	
	public OrderNotFoundException(Order order){
		System.out.println("ORDER ERROR: The order " + order+" couldn't be identified.");
	}
}
