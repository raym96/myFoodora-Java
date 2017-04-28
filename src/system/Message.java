/*
 * 
 */
package system;

import java.text.SimpleDateFormat;
import java.util.Date;

import user.model.User;


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
	
	private Boolean hasBeenRead;
	
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
		this.hasBeenRead = false;
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
		this.hasBeenRead = false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = sdf.format(date);
		return dateString + ", "+poster+": "+msgContent;
	}

	public Boolean hasBeenRead() {
		return hasBeenRead;
	}

	public void setHasBeenRead(Boolean read) {
		this.hasBeenRead = read;
	}
	
	
}
