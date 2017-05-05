/*
 * 
 */
package system;

import java.util.ArrayList;

import user.model.User;
import restaurant.*;


/**
 * The Interface SpecialOfferBoard.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface SpecialOfferBoard{

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
	 *
	 * @param meal the meal
	 */
	// notify all observers of a change of state of this observable
	public void notifyAllSpecialObservers(Meal meal);

	/**
	 * Gets the special offers.
	 *
	 * @return the special offers
	 */
	public ArrayList<Meal> getSpecialOffers();
		
}
