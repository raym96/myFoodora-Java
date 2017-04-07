package model.restaurant;

public class Dessert extends Dish {

	public Dessert(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
		// TODO Auto-generated constructor stub
	}
	
	public Dessert makeCopy(){
		return new Dessert(this.dishName,this.dishType,this.price);
	}
	
}
