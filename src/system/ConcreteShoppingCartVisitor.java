/*
 * 
 */
package system;

import restaurant.*;
import user.model.Restaurant;

// TODO: Auto-generated Javadoc
/**
 * The Class ConcreteShoppingCartVisitor.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ConcreteShoppingCartVisitor implements ShoppingCartVisitor {
	
	/** The restaurant. */
	private Restaurant restaurant;
	
	/**
	 * Instantiates a new concrete shopping cart visitor.
	 */
	public ConcreteShoppingCartVisitor() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see system.ShoppingCartVisitor#visit(system.SpecialMealOrder)
	 */
	public double visit(SpecialMealOrder mealorder){
		double price = mealorder.getMeal().getRawprice();
		double discount_factor = mealorder.getRestaurant().getSpecial_discount_factor();
		price*=(1.0 - discount_factor);
		return Math.floor(price*100)/100;
		
	}
	
	/* (non-Javadoc)
	 * @see system.ShoppingCartVisitor#visit(system.StandardMealOrder)
	 */
	@Override
	public double visit(StandardMealOrder mealorder) {
		// TODO Auto-generated method stub
		double price = mealorder.getMeal().getRawprice();
		double discount_factor =  mealorder.getRestaurant().getGeneric_discount_factor();
		price*=(1.0 - discount_factor);
		return Math.floor(price*100)/100;
	}
	

	/* (non-Javadoc)
	 * @see system.ShoppingCartVisitor#visit(system.AlaCarteOrder)
	 */
	@Override
	public double visit(AlaCarteOrder alacarteorder) {
		// TODO Auto-generated method stub
		return alacarteorder.getDish().getPrice();
	}
	
}
