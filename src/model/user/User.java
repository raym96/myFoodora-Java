package model.user;
import java.util.UUID;

import model.customer.Observable;
import model.myfoodora.MessageBoard;
import model.myfoodora.MyFoodora;

public abstract class User implements Observer{

	private String ID;
	protected String username;
	private String password;
	private boolean activated;
	private boolean notified;
	private MessageBoard messageBoard;
	
	public User(String username) {
		super();
		this.activated = true;
		this.ID = UUID.randomUUID().toString();
		this.username = username;
		this.notified = false;
		this.activated = false;
		this.messageBoard = new MessageBoard(this);
	}
	
	public void turnOnNotification(){
		notified = true;
	}
	
	public void turnOffNotification(){
		notified = false;
	}
	
	public String getUsername() {
		return username;
	}

	public MessageBoard getMessageBoard() {
		return messageBoard;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword(){
		return this.password;
	}



	public void setActived(boolean b) {
		activated = b;
		
	}

	@Override
	public abstract void update(Object o);


	@Override
	public abstract void observe(Observable o);

	public void registerOnFoodora(){
		MyFoodora.getInstance().register(this);
	}
	
	public void unregisterOnFoodora(){
		MyFoodora.getInstance().unregister(this);
	}

	@Override
	public String toString() {
		return "User ID=" + ID + ", activated=" + activated;
	}
}
