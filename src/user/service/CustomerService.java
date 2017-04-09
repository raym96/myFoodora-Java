/*
 * 
 */
package user.service;

import system.AlaCarteOrder;
import system.History;
import system.SpecialMealOrder;
import system.StandardMealOrder;
import user.model.Restaurant;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerService.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface CustomerService {

	// 1. place orders: this includes choosing a selection of items a-la-carte or one or more
	// meals offered by a given restaurant, and paying the total price for the composed
	/**
	 * Adds the special meal order.
	 *
	 * @param r the r
	 * @param mealName the meal name
	 */
	// order.
	public void addSpecialMealOrder(Restaurant r, String mealName);
	
	/**
	 * Adds the standard meal order.
	 *
	 * @param r the r
	 * @param mealName the meal name
	 * @param mealType the meal type
	 */
	public void addStandardMealOrder(Restaurant r, String mealName, String mealType);
	
	/**
	 * Adds the ala carte order.
	 *
	 * @param r the r
	 * @param dishName the dish name
	 */
	public void addAlaCarteOrder(Restaurant r, String dishName);
	
	/**
	 * Pay.
	 */
	public void pay();	
	
	/**
	 * Register card.
	 *
	 * @param cardType the card type
	 */
	// 2. register/unregister to/from a fidelity card plan
	public void registerCard(String cardType);
	
	/**
	 * Unregister card.
	 */
	public void unregisterCard();
	
	// 3. access the information related to their account: including history of orders, and
	// points acquired with a fidelity program
	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	History getHistory();
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	double getPoints();
	
	// 4. give/remove consensus to be notified whenever a new special offer is set by any
	/**
	 * Give consensus be notified special offers.
	 */
	// restaurant
	public void giveConsensusBeNotifiedSpecialOffers();
	
	/**
	 * Removes the consensus be notified special offers.
	 */
	public void removeConsensusBeNotifiedSpecialOffers();

}
