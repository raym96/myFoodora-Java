package model.user;

import java.util.ArrayList;

import model.restaurant.Order;

public class Courier extends User{

	private String surname;
	private String name;
	private AddressPoint position;
	private String phone;
	private int count;
	private boolean on_duty;
	
	private ArrayList<Order> order;
	
	public Courier(String name, String surname, String username, AddressPoint position, String phone) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.phone = phone;
		this.turnOffDuty();
		
		count = 0;
	}
	
	public void turnOnDuty(){
		this.on_duty=true;
	}
	public void turnOffDuty(){
		this.on_duty=false;
	}
	
	public AddressPoint getPosition(){
		return position;
	}
	
	public int getCount(){
		return count;
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
