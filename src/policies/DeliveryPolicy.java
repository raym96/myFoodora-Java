/*
 * 
 */
package policies;

import java.util.ArrayList;

import system.Order;
import user.model.Courier;

/**
 * The Interface DeliveryPolicy.
 * @author He Xiaoan
 * @author Ji Raymond
 */
//Strategy pattern
public interface DeliveryPolicy {

	/**
	 * Parses an order to a courier.
	 *
	 * @param order the order
	 * @param activecouriers the activecouriers
	 * @return the courier
	 */
	public abstract Courier parse(Order order, ArrayList<Courier> activecouriers);
	
}
