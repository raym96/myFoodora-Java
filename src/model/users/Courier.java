package model.users;

import java.util.ArrayList;

import exceptions.OrderNotFoundException;
import model.myfoodora.Message;
import model.myfoodora.SpecialOffer;
import model.myfoodora.SpecialOfferBoard;
import model.myfoodora.ConcreteSpecialOfferBoard;
import model.restaurant.Order;
import service.CourierService;
import service.impl.CourierServiceImpl;
import service.impl.MyFoodoraServiceImpl;

public class Courier extends User{

	private String surname;
	private String name;
	private AddressPoint position;
	private String phone;
	private int count;
	private boolean on_duty;
	
	private ArrayList<Order> deliveredOrders;
	private ArrayList<Order> waitingOrders; //unanswered orders, must confirm/decline the mission
	
	
	private CourierService courierService;
	
	public Courier(String name, String surname, String username, AddressPoint position, String phone) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.phone = phone;
		this.on_duty = false;
		this.deliveredOrders = new ArrayList<Order>();
		this.waitingOrders = new ArrayList<Order>();
		
		count = 0;
		this.courierService = new CourierServiceImpl(this);
	}

	
	public void setWaitingOrders(ArrayList<Order> waitingOrders) {
		this.waitingOrders = waitingOrders;
	}
	public ArrayList<Order> getWaitingOrders(){
		return waitingOrders;
	}
	
	public void addWaitingOrder(Order order) {
		waitingOrders.add(order);
	}

	public void refuseWaitingOrder(Order order) throws OrderNotFoundException{
		if (!(waitingOrders.contains(order))){
			throw new OrderNotFoundException(order);
		}
		waitingOrders.remove(order);
	}
	
	public void acceptWaitingOrder(Order order) throws OrderNotFoundException{
		if (!(waitingOrders.contains(order))){
			throw new OrderNotFoundException(order);
		}
		waitingOrders.remove(order);
		deliveredOrders.add(order);
		this.setCount(count+1);
	}

	public ArrayList<Order> getDeliveredOrders(){
		return deliveredOrders;
	}
	
	//Getters & Setters
	public CourierService getCourierService() {
		return courierService;
	}


	public AddressPoint getPosition(){
		return position;
	}
	
	public int getCount(){
		return count;
	}

	public boolean isOn_duty() {
		return on_duty;
	}


	public void setOn_duty(boolean on_duty) {
		this.on_duty = on_duty;
	}

	public void setCount(int i){
		count = i;
	}
	
	public void setPosition(AddressPoint a){
		position = a;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return  "<Courier> "+username+"; fullname = "+surname+" "+name+"; position="+position+"; "+phone;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		super.update(o);
	}

	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}


	

}
