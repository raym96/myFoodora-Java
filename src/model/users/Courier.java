package model.users;

import java.util.ArrayList;

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
	
	private ArrayList<Order> allDeliveryTasks;
	private Order currentDeliveryTask;
	
	
	private CourierService courierService;
	
	public Courier(String name, String surname, String username, AddressPoint position, String phone) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.phone = phone;
		this.on_duty = false;
		this.allDeliveryTasks = new ArrayList<Order>();
		this.currentDeliveryTask = null;
		
		count = 0;
		this.courierService = new CourierServiceImpl(this);
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


	public Order getCurrentDeliveryTask() {
		return currentDeliveryTask;
	}
	
	public void setCurrentDeliveryTask(Order order) {
		this.currentDeliveryTask = order;
	}

	
	public void addDeliveryTask(Order o){
		allDeliveryTasks.add(o);
	}

	public ArrayList<Order> getAllDeliveryTask(){
		return allDeliveryTasks;
	}


	@Override
	public String toString() {
		return  "<Courier> "+username+"; fullname = "+surname+" "+name+"; position="+position+"; phone="+phone+"; activated = "+activated + "; User ID = "+ID;
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
