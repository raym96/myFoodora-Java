/*
 * 
 */
package system;

import java.util.ArrayList;

import restaurant.Meal;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about SpecialOffer information as the SpecialOffer is constructed.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface SpecialOfferObserver {
	
	/**
	 * This method is called when information about an SpecialOffer
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param meal the meal
	 */
	public void addSpecialOffer(Meal meal); //add a new special offer
	
	/**
	 * This method is called when information about an SpecialOffer
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param specialoffers the specialoffers
	 */
	public void updateSpecialOffer(ArrayList<Meal> specialoffers); //update the special offers by a list
}
