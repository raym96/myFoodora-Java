package model.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.*;
import model.myfoodora.DeliveryPolicy;
import model.myfoodora.DeliveryTask;
import model.myfoodora.History;
import model.myfoodora.Message;
import model.myfoodora.MessageBoard;
import model.myfoodora.SpecialOffer;
import model.myfoodora.ConcreteSpecialOfferBoard;
import model.myfoodora.TargetProfitPolicy;
import model.myfoodora.TargetProfit_DeliveryCost;
import model.myfoodora.TargetProfit_Markup;
import model.myfoodora.TargetProfit_ServiceFee;
import model.restaurant.*;
import model.user.*;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class MyFoodora implements Observable{
	
	private ArrayList<User> users;
	private ArrayList<User> activeUsers;
	private ArrayList<Courier> couriers;
	private ArrayList<Courier> activecouriers;
	private ArrayList<Customer> specialofferobservers;

	private boolean delivery_task_state;
	private ArrayList<DeliveryTask> deliveryTasks;
	private DeliveryTask currentDeliveryTask;
	
	private ConcreteSpecialOfferBoard specialofferboard;
	
	private MessageBoard messageBoard;//OBSERVABLE, public message board
	
	private double service_fee;
	private double markup_percentage;
	private double delivery_cost;	
	
	private TargetProfitPolicy targetprofitpolicy;
	private DeliveryPolicy deliverypolicy;

	private History history;
	
	private double balance;
	
	//Singleton Pattern
	private static MyFoodora instance = null;
	private MyFoodora(){
		this.users = new ArrayList<User>();
		this.activeUsers = new ArrayList<User>();
		this.couriers = new ArrayList<Courier>();
		this.activecouriers = new ArrayList<Courier>();
		this.specialofferobservers = new ArrayList<Customer>();
		this.delivery_task_state = false;
		this.messageBoard = new MessageBoard(this);
		this.specialofferboard = new ConcreteSpecialOfferBoard();
	};
	
	private static synchronized void syncInit(){
		if(instance==null){
			instance = new MyFoodora();
		}
	}
	
	public static MyFoodora getInstance(){

		if(instance == null){
			syncInit();
		}
		return instance;
	}
	
	public Object readResolve(){
		return instance;
	}

	public void addSpecialOfferObserver(Customer c){
		specialofferobservers.add(c);
	}
	
	public void removeSpecialOfferObserver(Customer c){
		specialofferobservers.remove(c);
	}
	
	public ArrayList<Customer> getSpecialOfferObserver(){
		return specialofferobservers;
	}
	
	public void setDeliveryPolicy(DeliveryPolicy d){
		deliverypolicy = d;
	}
	
	public Courier parse(Order order){
		return deliverypolicy.parse(order, activecouriers);
	}
	
	
	public double getService_fee() {
		return service_fee;
	}

	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}

	public double getMarkup_percentage() {
		return markup_percentage;
	}

	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}

	public double getDelivery_cost() {
		return delivery_cost;
	}

	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}

	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getActiveUsers() {
		return activeUsers;
	}
	
	

	public ArrayList<Courier> getCouriers() {
		return couriers;
	}

	public void setActiveUsers(ArrayList<User> activeUsers) {
		this.activeUsers = activeUsers;
	}

	public ArrayList<Courier> getActivecouriers() {
		return activecouriers;
	}

	public void setActivecouriers(ArrayList<Courier> activecouriers) {
		this.activecouriers = activecouriers;
	}

	public TargetProfitPolicy getTargetprofitpolicy() {
		return targetprofitpolicy;
	}

	public DeliveryPolicy getDeliverypolicy() {
		return deliverypolicy;
	}

	public History getHistory() {
		return history;
	}

	public ConcreteSpecialOfferBoard getSpecialofferboard() {
		return specialofferboard;
	}

	public void displayUsers(){
		System.out.println("users::");
		System.out.println(users);
	}
	
	public void displayActiveUsers(){
		System.out.println("activeUsers::");
		System.out.println(activeUsers);	
	}

	public void addUser(User user){
		users.add(user);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}
	
	public void activateUser(User user){
		activeUsers.add(user);
	}
	
	public void disactivateUser(User user){
		activeUsers.remove(user);
	}

	public DeliveryTask getCurrentDeliveryTask() {
		return currentDeliveryTask;
	}

	public void setCurrentDeliveryTask(DeliveryTask currentDeliveryTask) {
		this.currentDeliveryTask = currentDeliveryTask;
	}
	
	public MessageBoard getMessageBoard() {
		return messageBoard;
	}
	
	public void refreshMessageBoard(){
		this.messageBoard.displayAllmsgs();
	}
	
	public ArrayList<User> getUsersOfAssignedType(String userType){
		ArrayList<User> usersOfType = new ArrayList<User>();
		
		switch (userType) {
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
	
	public void setTargetprofitpolicy(TargetProfitPolicy tpp) {
		this.targetprofitpolicy=tpp;
	}

	@Override
	public synchronized void register(Observer obs) {
		// TODO Auto-generated method stub
		users.add((User)obs);
		if(obs instanceof Courier){
			couriers.add((Courier)obs);
		}
		System.out.println("User " + ((User)obs).getUsername() + " has registed on myFoodora.");
	}

	@Override
	public synchronized void unregister(Observer obs) {
		// TODO Auto-generated method stub
		users.remove((User)obs);
		if(obs instanceof Courier){
			couriers.remove((Courier)obs);
		}
		System.out.println("User " + ((User)obs).getUsername() + " has registed on myFoodora.");
	}

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

	@Override
	public void notifyAllObservers(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void notifyObserver(Observer obs) {
		// TODO Auto-generated method stub
//		if( obs instanceof Courier ){
//			obs.update(this.currentDeliveryTask);
//		}
	}

	@Override
	public void notifyObserver(Observer obs, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers(ArrayList<Observer> observers) {
		// TODO Auto-generated method stub
		if(observers.get(0) instanceof Customer){
			
		}
	}

	@Override
	public void notifyObservers(ArrayList<User> observers, Object o) {
		// TODO Auto-generated method stub
		for(Observer obs : observers){
			obs.update(o);
		}
	}
	
	
}