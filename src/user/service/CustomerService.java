/*
 * 
 */
package user.service;

import java.text.ParseException;

import exceptions.NameAlreadyExistsException;
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
public interface CustomerService extends UserService{

	/**
	 * Creates the order.
	 *
	 * @param restaurantName the restaurant name
	 * @param orderName the order name
	 * @throws NameAlreadyExistsException 
	 * @throws NameNotFoundException 
	 */
	void createOrder(String restaurantName, String orderName) throws NameAlreadyExistsException, NameNotFoundException;

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
	 * register to a fidelity card plan. Possible entries "Point" for pointcard, "Lottery" for lottery card
	 *
	 * @param cardType the card type
	 * @throws NameNotFoundException 
	 */
	public void registerCard(String cardType) throws NameNotFoundException;
	
	/**
	 * unregister from a fidelity card plan.
	 */
	public void unregisterCard();
	
	
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
