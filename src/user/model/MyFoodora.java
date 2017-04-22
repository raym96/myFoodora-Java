/*
 * 
 */
 package user.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.NameNotFoundException;
import policies.DeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.TargetProfitPolicy;
import policies.TargetProfit_DeliveryCost;
import policies.TargetProfit_Markup;
import policies.TargetProfit_ServiceFee;
import restaurant.*;
import system.ConcreteSpecialOfferBoard;
import system.History;
import system.MessageBoard;
import system.Observable;
import system.Observer;
import system.Order;
import user.service.MyFoodoraService;
import user.service.impl.MyFoodoraServiceImpl;


/**
 * The Class MyFoodora.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyFoodora implements Observable{
	
	/** The users. */
	private ArrayList<User> users;

	/** The specialofferobservers. */
	private ArrayList<Customer> specialofferobservers;

	/** The current delivery task. */
	private Order currentDeliveryTask;
	
	/** The specialofferboard. */
	private ConcreteSpecialOfferBoard specialofferboard;
	
	/** The message board. */
	private MessageBoard messageBoard;//OBSERVABLE, public message board
	
	/** The service fee. */
	private double service_fee;
	
	/** The markup percentage. */
	private double markup_percentage;
	
	/** The delivery cost. */
	private double delivery_cost;	
	
	/** The targetprofitpolicy. */
	private TargetProfitPolicy targetprofitpolicy;
	
	/** The deliverypolicy. */
	private DeliveryPolicy deliverypolicy;

	/** The history. */
	private History history;
	
	/** The balance. */
	private double balance;
	
	/** The instance. */
	//Singleton Pattern
	private static MyFoodora instance = null;
	
	/**
	 * Instantiates a new my foodora.
	 */
	private MyFoodora(){
		this.users = new ArrayList<User>();
		this.specialofferobservers = new ArrayList<Customer>();
		this.messageBoard = new MessageBoard(this);
		this.specialofferboard = new ConcreteSpecialOfferBoard();
		this.history = new History();
		
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
	public MyFoodoraService getMyFoodoraService(){
		return new MyFoodoraServiceImpl();
	}
	
	/**
	 * Adds the special offer observer.
	 *
	 * @param c the c
	 */
	public void addSpecialOfferObserver(Customer c){
		specialofferobservers.add(c);
	}
	
	/**
	 * Removes the special offer observer.
	 *
	 * @param c the c
	 */
	public void removeSpecialOfferObserver(Customer c){
		specialofferobservers.remove(c);
	}
	
	/**
	 * Gets the special offer observer.
	 *
	 * @return the special offer observer
	 */
	public ArrayList<Customer> getSpecialOfferObserver(){
		return specialofferobservers;
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
	public ConcreteSpecialOfferBoard getSpecialofferboard() {
		return specialofferboard;
	}

	/**
	 * Display users.
	 */
	public void displayUsers(){
		System.out.println("\n[USERS]");
		for (User u:users){
			System.out.println(u);
		}
	}
	
	/**
	 * Display active users.
	 */
	public void displayActiveUsers(){
		System.out.println("\n[ACTIVEUSERS]");
		for (User u : getActiveUsers() ){
			System.out.println(u);
		}	
	}
	
	/**
	 * Display all menus.
	 */
	public void displayAllMenus() {
		System.out.println("\n[ALL MENUS]");
		for (User u:getUsersOfAssignedType("RESTAURANT")){
			((Restaurant)u).getRestaurantService().displayAllMenu();
		}
		System.out.println("[/All MENUS]");
	}
	
	/**
	 * Display history.
	 */
	public void displayHistory(){
		System.out.println("\n"+history);
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
		System.out.println("User " + (user).getUsername() + " has unregistered from myFoodora.");

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
	 * Gets the current delivery task.
	 *
	 * @return the current delivery task
	 */
	public Order getCurrentDeliveryTask() {
		return currentDeliveryTask;
	}

	/**
	 * Sets the current delivery task.
	 *
	 * @param currentDeliveryTask the new current delivery task
	 */
	public void setCurrentDeliveryTask(Order currentDeliveryTask) {
		this.currentDeliveryTask = currentDeliveryTask;
	}
	
	/**
	 * Gets the message board.
	 *
	 * @return the message board
	 */
	public MessageBoard getMessageBoard() {
		return messageBoard;
	}
	
	/**
	 * Refresh message board.
	 */
	public void refreshMessageBoard(){
		this.messageBoard.displayAllmsgs();
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

	/* (non-Javadoc)
	 * @see system.Observable#register(system.Observer)
	 */
	@Override
	public synchronized void register(Observer obs) {
		// TODO Auto-generated method stub
		users.add((User)obs);
	}

	/* (non-Javadoc)
	 * @see system.Observable#unregister(system.Observer)
	 */
	@Override
	public synchronized void unregister(Observer obs) {
		// TODO Auto-generated method stub
		users.remove((User)obs);
		System.out.println("User " + ((User)obs).getUsername() + " has unregistered from myFoodora.");
	}

	/* (non-Javadoc)
	 * @see system.Observable#notifyAllObservers()
	 */
	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
//		if (this.delivery_task_state){
//			for (Observer ob : couriers){
//				ob.update(this.deliveryTasks);
//			}
//			this.delivery_task_state=false;
//		}
	}

	/* (non-Javadoc)
	 * @see system.Observable#notifyAllObservers(java.lang.Object)
	 */
	@Override
	public void notifyAllObservers(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see system.Observable#notifyObserver(system.Observer)
	 */
	@Override
	public void notifyObserver(Observer obs) {
		// TODO Auto-generated method stub
//		if( obs instanceof Courier ){
//			obs.update(this.currentDeliveryTask);
//		}
	}

	/* (non-Javadoc)
	 * @see system.Observable#notifyObserver(system.Observer, java.lang.Object)
	 */
	@Override
	public void notifyObserver(Observer obs, Object o) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see system.Observable#notifyObservers(java.util.ArrayList)
	 */
	@Override
	public void notifyObservers(ArrayList<Observer> observers) {
		// TODO Auto-generated method stub
		if(observers.get(0) instanceof Customer){
			
		}
	}

	/* (non-Javadoc)
	 * @see system.Observable#notifyObservers(java.util.ArrayList, java.lang.Object)
	 */
	@Override
	public void notifyObservers(ArrayList<User> observers, Object o) {
		// TODO Auto-generated method stub
		for(Observer obs : observers){
			obs.update(o);
		}
	}



	
}