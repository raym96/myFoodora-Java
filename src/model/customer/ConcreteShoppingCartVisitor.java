package model.customer;

import model.restaurant.*;
import model.user.Restaurant;

public class ConcreteShoppingCartVisitor implements ShoppingCartVisitor {
	private Restaurant restaurant;
	
	public double visit(SpecialMealOrder mealorder){
		double price = 0;
		for (Dish dish:mealorder.getMeal().getDishes()){
			price += dish.getPrice();
		}
		double discount_factor = mealorder.getRestaurant().getSpecial_discount_factor();
		return price*=(1.0 - discount_factor);
		
	}
	@Override
	public double visit(StandardMealOrder mealorder) {
		// TODO Auto-generated method stub
		double price = 0;
		double discount_factor = 0;
		for (Dish dish:mealorder.getMeal().getDishes()){
			price += dish.getPrice();
		}
		discount_factor =  mealorder.getRestaurant().getGeneric_discount_factor();
		return price*=(1.0 - discount_factor);
	}
	

	@Override
	public double visit(AlaCarteOrder alacarteorder) {
		// TODO Auto-generated method stub
		return alacarteorder.getDish().getPrice();
	}
	
}
