package system;

import java.util.ArrayList;

import restaurant.Meal;

public interface SpecialOfferObserver {
	public void addSpecialOffer(Meal meal); //add a new special offer
	public void updateSpecialOffer(ArrayList<Meal> specialoffers); //update the special offers by a list
}
