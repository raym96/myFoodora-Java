/*
 * 
 */
package policies;

import java.util.ArrayList;

import system.AddressPoint;
import system.Order;
import user.model.Courier;

/**
 * The Class FastestDeliveryPolicy.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class FastestDeliveryPolicy implements DeliveryPolicy {
	
	/* (non-Javadoc)
	 * @see policies.DeliveryPolicy#parse(system.Order, java.util.ArrayList)
	 */
	@Override
	public Courier parse(Order order, ArrayList<Courier> activecouriers) {
		double min = Integer.MAX_VALUE ;
		Courier assignedcourier = null;
		AddressPoint addressRestaurant = order.getRestaurant().getAddress();
		for (Courier c:activecouriers){
			//find the closest courier
			double distance = addressRestaurant.calculateDistance(c.getPosition());
			if (distance<min){
				min = distance;
				assignedcourier = c;
			}
		}
		return assignedcourier;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FairOccupationDeliveryPolicy : the courier with the least number of completed delivery is chosen.";
	}
}
