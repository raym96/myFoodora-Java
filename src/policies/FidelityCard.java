package policies;

import user.Customer;
import user.User;

public abstract class FidelityCard {
	protected Customer customer;
	
	public FidelityCard(Customer c){
		customer = c;
	}
	
	public abstract void pay();
}
