/*
 * 
 */
package restaurant;


/**
 * The Class MainDish.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public final class MainDish extends Dish {

	/**
	 * Instantiates a new main dish.
	 *
	 * @param dishName the dish name
	 * @param dishType the dish type
	 * @param price the price
	 */
	public MainDish(String dishName, String dishType, double price) {
		super(dishName, dishType, price);
	}

	/* (non-Javadoc)
	 * @see restaurant.Dish#makeCopy()
	 */
	public MainDish makeCopy(){
		return new MainDish(this.dishName,this.dishType,this.price);
	}
	
}
