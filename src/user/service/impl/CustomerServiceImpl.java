/*
 * 
 */
package user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exceptions.NameNotFoundException;
import policies.LotteryCard;
import policies.PointCard;
import policies.StandardCard;
import restaurant.Item;
import restaurant.MealMenu;
import restaurant.Menu;
import system.ConcreteShoppingCartVisitor;
import system.History;
import system.Message;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.service.CustomerService;
import user.service.MyFoodoraService;


/**
 * The Class CustomerServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerServiceImpl implements CustomerService {

	/** The customer. */
	private Customer customer;
	
	/** The m. */
	MyFoodoraService m = new MyFoodoraServiceImpl();
	/**
	 * Instantiates a new customer service impl.
	 *
	 * @param customer the customer
	 */
	public CustomerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}

	/* (non-Javadoc)
	 * @see user.service.CustomerService#createOrder(java.lang.String, java.lang.String)
	 */
	@Override
	public void createOrder(String restaurantName, String orderName){
		Restaurant restaurant = (Restaurant)m.selectUser(restaurantName);
		Order newOrder = new Order(customer,restaurant,orderName);
		customer.getShoppingCart().addOrder(newOrder);
	}
		
	/* (non-Javadoc)
	 * @see user.service.CustomerService#addItem2Order(java.lang.String, java.lang.String)
	 */
	@Override
	public void addItem2Order(String orderName,String itemName) throws NameNotFoundException{
		Order order = customer.getShoppingCart().getOrder(orderName);
		Menu menu = order.getRestaurant().getMenu();
		MealMenu mealmenu = order.getRestaurant().getMealMenu();
		Item item = null;
		if (menu.hasDish(itemName)){
			item = menu.getDish(itemName);
		}
		else if (mealmenu.hasMeal(itemName)){
			item = mealmenu.getMeal(itemName);
		}
		else {
			throw new NameNotFoundException(orderName);
		}
		order.addItem(item);
	}
	
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#commandSpecialMeal(user.model.Restaurant, java.lang.String)
	 */
	public void commandSpecialMeal(Restaurant r, String mealName){
		if (customer.getShoppingCart().hasRestaurant(r)){
			Order order = customer.getShoppingCart().getOrder(r);
			order.addItem(r.getRestaurantService().createFactoryMeal("Special-Meal", mealName));
		}
		else{
			Order order = new Order(customer, r);
			order.addItem(r.getRestaurantService().createFactoryMeal("Special-Meal", mealName));
			customer.getShoppingCart().addOrder(order);
		}
		customer.update(new Message(customer.getUsername(), mealName+" has been added to your shopping cart !"));
	}
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#addOrder(user.model.Restaurant, java.lang.String, java.lang.String)
	 */
	public void commandRegularMeal(Restaurant r, String mealName, String mealCategory){
		if (customer.getShoppingCart().hasRestaurant(r)){
			Order order = customer.getShoppingCart().getOrder(r);
			order.addItem(r.getRestaurantService().createFactoryMeal(mealCategory, mealName));
		}
		else{
			Order order = new Order(customer, r);
			order.addItem(r.getRestaurantService().createFactoryMeal(mealCategory, mealName));
			customer.getShoppingCart().addOrder(order);
		}
		customer.update(new Message(customer.getUsername(), mealName+" has been added to your shopping cart !"));
	}

	/* (non-Javadoc)
	 * @see user.service.CustomerService#addOrder(user.model.Restaurant, java.lang.String)
	 */
	public void commandAlaCarte(Restaurant r, String dishName){
		if (customer.getShoppingCart().hasRestaurant(r)){
			Order order = customer.getShoppingCart().getOrder(r);
			order.addItem(r.getRestaurantService().createFactoryDish(dishName));
		}
		else{
			Order order = new Order(customer, r);
			order.addItem(r.getRestaurantService().createFactoryDish(dishName));
			customer.getShoppingCart().addOrder(order);
		}
		customer.update(new Message(customer.getUsername(), dishName+" has been added to your shopping cart !"));
	}

	/* (non-Javadoc)
	 * @see user.service.CustomerService#pay()
	 */
	@Override
	public void endOrder(String orderName, String stringDate) throws NameNotFoundException, ParseException{
		MyFoodoraService myfoodora_service = new MyFoodoraServiceImpl();
		Order order = customer.getShoppingCart().getOrder(orderName);
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse(stringDate);
		
		order.setDate(date);
		
		customer.getCard().pay(order);
		
		order.getRestaurant().addToHistory(order);
		MyFoodora.getInstance().addToHistory(order);		
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
