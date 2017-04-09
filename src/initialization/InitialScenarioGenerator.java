/*
 * 
 */
package initialization;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.ini4j.Ini;

import restaurant.MealMenu;
import restaurant.Menu;
import user.model.Customer;
import user.model.Restaurant;

// TODO: Auto-generated Javadoc
/** Provides methods to outprint initial scenarios, which can be copy-pasted
 *  to an .ini file, and once filled can be loaded by InitialScenario.load()
 *  
 * @author He Xiaoan
 * @author Ji Raymond
 *
 */

public class InitialScenarioGenerator {
	
	/** The n customer. */
	static int nCustomer = 2;
	
	/** The n restaurant. */
	static int nRestaurant = 2;
	
	/** The n manager. */
	static int nManager = 1;
	
	/** The n courier. */
	static int nCourier = 3;
	
	/** The n menu. */
	static int nMenu = 6; //total number of dishes per restaurant: 2 ST + 2 MD + 2 DS
	
	/** The n meal menu. */
	static int nMealMenu = 5; // 4 HM + 4 FM + 1SM per restaurant
	
	/** The n order. */
	static int nOrder = 30;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		outPrintOrders();
	}
	
	/**
	 * Out print customer.
	 */
	public static void outPrintCustomer(){
		for (int i = 1;i<nCustomer+1;i++){
			System.out.println("[Customer/"+i+"]");
			System.out.println("name=");
			System.out.println("surname=");
			System.out.println("username=");
			System.out.println("address=");
			System.out.println("email=");
			System.out.println("phone=");
			System.out.println();
		}
	}
	
	/**
	 * Out print manager.
	 */
	public static void outPrintManager(){
		for (int i = 1; i<nManager+1;i++){
			System.out.println("[Manager/"+i+"]");
			System.out.println("name=");
			System.out.println("surname=");
			System.out.println("username=");
			System.out.println();
		}
	}
	
	/**
	 * Out print restaurant.
	 */
	public static void outPrintRestaurant(){
		for (int i = 1; i<nRestaurant+1;i++){
			System.out.println("[Restaurant/"+i+"]");
			System.out.println("name=");
			System.out.println("username=");
			System.out.println("address=");
			System.out.println();
		}
	}
	
	/**
	 * Out print courier.
	 */
	public static void outPrintCourier(){
		for (int i = 1; i<nCourier+1;i++){
			System.out.println("[Courier/"+i+"]");
			System.out.println("name=");
			System.out.println("surname=");
			System.out.println("username=");
			System.out.println("position=");
			System.out.println("phone=");
			System.out.println();
		}
	}
		
	/**
	 * Out print menu.
	 */
	public static void outPrintMenu(){
		for (int i = 0; i<nRestaurant*nMenu; i++){
			System.out.println("[Dish/"+(i+1)+"]");
			if (i%6<2){
				System.out.println("category=Starter");
			}
			if (i%6<4 && i%6>=2){
				System.out.println("category=Main-dish");
			}
			if (i%6>=4){
				System.out.println("category=Dessert");
			}
			System.out.println("name=");
			System.out.println("type=");
			System.out.println("price=");
			System.out.println("restaurant=");
			System.out.println();
		}
	}
	
	/**
	 * Out print meal menu.
	 */
	public static void outPrintMealMenu(){
		for (int i = 0; i<nRestaurant*nMealMenu; i++){
			System.out.println("[Meal/"+(i+1)+"]");
			if (i%9<4){
				System.out.println("category=Full-meal");
				System.out.println("dish1=");
				System.out.println("dish2=");
				System.out.println("dish3=");
			}
			if (i%9<8 && i%9>=4){
				System.out.println("category=Half-meal");
				System.out.println("dish1=");
				System.out.println("dish2=");
			}
			if (i%9==8){
				System.out.println("category=Special-offer");
				System.out.println("dish1=");
				System.out.println("dish2=");
			}
			System.out.println("restaurant=restaurant_");
			System.out.println();
		}
	}
	
	/**
	 * Out print orders.
	 */
	public static void outPrintOrders(){
		Random random = new Random();
		try{
		Ini ini = new Ini(new File("scenario_test_services.ini"));
		ArrayList<Restaurant> restaurants = InitialScenario.loadRestaurant(ini);
		ArrayList<Customer> customers = InitialScenario.loadCustomer(ini);
		for (int i = 0;i<nOrder;i++){
			System.out.println("[Order/"+(i+1)+"]");
			int c = random.nextInt(customers.size());
			int r = random.nextInt(restaurants.size());
			System.out.println("customer="+customers.get(c).getUsername());
			System.out.println("restaurant="+restaurants.get(r).getUsername());
			if (random.nextInt()%3==0){
				System.out.println("category=Half-meal");
				MealMenu mealmenu = restaurants.get(r).getHalfMealMenu();
				System.out.println("name="+mealmenu.getMeals().get(random.nextInt(mealmenu.getMeals().size())).getName());
			}
			else if (random.nextInt()%3==1){
				System.out.println("category=Full-meal");
				MealMenu mealmenu = restaurants.get(r).getFullMealMenu();
				System.out.println("name="+mealmenu.getMeals().get(random.nextInt(mealmenu.getMeals().size())).getName());
			}
			else {
				System.out.println("category=A-la-carte");
				Menu menu = restaurants.get(r).getMenu();
				System.out.println("name="+menu.getDishes().get(random.nextInt(menu.getDishes().size())).getDishName());
			}
			System.out.println("courier=courier_"+(random.nextInt(nCourier)+1));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd, hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -random.nextInt(31)); 
			cal.add(Calendar.MINUTE, +random.nextInt(1440));
			String date = sdf.format(cal.getTime());
			
			System.out.println("date="+date);
			System.out.println();
		}
		}catch(IOException e){}
	}
}
