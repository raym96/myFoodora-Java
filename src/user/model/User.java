/*
 * 
 */
package user.model;

import java.util.Date;
import java.util.UUID;

import system.Message;
import system.MessageBoard;
import system.MessageBoardObserver;
import user.service.UserService;
import user.view.UserView;


/**
 * The Class User.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class User implements MessageBoardObserver{

	/** The id. */
	protected String ID;
	
	/** The user count to generate unique id. */
	private static int usercount = 1000;
	
	/** The username. */
	protected String username;
	
	/** The password. */
	private String password = "password";
	
	/** The email. */
	protected String email;
	
	/** The phone. */
	protected String phone;
	
	/** The notified. */
	private boolean notified;
	
	/** The activated. */
	protected boolean activated;

	/** The message board. */
	private MessageBoard messageBoard;
	
	/** The log status. */
	private boolean logStatus;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.activated = true; //activated by default
		this.notified = false;
		this.messageBoard = new MessageBoard(this);
		this.logStatus = false;
		
		this.ID = "MFDA"+String.valueOf(usercount);
		usercount++;
	}
	
	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public abstract UserService getService();

	/**
	 * Log in.
	 */
	public void logIn(){
		this.logStatus = true;		
	}
	
	/**
	 * Log out.
	 */
	public void logOut(){
		this.logStatus = false;
	}

	/**
	 * Turn on notification.
	 */
	public void turnOnNotification(){
		notified = true;
	}
	
	/**
	 * Turn off notification.
	 */
	public void turnOffNotification(){
		notified = false;
	}

	/* (non-Javadoc)
	 * @see system.MessageBoardObserver#observe(system.MessageBoard)
	 */
	@Override
	public void observe(MessageBoard messageboard){
		this.messageBoard.displayAllmsgs();
	}

	/* (non-Javadoc)
	 * @see system.MessageBoardObserver#update(system.Message)
	 */
	@Override
	public void update(Message message){
		this.messageBoard.addMessage(message);
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	//setters & getters
	public String getUsername() {
		return username;
	}
	
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the message board.
	 *
	 * @return the message board
	 */
	public MessageBoard getMessageBoard() {
		return messageBoard;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Sets the id.
	 *
	 * @param iD the new id
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * Sets the actived.
	 *
	 * @param b the new actived
	 */
	public void setActived(boolean b) {
		activated = b;
	}
	
	/**
	 * Checks if is log status.
	 *
	 * @return true, if is log status
	 */
	public boolean isLogStatus() {
		return logStatus;
	}
	
	/**
	 * Sets the log status.
	 *
	 * @param logStatus the new log status
	 */
	public void setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
	}
	
	/**
	 * Checks if is notified.
	 *
	 * @return true, if is notified
	 */
	public boolean isNotified() {
		return notified;
	}
	
	/**
	 * Sets the notified.
	 *
	 * @param notified the new notified
	 */
	public void setNotified(boolean notified) {
		this.notified = notified;
	}
	
	/**
	 * Checks if is activated.
	 *
	 * @return true, if is activated
	 */
	public boolean isActivated() {
		return activated;
	}
	
	/**
	 * Sets the activated.
	 *
	 * @param activated the new activated
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User ID=" + ID + ", username=" + username + ", activated=" + activated;
	}
	
}
