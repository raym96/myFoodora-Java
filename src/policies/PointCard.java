package model.customer;

import model.users.Customer;
import model.users.MyFoodora;

public class PointCard extends FidelityCard {
	private double balance;
	
	
	public PointCard(Customer c){
		super(c);
	}
	
	
	@Override
	public void pay() {
		// TODO Auto-generated method stub
		double amount = customer.getShoppingCart().getPrice();
		if (balance>=100){
			System.out.println("Your point balance reached 100, you now receive a 10% discount on this order.");
			balance-=100;
			customer.getShoppingCart().setPrice(amount*0.9);
		}
		
		customer.update("paid for a total amount of = " + amount );
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + amount);
		
		balance+=Math.floor(amount*100)/1000;
		System.out.println("you gained " + amount+" points on your point card for this order.");
		
		if (balance>=100){
			System.out.println("Your point balance reached 100, you will receive a 10% discount on the next order.");
		}
	}



	public double getPoints(){
		return balance;
	}
}
