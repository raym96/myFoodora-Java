package model.user;

import java.util.ArrayList;

import model.restaurant.Order;
import service.CourierService;
import service.impl.CourierServiceImpl;

public class Courier extends User {

	private String surname;
	private String name;
	private AddressPoint position;
	private String phone;
	private int count;
	private boolean on_duty;
	
	private ArrayList<Order> order;
	
	private CourierService courierService;
	
	public Courier(String name, String surname, String username, AddressPoint position, String phone) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.phone = phone;
		this.on_duty = false;
		
		count = 0;
		this.courierService = new CourierServiceImpl(this);
	}
	
	
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
}
