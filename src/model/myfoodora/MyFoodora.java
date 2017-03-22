package model.myfoodora;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import exceptions.UserNotFoundException;
import model.customer.*;
import model.myfoodora.DeliveryPolicy;
import model.myfoodora.History;
import model.myfoodora.SpecialOffer;
import model.myfoodora.SpecialOfferBoard;
import model.myfoodora.TargetProfitPolicy;
import model.myfoodora.TargetProfit_DeliveryCost;
import model.myfoodora.TargetProfit_Markup;
import model.myfoodora.TargetProfit_ServiceFee;
import model.restaurant.*;
import model.user.*;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class MyFoodora{
	
	public ArrayList<User> users;
	public ArrayList<User> activeUsers;
	public ArrayList<Courier> activecouriers;
	
	public SpecialOfferBoard specialofferboard;
	
	private double service_fee;
	private double markup_percentage;
	private double delivery_cost;	
	
	private TargetProfitPolicy targetprofitpolicy;
	private DeliveryPolicy deliverypolicy;

	private History history;
	
	private double balance;
	
	//Singleton Pattern
	private static MyFoodora instance = null;
	private MyFoodora(){}
	
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

	
	
	public SpecialOfferBoard getSpecialofferboard() {
		return specialofferboard;
	}

	public void displayUsers(){
		System.out.println(users);
	}
	
	public void displayActiveUsers(){
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
	
}