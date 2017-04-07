package user;

import java.util.ArrayList;

import policies.LotteryCard;
import policies.PointCard;
import policies.StandardCard;
import system.AlaCarteOrder;
import system.History;
import system.MealOrder;
import system.Message;
import system.Order;
import system.SpecialMealOrder;
import system.StandardMealOrder;

public class CustomerServiceImpl implements CustomerService {

	private Customer customer;
	
	public CustomerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}

	// 1. place orders: this includes choosing a selection of items a-la-carte or one or more
	// meals offered by a given restaurant, and paying the total price for the composed
	// order.
	public void addSpecialMealOrder(Restaurant r, String mealName){
		Order neworder = new SpecialMealOrder(customer, r,r.getRestaurantService().createMeal("Special-Meal", mealName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));
	}
	
	public void addStandardMealOrder(Restaurant r, String mealName, String mealCategory){
		Order neworder = new StandardMealOrder(customer, r,r.getRestaurantService().createMeal(mealCategory, mealName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));
	}

	public void addAlaCarteOrder(Restaurant r, String dishName){
		Order neworder = new AlaCarteOrder(customer,r,r.getRestaurantService().createDish(dishName));
		customer.getShoppingCart().addOrder(neworder);
		customer.update(new Message(customer.getUsername(), neworder.getOrderID()+" has been added to your shopping cart !"));

	}
	

	public void pay(){
		MyFoodoraService m = new MyFoodoraServiceImpl();
		customer.getCard().pay();
		for (Order order:customer.getShoppingCart().getOrders()){
			ArrayList<Courier> availablecouriers = MyFoodora.getInstance().getAvailableCouriers();
			m.parse(order, availablecouriers);
		}
		customer.getShoppingCart().clear();
	}
	

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
	
	@Override
	public void unregisterCard(){
		customer.setCard(new StandardCard(customer));
		System.out.println("" + customer.getUsername() + " has unregistered his/her card.");
	}


	// 3. access the information related to their account: including history of orders, and
		// points acquired with a fidelity program

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
