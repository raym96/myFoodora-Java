/*
 * 
 */
package policies;

import java.util.*;

import system.Order;

/**
 * The Class SortingByCriteria.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class SortingByCriteria {
	
	/**
	 * Count occurence.
	 *
	 * @param history the history
	 * @return the hash map
	 */
	public abstract HashMap countOccurence(ArrayList<Order> history);

	/**
	 * Display history in ascending order.
	 *
	 * @param history the history
	 */
	public abstract void displayAscending(ArrayList<Order> history);

	/**
	 * Display history in descending order.
	 *
	 * @param history the history
	 */
	public abstract void displayDescending(ArrayList<Order> history);

	/**
	 * Sort by values.
	 *
	 * @param map the map
	 * @return the hash map
	 */
	// Sort a HashMap, used in all SortingByCriteria subclasses
	public static HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
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

	/**
	 * Sort by values reversed.
	 *
	 * @param map the map
	 * @return the hash map
	 */
	public static HashMap sortByValuesReversed(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		ListIterator it = list.listIterator();
		while (it.hasNext()) {
			Object i = it.next();
		}
		while (it.hasPrevious()) {
			Map.Entry entry = (Map.Entry) it.previous();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
}
