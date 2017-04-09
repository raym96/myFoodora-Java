/*
 * 
 */
package user.service.impl;

import java.util.ArrayList;

import policies.LotteryCard;
import policies.PointCard;
import policies.StandardCard;
import system.AlaCarteOrder;
import system.ConcreteShoppingCartVisitor;
import system.History;
import system.MealOrder;
import system.Message;
import system.Order;
import system.SpecialMealOrder;
import system.StandardMealOrder;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.CustomerService;
import user.service.MyFoodoraService;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerServiceImpl implements CustomerService {

	/** The customer. */
	private Customer customer;
	
	/**
	 * Instantiates a new customer service impl.
	 *
	 * @param customer the customer
	 */
	public CustomerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}

	// 1. place orders: this includes choosing a selection of items a-la-carte or one or more
	// meals offered by a given restaurant, and paying the total price for the composed
	/* (non-Javadoc)
	 * @see user.service.CustomerService#addSpecialMealOrder(user.model.Restaurant, java.lang.String)
	 */
	// order.
	public void addSpecialMealOrder(Restaurant r, String mealName){
		Order neworder = new SpecialMealOrder(customer, r,r.getRestaurantService().createMeal("Special-Meal", mealName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));
	}
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#addStandardMealOrder(user.model.Restaurant, java.lang.String, java.lang.String)
	 */
	public void addStandardMealOrder(Restaurant r, String mealName, String mealCategory){
		Order neworder = new StandardMealOrder(customer, r,r.getRestaurantService().createMeal(mealCategory, mealName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));
	}

	/* (non-Javadoc)
	 * @see user.service.CustomerService#addAlaCarteOrder(user.model.Restaurant, java.lang.String)
	 */
	public void addAlaCarteOrder(Restaurant r, String dishName){
		Order neworder = new AlaCarteOrder(customer,r,r.getRestaurantService().createDish(dishName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));

	}
	

	/* (non-Javadoc)
	 * @see user.service.CustomerService#pay()
	 */
	public void pay(){
		MyFoodoraService m = new MyFoodoraServiceImpl();
		customer.getCard().pay();
		for (Order order:customer.getShoppingCart().getOrders()){
			ArrayList<Courier> availablecouriers = MyFoodora.getInstance().getAvailableCouriers();
			m.parse(order, availablecouriers);
		}
		customer.getShoppingCart().clear();
	}
	

	/* (non-Javadoc)
	 * @see user.service.CustomerService#registerCard(java.lang.String)
	 */
	// 2. register/unregister to/from a fidelity card plan
	@Override
	public void registerCard(String cardType){
		if (cardType.equalsIgnoreCase("lotterycard")){
			customer.setCard(new LotteryCard(customer));
			System.out.println("" + customer.getUsername() + " have registered a lottery card !");
		}else if (cardType.equalsIgnoreCase("pointcard")){
			customer.setCard(new PointCard(customer));
			System.out.println("" + customer.getUsername() + " have registered a point card !");
		}else{
			System.out.println("The fidelity card type "+cardType+" is not recognized");
		}
	}
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#unregisterCard()
	 */
	@Override
	public void unregisterCard(){
		customer.setCard(new StandardCard(customer));
		System.out.println("" + customer.getUsername() + " has unregistered his/her card.");
	}


	// 3. access the information related to their account: including history of orders, and
		// points acquired with a fidelity program

	/* (non-Javadoc)
	 * @see user.service.CustomerService#getHistory()
	 */
	@Override
	public History getHistory(){
		History history = new History();
		for (Order order:MyFoodora.getInstance().getHistory().getOrders()){
			if (order.getCustomer() == customer){
				history.addOrder(order);
			}
		}
		return history;
	}
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#getPoints()
	 */
	@Override
	public double getPoints(){
		if (customer.getCard() instanceof PointCard){
			return ((PointCard)customer.getCard()).getPoints();
		}
		else{
			System.out.println("the customer doesn't have a point fidelity card");
			return 0;
		}
	}
	
	
	// 4. give/remove consensus to be notified whenever a new special offer is set by any
	// restaurant
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#giveConsensusBeNotifiedSpecialOffers()
	 */
	@Override
	public void giveConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		customer.setAgreeBeNotifiedSpecialoffers(true);
		MyFoodora.getInstance().addSpecialOfferObserver(customer);
		customer.update("You agree to be notified.");
		customer.observe(MyFoodora.getInstance(),customer.isAgreeBeNotifiedSpecialoffers());
	}


	/* (non-Javadoc)
	 * @see user.service.CustomerService#removeConsensusBeNotifiedSpecialOffers()
	 */
	@Override
	public void removeConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		customer.setAgreeBeNotifiedSpecialoffers(false);
		MyFoodora.getInstance().removeSpecialOfferObserver(customer);
		customer.update("You refuse to be notified.");
		customer.observe(MyFoodora.getInstance(),customer.isAgreeBeNotifiedSpecialoffers());
	}



}
