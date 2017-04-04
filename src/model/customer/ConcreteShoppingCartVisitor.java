package model.customer;

import model.restaurant.*;
import model.users.Restaurant;

public class ConcreteShoppingCartVisitor implements ShoppingCartVisitor {
	private Restaurant restaurant;
	
	public double visit(SpecialMealOrder mealorder){
		double price = mealorder.getMeal().getRawprice();
		double discount_factor = mealorder.getRestaurant().getSpecial_discount_factor();
		price*=(1.0 - discount_factor);
		return Math.floor(price*100)/100;
		
	}
	@Override
	public double visit(StandardMealOrder mealorder) {
		// TODO Auto-generated method stub
		double price = mealorder.getMeal().getRawprice();
		double discount_factor =  mealorder.getRestaurant().getGeneric_discount_factor();
		price*=(1.0 - discount_factor);
		return Math.floor(price*100)/100;
	}
	

	@Override
	public double visit(AlaCarteOrder alacarteorder) {
		// TODO Auto-generated method stub
		return alacarteorder.getDish().getPrice();
	}
	
}
