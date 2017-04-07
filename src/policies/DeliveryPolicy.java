package model.myfoodora;

import java.util.ArrayList;

import model.restaurant.Order;
import model.users.Courier;

//Strategy pattern
public interface DeliveryPolicy {

	public abstract Courier parse(Order order, ArrayList<Courier> activecouriers);
	
}
