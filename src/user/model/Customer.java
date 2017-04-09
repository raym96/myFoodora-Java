/*
 * 
 */
package user.model;

import java.util.ArrayList;
import java.util.Date;

import policies.FidelityCard;
import policies.StandardCard;
import restaurant.Meal;
import system.AddressPoint;
import system.ConcreteSpecialOfferBoard;
import system.Message;
import system.MessageBoard;
import system.Observable;
import system.ShoppingCart;
import system.SpecialOffer;
import system.SpecialOfferObserver;
import user.service.CustomerService;
import user.service.impl.CustomerServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Customer extends User implements SpecialOfferObserver{
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The address. */
	private AddressPoint address;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;
	
	/** The card. */
	private FidelityCard card;
	
	/** The agree to be notified specialoffers. */
	private Boolean agreeToBeNotifiedSpecialoffers;
	
	/** The specialoffers. */
	private ArrayList<Meal> specialoffers;
	
	
	/** The shoppingcart. */
	private ShoppingCart shoppingcart;
	
	/** The customer service. */
	private CustomerService customerService;
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param username the username
	 * @param address the address
	 * @param email the email
	 * @param phone the phone
	 */
	public Customer(String name, String surname, String username, AddressPoint address, String email, String phone) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.card = new StandardCard(this);
		this.shoppingcart = new ShoppingCart();
		this.agreeToBeNotifiedSpecialoffers = false;
		this.customerService = new CustomerServiceImpl(this);
		this.specialoffers = new ArrayList<Meal>();
	}

	/**
	 *  basic methods *.
	 *
	 * @return the customer service
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}


	/**
	 * Gets the shopping cart.
	 *
	 * @return the shopping cart
	 */
	public ShoppingCart getShoppingCart(){
		return shoppingcart;
	}
	
	
	/**
	 * Checks if is agree be notified specialoffers.
	 *
	 * @return true, if is agree be notified specialoffers
	 */
	public boolean isAgreeBeNotifiedSpecialoffers() {
		return agreeToBeNotifiedSpecialoffers;
	}

	/**
	 * Sets the agree be notified specialoffers.
	 *
	 * @param agreeBeNotifiedSpecialoffers the new agree be notified specialoffers
	 */
	public void setAgreeBeNotifiedSpecialoffers(boolean agreeBeNotifiedSpecialoffers) {
		this.agreeToBeNotifiedSpecialoffers = agreeBeNotifiedSpecialoffers;
	}

	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public FidelityCard getCard(){
		return this.card;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname(){
		return this.surname;
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
	 * Gets the specialoffers.
	 *
	 * @return the specialoffers
	 */
	public ArrayList<Meal> getSpecialoffers() {
		return specialoffers;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
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
	 * @see user.model.User#getUsername()
	 */
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	/**
	 * Sets the card.
	 *
	 * @param card the new card
	 */
	public void setCard(FidelityCard card) {
		// TODO Auto-generated method stub
		this.card = card;
	}

	/**
	 * Sets the shoppingcart.
	 *
	 * @param shoppingcart the new shoppingcart
	 */
	public void setShoppingcart(ShoppingCart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}
	

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public AddressPoint getAddress() {
		return address;
	}

	/* (non-Javadoc)
	 * @see user.model.User#toString()
	 */
	@Override
	public String toString() {
		return  "<Customer> "+username+"; fullname = "+surname+" "+name+"; address="+address+"; "+email+"; "+phone;
	}
	
	/**
	 *  observer/observable business methods *.
	 *
	 * @param specialoffers the specialoffers
	 */
	//update the special offers
	@Override
	public void updateSpecialOffer(ArrayList<Meal> specialoffers){
	// TODO Auto-generated method stub
		this.specialoffers = specialoffers;
	}
	
	/* (non-Javadoc)
	 * @see system.SpecialOfferObserver#addSpecialOffer(restaurant.Meal)
	 */
	@Override
	public void addSpecialOffer(Meal meal) {
		specialoffers.add(meal);
	}

	/* (non-Javadoc)
	 * @see user.model.User#update(java.lang.Object)
	 */
	@Override
	public void update(Object o) {
		super.update(o);
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable)
	 */
	@Override
	public void observe(Observable o) {

		// complete when you need use it
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable, java.lang.Object)
	 */
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
