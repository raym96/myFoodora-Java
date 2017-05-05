package gui.model;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.GapContent;

import exceptions.NameNotFoundException;
import gui.Login;
import system.AddressPoint;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;

/**
 * The Class UserInfoPanel.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class UserInfoPanel extends JPanel{
	
	/** The user. */
	private User user;
	
	/** The gap usual. */
	private final int gap_usual = 50;
	
	/** The gap. */
	private final int gap = 15;
	
	/** The gap btn. */
	private final int gap_btn = 40;
	
	/** The header. */
	private JLabel header;
	
	/** The id field. */
	private JLabel idField;
	
	/** The username field. */
	private JLabel usernameField;
	
	/** The name field. */
	private TextFieldWithLabel nameField;
	
	/** The position field. */
	private TextFieldWithLabel positionField;
	
	/** The email field. */
	private TextFieldWithLabel emailField;
	
	/** The phone field. */
	private TextFieldWithLabel phoneField;
	
	/** The active field. */
	private JLabel activeField;
	
	/** The option field. */
	private MyRadioButton optionField;
	
	/** The super container. */
	private Container superContainer;

	/**
	 * Instantiates a new user info panel.
	 *
	 * @param username the username
	 */
	public UserInfoPanel(String username) {
		super();

		try {
			user = MyFoodora.getInstance().getService().selectUser(username);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		placeComponents();
	}

	/**
	 * Instantiates a new user info panel.
	 *
	 * @param username the username
	 * @param c the c
	 */
	public UserInfoPanel(String username, Container c) {
		this(username);
		c.add(this);
	}
	
	/**
	 * Place components.
	 */
	public void placeComponents(){
		String name = null;
		String position = "";
		
		if(user instanceof Manager){
			name = ((Manager)user).getName() + " " + ((Manager)user).getSurname();
		}else if(user instanceof Restaurant){
			name = ((Restaurant)user).getName();
			position = ((Restaurant)user).getAddress().toString();
		}else if(user instanceof Customer){
			name = ((Customer)user).getFullName();
			position = ((Customer)user).getAddress().toString();
		}else if(user instanceof Courier){
			name = ((Courier)user).getFullName();
			position = ((Courier)user).getPosition().toString();
		}
		
		// layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(gap_usual));
		
		// header
		header = new JLabel("User infromation: " + user.getUsername());
		header.setFont(new Font("Arial", Font.ITALIC, 25));
		this.add(header);
		this.add(Box.createVerticalStrut(gap_usual));
		
		// id
		idField = new JLabel("ID: " + user.getID());
		this.add(idField);
		this.add(Box.createVerticalStrut(gap));
		
		// username
		usernameField = new JLabel("username: " + user.getUsername());
		this.add(usernameField);
		this.add(Box.createVerticalStrut(gap));
		
		// name
		nameField = new TextFieldWithLabel("name: ", "name format wrong !", this);
		nameField.setTextFieldContent(name);
		this.add(Box.createVerticalStrut(gap));
		
		// position
		if(!(user instanceof Manager)){
			positionField = new TextFieldWithLabel("address/position: ", "address format wrong !", this);
			positionField.setTextFieldContent(position);
			this.add(Box.createVerticalStrut(gap));
		}
		
		// email
		emailField = new TextFieldWithLabel("email: ", "email format wrong !", this);
		emailField.setTextFieldContent(user.getEmail());
		this.add(Box.createVerticalStrut(gap));
		
		// phone
		phoneField = new TextFieldWithLabel("phone: ", "phone format wrong !", this);
		phoneField.setTextFieldContent(user.getPhone());
		this.add(Box.createVerticalStrut(gap));
		
		// active
		if(!(user instanceof Manager)){
			activeField = new JLabel("actived: " + user.isActivated());
			this.add(activeField);
			this.add(Box.createVerticalStrut(gap));	
		}
		
		// option
		if(user instanceof Customer){
			optionField = new MyRadioButton("agree to be notified", this, new String[] {"yes", "no"});
			this.add(Box.createVerticalStrut(gap));
		}else if(user instanceof Courier){
			optionField = new MyRadioButton("duty status", this, new String[] {"on", "off"});
			this.add(Box.createVerticalStrut(gap));
		}
		
		// btns
		JPanel subPanel_btn = new JPanel();
		subPanel_btn.setLayout(new BoxLayout(subPanel_btn, BoxLayout.X_AXIS));
		(new BasicButton("save modification", subPanel_btn)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User new_user = user;
				if(new_user instanceof Manager){
					((Manager)new_user).setName(nameField.getTextFieldContent().split(" ")[0]);
					((Manager)new_user).setName(nameField.getTextFieldContent().split(" ")[1]);
				}else if(new_user instanceof Restaurant){
					((Restaurant)new_user).setName(nameField.getTextFieldContent());
					((Restaurant)new_user).setAddress(new AddressPoint(positionField.getTextFieldContent()));
				}else if(new_user instanceof Customer){
					((Customer)new_user).setFullName(nameField.getTextFieldContent());
					((Customer)new_user).setAddress(new AddressPoint(positionField.getTextFieldContent()));
				}else if(new_user instanceof Courier){
					((Courier)new_user).setFullName(nameField.getTextFieldContent());
					((Courier)new_user).setPosition(new AddressPoint(positionField.getTextFieldContent()));
				}
				MyFoodora.getInstance().removeUser(user);
				MyFoodora.getInstance().addUser(new_user);
			}
		});
		subPanel_btn.add(Box.createHorizontalStrut(gap_btn));
		(new BasicButton("modify password", subPanel_btn)).addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				superContainer = UserInfoPanel.this.getParent();
				while(!(superContainer instanceof BasicFrame)){
					superContainer = superContainer.getParent();
				}
				superContainer.setFocusable(false);
				ChildFrame psdFrame = new ChildFrame("modify password");
				psdFrame.setAlwaysOnTop(true);
				JPanel subPanel = new JPanel();
				subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
				psdFrame.controlPanel.add(subPanel);
				final int gap_psd = 40;
				
				subPanel.add(Box.createVerticalStrut(2*gap_psd));
				
				PsdFieldWithLabel old_psd = new PsdFieldWithLabel("old password: ", "password not correct !", subPanel);
				subPanel.add(Box.createVerticalStrut(gap_psd));
				
				PsdFieldWithLabel new_psd = new PsdFieldWithLabel("new password: ", subPanel);
				subPanel.add(Box.createVerticalStrut(gap_psd));
				
				PsdFieldWithLabel confirm_psd = new PsdFieldWithLabel("confirm password: ", "two passwords not match !", subPanel);
				subPanel.add(Box.createVerticalStrut(gap_psd));
				
				JPanel subPanel_btn = new JPanel();
				subPanel_btn.setLayout(new BoxLayout(subPanel_btn, BoxLayout.X_AXIS));
				(new BasicButton("submit", subPanel_btn)).addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!old_psd.getPsdFieldContent().equalsIgnoreCase(user.getPassword())){
							old_psd.showIllegal();
						}else{
							old_psd.hideIllegal();
						}
						if(!new_psd.getPsdFieldContent().equalsIgnoreCase(confirm_psd.getPsdFieldContent())){
							confirm_psd.showIllegal();
						}else{
							confirm_psd.hideIllegal();
						}
						
						if(old_psd.getPsdFieldContent().equalsIgnoreCase(user.getPassword()) && new_psd.getPsdFieldContent().equalsIgnoreCase(confirm_psd.getPsdFieldContent())){
							User new_user = user;
							new_user.setPassword(confirm_psd.getPsdFieldContent());
							MyFoodora.getInstance().removeUser(user);
							MyFoodora.getInstance().addUser(new_user);
							
							JOptionPane.showMessageDialog(psdFrame, "modify password successful !", "information", JOptionPane.INFORMATION_MESSAGE);
							superContainer.setFocusable(true);
							psdFrame.dispose();
						}
					}
				});
				subPanel_btn.add(Box.createVerticalStrut(gap_btn));
				(new BasicButton("cancel", subPanel_btn)).addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						superContainer.setFocusable(true);
						psdFrame.dispose();
					}
				});
		
				subPanel.add(subPanel_btn);
				
			}
		});
		subPanel_btn.add(Box.createHorizontalStrut(gap_btn));
		(new BasicButton("login out", subPanel_btn)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				superContainer = UserInfoPanel.this.getParent();
				while(!(superContainer instanceof BasicFrame)){
					superContainer = superContainer.getParent();
				}
				((BasicFrame)superContainer).dispose();
				new Login();
			}
		});
		this.add(subPanel_btn);
	}
	
}
