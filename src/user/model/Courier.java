/*
 * 
 */
package user.model;

import java.util.ArrayList;

import exceptions.OrderNotFoundException;
import system.AddressPoint;
import system.ConcreteSpecialOfferBoard;
import system.Message;
import system.Observable;
import system.Order;
import system.SpecialOfferBoard;
import user.service.CourierService;
import user.service.impl.CourierServiceImpl;


/**
 * The Class Courier.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Courier extends User{

	/** The surname. */
	private String surname;
	
	/** The name. */
	private String name;
	
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
	 * @param name the name
	 * @param surname the surname
	 * @param username the username
	 * @param position the position
	 * @param password the password
	 */
	public Courier(String name, String surname, String username, AddressPoint position, String password) {
		super(username,password);
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.on_duty = true;
		this.deliveredOrders = new ArrayList<Order>();
		this.waitingOrders = new ArrayList<Order>();
		
		count = 0;
		this.courierService = new CourierServiceImpl(this);
	}
	
	/**
	 * Sets the waiting orders.
	 *
	 * @param waitingOrders the new waiting orders
	 */
	public void setWaitingOrders(ArrayList<Order> waitingOrders) {
		this.waitingOrders = waitingOrders;
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
	 * Adds the waiting order.
	 *
	 * @param order the order
	 */
	public void addWaitingOrder(Order order) {
		waitingOrders.add(order);
	}

	/**
	 * Refuse waiting order.
	 *
	 * @param order the order
	 * @throws OrderNotFoundException the order not found exception
	 */
	public void refuseWaitingOrder(Order order) throws OrderNotFoundException{
		if (!(waitingOrders.contains(order))){
			throw new OrderNotFoundException(order.getName());
		}
		waitingOrders.remove(order);
	}
	
	/**
	 * Accept waiting order.
	 *
	 * @param order the order
	 * @throws OrderNotFoundException the order not found exception
	 */
	public void acceptWaitingOrder(Order order) throws OrderNotFoundException{
		if (!(waitingOrders.contains(order))){
			throw new OrderNotFoundException(order.getName());
		}
		waitingOrders.remove(order);
		deliveredOrders.add(order);
		this.setCount(count+1);
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
	 * Gets the courier service.
	 *
	 * @return the courier service
	 */
	//Getters & Setters
	public CourierService getCourierService() {
		return courierService;
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
	 * Sets the position.
	 *
	 * @param a the new position
	 */
	public void setPosition(AddressPoint a){
		position = a;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName(){
		return name+" "+surname;
	}
	

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/* (non-Javadoc)
	 * @see user.model.User#toString()
	 */
	@Override
	public String toString() {
		return  "<Courier> "+username+"; fullname = "+surname+" "+name+"; position="+position+"; "+phone;
	}

	/* (non-Javadoc)
	 * @see user.model.User#update(java.lang.Object)
	 */
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		super.update(o);
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable)
	 */
	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable, java.lang.Object)
	 */
	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}


	

}
