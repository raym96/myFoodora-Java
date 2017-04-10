/*
 * 
 */
package restaurant;

import system.ShoppingCartVisitor;

/**
 * The Class Dessert.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Dessert extends Dish {

	/**
	 * Instantiates a new dessert.
	 *
	 * @param itemName the item name
	 * @param itemType the item type
	 * @param price the price
	 */
	public Dessert(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see restaurant.Dish#makeCopy()
	 */
	public Dessert makeCopy(){
		return new Dessert(this.dishName,this.dishType,this.price);
	}

	/* (non-Javadoc)
	 * @see restaurant.Item#accept(system.ShoppingCartVisitor)
	 */
	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
}
