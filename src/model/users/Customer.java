package model.users;

import java.util.ArrayList;
import java.util.Date;

import model.customer.*;
import model.myfoodora.Message;
import model.myfoodora.MessageBoard;
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
		this.balance = 0.0;
		this.card = new StandardCard();
		this.shoppingcart = new ShoppingCart();
		this.agreeToBeNotifiedSpecialoffers = false;
		this.customerService = new CustomerServiceImpl(this);
		this.specialoffers = new ArrayList<SpecialOffer>();
	}

	/** basic methods **/
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

	public String getName(){
		return this.name;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	
	
	public ArrayList<SpecialOffer> getSpecialoffers() {
		return specialoffers;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
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
		return  "<Customer> "+username+"; fullname = "+surname+" "+name+"; address="+address+"; email="+email+"; phone="+phone+"; activated = "+activated + "; User ID = "+ID;
	}
	
	/** observer/observable business methods **/
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

	@Override
	public void update(Object o) {
		super.update(o);
	}

	@Override
	public void observe(Observable o) {

		// complete when you need use it
	}

	@Override
	public void observe(Observable obv, Object o) {
		super.observe(obv, o);
		if( obv instanceof MyFoodora ){
			MessageBoard msgBoard = ((MyFoodora) obv).getMessageBoard();
			if( o instanceof Boolean && (Boolean)o==true ){
				msgBoard.addMessage(new Message("" + this.getUsername() + " agrees to be notified of special offers."));
			}else if( o instanceof Boolean && (Boolean)o==false ){
				msgBoard.addMessage(new Message("" + this.getUsername() + " refuses to be notified of special offers."));
			}
			System.out.println(msgBoard.getMessages().get(msgBoard.getMessages().size()-1));
		}
	}
	
}
