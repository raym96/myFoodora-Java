package model.users;

import java.util.Date;
import java.util.UUID;


import model.myfoodora.Message;
import model.myfoodora.MessageBoard;
import model.myfoodora.SpecialOfferBoard;

public abstract class User implements Observer{

	protected String ID;
	protected String username;
	private String password = "password";
	private boolean notified;
	protected boolean activated;

	private MessageBoard messageBoard;
	private boolean logStatus;
	
	public User() {
		super();
		this.ID = UUID.randomUUID().toString();
		this.messageBoard = new MessageBoard(this);
		this.notified = false;
		this.activated = false;
		this.logStatus = false;
	}

	public User(String username) {
		super();
		this.ID = UUID.randomUUID().toString();
		this.username = username;
		this.activated = false;
		this.notified = false;
		this.messageBoard = new MessageBoard(this);
		this.logStatus = false;
	}
	
	public void logIn(){
		this.logStatus = true;
		this.observe(MyFoodora.getInstance(), "" + this.getUsername() + " has logged in to the myFoodora system");
		
	}
	public void logOut(){
		this.logStatus = false;
		this.observe(MyFoodora.getInstance(), "" + this.getUsername() + " has logged out of the myFoodora system");
	}

	public void turnOnNotification(){
		notified = true;
	}
	
	public void turnOffNotification(){
		notified = false;
	}
	
	public void refreshMessageBoard(){
		this.messageBoard.displayAllmsgs();
	}
	

	@Override
	public abstract void observe(Observable o);
	@Override
	public void update(Object o){
		if(o instanceof String){
			MessageBoard msgBoard = this.getMessageBoard();
			msgBoard.addMessage(new Message(this.getUsername(), (String)o));
			System.out.println(msgBoard.getMessages().get(msgBoard.getMessages().size()-1));
		}
	}
	@Override
	public void observe(Observable obv, Object o){
		if(obv instanceof MyFoodora){
			if(o instanceof String){
				MessageBoard msgBoard = ((MyFoodora)obv).getMessageBoard();
				msgBoard.addMessage(new Message((String)o));
				System.out.println(msgBoard.getMessages().get(msgBoard.getMessages().size()-1));
			}
		}
	}

	
	//setters & getters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public MessageBoard getMessageBoard() {
		return messageBoard;
	}
	public String getPassword(){
		return this.password;
	}
	public void setActived(boolean b) {
		activated = b;
	}
	public boolean isLogStatus() {
		return logStatus;
	}
	public void setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
	}
	public boolean isNotified() {
		return notified;
	}
	public void setNotified(boolean notified) {
		this.notified = notified;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equalsIgnoreCase(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User ID=" + ID + ", username=" + username + ", activated=" + activated;
	}
}
