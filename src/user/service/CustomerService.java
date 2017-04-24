/*
 * 
 */
package user.service;

import java.text.ParseException;

import exceptions.NameNotFoundException;
import exceptions.NameNotFoundException;
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
	 * Creates the order.
	 *
	 * @param restaurantName the restaurant name
	 * @param orderName the order name
	 */
	void createOrder(String restaurantName, String orderName);

	/**
	 * Adds the item 2 order.
	 *
	 * @param orderName the order name
	 * @param itemName the item name
	 * @throws NameNotFoundException the name not found exception
	 */
	void addItem2Order(String orderName, String itemName) throws NameNotFoundException;

	/**
	 * end the order and pay for it at a given date
	 *
	 * @param orderName the order name
	 * @param date the date
	 * @throws NameNotFoundException the name not found exception
	 * @throws ParseException the parse exception
	 */
	void endOrder(String orderName, String date) throws NameNotFoundException, ParseException;
	
	/**
	 * register to a fidelity card plan.
	 *
	 * @param cardType the card type
	 */
	public void registerCard(String cardType);
	
	/**
	 * unregister from a fidelity card plan.
	 */
	public void unregisterCard();
	

	
	/**
	 * access the history of orders.
	 *
	 * @return the history
	 */
	void getHistory();
	
	/**
	 * access the points acquired with a fidelity program.
	 *
	 * @return the points
	 */
	void getPoints();
	
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
