/*
 * 
 */
package policies;

import user.model.Customer;
import user.model.MyFoodora;


/**
 * The Class PointCard.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class PointCard extends FidelityCard {
	
	/** The balance. */
	private double balance;
	
	
	/**
	 * Instantiates a new point card.
	 *
	 * @param c the customer
	 */
	public PointCard(Customer c){
		super(c);
	}
	
	
	/* (non-Javadoc)
	 * @see policies.FidelityCard#pay()
	 */
	@Override
	public void pay() {
		// TODO Auto-generated method stub
		double amount = customer.getShoppingCart().getTotalPrice();
		if (balance>=100){
			System.out.println("Your point balance reached 100, you now receive a 10% discount on this order.");
			balance-=100;
			customer.getShoppingCart().setTotalPrice(amount*0.9);
		}
		
		customer.update("paid for a total amount of = " + amount );
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + amount);
		
		balance+=Math.floor(amount*100)/1000;
		System.out.println("you gained " + balance+" points on your point card for this order.");
		
		if (balance>=100){
			System.out.println("Your point balance reached 100, you will receive a 10% discount on the next order.");
		}
	}



	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public double getPoints(){
		return balance;
	}
}
