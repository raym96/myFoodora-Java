/*
 * 
 */
package system;

import restaurant.Dish;
import user.model.Customer;
import user.model.Restaurant;

// TODO: Auto-generated Javadoc
/**
 * The Class AlaCarteOrder.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class AlaCarteOrder extends Order {
	
	/** The dish. */
	private Dish dish;
	
	/**
	 * Instantiates a new ala carte order.
	 *
	 * @param customer the customer
	 * @param restaurant the restaurant
	 * @param dish the dish
	 */
	public AlaCarteOrder(Customer customer, Restaurant restaurant, Dish dish) {
		super(customer,restaurant);
		this.dish = dish;
	}

	/* (non-Javadoc)
	 * @see system.Order#getRestaurant()
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	/**
	 * Gets the dish.
	 *
	 * @return the dish
	 */
	public Dish getDish(){
		return dish;
	}

	/* (non-Javadoc)
	 * @see system.Order#getName()
	 */
	public String getName() {
		return this.dish.getDishName();
	}
	

	/* (non-Javadoc)
	 * @see system.Order#accept(system.ShoppingCartVisitor)
	 */
	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		this.price = visitor.visit(this);
		return price;
	}
	
}
