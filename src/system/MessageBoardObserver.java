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
public interface MessageBoardObserver{

	/**
	 * Update.
	 *
	 * @param o the o
	 */
	// method for updating the state of the Observer
	public void update(Message message);
	
	/**
	 * Observe.
	 *
	 * @param obv the obv
	 */
	public void observe(MessageBoard messageBoard);
}
