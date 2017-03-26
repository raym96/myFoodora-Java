package service.impl;

import java.util.ArrayList;

import model.customer.LotteryCard;
import model.customer.PointCard;
import model.customer.ShoppingCart;
import model.customer.StandardCard;
import model.myfoodora.Message;
import model.restaurant.AlaCarteOrder;
import model.restaurant.MealOrder;
import model.restaurant.Order;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import model.user.Customer;
import model.user.MyFoodora;
import model.user.Restaurant;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private Customer customer;
	
	public CustomerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}

	// 1. place orders: this includes choosing a selection of items a-la-carte or one or more
	// meals offered by a given restaurant, and paying the total price for the composed
	// order.
	public void addSpecialMealOrder(Restaurant r, String mealType, String mealName){
		Order neworder = new SpecialMealOrder(customer, r,r.getRestaurantService().createMeal(mealType, mealName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));
	}
	
	public void addStandardMealOrder(Restaurant r, String mealType, String mealName){
		Order neworder = new StandardMealOrder(customer, r,r.getRestaurantService().createMeal(mealType, mealName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));
	}

	public void addAlaCarteOrder(Restaurant r, String dishName){
		Order neworder = new AlaCarteOrder(customer,r,r.getRestaurantService().createDish(dishName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));

	}
	
	public void clearShoppingCart(){
		//clear the shopping cart
		customer.setShoppingcart(new ShoppingCart());
	}
	
	public double calculatePrice(){
		return customer.getShoppingCart().calculatePrice();
	}
	
	public void pay(){
		double amount = calculatePrice();
		customer.setBalance(customer.getBalance() - amount);
		customer.update("paid for meal/dish = " + amount + ", balance = " + customer.getBalance());
		customer.observe(MyFoodora.getInstance(), "" + customer.getUsername() + " has paid " + amount);
		//gets points for each 10 euros spent in the restaurant if client has PointCard
		if (customer.getCard() instanceof PointCard){
			((PointCard)customer.getCard()).addPoints(calculatePrice()/10);
		}
	}
	

	// 2. register/unregister to/from a fidelity card plan
	@Override
	public void registerCard(String cardType){
		if (cardType=="lottery"){
			customer.setCard(new LotteryCard());
			System.out.println("" + customer.getUsername() + " have registed a lottery card !");
		}else if (cardType=="point"){
			customer.setCard(new PointCard());
			System.out.println("" + customer.getUsername() + " have registed a point card !");
		}else{
			System.out.println("The fidelity card type "+cardType+" is not recognized");
		}
	}
	
	@Override
	public void unregisterCard(){
		customer.setCard(new StandardCard());
		System.out.println("" + customer.getUsername() + " have unregisted!");
	}


	// 3. access the information related to their account: including history of orders, and
		// points acquired with a fidelity program

	//to be completed
	public ArrayList<Order> getHistory(){
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order:MyFoodora.getInstance().getHistory().getOrders()){
			if (order.getCustomer() == customer){
				orders.add(order);
			}
		}
		return orders;
	}
	
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
	
	@Override
	public void giveConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		customer.setAgreeBeNotifiedSpecialoffers(true);
		MyFoodora.getInstance().addSpecialOfferObserver(customer);
		customer.update("You agree to be notified.");
		customer.observe(MyFoodora.getInstance(),customer.isAgreeBeNotifiedSpecialoffers());
	}


	@Override
	public void removeConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		customer.setAgreeBeNotifiedSpecialoffers(false);
		MyFoodora.getInstance().removeSpecialOfferObserver(customer);
		customer.update("You refuse to be notified.");
		customer.observe(MyFoodora.getInstance(),customer.isAgreeBeNotifiedSpecialoffers());
	}



}
