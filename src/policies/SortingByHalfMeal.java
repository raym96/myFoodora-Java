/*
 * 
 */
package policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import restaurant.*;
import system.Order;
import system.StandardMealOrder;

import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class SortingByHalfMeal.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class SortingByHalfMeal extends SortingByCriteria{

	/**
	 * Instantiates a new sorting by half meal.
	 */
	public SortingByHalfMeal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#countOccurence(java.util.ArrayList)
	 */
	@Override
	public HashMap countOccurence(ArrayList<Order> h){
		HashMap<HalfMeal, Integer> map = new HashMap<HalfMeal,Integer>();
		for (Order order:h){
			if (order instanceof StandardMealOrder){
				if (((StandardMealOrder)order).getMeal() instanceof HalfMeal){
					HalfMeal hm = (HalfMeal) ((StandardMealOrder)order).getMeal();
					if (map.containsKey(hm)){
						map.put(hm, map.get(hm)+1);
					}else{
						map.put(hm,1);
					}
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
		// TODO Auto-generated method stub
		HashMap map = countOccurence(h);
		Map<HalfMeal,Integer> newMap = sortByValues(map);
	    for (Entry<HalfMeal, Integer> entry : newMap.entrySet()) {
            System.out.println("Half-meal <" + entry.getKey().getName()+">"
				+ "; number of shipped half-meals: " + entry.getValue());
	    }
	}


	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#displayDescending(java.util.ArrayList)
	 */
	@Override
	public void displayDescending(ArrayList<Order> h) {
		// TODO Auto-generated method stub
		HashMap map = countOccurence(h);
		Map<HalfMeal,Integer> newMap = sortByValuesReversed(map);
	    for (Entry<HalfMeal, Integer> entry : newMap.entrySet()) {
            System.out.println("Half-meal <" + entry.getKey().getName()+">"
				+ "; number of shipped half-meals: " + entry.getValue());
	    }
	}
}
