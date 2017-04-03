package model.myfoodora;

import java.util.*;

import model.restaurant.Order;

public abstract class SortingByCriteria {
	public abstract HashMap countOccurence(ArrayList<Order> history);
	public abstract void displayAscending(ArrayList<Order> history);
	public abstract void displayDescending(ArrayList<Order> history);
	
	
	//Sort a HashMap, used in all SortingByCriteria subclasses
	public static HashMap sortByValues(HashMap map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
	  }
	public static HashMap sortByValuesReversed(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       ListIterator it = list.listIterator();
	       while (it.hasNext()){
	    	   Object i = it.next();
	       }
	       while (it.hasPrevious()) {
	              Map.Entry entry = (Map.Entry) it.previous();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
		  }
}
