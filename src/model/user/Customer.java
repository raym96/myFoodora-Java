package model.user;

import java.util.ArrayList;

import model.customer.*;
import model.myfoodora.SpecialOffer;
import model.myfoodora.SpecialOfferBoard;
import model.restaurant.AlaCarteOrder;
import model.restaurant.MealOrder;
import model.restaurant.Order;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import service.CustomerService;

public class Customer extends User implements Observer{
	
	private String name;
	private String surname;
	private AddressPoint address;
	private String email;
	private String phone;
	private FidelityCard card;
	
	private ArrayList<SpecialOffer> specialoffers;
	private boolean notified;
	
	private ShoppingCart shoppingcart;
	private double balance;
	
	private CustomerService customerService;
	
	public Customer(String name, String surname, String username, AddressPoint address, String email, String phone, boolean notified) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.notified = notified;
		this.card = new StandardCard();
		this.shoppingcart = new ShoppingCart();
	}

	
	
	
	public CustomerService getCustomerService() {
		return customerService;
	}


	public ShoppingCart getShoppingCart(){
		return shoppingcart;
	}
	
	
	public FidelityCard getCard(){
		return this.card;
	}
	
	public void turnOnNotification(){
		notified = true;
	}
	
	public void turnOffNotification(){
		notified = false;
	}
	
	@Override
	public void update(ArrayList<SpecialOffer> specialoffers) {
		// TODO Auto-generated method stub
		this.specialoffers = specialoffers;
	}

	@Override
	public void observe(SpecialOfferBoard specialofferboard){
	// TODO Auto-generated method stub
		specialofferboard.getSpecialoffers();
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setCard(FidelityCard card) {
		// TODO Auto-generated method stub
		this.card = card;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setShoppingcart(ShoppingCart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}
	
	
}
