/*
 * 
 */
package policies;

import system.ConcreteShoppingCartVisitor;
import system.Order;
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
	public void pay(Order order) {
		// TODO Auto-generated method stub
		double price = order.accept(new ConcreteShoppingCartVisitor());
		if (balance>=100){
			System.out.println("Your point balance reached 100, you now receive a 10% discount on this order.");
			balance-=100;
			price *= 0.9;
		}
		
		customer.getShoppingCart().removeOrder(order);
		
		balance+=Math.floor((price/10)*100)/100;
		System.out.println("you earned " + Math.floor((price/10)*100)/100+" points on your point card for this order.");
		
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "point fidelity card";
	}
	
}
