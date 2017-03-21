package model.restaurant;

public class Starter extends Dish {

	public Starter(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
		// TODO Auto-generated constructor stub
	}

	public Starter makeCopy(){
		return new Starter(this.dishName,this.dishType,this.price);
	}
}
