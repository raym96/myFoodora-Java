package user.view;

import user.model.Manager;
import user.model.MyFoodora;

/**
 * The Class ManagerView.
 * @author He Xiaoan
 * @author Ji Raymond
 */
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
	
	/* (non-Javadoc)
	 * @see user.view.UserView#showInfo()
	 */
	public void showInfo(){
		String output = "";
		output+="<Manager> "+manager.getUsername()+"; fullname = "+manager.getSurname()+" "+manager.getName().toUpperCase();
		System.out.println(output);
	}
	
	/* (non-Javadoc)
	 * @see user.view.UserView#showHistory()
	 */
	public void showHistory(){
		MyFoodora.getInstance().getService().showHistory();
	}
	
}
