/*
 * 
 */
package policies;

import java.util.Random;

import user.model.Customer;
import user.model.MyFoodora;

// TODO: Auto-generated Javadoc
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
	public void pay(){
		if (Math.random()<probability){
			System.out.println("Congratulations ! You got the meal for free !");
		}
		customer.update("paid for a total amount of = " + customer.getShoppingCart().getTotalPrice() +" for FREE !");
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + customer.getShoppingCart().getTotalPrice());
		
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
