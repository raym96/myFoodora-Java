package model.myfoodora;

import java.util.ArrayList;

import model.user.User;

public class MessageBoard {

	private ArrayList<Message> messages;
	private User owner = null;

	public MessageBoard(User owner) {
		super();
		this.messages = new ArrayList<Message>();
		this.owner = owner;
	};
	
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
