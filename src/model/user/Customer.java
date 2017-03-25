package model.user;

import java.util.ArrayList;
import java.util.Date;

import model.customer.*;
import model.myfoodora.Message;
import model.myfoodora.SpecialOffer;
import model.myfoodora.ConcreteSpecialOfferBoard;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

public class Customer extends User implements SpecialOfferObserver{
	
	private String name;
	private String surname;
	private AddressPoint address;
	private String email;
	private String phone;
	private FidelityCard card;
	private Boolean agreeToBeNotifiedSpecialoffers;
	
	private ArrayList<SpecialOffer> specialoffers;
	
	
	private ShoppingCart shoppingcart;
	private double balance;
	
	private CustomerService customerService;
	
	public Customer(String name, String surname, String username, AddressPoint address, String email, String phone) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.card = new StandardCard();
		this.shoppingcart = new ShoppingCart();
		this.agreeToBeNotifiedSpecialoffers = false;
		this.customerService = new CustomerServiceImpl(this);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}


	public ShoppingCart getShoppingCart(){
		return shoppingcart;
	}
	
	
	public boolean isAgreeBeNotifiedSpecialoffers() {
		return agreeToBeNotifiedSpecialoffers;
	}

	public void setAgreeBeNotifiedSpecialoffers(boolean agreeBeNotifiedSpecialoffers) {
		this.agreeToBeNotifiedSpecialoffers = agreeBeNotifiedSpecialoffers;
	}

	public FidelityCard getCard(){
		return this.card;
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
	

	public AddressPoint getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return super.toString() + ", <Customer> name=" + name + ", surname=" + surname + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ".\n";
	}
	
	//update the special offers
	@Override
	public void updateSpecialOffer(ArrayList<SpecialOffer> specialoffers){
	// TODO Auto-generated method stub
		this.specialoffers = specialoffers;
	}
	
	@Override
	public void addSpecialOffer(SpecialOffer specialoffer) {
		specialoffers.add(specialoffer);
	}
}
