package model.restaurant;

public final class MainDish extends Dish {

	public MainDish(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
	}

	public MainDish makeCopy(){
		return new MainDish(this.dishName,this.dishType,this.price);
	}
	
}
