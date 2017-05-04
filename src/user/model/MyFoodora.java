/*
 * 
 */
 package user.model;

import java.util.ArrayList;

import exceptions.NameNotFoundException;
import policies.DeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.TargetProfitPolicy;
import policies.TargetProfit_DeliveryCost;
import restaurant.*;
import system.ConcreteSpecialOfferBoard;
import system.History;
import system.MessageBoard;
import system.MessageBoardObserver;
import system.Order;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;
import user.view.MyFoodoraView;


/**
 * The Class MyFoodora.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyFoodora {
	
	/** The users. */
	private ArrayList<User> users;

	/** The specialofferboard. */
	private ConcreteSpecialOfferBoard specialofferboard;
	
	/** The service fee. */
	private double service_fee;
	
	/** The markup percentage. */
	private double markup_percentage;
	
	/** The delivery cost. */
	private double delivery_cost;	
	
	/** The current target profit **/
	private double targetprofit;
	
	/** The targetprofitpolicy. */
	private TargetProfitPolicy targetprofitpolicy;
	
	/** The deliverypolicy. */
	private DeliveryPolicy deliverypolicy;

	/** The history. */
	private History history;
	
	/** The instance. */
	//Singleton Pattern
	private static MyFoodora instance = null;
	
	private MyFoodoraService myfoodoraService; 
	
	

	/**
	 * Instantiates a new my foodora.
	 */
	private MyFoodora(){
		this.users = new ArrayList<User>();
		this.specialofferboard = new ConcreteSpecialOfferBoard();
		this.history = new History();
		this.myfoodoraService = new MyFoodoraServiceImpl(this);
		//default values
		service_fee = 1;
		markup_percentage=0.1;
		delivery_cost = 0.5;
		
		// default policies
		this.deliverypolicy = new FastestDeliveryPolicy();
		this.targetprofitpolicy = new TargetProfit_DeliveryCost(this);
	};
	
	/**
	 * Sync init.
	 */
	private static synchronized void syncInit(){
		if(instance==null){
			instance = new MyFoodora();
		}
	}
	
	/**
	 * Gets the single instance of MyFoodora.
	 *
	 * @return single instance of MyFoodora
	 */
	public static MyFoodora getInstance(){
		//if no instance of myfoodora exists, returns a new myfoodora; otherwise returns the existing myfoodora
		if(instance == null){
			syncInit();
		}
		return instance;
	}
	
	/**
	 * Read resolve.
	 *
	 * @return the object
	 */
	public Object readResolve(){
		return instance;
	}

	
	/**
	 * Reset.
	 */
	public static synchronized void reset(){
		instance = null;
	}
	

	/**
	 * Gets the my foodora service.
	 *
	 * @return the my foodora service
	 */
	public MyFoodoraService getService(){
		return myfoodoraService;
	}
	
	/**
	 * Adds the special offer observer.
	 *
	 * @param c the c
	 */
	public void addSpecialOfferObserver(Customer c){
		specialofferboard.register(c);
	}
	
	/**
	 * Removes the special offer observer.
	 *
	 * @param c the c
	 */
	public void removeSpecialOfferObserver(Customer c){
		specialofferboard.unregister(c);
	}
	
	
	/**
	 * Sets the delivery policy.
	 *
	 * @param d the new delivery policy
	 */
	public void setDeliveryPolicy(DeliveryPolicy d){
		deliverypolicy = d;
	}
	
	/**
	 * Parses the.
	 *
	 * @param order the order
	 * @return the courier
	 */
	public Courier parse(Order order){
		
		return deliverypolicy.parse(order, getActivecouriers());
	}
	
	
	/**
	 * Gets the service fee.
	 *
	 * @return the service fee
	 */
	public double getService_fee() {
		return service_fee;
	}

	/**
	 * Sets the service fee.
	 *
	 * @param service_fee the new service fee
	 */
	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}

	/**
	 * Gets the markup percentage.
	 *
	 * @return the markup percentage
	 */
	public double getMarkup_percentage() {
		return markup_percentage;
	}
	
	/**
	 * Sets the target profit.
	 *
	 * @param targetprofit the new target profit
	 */
	public void setTargetprofit(double targetprofit) {
		this.targetprofit = targetprofit;
	}

	/**
	 * Gets the target profit.
	 *
	 * @return the target profit
	 */
	public double getTargetprofit() {
		return targetprofit;
	}

	/**
	 * Sets the markup percentage.
	 *
	 * @param markup_percentage the new markup percentage
	 */
	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}

	/**
	 * Gets the delivery cost.
	 *
	 * @return the delivery cost
	 */
	public double getDelivery_cost() {
		return delivery_cost;
	}

	/**
	 * Sets the delivery cost.
	 *
	 * @param delivery_cost the new delivery cost
	 */
	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}
	
	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * Gets the active users.
	 *
	 * @return the active users
	 */
	public ArrayList<User> getActiveUsers() {
		ArrayList<User> activeUsers = new ArrayList<User>();
		for(User user : users){
			if(user.isActivated()){
				activeUsers.add(user);
			}
		}
		return activeUsers;
	}
	

	/**
	 * Gets the couriers.
	 *
	 * @return the couriers
	 */
	public ArrayList<User> getCouriers() {
		return getUsersOfAssignedType("COURIER");
	}

	/**
	 * Gets the activecouriers.
	 *
	 * @return the activecouriers
	 */
	public ArrayList<Courier> getActivecouriers() {
		ArrayList<User> couriers = getUsersOfAssignedType("COURIER");
		ArrayList<Courier> activecouriers = new ArrayList<Courier>();
		for(User courier : couriers){
			if(courier.isActivated()){
				activecouriers.add((Courier)courier);
			}
		}
		
		return activecouriers;
	}


	/**
	 * Gets the targetprofitpolicy.
	 *
	 * @return the targetprofitpolicy
	 */
	public TargetProfitPolicy getTargetprofitpolicy() {
		return targetprofitpolicy;
	}

	/**
	 * Gets the deliverypolicy.
	 *
	 * @return the deliverypolicy
	 */
	public DeliveryPolicy getDeliverypolicy() {
		return deliverypolicy;
	}

	/**
	 * Sets the history.
	 *
	 * @param h the new history
	 */
	public void setHistory(History h){
		history = h;
	}
	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * Adds the to history.
	 *
	 * @param order the order
	 */
	public void addToHistory(Order order) {
		// TODO Auto-generated method stub
		history.getOrders().add(order);
	}
	
	
	/**
	 * Gets the specialofferboard.
	 *
	 * @return the specialofferboard
	 */
	public ConcreteSpecialOfferBoard getSpecialOfferBoard() {
		return specialofferboard;
	}

	

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 */
	public void addUser(User user){
		users.add(user);
	}
	
	/**
	 * Removes the user.
	 *
	 * @param user the user
	 */
	public void removeUser(User user){
		users.remove(user);
	}
	
	/**
	 * Activate user.
	 *
	 * @param user the user
	 * @throws NameNotFoundException the user not found exception
	 */
	public void activateUser(User user) throws NameNotFoundException{
		if(users.contains(user)){
			getActiveUsers().add(user);
			user.setActived(true);
		}else{
			throw new NameNotFoundException(user.getUsername());
		}
		
	}
	
	/**
	 * Disactivate user.
	 *
	 * @param user the user
	 * @throws NameNotFoundException the user not found exception
	 */
	public void disactivateUser(User user) throws NameNotFoundException {
		if(users.contains(user)){
			getActiveUsers().remove(user);
			user.setActived(false);
		}else{
			throw new NameNotFoundException(user.getUsername());
		}
		
	}

	/**
	 * Gets the available couriers.
	 *
	 * @return the available couriers
	 */
	public ArrayList<Courier> getAvailableCouriers() {
		ArrayList<Courier> availablecouriers = new ArrayList<Courier>();
		for (User c : getCouriers()){
			if (((Courier)c).isOn_duty()){
				availablecouriers.add((Courier) c);
			}
		}
		return availablecouriers;
	}
	
	public boolean hasUser(String username){
		for (User user : users){
			if (user.getUsername().equalsIgnoreCase(username)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the users of assigned type.
	 *
	 * @param userType the user type
	 * @return the users of assigned type
	 */
	public ArrayList<User> getUsersOfAssignedType(String userType){
		ArrayList<User> usersOfType = new ArrayList<User>();
		
		switch (userType.toUpperCase()) {
			case "MANAGER":
				for(User user : users){
					if(user instanceof Manager){
						usersOfType.add(user);
					}
				}
				break;
			case "RESTAURANT":
				for(User user : users){
					if(user instanceof Restaurant){
						usersOfType.add(user);
					}
				}
				break;
			case "CUSTOMER":
				for(User user : users){
					if(user instanceof Customer){
						usersOfType.add(user);
					}
				}
				break;
			case "COURIER":
				for(User user : users){
					if(user instanceof Courier){
						usersOfType.add(user);
					}
				}
				break;
	
			default:
				break;
		}
		
		return usersOfType;
	}
	
	/**
	 * Sets the targetprofitpolicy.
	 *
	 * @param tpp the new targetprofitpolicy
	 */
	public void setTargetprofitpolicy(TargetProfitPolicy tpp) {
		this.targetprofitpolicy=tpp;
	}


	public synchronized void register(User user) {
		// TODO Auto-generated method stub
		users.add(user);
	}


	public synchronized void unregister(User user) {
		// TODO Auto-generated method stub
		users.remove(user);
	}
	
}