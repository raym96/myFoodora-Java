package user.view;

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
	
	public void showDeliveryCount(){
		System.out.println(c.getCount());
	}
	
	public void showDeliveredOrders(){
		System.out.println(c.getDeliveredOrders());
	}
	
	public void showWaitingOrders(){
		System.out.println(c.getWaitingOrders());
	}
}
