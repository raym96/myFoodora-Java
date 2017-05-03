/*
 * 
 */
package policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import restaurant.*;
import system.Order;


/**
 * The Class SortingByAlaCarte.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SortingByAlaCarte extends SortingByCriteria {

	/**
	 * Instantiates a new sorting by ala carte.
	 */
	public SortingByAlaCarte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#countOccurence(java.util.ArrayList)
	 */
	@Override
	public HashMap countOccurence(ArrayList<Order> history){
		HashMap<Dish, Integer> map = new HashMap<Dish,Integer>();
		
		//assign value 0 at the beginning
		ArrayList<Dish> dishes = history.get(0).getRestaurant().getMenu().getDishes();
		for (Dish dish:dishes){
			map.put(dish,0);
		}
		
		//count occurence
		for (Order order:history){
			for (Item item: order.getItems()){
				if (item instanceof Dish){
					Dish dish = (Dish)item;

					map.put(dish, map.get(dish)+1);
				}
			}
		}
		return map;
	}
	
	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#displayAscending(java.util.ArrayList)
	 */
	@Override
	public void displayAscending(ArrayList<Order> h) {
		HashMap map = countOccurence(h);
		Map<Dish,Integer> newMap = sortByValues(map);
	    for (Entry<Dish, Integer> entry : newMap.entrySet()) {
            System.out.println("Dish <" + entry.getKey().getDishName()+">"
				+ "; Number of shipped items : " + entry.getValue());
	    }
	}


	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#displayDescending(java.util.ArrayList)
	 */
	@Override
	public void displayDescending(ArrayList<Order> h) {
		HashMap map = countOccurence(h);
		Map<Dish,Integer> newMap = sortByValuesReversed(map);
	    for (Entry<Dish, Integer> entry : newMap.entrySet()) {
            System.out.println("Dish <" + entry.getKey().getDishName()+">"
				+ "; Number of shipped items : " + entry.getValue());
	    }
	}
}