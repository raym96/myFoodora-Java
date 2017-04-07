package model.customer;

import model.users.Customer;
import model.users.User;

public abstract class FidelityCard {
	protected Customer customer;
	
	public FidelityCard(Customer c){
		customer = c;
	}
	
	public abstract void pay();
}
