/*
 * 
 */
package user.service;

import system.AddressPoint;
import system.Order;

// TODO: Auto-generated Javadoc
/**
 * The Interface CourierService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface CourierService {
	
	/**
	 * Register.
	 */
	// 1. register/unregister their account to the MyFoodora system.
	public void register();
	
	/**
	 * Unregister.
	 */
	public void unregister();
	
	/**
	 * Turn on duty.
	 */
	// 2. set their state as on-duty or off-duty
	public void turnOnDuty();
	
	/**
	 * Turn off duty.
	 */
	public void turnOffDuty();
	
	/**
	 * Change position.
	 *
	 * @param newPoint the new point
	 */
	// 3. change their position
	public void changePosition(AddressPoint newPoint);
	
	/**
	 * Accept call.
	 *
	 * @param order the order
	 */
	// 4. accept/refuse to a delivery call (received by the MyFoodora system)
	public void acceptCall(Order order);
	
	/**
	 * Refuse call.
	 *
	 * @param order the order
	 */
	public void refuseCall(Order order);
}
