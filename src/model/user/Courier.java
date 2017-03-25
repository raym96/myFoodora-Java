package model.user;

import java.util.ArrayList;

import model.myfoodora.DeliveryTask;
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
	
	private ArrayList<DeliveryTask> allDeliveryTasks;
	private DeliveryTask currentDeliveryTask;
	
	
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


	public DeliveryTask getCurrentDeliveryTask() {
		return currentDeliveryTask;
	}


	public void setCurrentDeliveryTask(DeliveryTask currentDeliveryTask) {
		this.currentDeliveryTask = currentDeliveryTask;
	}

	

	@Override
	public String toString() {
		return super.toString() + ", <Courier> surname=" + surname + ", name=" + name + ", phone=" + phone + ".\n";
	}


	
	
}
