package service.impl;

import model.myfoodora.MyFoodora;
import model.user.AddressPoint;
import model.user.Courier;
import service.CourierService;
import service.MyFoodoraService;

public class CourierServiceImpl implements CourierService {

	private Courier courier;
	private MyFoodoraService myFoodoraService;
	
	public CourierServiceImpl(Courier courier) {
		super();
		this.courier = courier;
		myFoodoraService = new MyFoodoraServiceImpl();
	}


	@Override
	public void turnOnDuty() {
		// TODO Auto-generated method stub
		myFoodoraService.setOnDuty(courier);
	}

	@Override
	public void turnOffDuty() {
		// TODO Auto-generated method stub
		myFoodoraService.setOffDuty(courier);
	}

	@Override
	public void changePosition(AddressPoint newPoint) {
		// TODO Auto-generated method stub
		courier.setPosition(newPoint);
	}

	@Override
	public void acceptCall() {
		// TODO Auto-generated method stub
		courier.setAccepted(true);
		courier.getCurrentDeliveryTask().setCourier(courier);
		courier.getCurrentDeliveryTask().setAssigned(true);
		courier.observe(MyFoodora.getInstance());
	}

	@Override
	public void refuseCall() {
		// TODO Auto-generated method stub
		courier.setAccepted(false);
		courier.observe(MyFoodora.getInstance());
	}

}
