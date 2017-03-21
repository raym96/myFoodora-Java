package service;

import model.user.Restaurant;

public interface CustomerService {

	public void clearShoppingCart();
	public double calculatePrice();
	public void pay();
	public void registerCard(String cardType);
	public void unregisterCard();
}
