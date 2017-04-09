package policies;

import java.util.ArrayList;

import system.Order;
import user.model.Courier;

//Strategy pattern
public interface DeliveryPolicy {

	public abstract Courier parse(Order order, ArrayList<Courier> activecouriers);
	
}
