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
	 * @param positionString the position string
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
	
	/* (non-Javadoc)
	 * @see user.service.UserService#showInfo()
	 */
	public void showInfo();
	
	/**
	 * Show count.
	 */
	public void showCount();
	
	/* (non-Javadoc)
	 * @see user.service.UserService#showHistory()
	 */
	public void showHistory();
	
	/**
	 * Show waiting orders.
	 */
	public void showWaitingOrders();

}
