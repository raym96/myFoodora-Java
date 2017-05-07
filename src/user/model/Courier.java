/*
 * 
 */
package user.model;

import java.util.ArrayList;

import restaurant.Dish;
import system.AddressPoint;
import system.ConcreteSpecialOfferBoard;
import system.Message;
import system.Order;
import system.SpecialOfferBoard;
import user.service.CourierService;
import user.service.impl.CourierServiceImpl;
import user.view.CourierView;


/**
 * The Class Courier.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Courier extends User{

	/** The surname. */
	private String lastname;
	
	/** The name. */
	private String firstname;
	
	/** The position. */
	private AddressPoint position;
	
	/** The phone. */
	private String phone;
	
	/** The count. */
	private int count;
	
	/** The on duty. */
	private boolean on_duty;
	
	/** The delivered orders. */
	private ArrayList<Order> deliveredOrders;
	
	/** The waiting orders. */
	private ArrayList<Order> waitingOrders; //unanswered orders, must confirm/decline the mission
	
	
	/** The courier service. */
	private CourierService courierService;
	
	/**
	 * Instantiates a new courier.
	 *
	 * @param firstname the firstname
	 * @param lastname the surname
	 * @param username the username
	 * @param position the position
	 * @param password the password
	 */
	public Courier(String firstname, String lastname, String username, AddressPoint position, String password) {
		super(username,password);
		this.firstname = firstname;
		this.lastname = lastname;
		this.position = position;
		this.on_duty = true;
		this.deliveredOrders = new ArrayList<Order>();
		this.waitingOrders = new ArrayList<Order>();
		
		count = 0;
		this.courierService = new CourierServiceImpl(this);
	}
	

	/**
	 * Gets the courier service.
	 *
	 * @return the courier service
	 */
	public CourierService getService() {
		return courierService;
	}

	
	/**
	 * Gets the waiting orders.
	 *
	 * @return the waiting orders
	 */
	public ArrayList<Order> getWaitingOrders(){
		return waitingOrders;
	}
	
	
	/**
	 * Gets the waiting order.
	 *
	 * @param orderName the order name
	 * @return the waiting order
	 */
	public Order getWaitingOrder(String orderName){
		Order order = null;
		for (Order o : this.getWaitingOrders()){
			if (o.getName().equalsIgnoreCase(orderName)){
				order = o;
			}
		}
		return order;
	}
	
	/**
	 * Gets the deliveried order.
	 *
	 * @param orderId the order id
	 * @return the deliveried order
	 */
	public Order getDeliveredOrderById(String orderId){
		Order order = null;
		for (Order o : this.getDeliveredOrders()){
			if (o.getOrderID().equalsIgnoreCase(orderId)){
				order = o;
			}
		}
		return order;
	}
	
	/**
	 * Gets the waiting order.
	 *
	 * @param orderId the order id
	 * @return the waiting order
	 */
	public Order getWaitingOrderById(String orderId){
		Order order = null;
		for (Order o : this.getWaitingOrders()){
			if (o.getOrderID().equalsIgnoreCase(orderId)){
				order = o;
			}
		}
		return order;
	}
	
	/**
	 * Adds the waiting order.
	 *
	 * @param order the order
	 */
	public void addWaitingOrder(Order order) {
		waitingOrders.add(order);
	}

	/**
	 * Accept waiting order.
	 *
	 * @param orderName the order name
	 */
	public void acceptWaitingOrder(String orderName){
		Order order = getWaitingOrder(orderName);
		order.setCourier(this); //the courier is assigned to the delivery-task
		order.setAssigned(true);
		waitingOrders.remove(order);
		deliveredOrders.add(order);
		this.setCount(count+1);
	}


	/**
	 * Refuse waiting order.
	 *
	 * @param orderName the order name
	 */
	public void refuseWaitingOrder(String orderName){
		Order order = getWaitingOrder(orderName);
		waitingOrders.remove(order);
	}
	
	/**
	 * Gets the delivered orders.
	 *
	 * @return the delivered orders
	 */
	public ArrayList<Order> getDeliveredOrders(){
		return deliveredOrders;
	}
	
	


	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public AddressPoint getPosition(){
		return position;
	}
	
	
	/**
	 * Sets the position.
	 *
	 * @param a the new position
	 */
	public void setPosition(AddressPoint a){
		position = a;
	}


	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount(){
		return count;
	}

	/**
	 * Checks if is on duty.
	 *
	 * @return true, if is on duty
	 */
	public boolean isOn_duty() {
		return on_duty;
	}


	/**
	 * Sets the on duty.
	 *
	 * @param on_duty the new on duty
	 */
	public void setOn_duty(boolean on_duty) {
		this.on_duty = on_duty;
	}

	/**
	 * Sets the count.
	 *
	 * @param i the new count
	 */
	public void setCount(int i){
		count = i;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName(){
		return firstname+" "+lastname.toUpperCase();
	}
	
	/**
	 * Sets the full name.
	 *
	 * @param name the first name and last name
	 */
	public void setFullName(String name) {
		// TODO Auto-generated method stub
		this.firstname = name.split(" ")[0];
		this.lastname = name.split(" ")[1];
	}


	/* (non-Javadoc)
	 * @see user.model.User#toString()
	 */
	@Override
	public String toString() {
		return  "<Courier> "+username+"; fullname = "+firstname+" "+lastname.toUpperCase()+"; position="+position;
	}
	
	

}
