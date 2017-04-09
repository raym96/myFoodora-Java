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
public class MessageBoard {

	/** The messages. */
	private ArrayList<Message> messages;
	
	/** The owner. */
	private User owner = null;

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
	}
	
	/**
	 * Display allmsgs.
	 */
	public void displayAllmsgs(){
		if(owner != null){
			System.out.println("-- MessageBoard of " + owner.getUsername() + " --");
		}else{
			System.out.println("-- MessageBoard of myFoodora --");
		}
		System.out.println(messages);
	}
}
