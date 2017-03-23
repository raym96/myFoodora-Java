package model.user;

import java.util.ArrayList;
import java.util.Date;

import model.customer.*;
import model.myfoodora.Message;
import model.myfoodora.MyFoodora;
import model.myfoodora.SpecialOffer;
import model.myfoodora.SpecialOfferBoard;
import model.restaurant.AlaCarteOrder;
import model.restaurant.MealOrder;
import model.restaurant.Order;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

public class Customer extends User{
	
	private String name;
	private String surname;
	private AddressPoint address;
	private String email;
	private String phone;
	private FidelityCard card;
	private Boolean agreeBeNotifiedSpecialoffers;
	
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
		this.agreeBeNotifiedSpecialoffers = false;
		this.customerService = new CustomerServiceImpl(this);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}


	public ShoppingCart getShoppingCart(){
		return shoppingcart;
	}
	
	
	public boolean isAgreeBeNotifiedSpecialoffers() {
		return agreeBeNotifiedSpecialoffers;
	}

	public void setAgreeBeNotifiedSpecialoffers(boolean agreeBeNotifiedSpecialoffers) {
		this.agreeBeNotifiedSpecialoffers = agreeBeNotifiedSpecialoffers;
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

	@Override
	public String toString() {
		return super.toString() + ", <Customer> name=" + name + ", surname=" + surname + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ".\n";
	}
	
	
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof ArrayList<?>){
			this.specialoffers = (ArrayList<SpecialOffer>)o;
		}
		if(o instanceof String){
			this.getMessageBoard().addMessage(new Message(new Date(), (String)o));
			this.getMessageBoard().displayAllmsgs();
		}
	}

	@Override
	public void observe(Observable obv){
	// TODO Auto-generated method stub
		if( obv instanceof SpecialOfferBoard ){
			((SpecialOfferBoard)obv).getSpecialoffers();
		}
	}

	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		if( obv instanceof MyFoodora ){
			if( o instanceof Boolean && (Boolean)o==true ){
				((MyFoodora) obv).getMessageBoard().addMessage(new Message(new Date(), "" + this.getUsername() + " agree to be notified of special offers."));
				((MyFoodora) obv).getMessageBoard().displayAllmsgs();
			}else if( o instanceof Boolean && (Boolean)o==false ){
				((MyFoodora) obv).getMessageBoard().addMessage(new Message(new Date(), "" + this.getUsername() + " refuse to be notified of special offers."));
				((MyFoodora) obv).getMessageBoard().displayAllmsgs();
			}
		}
	}
}
