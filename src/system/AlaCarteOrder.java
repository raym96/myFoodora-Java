package system;

import restaurant.Dish;
import user.Customer;
import user.Restaurant;

public class AlaCarteOrder extends Order {
	private Dish dish;
	
	public AlaCarteOrder(Customer customer, Restaurant restaurant, Dish dish) {
		super(customer,restaurant);
		this.dish = dish;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public Dish getDish(){
		return dish;
	}

	public String getName() {
		return this.dish.getDishName();
	}
	

	@Override
	public double accept(ShoppingCartVisitor visitor) {
		// TODO Auto-generated method stub
		this.price = visitor.visit(this);
		return price;
	}
	
}
