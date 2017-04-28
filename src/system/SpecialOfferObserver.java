/*
 * 
 */
package system;

import java.util.ArrayList;

import restaurant.Meal;


/**
 * An asynchronous update interface for receiving notifications
 * about SpecialOffer information as the SpecialOffer is constructed.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface SpecialOfferObserver{
	public void updateNewOffer(Meal meal);
}
