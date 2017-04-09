package policies;

import java.util.ArrayList;

import system.AddressPoint;
import system.Order;
import user.model.Courier;

public class FastestDeliveryPolicy implements DeliveryPolicy {
	
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
}
