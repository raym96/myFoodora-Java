/*
 * 
 */
package user.service;

import system.AddressPoint;
import system.Order;


/**
 * The Interface CourierService. 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface CourierService extends UserService {
	
	/**
	 * register their account to the MyFoodora system.
	 */
	public void register();
	
	/**
	 * unregister their account to the MyFoodora system.
	 */
	public void unregister();
	
	
	/**
	 * Set the state as on-duty.
	 */
	public void turnOnDuty();
	
	/**
	 * Set the state as off-duty.
	 */
	public void turnOffDuty();
	

	/**
	 * Change position.
	 *
	 * @param newPoint the new address
	 */
	public void changePosition(String positionString);
	
	/**
	 * accept to a delivery call (received by the MyFoodora system).
	 *
	 * @param orderName the order name
	 */
	public void acceptCall(String orderName);
	
	/**
	 * refuse a delivery call (received by the MyFoodora system).
	 *
	 * @param orderName the order name
	 */
	public void refuseCall(String orderName);
	
	public void showInfo();
	
	public void showCount();
	
	public void showHistory();
	
	public void showWaitingOrders();

}
