package model.myfoodora;

import java.util.Date;

public class Message {

	private Date date;
	private String msgContent;
	
	public Message(Date date, String msgContent) {
		super();
		this.date = date;
		this.msgContent = msgContent;
	}

	@Override
	public String toString() {
		return "msg : date=" + date + ", msgContent=" + msgContent + ".\n";
	}
	
	
}
