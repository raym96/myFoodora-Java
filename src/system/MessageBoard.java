/*
 * 
 */
package system;

import java.util.ArrayList;

import user.model.MyFoodora;
import user.model.User;


/**
 * The Class MessageBoard.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MessageBoard{

	/** The messages. */
	private ArrayList<Message> messages;
	
	/** The owner. */
	private User owner = null;
	
	private Boolean hasUnreadMessages;

	/**
	 * Instantiates a new message board.
	 *
	 * @param owner the owner
	 */
	//Case of a private message board, belonging to the user
	public MessageBoard(User owner) {
		super();
		this.messages = new ArrayList<Message>();
		this.owner = owner;
		hasUnreadMessages = false;
	};
	
	/**
	 * Instantiates a new message board.
	 *
	 * @param myFoodora the my foodora
	 */
	//Case of the public message board of MyFoodora
	public MessageBoard(MyFoodora myFoodora) {
		super();
		this.messages = new ArrayList<Message>();
		hasUnreadMessages = false;
	};
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}

	/**
	 * Adds the message.
	 *
	 * @param msg the msg
	 */
	public void addMessage(Message msg){
		messages.add(msg);
		this.hasUnreadMessages = true;
	}
	
	/**
	 * Display allmsgs.
	 */
	public void displayAllmsgs(){
		if (messages.size()==0){
			System.out.println("There are no messages yet.");
			return;
		}
		System.out.println("-- MessageBoard of " + owner.getUsername() + " --");
		for (Message message : messages){
			System.out.println(message);
			message.setHasBeenRead(true);
		}
		hasUnreadMessages = false;
	}
	
	public void displayUnreadMessages(){
		if (hasUnreadMessages == false){
			System.out.println("There are no unread messages.");
			return;
		}
		for (Message message : messages){
			if (!(message.hasBeenRead())){
				System.out.println(message);
				message.setHasBeenRead(true);
			}
		}
		hasUnreadMessages = false;
	}
	

	public boolean getHasUnreadMessages() {
		// TODO Auto-generated method stub
		return hasUnreadMessages;
	}
}
