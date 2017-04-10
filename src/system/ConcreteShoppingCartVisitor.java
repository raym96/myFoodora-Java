package system;

import restaurant.*;
import user.model.Restaurant;

/**
 * The Class ConcreteShoppingCartVisitor.
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
	public double visit(Meal meal){
		double price = meal.getRawprice();
		double discount_factor = 0;
		if (meal.isSpecial()){
			discount_factor = meal.getRestaurant().getSpecial_discount_factor();
		}
		else{
			discount_factor = meal.getRestaurant().getGeneric_discount_factor();
		}
		price*=(1.0 - discount_factor);
		return Math.floor(price*100)/100;
		
	}
	

	/* (non-Javadoc)
	 * @see system.ShoppingCartVisitor#visit(system.Order)
	 */
	@Override
	public double visit(Dish dish) {
		// TODO Auto-generated method stub
		return dish.getPrice();
	}
	
	/* (non-Javadoc)
	 * @see system.ShoppingCartVisitor#visit(system.Order)
	 */
	@Override
	public double visit(Order order){
		double price = 0;
		for (Item item:order.getItems()){
			price += item.accept(new ConcreteShoppingCartVisitor());
		}
		return Math.floor(price*100)/100;
	}
	
}
