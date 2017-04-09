package policies;

import user.model.Customer;
import user.model.User;

public abstract class FidelityCard {
	protected Customer customer;
	
	public FidelityCard(Customer c){
		customer = c;
	}
	
	public abstract void pay();
}
