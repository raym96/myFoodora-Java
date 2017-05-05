package user.view;

import system.Order;
import user.model.Courier;

/**
 * The Class CourierView.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CourierView implements UserView{
	
	/** The courier. */
	private Courier courier;
	
	/**
	 * Instantiates a new courier service view.
	 *
	 * @param courier the courier
	 */
	public CourierView(Courier courier) {
		super();
		this.courier = courier;
	}
	
	/* (non-Javadoc)
	 * @see user.view.UserView#showInfo()
	 */
	public void showInfo(){
		String output = "";
		output+="<Courier> "+courier.getUsername()+"; fullname = "+courier.getSurname()+" "+courier.getName().toUpperCase()+"; position="+courier.getPosition();
		if (courier.getEmail() !=null){
			output+="; email = "+courier.getEmail();
		}
		if (courier.getPhone() !=null){
			output+="; phone = "+courier.getPhone();
		}
		if (courier.isOn_duty()){
			output+="; is on-duty.";
		}
		if (!(courier.isOn_duty())){
			output+="; is off-duty";
		}
		System.out.println(output);
	}
	
	/**
	 * Show count.
	 */
	public void showCount(){
		System.out.println(courier.getName()+ "'s total delivery count = " + courier.getCount());
	}
	
	/* (non-Javadoc)
	 * @see user.view.UserView#showHistory()
	 */
	public void showHistory(){
		System.out.println("List of delivered orders by "+courier.getName()+":");
		for (Order order: courier.getDeliveredOrders()){
			System.out.println(order+"\n");
		}
	}
	
	/**
	 * Show waiting orders.
	 */
	public void showWaitingOrders(){
		if (courier.getWaitingOrders().size()==0){
			System.out.println("You have no waiting delivery tasks.");
			return;
		}
		System.out.println("You have new waiting delivery tasks.");
		for (Order order: courier.getWaitingOrders()){
			System.out.println(order);
		}			
	}
	
}
