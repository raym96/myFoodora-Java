package model.restaurant;

import static org.junit.Assert.*;

import org.junit.Test;

public class Starter extends Dish {

	public Starter(String itemName, String itemType, double price) {
		super(itemName, itemType, price);
		// TODO Auto-generated constructor stub
	}

	public Starter makeCopy(){
		Starter s = new Starter(this.dishName,this.dishType,this.price);
		
		return s;
		
	}
}
