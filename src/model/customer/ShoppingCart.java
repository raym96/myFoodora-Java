package model.customer;

import java.util.ArrayList;

import model.restaurant.Order;

public class ShoppingCart {
	private ArrayList<Order> orders;
	
	public void addOrder(Order o){
		orders.add(o);
	}
	
	public void removeOrder(Order o){
		orders.remove(o);
	}

	public double calculatePrice(){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double sum =0;
		for (Order order:orders){
			sum = sum + order.accept(visitor);
		}
		return sum;
	}
	
	
	public ArrayList<Order> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		String str = "";
		for (Order order:orders){
			str += order.getName() + ", " + order.getRestaurant()+"\n";
		}
		return "ShoppingCart : \n" + orders;
	}
	
	
}
