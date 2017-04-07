package model.myfoodora;

import java.util.ArrayList;

import model.restaurant.Order;
import model.users.AddressPoint;
import model.users.Courier;

public class FastestDelivery implements DeliveryPolicy {
	
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
