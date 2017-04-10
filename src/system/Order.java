package system;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import restaurant.*;
import user.model.Courier;
import user.model.Customer;
import user.model.Restaurant;
import user.model.User;

/**
 * The Class Order.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
//An item to be visited (Visitable) by a ShoppingCartVisitor (Visitor) to determine the price
public class Order {
	
	/** The date. */
	protected Date date;
	
	/** The order ID. */
	protected String orderID;
	
	static private int ordercount = 1244;
	
	/** The restaurant. */
	protected Restaurant restaurant;
	
	/** The customer. */
	protected Customer customer;
	
	/** The courier. */
	//relative to delivery
	private Courier courier;
	
	/** whether the order is assigned. */
	private boolean assigned;
	
	private ArrayList<Item> items;
	/**
	 * Instantiates a new order.
	 *
	 * @param customer the customer
	 * @param restaurant the restaurant
	 */
	public Order(Customer customer,Restaurant restaurant){
		this.customer = customer;
		this.restaurant = restaurant;
		this.items = new ArrayList<Item>();
		date = new Date();
		orderID = String.valueOf(ordercount);
		ordercount++;
		
		//initialy no courier is assigned
		courier = new Courier("default","default","NOT ASSIGNED",new AddressPoint(0,0),"default");
		assigned = false;
	}
	
	/**
	 * Instantiates a new order.
	 *
	 * @param customer the customer
	 * @param restaurant the restaurant
	 * @param date the date
	 */
	//Only for old order registration with a past date (history for example)
	public Order(Customer customer,Restaurant restaurant, Date date){
		this.customer = customer;
		this.restaurant = restaurant;
		this.items = new ArrayList<Item>();
		orderID = String.valueOf(ordercount);
		ordercount++;
		this.date = date;
		
		//initialy no courier is assigned
		courier = new Courier("default","default","NOT ASSIGNED",new AddressPoint(0,0),"default");
		assigned = false;
	}
	
	
	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer(){
		return customer;
	}
	
	/**
	 * Gets the restaurant.
	 *
	 * @return the restaurant
	 */
	public Restaurant getRestaurant(){
		return restaurant;
	}
	
	/**
	 * Gets the order ID.
	 *
	 * @return the order ID
	 */
	public String getOrderID(){
		return orderID;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}
	
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date){
		this.date = date;
	}
	
	
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item){
		items.add(item);
	}

	/**
	 * Checks if is assigned.
	 *
	 * @return true, if is assigned
	 */
	//relative to delivery
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * Sets the assigned.
	 *
	 * @param assigned the new assigned
	 */
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public AddressPoint getDestination() {
		return getCustomer().getAddress();
	}

	/**
	 * Gets the courier.
	 *
	 * @return the courier
	 */
	public Courier getCourier() {
		return courier;
	}

	/**
	 * Sets the courier.
	 *
	 * @param courier the new courier
	 */
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String str = "[Order ID"+orderID+"]\n";
		str+= "["+accept(new ConcreteShoppingCartVisitor())+"€] \n";
		for (Item item:items){
			if (item instanceof Dish){
				str+="A-la-carte <";
				str+= ((Dish)item).getDishName()+">\n";
			}
			if (item instanceof Meal){
				str+="Meal <";
				str+= ((Meal)item).getName()+">\n";
			}
		}
		str+="BY <"+customer.getUsername()+ "> "+customer.getFullName()+"\n";
		str+="AT <" +restaurant.getUsername()+"> "+restaurant.getName()+"\n";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		str+="ON "+ sdf.format(date);
		
		if (assigned){
			str+= "> DELIVERED BY <" + courier.getUsername()+">";
		}
		return str;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	public double accept(ShoppingCartVisitor visitor){
		return visitor.visit(this);
	}
}

