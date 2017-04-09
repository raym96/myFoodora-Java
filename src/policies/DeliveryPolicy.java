/*
 * 
 */
package policies;

import java.util.ArrayList;

import system.Order;
import user.model.Courier;

// TODO: Auto-generated Javadoc
/**
 * The Interface DeliveryPolicy.
 * @author He Xiaoan
 * @author Ji Raymond
 */
//Strategy pattern
public interface DeliveryPolicy {

	/**
	 * Parses the.
	 *
	 * @param order the order
	 * @param activecouriers the activecouriers
	 * @return the courier
	 */
	public abstract Courier parse(Order order, ArrayList<Courier> activecouriers);
	
}
