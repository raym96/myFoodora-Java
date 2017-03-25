package model.user;

import java.util.ArrayList;

import model.myfoodora.*;

public interface MessageBoardUser {
	
	public void update(Message message); //add a new message on the private message board
	public void updatePublic(Message message); //posts a new message on the message board of MyFoodora
	
}
