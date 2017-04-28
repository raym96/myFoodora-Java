package user.view;

import java.util.ArrayList;

import system.Order;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;

public class MyFoodoraView{

	private MyFoodora myfoodora;
	
	public MyFoodoraView(MyFoodora myfoodora){
		super();
		this.myfoodora=myfoodora;
	}
	

	public void showUsersOfAssignedType(String userType){
		ArrayList<User> users = myfoodora.getUsersOfAssignedType(userType);
		if (users.size()==0){
			System.out.println("The user type "+userType +" is not recognized.");
			return;
		}
		System.out.println("[" + userType.toUpperCase() + "]");
		for(User user :users){
			 System.out.println(user);
		}
	}
	
	/**
	 * Display users.
	 */
	public void showUsers(){
		System.out.println("\n[USERS]");
		for (User u:myfoodora.getUsers()){
			u.getView().showInfo();
		}
	}
	
	/**
	 * Display active users.
	 */
	public void showActiveUsers(){
		System.out.println("\n[ACTIVEUSERS]");
		for (User u :myfoodora.getActiveUsers()){
			u.getView().showInfo();
		}	
	}
	
	/**
	 * Display menus of all restaurants.
	 */
	public void showRestaurantMenus() {
		for (User u:myfoodora.getUsersOfAssignedType("RESTAURANT")){
			((Restaurant)u).getView().showMenu();
		}
	}
	

	/**
	 * Display history.
	 */
	public void showHistory(){
		System.out.println("\n"+myfoodora.getHistory());
	}
	
	public void showSystemValues(){
		System.out.println("Delivery cost = "+myfoodora.getDelivery_cost());
		System.out.println("Mark up percentage = "+myfoodora.getMarkup_percentage());
		System.out.println("Service fee = "+myfoodora.getService_fee());
	}
	
	public void showPolicies(){
		System.out.println("Delivery policy = "+myfoodora.getDeliverypolicy());
		System.out.println("Target profit policy = "+myfoodora.getTargetprofitpolicy());
	}
}
