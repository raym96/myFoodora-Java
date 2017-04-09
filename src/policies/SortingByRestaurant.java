package policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import restaurant.HalfMeal;
import restaurant.Meal;
import system.Order;
import user.model.Restaurant;

public class SortingByRestaurant extends SortingByCriteria {

	public SortingByRestaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashMap countOccurence(ArrayList<Order> h){
		HashMap<Restaurant, Integer> map = new HashMap<Restaurant,Integer>();
		for (Order order:h){
			Restaurant r = order.getRestaurant();
			if (map.containsKey(r)){
				map.put(r, map.get(r)+1);
			}else{
				map.put(r,1);
			}
		}
		return map;
	}
	
	@Override
	public void displayAscending(ArrayList<Order> h) {
		System.out.println("\nDisplaying restaurants sorted w.r.t the number of shipped orders ");
		// TODO Auto-generated method stub
		HashMap map = countOccurence(h);
		Map<Restaurant,Integer> newMap = sortByValues(map);
	    for (Entry<Restaurant, Integer> entry : newMap.entrySet()) {
            System.out.println("Restaurant <" + entry.getKey().getName()+">"
				+ "; number of orders: " + entry.getValue());
	    }
	}


	@Override
	public void displayDescending(ArrayList<Order> h) {
		// TODO Auto-generated method stub
		System.out.println("\nDisplaying restaurants sorted w.r.t the number of shipped orders ");
		HashMap map = countOccurence(h);
		Map<Restaurant,Integer> newMap = sortByValuesReversed(map);
	    for (Entry<Restaurant, Integer> entry : newMap.entrySet()) {
            System.out.println("Restaurant <" + entry.getKey().getName()+">"
				+ "; number of orders: " + entry.getValue());
	    }
	}
}
