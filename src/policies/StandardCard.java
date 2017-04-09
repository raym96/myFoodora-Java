/*
 * 
 */
package policies;

import user.model.Customer;
import user.model.MyFoodora;

// TODO: Auto-generated Javadoc
/**
 * The Class StandardCard.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class StandardCard extends FidelityCard {

	/**
	 * Instantiates a new standard card.
	 *
	 * @param c the c
	 */
	public StandardCard(Customer c){
		super(c);
	}
	
	
	/* (non-Javadoc)
	 * @see policies.FidelityCard#pay()
	 */
	@Override
	public void pay() {
		// TODO Auto-generated method stub
		double amount = customer.getShoppingCart().getTotalPrice();
		customer.update("paid for a total amount of = " + amount );
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + amount);
	}
	
}
