package model.user;

import java.util.ArrayList;

import model.myfoodora.Message;

public class MessageBoard {

	private ArrayList<Message> messages;
	private User owner = null;

	//Case of a private message board, belonging to the user
	public MessageBoard(User owner) {
		super();
		this.messages = new ArrayList<Message>();
		this.owner = owner;
	};
	
	//Case of the public message board of MyFoodora
	public MessageBoard(MyFoodora myFoodora) {
		super();
		this.messages = new ArrayList<Message>();
	};
	
	public void addMessage(Message msg){
		messages.add(msg);
	}
	
	public void displayAllmsgs(){
		if(owner != null){
			System.out.println("-- MessageBoard of " + owner.getUsername() + " --");
		}else{
			System.out.println("-- MessageBoard of myFoodora --");
		}
		System.out.println(messages);
	}
}
