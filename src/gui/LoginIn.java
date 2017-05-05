package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import exceptions.NameNotFoundException;
import gui.model.BasicButton;
import gui.model.BasicFrameSimplePage;
import gui.model.MyRadioButton;
import gui.model.PsdFieldWithLabel;
import gui.model.TextFieldWithLabel;
import system.AddressPoint;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;

/**
 * The Class LoginIn.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class LoginIn extends BasicFrameSimplePage implements ItemListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The rbtn user type. */
	private MyRadioButton rbtn_userType;
	
	/** The rbtn contact. */
	private MyRadioButton rbtn_contact;
	
	/** The rbtn notify. */
	private MyRadioButton rbtn_notify;
	
	/** The rbtn duty. */
	private MyRadioButton rbtn_duty;
	
	/** The username field. */
	private TextFieldWithLabel usernameField;
	
	/** The password field. */
	private PsdFieldWithLabel passwordField;
	
	/** The confirm password field. */
	private PsdFieldWithLabel confirm_passwordField;
	
	/** The firstname field. */
	private TextFieldWithLabel firstnameField;
	
	/** The lastname field. */
	private TextFieldWithLabel lastnameField;
	
	/** The name field. */
	private TextFieldWithLabel nameField;
	
	/** The position. */
	private TextFieldWithLabel position;
	
	/** The contact field. */
	private TextFieldWithLabel contactField;
	
	/** The Ok button. */
	private JButton OkButton;
	
	/** The Cancel button. */
	private JButton CancelButton;
	
	/** The sub panel name. */
	private JPanel subPanel_name;
	
	/** The sub panel option. */
	private JPanel subPanel_option;
	
	/** The login in panel. */
	private JPanel loginInPanel;
	
	/** The user. */
	private User user;
	
	/** The notified. */
	private boolean notified = false;
	
	/** The duty. */
	private boolean duty = false;

	/**
	 * Instantiates a new login in.
	 */
	public LoginIn() {
		super("login in");

		headerLabel.setText("Login up");
		statusLabel.setText("login in as a restaurant, a customer or a courier ");
		
		placeComponents();
	}
	
	/* (non-Javadoc)
	 * @see gui.model.BasicFrame#placeComponents()
	 */
	@Override
	public void placeComponents() {

		loginInPanel = new JPanel();
//		loginInPanel.setBounds(controlPanel.getWidth()/6, controlPanel.getHeight()/6, controlPanel.getWidth()*1/3, controlPanel.getHeight()*2/3);
		controlPanel.add(loginInPanel);
		
		loginInPanel.setLayout(new BoxLayout(loginInPanel, BoxLayout.Y_AXIS));
		int gap = 20;
		
		// 1. choose user type
		rbtn_userType = new MyRadioButton("You want to register an acount of which type ? ", loginInPanel, new String[] {"Customer", "Restaurant", "Courier"});
		rbtn_userType.bindItemListener(this);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 2. username
		usernameField = new TextFieldWithLabel("username: ", "username already exists !", loginInPanel);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 3. password
		passwordField = new PsdFieldWithLabel("password: ", loginInPanel);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 4. confirm password
		confirm_passwordField = new PsdFieldWithLabel("confirm password: ", "two password not match !", loginInPanel);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 5. first/last name or name
		subPanel_name = new JPanel();
		loginInPanel.add(subPanel_name);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 6. address/position
		position = new TextFieldWithLabel("address/position: ", "address format wrong !", loginInPanel);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 7. contact
		rbtn_contact = new MyRadioButton("contact: email or phone ?", loginInPanel, new String[] {"Email", "Phone"});
		rbtn_contact.bindItemListener(this);
		rbtn_contact.add(Box.createVerticalStrut(MyRadioButton.gap));
		contactField = new TextFieldWithLabel("", "contact format wrong !", rbtn_contact);
		loginInPanel.add(Box.createVerticalStrut(gap));
		
		// 8. customer/courier option
		subPanel_option = new JPanel();
		loginInPanel.add(subPanel_option);
		loginInPanel.add(Box.createVerticalStrut(gap));
			
		// 9. buttons
		JPanel subPanel_btn = new JPanel();
		subPanel_btn.setLayout(new BoxLayout(subPanel_btn, BoxLayout.X_AXIS));
		// button for login up
		OkButton = new BasicButton("OK");
		OkButton.addActionListener(this);
		subPanel_btn.add(OkButton);
		subPanel_btn.add(Box.createHorizontalStrut(50));
		// button for login in 
		CancelButton = new BasicButton("cancel");
		CancelButton.addActionListener(this);
		subPanel_btn.add(CancelButton);
		loginInPanel.add(subPanel_btn);

		rbtn_userType.getButton("Customer").doClick();	
		rbtn_contact.getButton("Email").doClick();
	}

	/* (non-Javadoc)
	 * @see gui.model.BasicFrameSimplePage#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==OkButton){
			String username = usernameField.getTextFieldContent();
			if(MyFoodora.getInstance().hasUser(username)){
				usernameField.showIllegal();
				usernameField.setTextFieldContent(null);;
			}
			if(!passwordField.getPsdFieldContent().equalsIgnoreCase(confirm_passwordField.getPsdFieldContent())){
				confirm_passwordField.showIllegal();
				confirm_passwordField.setPsdFieldContent(null);
			}
			
			loginIn();
		}
		else if(e.getSource()==CancelButton){
			System.exit(0);
		}
	}
	
	/**
	 * Login in.
	 */
	public void loginIn(){
		
		if(rbtn_userType.getGroup().getSelection()==rbtn_userType.getButton("Customer")) {
			user = new Customer(firstnameField.getTextFieldContent(), 
					lastnameField.getTextFieldContent(), 
					usernameField.getTextFieldContent(), 
					new AddressPoint(position.getTextFieldContent()), 
					passwordField.getPsdFieldContent());
			((Customer)user).setActived(notified);
		}else if(rbtn_userType.getGroup().getSelection()==rbtn_userType.getButton("Courier")){
			user = new Courier(firstnameField.getTextFieldContent(), 
					lastnameField.getTextFieldContent(), 
					usernameField.getTextFieldContent(),  
					new AddressPoint(position.getTextFieldContent()), 
					passwordField.getPsdFieldContent());
			((Courier)user).setOn_duty(duty);
		}else if(rbtn_userType.getGroup().getSelection()==rbtn_userType.getButton("Restaurant")){
			user = new Restaurant(nameField.getTextFieldContent(), 
					usernameField.getTextFieldContent(), 
					new AddressPoint(position.getTextFieldContent()), 
					passwordField.getPsdFieldContent());
		}
		
		if(rbtn_contact.getGroup().getSelection()==rbtn_contact.getButton("Email")){
			user.setEmail(contactField.getTextFieldContent());
		}else if(rbtn_contact.getGroup().getSelection()==rbtn_contact.getButton("Phone")){
			user.setPhone(contactField.getTextFieldContent());
		}
		
		if(user!=null){
			MyFoodora.getInstance().addUser(user);
			JOptionPane.showMessageDialog(this, "login in successful", "Information", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "Information incomplete", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==rbtn_userType.getButton("Customer")){
			subPanel_name.removeAll();
			subPanel_option.removeAll();
			// first/last name 
			firstnameField = new TextFieldWithLabel("firstname: ", subPanel_name);
			lastnameField = new TextFieldWithLabel("lastname: ", subPanel_name);
			
			// agree/refuse to be notified
			rbtn_notify = new MyRadioButton("Do you agree to be notified about the special offer ? It's no by default.", subPanel_option, new String[] {"yes", "no"});
			rbtn_notify.bindItemListener(this);
			rbtn_notify.getButton("no").doClick();
			
		}else if(e.getSource()==rbtn_userType.getButton("Courier")){
			subPanel_name.removeAll();
			subPanel_option.removeAll();
			// first/last name 
			firstnameField = new TextFieldWithLabel("firstname: ", subPanel_name);
			lastnameField = new TextFieldWithLabel("lastname: ", subPanel_name);
			
			// agree/refuse to be on duty
			rbtn_duty = new MyRadioButton("Do you want to set your current duty status as on-duty ? By default it's off-duty.", subPanel_option, new String[] {"on", "off"});
			rbtn_duty.bindItemListener(this);
			rbtn_duty.getButton("off").doClick();

		}else if(e.getSource()==rbtn_userType.getButton("Restaurant")){
			subPanel_name.removeAll();
			subPanel_option.removeAll();
			// name
			nameField = new TextFieldWithLabel("name: ", subPanel_name);
			
		}else if(e.getSource()==rbtn_contact.getButton("Email")){
			contactField.getLabel().setText("Email: ");
		}else if(e.getSource()==rbtn_contact.getButton("Phone")){
			contactField.getLabel().setText("Phone: ");
		}else if(e.getSource()==rbtn_notify.getButton("yes")){
			notified = true;		
		}else if(e.getSource()==rbtn_notify.getButton("no")){
			notified = false;	
		}else if(e.getSource()==rbtn_duty.getButton("on")){
			duty = true;		
		}else if(e.getSource()==rbtn_duty.getButton("off")){
			duty = false;		
		}
		
		controlPanel.validate();
	}	
}
