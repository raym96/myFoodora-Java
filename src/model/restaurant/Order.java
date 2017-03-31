package model.restaurant;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import model.customer.ShoppingCartVisitor;
import model.user.Customer;
import model.user.Restaurant;

//An item to be visited (Visitable) by a ShoppingCartVisitor (Visitor) to determine the price
public abstract class Order {
	protected Date date;
	protected String orderID;
	protected Restaurant restaurant;
	protected Customer customer;
	
	protected double price;
	
	public Order(Customer customer,Restaurant restaurant){
		this.customer = customer;
		this.restaurant = restaurant;
		date = new Date();
		orderID = UUID.randomUUID().toString();
	}
	
	public abstract String getName();
	
	public Customer getCustomer(){
		return customer;
	}
	public abstract Restaurant getRestaurant();
	
	public abstract double accept(ShoppingCartVisitor visitor);
	
	public String getOrderID(){
		return orderID;
	}
	
	public Date getDate(){
		return date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "["+customer+" "+restaurant+" OrderID "+orderID+ " at "+date+"]";
	}
	
}

