/*
 * 
 */
package policies;

import user.model.Customer;
import user.model.User;

/**
 * The Class FidelityCard.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class FidelityCard {
	
	/** The customer. */
	protected Customer customer;
	
	/**
	 * Instantiates a new fidelity card.
	 *
	 * @param c the c
	 */
	public FidelityCard(Customer c){
		customer = c;
	}
	
	/**
	 * Pay.
	 */
	public abstract void pay();
}
