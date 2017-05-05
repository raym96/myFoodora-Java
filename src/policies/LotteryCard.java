/*
 * 
 */
package policies;

import java.util.Random;

import system.ConcreteShoppingCartVisitor;
import system.Order;
import user.model.Customer;
import user.model.MyFoodora;

/**
 * The Class LotteryCard.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class LotteryCard extends FidelityCard {
	
	/** The probability. */
	private static double probability = 0.1;

	/**
	 * Instantiates a new lottery card.
	 *
	 * @param c the c
	 */
	public LotteryCard(Customer c){
		super(c);
	}
	
	
	/* (non-Javadoc)
	 * @see policies.FidelityCard#pay()
	 */
	@Override
	public void pay(Order order){
		double price = order.accept(new ConcreteShoppingCartVisitor());
		if (Math.random()<probability){
			System.out.println("Congratulations ! You got the meal for free !");
		}
		customer.getShoppingCart().removeOrder(order);
	}
	
	
	/**
	 * Gets the probability.
	 *
	 * @return the probability
	 */
	public static double getProbability() {
		return probability;
	}

	/**
	 * Sets the probability.
	 *
	 * @param probability the new probability
	 */
	public static void setProbability(double probability) {
		LotteryCard.probability = probability;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "point fidelity card";
	}
	
}
