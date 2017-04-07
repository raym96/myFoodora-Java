package model.customer;

import java.util.Random;

import model.users.Customer;
import model.users.MyFoodora;

public class LotteryCard extends FidelityCard {
	private static double probability = 0.01;

	public LotteryCard(Customer c){
		super(c);
	}
	
	
	@Override
	public void pay(){
		if (Math.random()<probability){
			System.out.println("Congratulations ! You got the meal for free !");
		}
		customer.update("paid for a total amount of = " + customer.getShoppingCart().getPrice() +" for FREE !");
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + customer.getShoppingCart().getPrice());
		
	}
	
	
	public static double getProbability() {
		return probability;
	}

	public static void setProbability(double probability) {
		LotteryCard.probability = probability;
	}
	
	
}
