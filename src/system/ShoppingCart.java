package system;

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<Order> orders;
	
	private double totalprice;
	
	public ShoppingCart() {
		super();
		this.orders = new ArrayList<Order>();
	}

	public void addOrder(Order o){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		totalprice += o.accept(visitor);
		o.setPrice(o.accept(visitor)); //the price of the order is updated
		orders.add(o);

	}
	
	public void removeOrder(Order o){
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		totalprice -= o.accept(visitor);
		orders.remove(o);
	}
	
	
	public ArrayList<Order> getOrders(){
		return orders;
	}

	public double getTotalPrice(){
		return totalprice;
	}
	
	public void setTotalPrice(double price){
		this.totalprice = price;
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
		return "ShoppingCart : \n" + str +"\nTotal price is: "+getTotalPrice();
	}
	
	
}
