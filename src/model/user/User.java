package model.user;
import java.util.Date;
import java.util.UUID;

import model.customer.Observable;
import model.myfoodora.Message;
import model.myfoodora.MessageBoard;
import model.myfoodora.MyFoodora;

public abstract class User implements Observer{

	private String ID;
	protected String username;
	private String password = "password";
	private boolean activated;
	private boolean notified;
	private MessageBoard messageBoard;
	private boolean loginIn;
	
	public User() {
		super();
		this.ID = UUID.randomUUID().toString();
		this.messageBoard = new MessageBoard(this);
		this.notified = false;
		this.activated = false;
		this.loginIn = false;
	}

	public User(String username) {
		super();
		this.ID = UUID.randomUUID().toString();
		this.username = username;
		this.notified = false;
		this.activated = false;
		this.messageBoard = new MessageBoard(this);
		this.loginIn = false;
	}
	
	public void loginIn(){
		this.loginIn = true;
		this.observe(MyFoodora.getInstance(), " has login in the myFoodora");
		
	}
	public void loginOut(){
		this.loginIn = false;
		this.observe(MyFoodora.getInstance(), " has login out the myFoodora");
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
	
	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		if( obv instanceof MyFoodora ){
			if( o instanceof String ){
				((MyFoodora) obv).getMessageBoard().addMessage(new Message(new Date(), "" + this.getUsername() + (String)o));
				((MyFoodora) obv).getMessageBoard().displayAllmsgs();	
			}
		}
	}

	public void registerOnFoodora(){
		MyFoodora.getInstance().register(this);
	}
	
	public void unregisterOnFoodora(){
		MyFoodora.getInstance().unregister(this);
	}

	@Override
	public String toString() {
		return "User ID=" + ID + ", username=" + username + ", activated=" + activated;
	}
}
