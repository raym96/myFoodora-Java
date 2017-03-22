package service.impl;

import model.user.AddressPoint;
import model.user.Courier;
import service.CourierService;

public class CourierServiceImpl implements CourierService {

	private Courier courier;
	
	public CourierServiceImpl(Courier courier) {
		super();
		this.courier = courier;
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOnDuty() {
		// TODO Auto-generated method stub
		courier.setOn_duty(true);
	}

	@Override
	public void turnOffDuty() {
		// TODO Auto-generated method stub
		courier.setOn_duty(false);
	}

	@Override
	public void changePosition(AddressPoint newPoint) {
		// TODO Auto-generated method stub
		courier.setPosition(newPoint);
	}

	@Override
	public void acceptCall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refuseCall() {
		// TODO Auto-generated method stub

	}

}
