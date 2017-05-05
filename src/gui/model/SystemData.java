package gui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import clui.InitialScenario;
import exceptions.NameNotFoundException;
import policies.FairOccupationDeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.SortingByAlaCarte;
import policies.SortingByCourierDeliveries;
import policies.SortingByCriteria;
import policies.SortingByRestaurant;
import policies.TargetProfit_DeliveryCost;
import policies.TargetProfit_Markup;
import policies.TargetProfit_ServiceFee;
import restaurant.Dessert;
import restaurant.Dish;
import restaurant.Item;
import restaurant.MainDish;
import restaurant.Meal;
import restaurant.Starter;
import system.ConcreteShoppingCartVisitor;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;

public class SystemData {

	private static String startingDate = "01/01/2017";
	private static final String Monetary_unit = " euros";


	public SystemData() {
		super();

	}

	public static void initialMyFoodora(){
		MyFoodora.reset();
		InitialScenario.load("my_foodora.ini");
	}
	
	public static synchronized HashMap<String, String> updateParams(){
		HashMap<String, String> myfoodora_params = new HashMap<String, String>();
		myfoodora_params.put("targetProfit", String.valueOf(MyFoodora.getInstance().getTargetprofit()));
		myfoodora_params.put("servicefee", String.valueOf(MyFoodora.getInstance().getService_fee()));
		myfoodora_params.put("markupPercentage", String.valueOf(MyFoodora.getInstance().getMarkup_percentage()));
		myfoodora_params.put("deliveryCost", String.valueOf(MyFoodora.getInstance().getDelivery_cost()));

		if(MyFoodora.getInstance().getTargetprofitpolicy() instanceof TargetProfit_DeliveryCost){
			myfoodora_params.put("targetprofitPolicy", "delivery cost");
		}else if(MyFoodora.getInstance().getTargetprofitpolicy() instanceof TargetProfit_Markup){
			myfoodora_params.put("targetprofitPolicy", "markup percentage");
		}else if(MyFoodora.getInstance().getTargetprofitpolicy() instanceof TargetProfit_ServiceFee){
			myfoodora_params.put("targetprofitPolicy", "service fee");
		}
		
		if(MyFoodora.getInstance().getDeliverypolicy() instanceof FairOccupationDeliveryPolicy){
			myfoodora_params.put("deliveryPolicy", "fair-occupation");
		}else if(MyFoodora.getInstance().getDeliverypolicy() instanceof FastestDeliveryPolicy){
			myfoodora_params.put("deliveryPolicy", "fastest");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String endingDate = sdf.format(new Date());
		try {
			myfoodora_params.put("total income", String.valueOf(MyFoodora.getInstance().getService().getTotalIncome(startingDate, endingDate)));
			myfoodora_params.put("total profit", String.valueOf(MyFoodora.getInstance().getService().getTotalProfit(startingDate, endingDate)));
			myfoodora_params.put("average income per customer", String.valueOf(MyFoodora.getInstance().getService().getAverageIncomePerCustomer(startingDate, endingDate)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myfoodora_params;
	}
	
	public static synchronized ArrayList<String> getUsernamesFromUsers(ArrayList<User> users, String sorttype){
		
		ArrayList<String> usernames = new ArrayList<String>();
		
		if(users.get(0) instanceof Restaurant){
			ArrayList<Order> history = MyFoodora.getInstance().getHistory().getOrders();
			SortingByCriteria sortingcriteria = new SortingByRestaurant();
			HashMap<Restaurant,Integer> sortedRestaurant = null;
			if(sorttype.equalsIgnoreCase("asc")){
				sortedRestaurant = sortingcriteria.sortByValues(sortingcriteria.countOccurence(history));
			}else if(sorttype.equalsIgnoreCase("desc")){
				sortedRestaurant = sortingcriteria.sortByValuesReversed(sortingcriteria.countOccurence(history));
			}
			for (Entry<Restaurant, Integer> entry : sortedRestaurant.entrySet()) {
				usernames.add(entry.getKey().getUsername() + " - " + entry.getValue() + " orders");     
		    }
		}else if(users.get(0) instanceof Courier){
			ArrayList<Order> history = MyFoodora.getInstance().getHistory().getOrders();
			SortingByCriteria sortingcriteria = new SortingByCourierDeliveries();
			HashMap<Courier,Integer> sortedCourier = null;
			if(sorttype.equalsIgnoreCase("asc")){
				sortedCourier = sortingcriteria.sortByValues(sortingcriteria.countOccurence(history));
			}else if(sorttype.equalsIgnoreCase("desc")){
				sortedCourier = sortingcriteria.sortByValuesReversed(sortingcriteria.countOccurence(history));
			}
			for (Entry<Courier, Integer> entry : sortedCourier.entrySet()) {
				usernames.add(entry.getKey().getUsername() + " - " + entry.getValue() + " orders");     
		    }
		}else if(users.get(0) instanceof Customer){
			for(User user : users){
				usernames.add(user.getUsername());
			}
		}	
		return usernames;
	}
	
	public static synchronized ArrayList<String> getUsernamesFromUsers(ArrayList<User> users){
		
		ArrayList<String> usernames = new ArrayList<String>();
		
		for(User user : users){
			usernames.add(user.getUsername());
		}
		
		return usernames;
		
	}
	
	public static synchronized ArrayList<String> getDishnamesFromRestaurant(Restaurant r, String sorttype){
		ArrayList<String> dishnames = new ArrayList<String>();
		
		ArrayList<Order> history = MyFoodora.getInstance().getHistory().getOrders();
		HashMap<Dish, Integer> map = new HashMap<Dish, Integer>();

		ArrayList<Dish> dishes = r.getMenu().getDishes();
		for (Dish dish:dishes){
			map.put(dish,0);
		}

		
		//count occurence
		for (Order order : history){
			if (r==order.getRestaurant()){

				for (Item item: order.getItems()){
					if (item instanceof Dish){
						Dish dish = (Dish)item;
						map.put(dish, map.get(dish)+1);

					}
				}
			}
		}
		
		SortingByCriteria sortingcriteria = new SortingByAlaCarte();
		HashMap<Dish,Integer> sortedDish = null;
		if(sorttype.equalsIgnoreCase("asc")){
			sortedDish = sortingcriteria.sortByValues(map);
		}else if(sorttype.equalsIgnoreCase("desc")){
			sortedDish = sortingcriteria.sortByValuesReversed(map);
		}
		for (Entry<Dish, Integer> entry : sortedDish.entrySet()) {
			dishnames.add(entry.getKey().getDishName() + " - " + entry.getValue() + " orderd");     
	    }
		
		return dishnames;
	}
	
	public static synchronized ArrayList<String> getDishnamesFromRestaurant(Restaurant r){
		ArrayList<String> dishnames = new ArrayList<String>();
	
		for(Dish d : r.getMenu().getDishes()){
			dishnames.add(d.getDishName());
		}
		
		return dishnames;
	}
	
	public static synchronized ArrayList<String> getDishnamesFromRestaurantByCategory(Restaurant r, String dishCategory){
		ArrayList<String> dishnames = new ArrayList<String>();
		for(Dish d : r.getMenu().getDishes()){
			if(dishCategory.equalsIgnoreCase("Starter")){
				if(d instanceof Starter){
					dishnames.add(d.getDishName());
				}
			}else if(dishCategory.equalsIgnoreCase("Main")){
				if(d instanceof MainDish){
					dishnames.add(d.getDishName());
				}
			}else if(dishCategory.equalsIgnoreCase("Dessert")){
				if(d instanceof Dessert){
					dishnames.add(d.getDishName());
				}
			}	
		}
		return dishnames;
	}
	
	public static synchronized ArrayList<String> getDishnamesFromRestaurantByCategoryWithPrice(Restaurant r, String dishCategory){
		ArrayList<String> dishnames = new ArrayList<String>();
		for(Dish d : r.getMenu().getDishes()){
			if(dishCategory.equalsIgnoreCase("Starter")){
				if(d instanceof Starter){
					dishnames.add(d.getDishName() + " - " + d.getPrice() + Monetary_unit);
				}
			}else if(dishCategory.equalsIgnoreCase("Main")){
				if(d instanceof MainDish){
					dishnames.add(d.getDishName() + " - " + d.getPrice() + Monetary_unit);
				}
			}else if(dishCategory.equalsIgnoreCase("Dessert")){
				if(d instanceof Dessert){
					dishnames.add(d.getDishName() + " - " + d.getPrice() + Monetary_unit);
				}
			}	
		}
		return dishnames;
	}
	
	public static synchronized ArrayList<String> getMealnamesFromRestaurant(Restaurant r){
		ArrayList<String> mealnames = new ArrayList<String>();
		for(Meal m : r.getMealMenu().getMeals()){
			mealnames.add(m.getName());
		}
		return mealnames;
	}
	
	public static synchronized ArrayList<String> getMealnamesFromRestaurantWithPrice(Restaurant r){
		ArrayList<String> mealnames = new ArrayList<String>();
		for(Meal m : r.getMealMenu().getMeals()){
			m.setRestaurant(r);
			mealnames.add(m.getName() + " - " + m.accept(new ConcreteShoppingCartVisitor()) + Monetary_unit);
		}
		return mealnames;
	}
	
	public static synchronized ArrayList<String> getSpecialmealnamesFromRestaurant(Restaurant r){
		ArrayList<String> mealnames = new ArrayList<String>();
		for(Meal m : r.getSpecialmealmenu().getMeals()){
			mealnames.add(m.getName());
		}
		return mealnames;
	}
	
	public static synchronized ArrayList<String> getSpecialmealnamesFromRestaurantWithPrice(Restaurant r){
		ArrayList<String> mealnames = new ArrayList<String>();
		for(Meal m : r.getSpecialmealmenu().getMeals()){
			m.setRestaurant(r);
			mealnames.add(m.getName() + " - " + m.accept(new ConcreteShoppingCartVisitor()) + Monetary_unit);
		}
		return mealnames;
	}
	
	public static synchronized ArrayList<String> getAssignedOrderNames(){
		ArrayList<String> ordernames = new ArrayList<String>();
		
		for(Order order : MyFoodora.getInstance().getHistory().getOrders()){
			if(order.getCourier() != null){
				ordernames.add(order.getName() + " - " + order.getOrderID());
			}
			
		}
		
		return ordernames;
	}
	
	public static synchronized ArrayList<String> getAssignedOrderNamesByCourier(Courier courier){
		ArrayList<String> ordernames = new ArrayList<String>();
		
		for(Order order : MyFoodora.getInstance().getHistory().getOrders()){
			if(order.getCourier() == courier){
				ordernames.add(order.getName() + " - " + order.getOrderID());
			}
			
		}
		
		return ordernames;
	}
	
	public static synchronized ArrayList<String> getUnassignedOrderNames(){
		ArrayList<String> ordernames = new ArrayList<String>();
		
		for(Order order : MyFoodora.getInstance().getHistory().getOrders()){
			if(order.getCourier() == null){
				ordernames.add(order.getName() + " - " + order.getOrderID());
			}
			
		}
		
		return ordernames;
	}

	public static void main(String[] args) throws NameNotFoundException {
		
		SystemData.initialMyFoodora();
		
		System.out.println(SystemData.updateParams().toString());
	}
}
