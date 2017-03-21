package service;

import model.restaurant.AlaCarteOrder;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import model.user.Restaurant;

public interface CustomerService {

	public void clearShoppingCart();
	public double calculatePrice();
	public void pay();
	public void registerCard(String cardType);
	public void unregisterCard();
	public void addSpecialMealOrder(Restaurant r, String mealType, String mealName);
	
	public void addStandardMealOrder(Restaurant r, String mealType, String mealName);

	public void addAlaCarteOrder(Restaurant r, String dishName);
}
