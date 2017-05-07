package policies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import system.Order;
import user.model.Courier;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;

/**
 * The Class SortingByCourierDeliveries.
 */
public class SortingByCourierDeliveries extends SortingByCriteria {
	/**
	 * Instantiates a new sorting by restaurant.
	 */
	public SortingByCourierDeliveries() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#countOccurence(java.util.ArrayList)
	 */
	@Override
	public HashMap countOccurence(ArrayList<Order> h){
		HashMap<Courier, Integer> map = new HashMap<Courier,Integer>();
		ArrayList<User> couriers = MyFoodora.getInstance().getUsersOfAssignedType("courier");
		for (User u : couriers){
			map.put((Courier)u,0);
		}
		for (Order order:h){
			Courier co = order.getCourier();
			map.put(co, map.get(co)+1);
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
		Map<Courier,Integer> newMap = sortByValues(map);
	    for (Entry<Courier, Integer> entry : newMap.entrySet()) {
            System.out.println("Courier <" + entry.getKey().getFirstname()+">"
				+ "; number of delivered orders: " + entry.getValue());
	    }
	}


	/* (non-Javadoc)
	 * @see policies.SortingByCriteria#displayDescending(java.util.ArrayList)
	 */
	@Override
	public void displayDescending(ArrayList<Order> h) {
		// TODO Auto-generated method stub
		HashMap map = countOccurence(h);
		Map<Courier,Integer> newMap = sortByValuesReversed(map);
	    for (Entry<Courier, Integer> entry : newMap.entrySet()) {
            System.out.println("Courier <" + entry.getKey().getFirstname()+">"
				+ "; number of delivered orders: " + entry.getValue());
	    }
	}
}
