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

/**
 * The Class CustomerView.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerView implements UserView{
	/** The customer. */
	private Customer customer;
	
	/**
	 * Instantiates a new customer view.
	 *
	 * @param customer the customer
	 */
	public CustomerView(Customer customer) {
		super();
		this.customer = customer;
	}

	/* (non-Javadoc)
	 * @see user.view.UserView#showInfo()
	 */
	public void showInfo(){
		String output = "";
		output+="<Customer> "+customer.getUsername()+"; fullname = "+customer.getSurname()+" "+customer.getName().toUpperCase()+"; address="+customer.getAddress();
		if (customer.getEmail() !=null){
			output+="; email = "+customer.getEmail();
		}
		if (customer.getPhone() !=null){
			output+="; phone = "+customer.getPhone();
		}
		if (customer.isAgreeBeNotifiedSpecialoffers()){
			output+="; notification : ON;";
		}
		if (!(customer.isAgreeBeNotifiedSpecialoffers())){
			output+="; notification : OFF;";
		}
		output += " has a "+customer.getCard();
		System.out.println(output);
	}
	

	/* (non-Javadoc)
	 * @see user.view.UserView#showHistory()
	 */
	public void showHistory(){
		History history = new History();
		for (Order order:MyFoodora.getInstance().getHistory().getOrders()){
			if (order.getCustomer().equals(customer)){
				history.addOrder(order);
			}
		}
		history.display();
	}


	/**
	 * Show shopping cart.
	 */
	public void showShoppingCart(){
		customer.getShoppingCart().display();
	}
	
	/**
	 * Show points.
	 */
	public void showPoints(){
		if (customer.getCard() instanceof PointCard){
			System.out.println("Balance of points = "+((PointCard)customer.getCard()).getPoints());
		}
		else{
			System.out.println("You don't have a point card.");
		}
	}
	
	/**
	 * Show special offers.
	 */
	public void showSpecialOffers(){
		System.out.println("Special offers :");
		for (Meal meal : customer.getSpecialoffers()){
			System.out.println(meal+" BY "+meal.getRestaurant().getName());
		}
	}
}
