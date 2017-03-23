package service.impl;

import model.customer.LotteryCard;
import model.customer.PointCard;
import model.customer.ShoppingCart;
import model.customer.StandardCard;
import model.myfoodora.MyFoodora;
import model.restaurant.AlaCarteOrder;
import model.restaurant.MealOrder;
import model.restaurant.SpecialMealOrder;
import model.restaurant.StandardMealOrder;
import model.user.Customer;
import model.user.Restaurant;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private Customer customer;
	
	public CustomerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}


	public void addSpecialMealOrder(Restaurant r, String mealType, String mealName){
		customer.getShoppingCart().addOrder(new SpecialMealOrder(customer, r,r.getRestaurantService().createMeal(mealType, mealName)));
	}
	
	public void addStandardMealOrder(Restaurant r, String mealType, String mealName){
		customer.getShoppingCart().addOrder(new StandardMealOrder(customer, r,r.getRestaurantService().createMeal(mealType, mealName)));
	}

	public void addAlaCarteOrder(Restaurant r, String dishName){
		customer.getShoppingCart().addOrder(new AlaCarteOrder(customer,r,r.getRestaurantService().createDish(dishName)));
	}


	public void clearShoppingCart(){
		//clear the shopping cart
		customer.setShoppingcart(new ShoppingCart());
	}
	
	public double calculatePrice(){
		return customer.getShoppingCart().calculatePrice();
	}
	
	public void pay(){
		customer.setBalance(customer.getBalance() - calculatePrice());
		//gets points for each 10 euros spent in the restaurant if client has PointCard
		if (customer.getCard() instanceof PointCard){
			((PointCard)customer.getCard()).addPoints(calculatePrice()/10);
		}
	}
	
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
	
	public void unregisterCard(){
		customer.setCard(new StandardCard());
		System.out.println("" + customer.getUsername() + " have unregisted!");
	}


	@Override
	public void giveConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		customer.setAgreeBeNotifiedSpecialoffers(true);
		customer.observe(MyFoodora.getInstance(),customer.isAgreeBeNotifiedSpecialoffers());
	}


	@Override
	public void removeConsensusBeNotifiedSpecialOffers() {
		// TODO Auto-generated method stub
		customer.setAgreeBeNotifiedSpecialoffers(false);
		customer.observe(MyFoodora.getInstance(),customer.isAgreeBeNotifiedSpecialoffers());
	}


}
