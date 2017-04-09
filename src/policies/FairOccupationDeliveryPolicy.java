package policies;

import java.util.ArrayList;

import system.Order;
import user.model.Courier;

public class FairOccupationDeliveryPolicy implements DeliveryPolicy {

	public FairOccupationDeliveryPolicy() {
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
