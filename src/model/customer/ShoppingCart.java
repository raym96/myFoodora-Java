package model.customer;

import java.util.ArrayList;

import model.restaurant.Order;

public class ShoppingCart {
	private ArrayList<Order> orders;
	private double price;
	
	public ShoppingCart() {
		super();
		this.orders = new ArrayList<Order>();
	}

	public void addOrder(Order o){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		price += o.accept(visitor);
		orders.add(o);

	}
	
	public void removeOrder(Order o){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		price -= o.accept(visitor);
		orders.remove(o);
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}

	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public boolean contains(Order order){
		return orders.contains(order);
	}
	
	public void clear(){
		this.orders = new ArrayList<Order>();
	}
	
	
	public int size(){
		return orders.size();
	}

	@Override
	public String toString() {
		String str = "";
		for (Order order:orders){
			str += order+"\n";
		}
		return "ShoppingCart : \n" + str;
	}
	
	
}
