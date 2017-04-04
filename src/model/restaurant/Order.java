package model.restaurant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import model.customer.ShoppingCartVisitor;
import model.users.AddressPoint;
import model.users.Courier;
import model.users.Customer;
import model.users.Restaurant;

//An item to be visited (Visitable) by a ShoppingCartVisitor (Visitor) to determine the price
public abstract class Order {
	protected Date date;
	protected String orderID;
	protected Restaurant restaurant;
	protected Customer customer;
	
	//relative to delivery
	private Courier courier;
	private boolean assigned;
	
	protected double price;
	
	public Order(Customer customer,Restaurant restaurant){
		this.customer = customer;
		this.restaurant = restaurant;
		date = new Date();
		orderID = UUID.randomUUID().toString();
		
		//initialy no courier is assigned
		courier = null;
		assigned = false;
	}
	
	//Only for order generation with a date
	public Order(Customer customer,Restaurant restaurant, Date date){
		this.customer = customer;
		this.restaurant = restaurant;
		orderID = UUID.randomUUID().toString();
		this.date = date;
		
		//initialy no courier is assigned
		courier = null;
		assigned = false;
	}
	
	public abstract double accept(ShoppingCartVisitor visitor);	
	
	public abstract String getName(); //depends on whether it is a meal or a-la-carte
	
	//getters and setters
	public Customer getCustomer(){
		return customer;
	}
	public Restaurant getRestaurant(){
		return restaurant;
	}
	
	public String getOrderID(){
		return orderID;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	//relative to delivery
	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public AddressPoint getDestination() {
		return getCustomer().getAddress();
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	
	@Override
	public String toString(){
		String str = "";
		if (this instanceof SpecialMealOrder){
			str+= "[Special-meal] <";
			str+= ((SpecialMealOrder)this).getName();
		}
		else if (this instanceof StandardMealOrder){
			str+="[Meal] <";
			str+= ((StandardMealOrder)this).getName();
		}
		else if (this instanceof AlaCarteOrder){
			str+="[A-la-carte] <";
			str+= ((AlaCarteOrder)this).getName();
		}
		str+="> ORDERED BY <"+customer.getUsername()+ "> AT <" +restaurant.getUsername()+ "> DELIVERED BY <" + courier.getUsername()+"> ON <"+ date+">";
		return str;
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
	
}

