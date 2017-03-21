package system;

import model.restaurant.*;
import model.user.Restaurant;

public class SpecialOffer {
	private Meal meal;
	private Restaurant restaurant;
	
	public SpecialOffer(Restaurant restaurant, Meal meal) {
		super();
		this.meal = meal;
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Special "+ meal + " proposed by "+ restaurant;
	}
	
}
