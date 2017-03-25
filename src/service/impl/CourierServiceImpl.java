package service.impl;

import java.util.ArrayList;

import model.myfoodora.Message;
import model.user.AddressPoint;
import model.user.Courier;
import model.user.Customer;
import model.user.MyFoodora;
import service.CourierService;
import service.MyFoodoraService;

public class CourierServiceImpl implements CourierService {

	private Courier courier;
	
	public CourierServiceImpl(Courier courier) {
		super();
		this.courier = courier;
	}

	// 1. register/unregister their account to the MyFoodora system.
//	public void register();
//	public void unregister();
	
	// 2. set their state as on-duty or off-duty
	@Override
	public void turnOnDuty() {
		// TODO Auto-generated method stub
		courier.setOn_duty(true);;
		MyFoodora.getInstance().getActivecouriers().add(courier);
	}

	@Override
	public void turnOffDuty() {
		// TODO Auto-generated method stub
		courier.setOn_duty(false);
		MyFoodora.getInstance().getActivecouriers().remove(courier);
	}

	// 3. change their position
	@Override
	public void changePosition(AddressPoint newPoint) {
		// TODO Auto-generated method stub
		courier.setPosition(newPoint);
	}

	// 4. accept/refuse to a delivery call (received by the MyFoodora system)
	@Override
	public void acceptCall() {
		// TODO Auto-generated method stub
		courier.getCurrentDeliveryTask().setCourier(courier);
		courier.getCurrentDeliveryTask().setAssigned(true);
		courier.setCount(courier.getCount()+1);
		System.out.println("courier "+courier.getName()+" accepts to take the order.");
		//new message appears on message board of customer
		Customer c = courier.getCurrentDeliveryTask().getOrder().getCustomer();
		c.update(new Message("courier "+courier.getName()+" accepts to take the order."));
	}

	@Override
	public void refuseCall() {
		// TODO Auto-generated method stub
		courier.setCurrentDeliveryTask(null);
		ArrayList<Courier>availablecouriers = MyFoodora.getInstance().getActivecouriers();
		availablecouriers.remove(courier); //this courier doesn't want to take the order, so he won't be considered for the parsing algorithm
		System.out.println("courier "+courier.getName()+" refuses to take the order. A new courier is being assigned.");
		// new message appears on message board of customer
		Customer c = courier.getCurrentDeliveryTask().getOrder().getCustomer();
		c.update(new Message("courier "+courier.getName()+" refused to take the order. Please wait for an other courier"));
	
		new MyFoodoraServiceImpl().parse(courier.getCurrentDeliveryTask().getOrder(), availablecouriers); //a new courier is assigned

	}
}
