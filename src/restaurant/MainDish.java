/*
 * 
 */
package restaurant;

// TODO: Auto-generated Javadoc
/**
 * The Class MainDish.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public final class MainDish extends Dish {

	/**
	 * Instantiates a new main dish.
	 *
	 * @param itemName the item name
	 * @param itemType the item type
	 * @param price the price
	 */
	public MainDish(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
	}

	/* (non-Javadoc)
	 * @see restaurant.Dish#makeCopy()
	 */
	public MainDish makeCopy(){
		return new MainDish(this.dishName,this.dishType,this.price);
	}
	
}
