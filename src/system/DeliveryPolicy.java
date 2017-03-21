package system;

import java.util.ArrayList;

import model.restaurant.Order;
import model.user.Courier;

//Strategy pattern
public interface DeliveryPolicy {

	public abstract Courier parse(Order order, ArrayList<Courier> activecouriers);
	
}
