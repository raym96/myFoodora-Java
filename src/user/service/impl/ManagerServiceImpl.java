/*
 * 
 */
package user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import exceptions.NameNotFoundException;
import policies.DeliveryPolicy;
import policies.FairOccupationDeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.SortingByCriteria;
import policies.SortingByRestaurant;
import policies.TargetProfitPolicy;
import policies.TargetProfit_DeliveryCost;
import policies.TargetProfit_Markup;
import policies.TargetProfit_ServiceFee;
import system.ConcreteShoppingCartVisitor;
import system.Order;
import system.ShoppingCartVisitor;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.ManagerService;
import user.service.MyFoodoraService;


/**
 * The Class ManagerServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ManagerServiceImpl implements ManagerService {

	/** The manager. */
	private Manager manager;
	
	private MyFoodoraService myfoodora_service = new MyFoodoraServiceImpl();
	/**
	 * Instantiates a new manager service impl.
	 *
	 * @param manager the manager
	 */
	public ManagerServiceImpl(Manager manager) {
		super();
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#addUser(user.model.User)
	 */
	// 1. add/remove any kind of user (restaurant, customers and/or couriers) to/from the system.
	@Override
	public void addUser(User user){
		MyFoodora.getInstance().register(user);
	}
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#removeUser(user.model.User)
	 */
	@Override
	public void removeUser(User user){
		MyFoodora.getInstance().unregister(user);
	}
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#activateUser(user.model.User)
	 */
	// 2. activate/disactivate any kind of user (restaurant, customers and/or couriers) of the system
	@Override
	public void activateUser(User user) throws NameNotFoundException{
		MyFoodora.getInstance().activateUser(user);
	}
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#disactivateUser(user.model.User)
	 */
	@Override
	public void disactivateUser(User user) throws NameNotFoundException{
		MyFoodora.getInstance().disactivateUser(user);
		
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#setServiceFree(double)
	 */
	// 3. changing the service-fee percentage and/or the markup percentage (\percentage de marge") and/or the delivery-cost
	@Override
	public synchronized void setServiceFree(double service_fee) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setService_fee(service_fee);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#setMarkUpPencentage(double)
	 */
	@Override
	public synchronized void setMarkUpPencentage(double markup_percentage) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setMarkup_percentage(markup_percentage);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#setDeliveryCost(double)
	 */
	@Override
	public synchronized void setDeliveryCost(double delivery_cost) {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().setDelivery_cost(delivery_cost);
	}

	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#getTotalIncome(java.util.Date, java.util.Date)
	 */
	// 4. computing the total income and/or prot over a time period
	@Override
	public double showTotalIncome(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return myfoodora_service.getTotalIncome(date1, date2);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getTotalProfit(java.util.Date, java.util.Date)
	 */
	@Override
	public double showTotalProfit(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return myfoodora_service.getTotalProfit(date1, date2);
	}


	// 5. computing the average income per customer (i.e., the total income divided by
	// the number of customers that placed at least one command) a time period
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#getAverageIncomePerCustomer(java.util.Date, java.util.Date)
	 */
	@Override
	public double showAverageIncomePerCustomer(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return myfoodora_service.getAverageIncomePerCustomer(date1, date2);
	}
	
	// 6. determining either the service-fee and/or markup percentage and/or the delivery-
		/* (non-Javadoc)
	 * @see user.service.ManagerService#setTargetProfitPolicy(policies.TargetProfitPolicy)
	 */
	//cost so to meet a target-prot (see target prot policies below)
	@Override
	public void setTargetProfitPolicy(String ProfitPolicyName){
		if (ProfitPolicyName.equalsIgnoreCase("delivery_cost")){
			MyFoodora.getInstance().setTargetprofitpolicy(new TargetProfit_DeliveryCost(MyFoodora.getInstance()));
		}
		if (ProfitPolicyName.equalsIgnoreCase("markup_pencentage")){
			MyFoodora.getInstance().setTargetprofitpolicy(new TargetProfit_Markup(MyFoodora.getInstance()));
		}
		if (ProfitPolicyName.equalsIgnoreCase("service_fee")){
			MyFoodora.getInstance().setTargetprofitpolicy(new TargetProfit_ServiceFee(MyFoodora.getInstance()));
		}
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#determineParam2MeetTargetProfit(double)
	 */
	@Override
	public void determineParam2MeetTargetProfit(double targetProfit) {
		// TODO Auto-generated method stub
		myfoodora_service.applyTargetProfitPolicy(targetProfit);
	}

	// 7. determining the most/least selling restaurant

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getBestRestaurant()
	 */
	@Override
	public Restaurant showRestaurantDesc() {
		// TODO Auto-generated method stub
		double income = 0;
		Restaurant restaurant = null;
		ArrayList<User> restaurant_users =  myfoodora_service.getUsersOfAssignedType("RESTAURANT");
		for (User u : restaurant_users){
			Restaurant r = (Restaurant)u;
			double rincome = r.getIncome(); //the number of commands passed at restaurant r is the length of its history
			System.out.println(r.getName()+": total income = "+rincome);
			if (rincome>income){
				income = rincome;
				restaurant = r;
			}
		}
		System.out.println("\nThe best selling restaurant is:");
		System.out.println(restaurant.getName());
		return restaurant;
		
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getWorstRestaurant()
	 */
	@Override
	public Restaurant showRestaurantAsc() {
		// TODO Auto-generated method stub
		double income = Integer.MAX_VALUE;
		Restaurant restaurant = null;
		ArrayList<User> restaurant_users =  myfoodora_service.getUsersOfAssignedType("RESTAURANT");
		for (User u : restaurant_users){
			Restaurant r = (Restaurant)u;
			double rincome = r.getIncome(); //the number of commands passed at restaurant r is the length of its history
			System.out.println(r.getName()+": total income = "+rincome);
			if (rincome<income){
				income = rincome;
				restaurant = r;
			}
		}
		System.out.println("\nThe worst selling restaurant is:");
		System.out.println(restaurant.getName());
		return restaurant;
	}


	/* (non-Javadoc)
	 * @see user.service.ManagerService#getBestCourier()
	 */
	// 8. determining the most/least active courier of the fleet
	@Override
	public Courier showCourierDesc() {
		// TODO Auto-generated method stub
		int count = 0;
		Courier courier = null;
		ArrayList<User> courier_users =  myfoodora_service.getUsersOfAssignedType("COURIER");
		for (User u : courier_users){
			Courier c = (Courier)u;
			int ccount = c.getCount(); //the number of commands passed at restaurant r is the length of its history
			if (ccount>count){
				count = ccount;
				courier = c;
			}
		}
		System.out.println("\nThe most active courier is:");
		System.out.println(courier.getName());
		return courier;
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getWorstCourier()
	 */
	@Override
	public Courier showCourierAsc() {
		// TODO Auto-generated method stub
		int count = Integer.MAX_VALUE;
		Courier courier = null;
		ArrayList<User> courier_users =  myfoodora_service.getUsersOfAssignedType("COURIER");
		for (User u : courier_users){
			Courier c = (Courier)u;
			int ccount = c.getCount(); //the number of commands passed at restaurant r is the length of its history
			if (ccount<count){
				count = ccount;
				courier = c;
			}
		}
		System.out.println("\nThe least active courier is:");
		System.out.println(courier.getName());
		return courier;
	}

	// 9. setting the current delivery-policy used by MyFoodora to determine which courier
	/* (non-Javadoc)
	 * @see user.service.ManagerService#setDeliveryPolicy(policies.DeliveryPolicy)
	 */
	// is assigned to deliver an order placed by a customer
	@Override
	public void setDeliveryPolicy(String deliverypolicy) {
		// TODO Auto-generated method stub
		if (deliverypolicy.equalsIgnoreCase("fair")){
			MyFoodora.getInstance().setDeliveryPolicy(new FairOccupationDeliveryPolicy());
		}
		if (deliverypolicy.equalsIgnoreCase("fastest")){
			MyFoodora.getInstance().setDeliveryPolicy(new FastestDeliveryPolicy());
		}
	}
	
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#displayUsers()
	 */
	// ##. extra tool method
	@Override
	public void displayUsers() {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().displayUsers();
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#displayActiveUsers()
	 */
	@Override
	public void displayActiveUsers() {
		// TODO Auto-generated method stub
		MyFoodora.getInstance().displayActiveUsers();
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#selectUser(java.lang.String)
	 */
	@Override
	public User selectUser(String username) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return myfoodora_service.selectUser(username);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#displayUsersOfAssignedType(java.lang.String)
	 */
	public void displayUsersOfAssignedType(String userType){
		ArrayList<User> users = MyFoodora.getInstance().getUsersOfAssignedType(userType);
		System.out.println("--- " + userType + " ---");
		System.out.println(users);
	}

	@Override
	public void associateCard(String username, String cardType) throws NameNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = (Customer)selectUser(username);
		customer.getCustomerService().registerCard(cardType);
	}
}
