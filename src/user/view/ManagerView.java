package user.view;

import user.model.Manager;
import user.model.MyFoodora;

public class ManagerView implements UserView{
	/** The manager. */
	private Manager manager;
	
		/**
	 * Instantiates a new manager view.
	 *
	 * @param manager the manager
	 */
	public ManagerView(Manager manager) {
		super();
		this.manager = manager;
	}
	
	public void showInfo(){
		String output = "";
		output+="<Manager> "+manager.getUsername()+"; fullname = "+manager.getSurname()+" "+manager.getName().toUpperCase();
		System.out.println(output);
	}
	
	public void showHistory(){
		MyFoodora.getInstance().getService().showHistory();
	}
	
}
