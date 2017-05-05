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
	 * @param message the message
	 */
	// method for updating the state of the Observer
	public void update(Message message);
	
	/**
	 * Observe.
	 *
	 * @param messageBoard the message board
	 */
	public void observe(MessageBoard messageBoard);
}
