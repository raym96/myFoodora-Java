package model.user;
import java.util.UUID;

public abstract class User {

	private String ID;
	protected String username;
	private String password;
	private boolean activated;
	
	public User(String username) {
		super();
		activated = true;
		ID = UUID.randomUUID().toString();
		this.username = username;
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword(){
		return this.password;
	}



	public void setActived(boolean b) {
		activated = b;
		
	}
}
