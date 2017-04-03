package model.myfoodora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import model.restaurant.*;

public class SortingByAlaCarte extends SortingByCriteria {
	
	@Override
	public HashMap countOccurence(ArrayList<Order> h){
		HashMap<Dish, Integer> map = new HashMap<Dish,Integer>();
		for (Order order:h){
			if (order instanceof AlaCarteOrder){
				Dish dish = ((AlaCarteOrder)order).getDish();
				if (map.containsKey(dish)){
					map.put(dish, map.get(dish)+1);
				}else{
					map.put(dish,1);
				}
			}
		}
		return map;
	}
	@Override
	public void displayAscending(ArrayList<Order> h) {
		System.out.println("Displaying all menu items sorted w.r.t the number of shipped half-meals: ");
		HashMap map = countOccurence(h);
		Map<Dish,Integer> newMap = sortByValues(map);
	    for (Entry<Dish, Integer> entry : newMap.entrySet()) {
            System.out.println("Dish <" + entry.getKey().getDishName()+">"
				+ "; Number of shipped items : " + entry.getValue());
	    }
	}


	@Override
	public void displayDescending(ArrayList<Order> h) {
		System.out.println("Displaying all menu items sorted w.r.t the number of shipped half-meals: ");
		HashMap map = countOccurence(h);
		Map<Dish,Integer> newMap = sortByValuesReversed(map);
	    for (Entry<Dish, Integer> entry : newMap.entrySet()) {
            System.out.println("Dish <" + entry.getKey().getDishName()+">"
				+ "; Number of shipped items : " + entry.getValue());
	    }
	}
}