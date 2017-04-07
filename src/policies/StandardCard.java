package model.customer;

import model.users.Customer;
import model.users.MyFoodora;

public class StandardCard extends FidelityCard {

	public StandardCard(Customer c){
		super(c);
	}
	
	
	@Override
	public void pay() {
		// TODO Auto-generated method stub
		double amount = customer.getShoppingCart().getPrice();
		customer.update("paid for a total amount of = " + amount );
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + amount);
	}
	
}
