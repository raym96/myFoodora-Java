/*
 * 
 */
package system;

import java.util.ArrayList;

import user.model.User;


/**
 * The Interface SpecialOfferBoard.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface SpecialOfferBoard {

	/**
	 * Register.
	 *
	 * @param obs the obs
	 */
	// add a new observer to this observable
	public void register(SpecialOfferObserver obs);
	
	/**
	 * Unregister.
	 *
	 * @param obs the obs
	 */
	// remove an existing observer of this observable
	public void unregister(SpecialOfferObserver obs);
	
	/**
	 * Notify all observers.
	 */
	// notify all observers of a change of state of this observable
	public void notifyAllObservers();
	
	/**
	 * Notify all observers.
	 *
	 * @param o the o
	 */
	public void notifyAllObservers(Object o);
	
	/**
	 * Notify observer.
	 *
	 * @param obs the obs
	 */
	// notify a appointed observer
	public void notifyObserver(Observer obs);
	
	/**
	 * Notify observer.
	 *
	 * @param obs the obs
	 * @param o the o
	 */
	public void notifyObserver(Observer obs, Object o);
	
	/**
	 * Notify observers.
	 *
	 * @param observers the observers
	 */
	public void notifyObservers(ArrayList<Observer> observers);	
	
	/**
	 * Notify observers.
	 *
	 * @param observers the observers
	 * @param o the o
	 */
	public void notifyObservers(ArrayList<User> observers, Object o);
}
