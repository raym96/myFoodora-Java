/*
 * 
 */
package user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.LoginErrorException;
import exceptions.NameNotFoundException;
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


/**
 * The Class MyFoodoraServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyFoodoraServiceImpl implements MyFoodoraService{
	
	/** The myfoodora. */
	private MyFoodora myfoodora;
	
	/**
	 * Instantiates a new my foodora service impl.
	 */
	public MyFoodoraServiceImpl(MyFoodora myfoodora) {
		super();
		this.myfoodora = myfoodora;
	}

/**
 *  myfoodora's specific services *.
 *
 * @param service_fee the new service fee
 */
	// 1. setting of the service-fee, the markup percentage (\percentage de marge") and the
	// delivery cost values
	@Override
	public void setServiceFee(double service_fee) {
		myfoodora.setService_fee(service_fee);
	}

	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#setMarkUpPercentage(double)
	 */
	@Override
	public void setMarkUpPercentage(double markup_percentage) {
		// TODO Auto-generated method stub
		myfoodora.setMarkup_percentage(markup_percentage);
	}

	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#setDeliveryCost(double)
	 */
	@Override
	public void setDeliveryCost(double delivery_cost) {
		// TODO Auto-generated method stub
		myfoodora.setDelivery_cost(delivery_cost);
	}
	
	// 2. allocating of a courier to an order placed by a customer (by application of the current
	// delivery policy, see below details of supported policies)/
	
	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#parse(system.Order, java.util.ArrayList)
	 */
	public void findDeliverer(Order order, ArrayList<Courier> availablecouriers){
		if (availablecouriers.size()==0){
			order.getCustomer().update(new Message("No courier available for the moment, sorry."));
			return;
		}
		Courier courier = myfoodora.getDeliverypolicy().parse(order, availablecouriers);
		courier.addWaitingOrder(order); //allocate delivery task to the waiting list of courier
		System.out.println(courier.getUsername()+" has been assigned to the delivery task. Please wait for confirmation.");
		order.getCustomer().update(new Message(courier.getUsername()+" has been assigned to the delivery task. Please wait for confirmation.")); //the courier must accept
	}
	
	/**
	 *  if the courier accepts the order, a message will display and the operation is over.
	 * 	//if the courier declines the order, a new parse(order, availablecouriers) will be launched.
	 * 	//for more information check the code in CourierServiceImpl.java *
	 *
	 * @param specialoffer the specialoffer
	 */

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
	
	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#getTotalIncome(java.util.Date, java.util.Date)
	 */
	public double getTotalIncome(String stringDate1, String stringDate2) throws ParseException{
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(stringDate1, stringDate2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalIncome = 0;
		for (Order order:list){
			totalIncome += order.accept(visitor);
		}
		return Math.floor(totalIncome*1000)/1000;
	}
	
	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#getTotalProfit(java.util.Date, java.util.Date)
	 */
	public double getTotalProfit(String stringDate1, String stringDate2) throws ParseException{
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(stringDate1, stringDate2);
		ShoppingCartVisitor visitor = new ConcreteShoppingCartVisitor();
		double totalProfit = 0;
		for (Order order:list){
			totalProfit += order.accept(visitor)*myfoodora.getMarkup_percentage()+myfoodora.getService_fee()-myfoodora.getDelivery_cost();
		}
		return Math.floor(totalProfit*1000)/1000;
	}
	
	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#getAverageIncomePerCustomer(java.util.Date, java.util.Date)
	 */
	public double getAverageIncomePerCustomer(String stringDate1, String stringDate2) throws ParseException{
		ArrayList<Order> list = myfoodora.getHistory().getOrderBetween(stringDate1, stringDate2);
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
			return Math.floor((getTotalIncome(stringDate1, stringDate2)/customerlist.size())*1000)/1000;
		}
	}
	
	
	
	// 5. choose the target profit policy (see below) used to optimise the profit-related-
	/**
	 * Sets the target profit policy.
	 *
	 * @param tpp the new target profit policy
	 */
	// parameters (service-fee, markup percentage, and the delivery cost)
	public void setTargetProfitPolicy(TargetProfitPolicy tpp){
		myfoodora.setTargetprofitpolicy(tpp);
	}
	
	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#applyTargetProfitPolicy(double)
	 */
	public void applyTargetProfitPolicy(double targetProfit){
		myfoodora.getTargetprofitpolicy().meetTargetProfit(targetProfit);
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#getUsersOfAssignedType(java.lang.String)
	 */
	public ArrayList<User> getUsersOfAssignedType(String userType){
		 return myfoodora.getUsersOfAssignedType(userType);
	}
	


	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#selectUser(java.lang.String)
	 */
	@Override
	public User selectUser(String username) throws NameNotFoundException {
		// TODO Auto-generated method stub
		for(User user : myfoodora.getUsers()){
			if( username.equalsIgnoreCase(user.getUsername()) ){
				return user;
			}
		}
		throw new NameNotFoundException(username);
	}

	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#askAgree2customers(java.lang.String)
	 */
	@Override
	public void askAgree2customers(String ask) {
		// TODO Auto-generated method stub
		myfoodora.notifyObservers(myfoodora.getUsersOfAssignedType("CUSTOMER"), (Object)ask);
	}


	/* (non-Javadoc)
	 * @see user.service.MyFoodoraService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public void login(String username, String password) throws LoginErrorException{
		for(User u : myfoodora.getUsers()){
			if( username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword()) ){
				u.logIn();
				return;
			}
		}
		throw new LoginErrorException();
	}
}
