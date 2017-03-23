package model.myfoodora;

import model.restaurant.Order;
import model.user.AddressPoint;
import model.user.Courier;

public class DeliveryTask {

	private Order order;
	private AddressPoint destination;
	private Courier courier;
	private DeliveryPolicy deliveryPolicy;
	private boolean assigned;
	
	public DeliveryTask(Order order, AddressPoint destination, DeliveryPolicy deliveryPolicy) {
		super();
		this.order = order;
		this.destination = destination;
		this.deliveryPolicy = deliveryPolicy;
		this.assigned = false;
	}

	
	
	public boolean isAssigned() {
		return assigned;
	}



	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}



	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public AddressPoint getDestination() {
		return destination;
	}

	public void setDestination(AddressPoint destination) {
		this.destination = destination;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}
	
	
}
