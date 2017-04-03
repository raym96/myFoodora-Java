package model.myfoodora;

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
import java.util.TreeMap;

import model.restaurant.*;
import model.users.Restaurant;

public class SortingByHalfMeal extends SortingByCriteria{

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
	
	@Override
	public void displayAscending(ArrayList<Order> h) {
		System.out.println("Displaying all menu items sorted w.r.t the number of shipped half-meals: ");
		// TODO Auto-generated method stub
		HashMap map = countOccurence(h);
		Map<HalfMeal,Integer> newMap = sortByValues(map);
	    for (Entry<HalfMeal, Integer> entry : newMap.entrySet()) {
            System.out.println("Half-meal <" + entry.getKey()+">"
				+ "; number of shipped half-meals: " + entry.getValue());
	    }
	}


	@Override
	public void displayDescending(ArrayList<Order> h) {
		// TODO Auto-generated method stub
		System.out.println("Displaying all menu items sorted w.r.t the number of shipped half-meals: ");
		HashMap map = countOccurence(h);
		Map<HalfMeal,Integer> newMap = sortByValuesReversed(map);
	    for (Entry<HalfMeal, Integer> entry : newMap.entrySet()) {
            System.out.println("Half-meal <" + entry.getKey()+">"
				+ "; number of shipped half-meals: " + entry.getValue());
	    }
	}
}
