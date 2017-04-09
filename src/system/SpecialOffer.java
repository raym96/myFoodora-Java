/*
 * 
 */
package system;

import restaurant.*;
import user.model.Restaurant;

// TODO: Auto-generated Javadoc
/**
 * The Class SpecialOffer.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SpecialOffer {
	
	/** The meal. */
	private Meal meal;
	
	/** The restaurant. */
	private Restaurant restaurant;
	
	/**
	 * Instantiates a new special offer.
	 *
	 * @param restaurant the restaurant
	 * @param meal the meal
	 */
	public SpecialOffer(Restaurant restaurant, Meal meal) {
		super();
		this.meal = meal;
		this.restaurant = restaurant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Special "+ meal + " proposed by "+ restaurant;
	}
	
}
