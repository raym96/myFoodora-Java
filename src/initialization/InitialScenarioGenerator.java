package initialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import restaurant.MealMenu;
import restaurant.Menu;
import user.Customer;
import user.Restaurant;

/** Provides methods to outprint initial scenarios, which can be copy-pasted
 *  to an .ini file, and once filled can be loaded by InitialScenario.load()
 *  
 * @author Ray
 *
 */

public class InitialScenarioGenerator {
	
	static int nCustomer = 2;
	static int nRestaurant = 2;
	static int nManager = 1;
	static int nCourier = 3;
	static int nMenu = 6; //total number of dishes per restaurant: 2 ST + 2 MD + 2 DS
	static int nMealMenu = 5; // 4 HM + 4 FM + 1SM per restaurant
	static int nOrder = 30;
	
	public static void main(String[] args) {
		outPrintOrders();
	}
	
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
	
	public static void outPrintManager(){
		for (int i = 1; i<nManager+1;i++){
			System.out.println("[Manager/"+i+"]");
			System.out.println("name=");
			System.out.println("surname=");
			System.out.println("username=");
			System.out.println();
		}
	}
	
	public static void outPrintRestaurant(){
		for (int i = 1; i<nRestaurant+1;i++){
			System.out.println("[Restaurant/"+i+"]");
			System.out.println("name=");
			System.out.println("username=");
			System.out.println("address=");
			System.out.println();
		}
	}
	
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
	
	public static void outPrintOrders(){
		Random random = new Random();
		try{
		ArrayList<Restaurant> restaurants = InitialScenario.loadRestaurant("initialScenarioForTests.ini");
		ArrayList<Customer> customers = InitialScenario.loadCustomer("initialScenarioForTests.ini");
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
			System.out.println("courier=courier_"+(random.nextInt(4)+1));
			System.out.println();
		}
		}catch(IOException e){}
	}
}
