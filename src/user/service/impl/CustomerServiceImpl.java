/*
 * 
 */
package user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exceptions.NameAlreadyExistsException;
import exceptions.NameNotFoundException;
import policies.LotteryCard;
import policies.PointCard;
import policies.StandardCard;
import restaurant.Item;
import restaurant.Meal;
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
import user.view.CustomerView;


/**
 * The Class CustomerServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerServiceImpl implements CustomerService {

	/** The customer. */
	private Customer customer;
	
	/** The customer view. */
	private CustomerView customerView;
	
	/** The m. */
	MyFoodoraService m = MyFoodora.getInstance().getService();
	/**
	 * Instantiates a new customer service impl.
	 *
	 * @param customer the customer
	 */
	public CustomerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
		this.customerView = new CustomerView(customer);
	}

	/* (non-Javadoc)
	 * @see user.service.CustomerService#createOrder(java.lang.String, java.lang.String)
	 */
	@Override
	public void createOrder(String restaurantName, String orderName) throws NameAlreadyExistsException, NameNotFoundException{
		Restaurant restaurant = (Restaurant)m.selectUser(restaurantName);
		Order newOrder = new Order(customer,restaurant,orderName);
		if (customer.getShoppingCart().hasOrder(orderName)){
			throw new NameAlreadyExistsException(orderName);
		}
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
		MealMenu specialmealmenu = order.getRestaurant().getSpecialmealmenu();
		Item item = null;
		if (menu.hasDish(itemName)){
			item = menu.getDish(itemName);
		}
		else if (mealmenu.hasMeal(itemName)){
			item = mealmenu.getMeal(itemName);
		}
		else if (specialmealmenu.hasMeal(itemName)){
			item = specialmealmenu.getMeal(itemName);
		}
		else {
			throw new NameNotFoundException(itemName);
		}
		order.addItem(item);
	}
	
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#pay()
	 */
	@Override
	public void endOrder(String orderName, String stringDate) throws NameNotFoundException, ParseException{
		MyFoodoraService myfoodora_service = MyFoodora.getInstance().getService();
		Order order = customer.getShoppingCart().getOrder(orderName);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse(stringDate);
		
		order.setDate(date);
		
		customer.getCard().pay(order);
		
		//automatically find a deliverer
		MyFoodora myfoodora = MyFoodora.getInstance();
		myfoodora_service.findDeliverer(order, myfoodora.getAvailableCouriers());
		
		order.getRestaurant().addToHistory(order);
		MyFoodora.getInstance().addToHistory(order);		
	}
	

	/* (non-Javadoc)
	 * @see user.service.CustomerService#registerCard(java.lang.String)
	 */
	// 2. register/unregister to/from a fidelity card plan
	@Override
	public void registerCard(String cardType) throws NameNotFoundException{
		if (cardType.equalsIgnoreCase("lottery")){
			customer.setCard(new LotteryCard(customer));
		}else if (cardType.equalsIgnoreCase("point")){
			customer.setCard(new PointCard(customer));
		}else{
			throw new NameNotFoundException(cardType);
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

	
	
	// 4. give/remove consensus to be notified whenever a new special offer is set by any
	// restaurant
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#giveConsensusBeNotifiedSpecialOffers()
	 */
	@Override
	public void giveConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		if (customer.isAgreeBeNotifiedSpecialoffers()){
			return;
		}
		customer.setAgreeBeNotifiedSpecialoffers(true);
		MyFoodora.getInstance().addSpecialOfferObserver(customer);
	}


	/* (non-Javadoc)
	 * @see user.service.CustomerService#removeConsensusBeNotifiedSpecialOffers()
	 */
	@Override
	public void removeConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		if (!(customer.isAgreeBeNotifiedSpecialoffers())){
			return;
		}
		customer.setAgreeBeNotifiedSpecialoffers(false);
		MyFoodora.getInstance().removeSpecialOfferObserver(customer);
	}

	/* (non-Javadoc)
	 * @see user.service.CustomerService#showInfo()
	 */
	@Override
	public void showInfo(){
		customerView.showInfo();
	}
	

	/* (non-Javadoc)
	 * @see user.service.CustomerService#showHistory()
	 */
	public void showHistory(){
		customerView.showHistory();
	}


	/* (non-Javadoc)
	 * @see user.service.CustomerService#showShoppingCart()
	 */
	@Override
	public void showShoppingCart(){
		customerView.showShoppingCart();
	}
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#showPoints()
	 */
	@Override
	public void showPoints(){
		customerView.showPoints();
	}
	
	/* (non-Javadoc)
	 * @see user.service.CustomerService#showSpecialOffers()
	 */
	@Override
	public void showSpecialOffers(){
		customerView.showSpecialOffers();
	}

}
