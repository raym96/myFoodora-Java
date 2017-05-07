/*
 * 
 */
package user.service.impl;

import java.util.ArrayList;

import exceptions.NameNotFoundException;
import system.AddressPoint;
import system.Message;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.service.CourierService;
import user.view.CourierView;


/**
 * The Class CourierServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CourierServiceImpl implements CourierService {

	/** The courier. */
	private Courier courier;
	
	/** The courier view. */
	private CourierView courierView;
	
	/**
	 * Instantiates a new courier service impl.
	 *
	 * @param courier the courier
	 */
	public CourierServiceImpl(Courier courier) {
		super();
		this.courier = courier;
		this.courierView = new CourierView(courier);
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#register()
	 */
	// 1. register/unregister their account to the MyFoodora system.
	@Override
	public void register(){
		MyFoodora myfoodora = MyFoodora.getInstance();
		myfoodora.register(courier); 
	}
	
	/* (non-Javadoc)
	 * @see user.service.CourierService#unregister()
	 */
	@Override
	public void unregister(){
		MyFoodora myfoodora = MyFoodora.getInstance();
		myfoodora.unregister(courier);
	}
	
	/* (non-Javadoc)
	 * @see user.service.CourierService#turnOnDuty()
	 */
	// 2. set their state as on-duty or off-duty
	@Override
	public void turnOnDuty() {
		// TODO Auto-generated method stub
		courier.setOn_duty(true);
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#turnOffDuty()
	 */
	@Override
	public void turnOffDuty() {
		// TODO Auto-generated method stub
		courier.setOn_duty(false);
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#changePosition(system.AddressPoint)
	 */
	// 3. change their position
	@Override
	public void changePosition(String positionString) {
		// TODO Auto-generated method stub
		AddressPoint newPoint = new AddressPoint(positionString);
		courier.setPosition(newPoint);
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#acceptCall(system.Order)
	 */
	// 4. accept/refuse to a delivery call (received by the MyFoodora system)
	@Override
	public void acceptCall(String orderName) {
		// TODO Auto-generated method stub
		Order order = courier.getWaitingOrder(orderName);
		courier.acceptWaitingOrder(orderName); // automatic +1 for the delivery count of the courier
									// may throw NameNotFoundException		
		//new message appears on message board of customer
		Customer c = order.getCustomer();
//		c.update(new Message("courier "+courier.getName()+" <"+courier.getUsername()+"> accepted to deliver your order <"+order.getName()+">."));	
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#refuseCall(system.Order)
	 */
	@Override
	public void refuseCall(String orderName) {
		// TODO Auto-generated method stub
		Order order = courier.getWaitingOrder(orderName);
		courier.refuseWaitingOrder(orderName);	
		
		ArrayList<Courier>availablecouriers = MyFoodora.getInstance().getAvailableCouriers();
		availablecouriers.remove(courier); //this courier doesn't want to take the order, so he won't be considered for the next parsing
		
		// new message appears on message board of customer
		Customer c = order.getCustomer();

		MyFoodora.getInstance().getService().findDeliverer(order, availablecouriers); //a new courier is assigned
	}
	
	/* (non-Javadoc)
	 * @see user.service.CourierService#showInfo()
	 */
	@Override
	public void showInfo(){
		courierView.showInfo();
	}
	
	/* (non-Javadoc)
	 * @see user.service.CourierService#showCount()
	 */
	@Override
	public void showCount(){
		courierView.showCount();
	}
	
	/* (non-Javadoc)
	 * @see user.service.CourierService#showHistory()
	 */
	@Override
	public void showHistory(){
		courierView.showHistory();
	}
	
	/* (non-Javadoc)
	 * @see user.service.CourierService#showWaitingOrders()
	 */
	@Override
	public void showWaitingOrders(){
		courierView.showWaitingOrders();	
	}
}
