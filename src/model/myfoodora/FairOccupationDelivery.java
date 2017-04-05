package model.myfoodora;

import java.util.ArrayList;

import model.restaurant.Order;
import model.users.Courier;

public class FairOccupationDelivery implements DeliveryPolicy {

	public FairOccupationDelivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Courier parse(Order order, ArrayList<Courier> activecouriers) {
		int mincount = Integer.MAX_VALUE;
		Courier assignedcourier = null;
		for (Courier c : activecouriers){
			if (c.getCount()<mincount){
				mincount = c.getCount();
				assignedcourier = c;
			}
		}
		return assignedcourier;
	}
	
}
