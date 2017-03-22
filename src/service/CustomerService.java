package service;

import model.restaurant.AlaCarteOrder;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import model.user.Restaurant;

public interface CustomerService {

	// 1. place orders: this includes choosing a selection of items a-la-carte or one or more
	// meals offered by a given restaurant, and paying the total price for the composed
	// order.
	public void addSpecialMealOrder(Restaurant r, String mealType, String mealName);
	public void addStandardMealOrder(Restaurant r, String mealType, String mealName);
	public void addAlaCarteOrder(Restaurant r, String dishName);
	public void clearShoppingCart();
	public double calculatePrice();
	public void pay();	
	
	// 2. register/unregister to/from a fidelity card plan
	public void registerCard(String cardType);
	public void unregisterCard();
	
	// 3. access the information related to their account: including history of orders, and
	// points acquired with a fidelity program
	
	// to be completed
	
	// 4. give/remove consensus to be notified whenever a new special offer is set by any
	// restaurant
	
	// to be completed
}
