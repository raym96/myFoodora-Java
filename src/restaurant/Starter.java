/*
 * 
 */
package restaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import system.ShoppingCartVisitor;


/**
 * The Class Starter.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Starter extends Dish {

	/**
	 * Instantiates a new starter.
	 *
	 * @param itemName the item name
	 * @param itemType the item type
	 * @param price the price
	 */
	public Starter(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see restaurant.Dish#makeCopy()
	 */
	public Starter makeCopy(){
		Starter s = new Starter(this.dishName,this.dishType,this.price);
		
		return s;
		
	}

	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
}
