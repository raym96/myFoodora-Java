/*
 * 
 */
package user.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import exceptions.NameAlreadyExistsException;
import exceptions.NameNotFoundException;
import policies.DeliveryPolicy;
import policies.FairOccupationDeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.SortingByAlaCarte;
import policies.SortingByCourierDeliveries;
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
import user.view.ManagerView;


/**
 * The Class ManagerServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ManagerServiceImpl implements ManagerService {

	/** The manager. */
	private Manager manager;
	
	/** The manager view. */
	private ManagerView managerView;
	
	/** The myfoodora service. */
	private MyFoodoraService myfoodora_service = MyFoodora.getInstance().getService();
	/**
	 * Instantiates a new manager service impl.
	 *
	 * @param manager the manager
	 */
	public ManagerServiceImpl(Manager manager) {
		super();
		this.manager = manager;
		this.managerView = new ManagerView(manager);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#addUser(user.model.User)
	 */
	// 1. add/remove any kind of user (restaurant, customers and/or couriers) to/from the system.
	@Override
	public void addUser(User user) throws NameAlreadyExistsException{
		if (MyFoodora.getInstance().hasUser(user.getUsername())) throw new NameAlreadyExistsException(user.getUsername());
		MyFoodora.getInstance().register(user);
	}
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#removeUser(user.model.User)
	 */
	@Override
	public void removeUser(String username) throws NameNotFoundException{
		User user = selectUser(username);
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
	public double getTotalIncome(String stringDate1, String stringDate2) throws ParseException {
		// TODO Auto-generated method stub
		return myfoodora_service.getTotalIncome(stringDate1, stringDate2);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getTotalProfit(java.util.Date, java.util.Date)
	 */
	@Override
	public double getTotalProfit(String stringDate1, String stringDate2) throws ParseException {
		// TODO Auto-generated method stub
		return myfoodora_service.getTotalProfit(stringDate1, stringDate2);
	}


	// 5. computing the average income per customer (i.e., the total income divided by
	// the number of customers that placed at least one command) a time period
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#getAverageIncomePerCustomer(java.util.Date, java.util.Date)
	 */
	@Override
	public double getAverageIncomePerCustomer(String stringDate1, String stringDate2) throws ParseException {
		// TODO Auto-generated method stub
		return myfoodora_service.getAverageIncomePerCustomer(stringDate1, stringDate2);
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
		if (ProfitPolicyName.equalsIgnoreCase("markup_percentage")){
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
	public void showRestaurantDesc() {
		SortingByCriteria sortingcriteria = new SortingByRestaurant();
		System.out.println("Displaying restaurants sorted w.r.t the number of shipped orders ");
		sortingcriteria.displayDescending(MyFoodora.getInstance().getHistory().getOrders());
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getWorstRestaurant()
	 */
	@Override
	public void showRestaurantAsc() {
		// TODO Auto-generated method stub
		SortingByCriteria sortingcriteria = new SortingByRestaurant();
		System.out.println("Displaying restaurants sorted w.r.t the number of shipped orders (ascending order)");
		sortingcriteria.displayAscending(MyFoodora.getInstance().getHistory().getOrders());

	}


	/* (non-Javadoc)
	 * @see user.service.ManagerService#getBestCourier()
	 */
	// 8. determining the most/least active courier of the fleet
	@Override
	public void showCourierDesc() {
		// TODO Auto-generated method stub
		SortingByCriteria sortingcriteria = new SortingByCourierDeliveries();
		System.out.println("Displaying couriers sorted w.r.t the number of delivered orders ");
		sortingcriteria.displayDescending(MyFoodora.getInstance().getHistory().getOrders());
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#getWorstCourier()
	 */
	@Override
	public void showCourierAsc() {
		SortingByCriteria sortingcriteria = new SortingByCourierDeliveries();
		System.out.println("Displaying couriers sorted w.r.t the number of delivered orders (ascending order)");
		sortingcriteria.displayAscending(MyFoodora.getInstance().getHistory().getOrders());

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
	 * @see user.service.ManagerService#selectUser(java.lang.String)
	 */
	@Override
	public User selectUser(String username) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return myfoodora_service.selectUser(username);
	}

	/* (non-Javadoc)
	 * @see user.service.ManagerService#associateCard(java.lang.String, java.lang.String)
	 */
	@Override
	public void associateCard(String username, String cardType) throws NameNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = (Customer)selectUser(username);
		customer.getService().registerCard(cardType);
	}
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#showInfo()
	 */
	@Override
	public void showInfo(){
		managerView.showInfo();
	}
	
	/* (non-Javadoc)
	 * @see user.service.ManagerService#showHistory()
	 */
	@Override
	public void showHistory(){
		managerView.showHistory();
	}
}
