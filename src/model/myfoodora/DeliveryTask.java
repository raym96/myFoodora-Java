package model.myfoodora;

import model.restaurant.Order;
import model.user.AddressPoint;
import model.user.Courier;

public class DeliveryTask {

	private Order order;
	private Courier courier;
	private DeliveryPolicy deliveryPolicy;
	private boolean assigned;
	
	public DeliveryTask(Order order) {
		super();
		this.order = order;
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
		return this.order.getCustomer().getAddress();
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
