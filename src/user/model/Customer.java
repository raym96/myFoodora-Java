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
import system.ShoppingCart;
import system.SpecialOfferBoard;
import system.SpecialOfferObserver;
import user.service.CustomerService;
import user.service.impl.CustomerServiceImpl;
import user.view.CustomerView;


/**
 * The Class Customer.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Customer extends User implements SpecialOfferObserver{
	
	/** The name. */
	private String lastname;
	
	/** The surname. */
	private String firstname;
	
	/** The address. */
	private AddressPoint address;
	
	/** The card. */
	private FidelityCard card;
	
	/** The agree to be notified specialoffers. */
	private Boolean agreeToBeNotifiedSpecialoffers;
	
	private SpecialOfferBoard specialOfferBoard;

	/** The shoppingcart. */
	private ShoppingCart shoppingcart;
	
	/** The customer service. */
	private CustomerService customerService;
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param username the username
	 * @param address the address
	 * @param password the password
	 */	
	public Customer(String firstName, String lastName, String username, AddressPoint address, String password){
		super(username,password);
		this.lastname = lastName;
		this.firstname=firstName;
		this.address = address;
		this.card = new StandardCard(this);
		this.shoppingcart = new ShoppingCart();
		this.agreeToBeNotifiedSpecialoffers = false;
		this.specialOfferBoard = MyFoodora.getInstance().getSpecialOfferBoard();
		this.customerService = new CustomerServiceImpl(this);
	}
	/**
	 *  basic methods *.
	 *
	 * @return the customer service
	 */
	public CustomerService getService() {
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
		return this.lastname;
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname(){
		return this.firstname;
	}
	
	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName(){
		return firstname+" "+lastname;
	}
	
	/**
	 * Sets the full name.
	 *
	 * @param name the first name and last name
	 */
	public void setFullName(String name) {
		// TODO Auto-generated method stub
		this.firstname = name.split(" ")[0];
		this.lastname = name.split(" ")[1];
	}
	
	/**
	 * Gets the specialoffers.
	 *
	 * @return the specialoffers
	 */
	public ArrayList<Meal> getSpecialoffers() {
		return specialOfferBoard.getSpecialOffers();
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
	
	/**
	 * Sets the address.
	 *
	 * @param address the address
	 */
	public void setAddress(AddressPoint address){
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see user.model.User#toString()
	 */
	@Override
	public String toString() {
		return  "<Customer> "+username+"; fullname = "+firstname+" "+lastname.toUpperCase()+"; address="+address;
	}


	@Override
	public void updateNewOffer(Meal meal) {
		// TODO Auto-generated method stub
		update(new Message("There is a new special-offer : "+meal+" by "+meal.getRestaurant().getName()));
	}
	
}
