package user.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import policies.TargetProfitPolicy;
import policies.TargetProfit_DeliveryCost;
import policies.TargetProfit_Markup;
import policies.TargetProfit_ServiceFee;
import restaurant.*;
import system.*;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.User;
import user.service.MyFoodoraService;

public class MyFoodoraServiceImpl implements MyFoodoraService{
	private MyFoodora myfoodora;
	
	public MyFoodoraServiceImpl() {
		super();
		this.myfoodora = MyFoodora.getInstance();
	}

/** myfoodora's specific services **/
	// 1. setting of the service-fee, the markup percentage (\percentage de marge") and the
	// delivery cost values
	@Override
	public void setServiceFee(double service_fee) {
		myfoodora.setService_fee(service_fee);
	}

	@Override
	public void setMarkUpPercentage(double markup_percentage) {
		// TODO Auto-generated method stub
		myfoodora.setMarkup_percentage(markup_percentage);
	}

	@Override
	public void setDeliveryCost(double delivery_cost) {
		// TODO Auto-generated method stub
		myfoodora.setDelivery_cost(delivery_cost);
	}
	
	// 2. allocating of a courier to an order placed by a customer (by application of the current
	// delivery policy, see below details of supported policies)/
	
	public void parse(Order order, ArrayList<Courier> availablecouriers){
		if (availablecouriers.size()==0){
			order.getCustomer().update(new Message("No courier available for the moment, sorry."));
			return;
		}
		Courier courier = myfoodora.getDeliverypolicy().parse(order, availablecouriers);
		courier.addWaitingOrder(order); //allocate delivery task to the waiting list of courier
		System.out.println(courier.getUsername()+" has been assigned to the delivery task. Please wait for confirmation.");
		order.getCustomer().update(new Message(courier.getUsername()+" has been assigned to the delivery task. Please wait for confirmation.")); //the courier must accept
	}
	
	/** if the courier accepts the order, a message will display and the operation is over.
	//if the courier declines the order, a new parse(order, availablecouriers) will be launched.
	//for more information check the code in CourierServiceImpl.java **/

	// 3. notifying users that gave consensus to receive special others notifcations, of a new
	// special offer set by a restaurant
	
	@Override
	public void notifyAll(Meal specialoffer) {
		// TODO Auto-generated method stub
		for (Customer c : myfoodora.getSpecialOfferObserver()){
			//add the new special offer to the special-offer list of the customer
			c.addSpecialOffer(specialoffer);
		}
	}

	
	// 4. computing the total income (i.e. the sum of all completed orders) as well as the total
	// profit of the system, knowing that the the prot of a single order is given by:
	// profit for one order = order_price * markup_percentage + service_fee - delivery cost
	
	public double getTotalIncome(Date date1, Date date2){
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(date1, date2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalIncome = 0;
		for (Order order:list){
			totalIncome += order.accept(visitor);
		}
		return Math.floor(totalIncome*1000)/1000;
	}
	
	public double getTotalProfit(Date date1, Date date2){
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(date1, date2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalProfit = 0;
		for (Order order:list){
			totalProfit += order.accept(visitor)*myfoodora.getMarkup_percentage()+myfoodora.getService_fee()-myfoodora.getDelivery_cost();
		}
		return Math.floor(totalProfit*1000)/1000;
	}
	
	public double getAverageIncomePerCustomer(Date date1, Date date2){
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(date1, date2);
		ArrayList<Customer> customerlist = new ArrayList<Customer>();
		for (Order order:list){
			//count the number of different customers over the time period
			if (!(customerlist.contains(order.getCustomer()))){
				customerlist.add(order.getCustomer());
			}
		}
		
		if (customerlist.size()==0){
			//no order has been made by any customer over the time period
			return 0;
		}
		else{
			return Math.floor((getTotalIncome(date1, date2)/customerlist.size())*1000)/1000;
		}
	}
	
	
	
	// 5. choose the target profit policy (see below) used to optimise the profit-related-
	// parameters (service-fee, markup percentage, and the delivery cost)
	public void setTargetProfitPolicy(TargetProfitPolicy tpp){
		myfoodora.setTargetprofitpolicy(tpp);
	}
	
	public void applyTargetProfitPolicy(double targetProfit){
		myfoodora.getTargetprofitpolicy().meetTargetProfit(targetProfit);
		
	}
	
	
/** myfoodora's basic services provided to User's specific operations rely on **/
	
	public void displayUsersOfAssignedType(String userType){
		
		ArrayList<User> users = myfoodora.getUsersOfAssignedType(userType);
		System.out.println("--- " + userType + " ---");
		System.out.println(users);
	}
	
	public ArrayList<User> getUsersOfAssignedType(String userType){
		 return myfoodora.getUsersOfAssignedType(userType);
	}
	

	public void addToHistory(Order order){
		myfoodora.getHistory().addOrder(order);
	}

	public void displayUsers() {
		// TODO Auto-generated method stub
		myfoodora.displayUsers();
	}
	public void displayActiveUsers(){
		myfoodora.displayActiveUsers();
	}
	
	public void displayAllMenus(){
		myfoodora.displayAllMenus();
	}


	@Override
	public User selectUser(String username) {
		// TODO Auto-generated method stub
		for(User user : myfoodora.getUsers()){
			if( username.equalsIgnoreCase(user.getUsername()) ){
				return user;
			}
		}
		return null;
	}

	@Override
	public void askAgree2customers(String ask) {
		// TODO Auto-generated method stub
		myfoodora.notifyObservers(myfoodora.getUsersOfAssignedType("CUSTOMER"), (Object)ask);
	}

	@Override
	public History getHistory(){
		return myfoodora.getHistory();
	}
	
}