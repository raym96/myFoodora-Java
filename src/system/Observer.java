/*
 * 
 */
package system;

import java.util.ArrayList;

import system.*;


/**
 * The Interface Observer.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface Observer {

	/**
	 * Update.
	 *
	 * @param o the o
	 */
	// method for updating the state of the Observer
	public void update(Object o);
	
	/**
	 * Observe.
	 *
	 * @param obv the obv
	 */
	public void observe(Observable obv);
	
	/**
	 * Observe.
	 *
	 * @param obv the obv
	 * @param o the o
	 */
	public void observe(Observable obv, Object o);
}
