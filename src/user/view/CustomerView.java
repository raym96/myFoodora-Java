package user.view;

import policies.PointCard;
import restaurant.Item;
import restaurant.Meal;
import system.History;
import system.Order;
import user.model.Customer;
import user.model.MyFoodora;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;

public class CustomerView implements UserView{
	/** The customer. */
	private Customer c;
	
	/**
	 * Instantiates a new customer view.
	 *
	 * @param customer the customer
	 */
	public CustomerView(Customer customer) {
		super();
		this.c = customer;
	}

	public void showInfo(){
		String output = "";
		output+="<Customer> "+c.getUsername()+"; fullname = "+c.getSurname()+" "+c.getUsername().toUpperCase()+"; address="+c.getAddress();
		if (c.getEmail() !=null){
			output+="; email = "+c.getEmail();
		}
		if (c.getPhone() !=null){
			output+="; phone = "+c.getPhone();
		}
		if (c.isAgreeBeNotifiedSpecialoffers()){
			output+="; agrees to be notified of special offers.";
		}
		if (!(c.isAgreeBeNotifiedSpecialoffers())){
			output+="; will not be notified of special offers.";
		}
		System.out.println(output);
	}
	

	public void showHistory(){
		History history = new History();
		for (Order order:MyFoodora.getInstance().getHistory().getOrders()){
			if (order.getCustomer().equals(c)){
				history.addOrder(order);
			}
		}
		history.display();
	}


	public void showShoppingCart(){
		c.getShoppingCart().display();
	}
	
	public void showPoints(){
		if (c.getCard() instanceof PointCard){
			System.out.println("Balance of points = "+((PointCard)c.getCard()).getPoints());
		}
		else{
			System.out.println("You don't have a point card.");
		}
	}
	
	public void showSpecialOffers(){
		System.out.println("Special offers :");
		for (Meal meal : c.getSpecialoffers()){
			System.out.println(meal+" BY "+meal.getRestaurant().getName());
		}
	}
}
