/*
 * 
 */
package policies;

import java.util.ArrayList;

import system.Order;
import user.model.Courier;

// TODO: Auto-generated Javadoc
/**
 * The Class FairOccupationDeliveryPolicy.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class FairOccupationDeliveryPolicy implements DeliveryPolicy {

	/**
	 * Instantiates a new fair occupation delivery policy.
	 */
	public FairOccupationDeliveryPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see policies.DeliveryPolicy#parse(system.Order, java.util.ArrayList)
	 */
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
