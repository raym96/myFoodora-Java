/*
 * 
 */
package system;

import java.util.Date;

import user.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Message {

	/** The date. */
	private Date date;
	
	/** The msg content. */
	private String msgContent;
	
	/** The poster. */
	private String poster; //message poster's name
	
	/**
	 * Instantiates a new message.
	 *
	 * @param msgContent the msg content
	 */
	public Message(String msgContent) {
		//when the poster is not specified it's the Myfoodora system
		super();
		this.date = new Date();
		this.msgContent = msgContent;
		this.poster = "SYSTEM";
	}
	
	/**
	 * Instantiates a new message.
	 *
	 * @param poster the poster
	 * @param msgContent the msg content
	 */
	public Message(String poster, String msgContent) {
		super();
		this.date = new Date();
		this.msgContent = msgContent;
		this.poster = poster;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return date + ", "+poster+": "+msgContent + "\n";
	}
	
	
}
