/*
 * 
 */
package policies;

import system.ConcreteShoppingCartVisitor;
import system.Order;
import user.model.Customer;
import user.model.MyFoodora;


/**
 * The Class StandardCard.
 * 
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
	public void pay(Order order) {
		// TODO Auto-generated method stub
		customer.getShoppingCart().removeOrder(order);
	}
	
}
