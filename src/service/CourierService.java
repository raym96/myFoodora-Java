package service;

import model.user.AddressPoint;

public interface CourierService {
	
	// 1. register/unregister their account to the MyFoodora system.
//	public void register();
//	public void unregister();
	
	// 2. set their state as on-duty or off-duty
	public void turnOnDuty();
	public void turnOffDuty();
	
	// 3. change their position
	public void changePosition(AddressPoint newPoint);
	
	// 4. accept/refuse to a delivery call (received by the MyFoodora system)
	public void acceptCall();
	public void refuseCall();
}
