package user.view;

import system.Order;
import user.model.Courier;

public class CourierView implements UserView{
	
	/** The courier. */
	private Courier c;
	
	/**
	 * Instantiates a new courier service view.
	 *
	 * @param courier the courier
	 */
	public CourierView(Courier courier) {
		super();
		this.c = courier;
	}
	
	public void showInfo(){
		String output = "";
		output+="<Courier> "+c.getUsername()+"; fullname = "+c.getSurname()+" "+c.getUsername().toUpperCase()+"; position="+c.getPosition();
		if (c.getEmail() !=null){
			output+="; email = "+c.getEmail();
		}
		if (c.getPhone() !=null){
			output+="; phone = "+c.getPhone();
		}
		if (c.isOn_duty()){
			output+="; is on-duty.";
		}
		if (!(c.isOn_duty())){
			output+="; is off-duty";
		}
		System.out.println(output);
	}
	
	public void showCount(){
		System.out.println(c.getName()+ "'s total delivery count = " + c.getCount());
	}
	
	public void showHistory(){
		System.out.println("List of delivered orders by "+c.getName()+":");
		for (Order order: c.getDeliveredOrders()){
			System.out.println(order+"\n");
		}
	}
	
	public void showWaitingOrders(){
		System.out.println("List of waiting orders by "+c.getName()+":");
		if (c.getWaitingOrders().size()==0){
			System.out.println("EMPTY");
		}
		for (Order order: c.getWaitingOrders()){
			System.out.println(order+"\n");
		}		
		System.out.println("Please answer each of them (acceptCall/refuseCall) as soon as possible.");
	}
}
