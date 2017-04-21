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
	private static double probability = 0.01;

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
		customer.update("paid for a total amount of = " + price +" for FREE !");
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + customer.getShoppingCart().getTotalPrice());
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
	
	
}
