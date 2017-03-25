package model.user;
import java.util.Date;
import java.util.UUID;

import model.myfoodora.Message;
import model.myfoodora.SpecialOfferBoard;

public abstract class User implements MessageBoardUser{

	private String ID;
	protected String username;
	private String password = "password";
	private boolean activated;

	private MessageBoard messageBoard;
	private boolean logStatus;
	
	public User() {
		super();
		this.ID = UUID.randomUUID().toString();
		this.messageBoard = new MessageBoard(this);
		this.activated = false;
		this.logStatus = false;
	}

	public User(String username) {
		super();
		this.ID = UUID.randomUUID().toString();
		this.username = username;
		this.activated = false;
		this.messageBoard = new MessageBoard(this);
		this.logStatus = false;
	}
	
	public void logIn(){
		this.logStatus = true;
		this.updatePublic(new Message(this.getUsername()+" has logged in to the myFoodora system"));
		
	}
	public void logOut(){
		this.logStatus = false;
		this.updatePublic(new Message(this.getUsername()+" has logged out of the myFoodora system"));
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

	@Override
	public void update(Message message){
		this.messageBoard.addMessage(message);
		System.out.println(message);
	}
	
	@Override
	public void updatePublic(Message message) {
		MyFoodora.getInstance().getMessageBoard().addMessage(message);
		System.out.println(message);
	}
	
	public void getMessage(){
		this.messageBoard.displayAllmsgs();
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
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User ID=" + ID + ", username=" + username + ", activated=" + activated;
	}
}
