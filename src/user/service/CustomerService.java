/*
 * 
 */
package user.service;

import system.History;
import user.model.Restaurant;


/**
 * The Interface CustomerService. 
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public interface CustomerService {

	/**
	 * Place the special meal order.
	 *
	 * @param restaurant the restaurant
	 * @param mealName the meal name
	 */
	// order.
	public void commandSpecialMeal(Restaurant restaurant, String mealName);
	
	/**
	 * Place the standard meal order.
	 *
	 * @param restaurant the restaurant
	 * @param mealName the meal name
	 * @param mealType the meal type
	 */
	public void commandRegularMeal(Restaurant restaurant, String mealName, String mealType);
	
	/**
	 * Place the a-la-carte order.
	 *
	 * @param restaurant the restaurant
	 * @param dishName the dish name
	 */
	public void commandAlaCarte(Restaurant restaurant, String dishName);
	
	/**
	 * Pay.
	 */
	public void pay();	
	
	/**
	 * register to a fidelity card plan
	 *
	 * @param cardType the card type
	 */
	public void registerCard(String cardType);
	
	/**
	 * unregister from a fidelity card plan
	 */
	public void unregisterCard();
	

	
	/**
	 * access the history of orders
	 *
	 * @return the history
	 */
	History getHistory();
	
	/**
	 * access the points acquired with a fidelity program
	 *
	 * @return the points
	 */
	double getPoints();
	
	/**
	 * Give consensus to be notified of special offers.
	 */
	// restaurant
	public void giveConsensusBeNotifiedSpecialOffers();
	
	/**
	 * Removes the consensus to be notified of special offers.
	 */
	public void removeConsensusBeNotifiedSpecialOffers();

}
