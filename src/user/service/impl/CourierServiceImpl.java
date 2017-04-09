/*
 * 
 */
package user.service.impl;

import java.util.ArrayList;

import exceptions.OrderNotFoundException;
import system.AddressPoint;
import system.Message;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.service.CourierService;


/**
 * The Class CourierServiceImpl.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CourierServiceImpl implements CourierService {

	/** The courier. */
	private Courier courier;
	
	/**
	 * Instantiates a new courier service impl.
	 *
	 * @param courier the courier
	 */
	public CourierServiceImpl(Courier courier) {
		super();
		this.courier = courier;
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
	public void changePosition(AddressPoint newPoint) {
		// TODO Auto-generated method stub
		courier.setPosition(newPoint);
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#acceptCall(system.Order)
	 */
	// 4. accept/refuse to a delivery call (received by the MyFoodora system)
	@Override
	public void acceptCall(Order order) {
		// TODO Auto-generated method stub
		try{
			order.setCourier(courier); //the courier is assigned to the delivery-task
			order.setAssigned(true);
			courier.acceptWaitingOrder(order); // automatic +1 for the delivery count of the courier
										// may throw orderNotFoundException
			System.out.println("courier "+courier.getName()+" accepts to take the order.");
			
			//new message appears on message board of customer
			Customer c = order.getCustomer();
			c.update(new Message("courier "+courier.getName()+" accepts to take the order."));

			//Update the history
			order.getRestaurant().addToHistory(order);
			MyFoodora.getInstance().addToHistory(order);
		}catch (OrderNotFoundException e){}
		
	}

	/* (non-Javadoc)
	 * @see user.service.CourierService#refuseCall(system.Order)
	 */
	@Override
	public void refuseCall(Order order) {
		// TODO Auto-generated method stub
		try{
			courier.refuseWaitingOrder(order);	
			ArrayList<Courier>availablecouriers = MyFoodora.getInstance().getAvailableCouriers();
			availablecouriers.remove(courier); //this courier doesn't want to take the order, so he won't be considered for the next parsing
			
			System.out.println("courier "+courier.getName()+" refuses to take the order. A new courier is being assigned.");
			
			// new message appears on message board of customer
			Customer c = order.getCustomer();
			c.update(new Message("courier "+courier.getName()+" refused to take the order. Please wait for an other courier"));
		
			//A new courier is assigned to the delivery-task
			new MyFoodoraServiceImpl().parse(order, availablecouriers); //a new courier is assigned
		} catch (OrderNotFoundException e){}
	}
}
