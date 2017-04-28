package user.view.GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import system.AddressPoint;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.view.GUI.model.BasicButton;
import user.view.GUI.model.BasicFrameSimplePage;
import user.view.GUI.model.TextFieldWithLabel;

public class LoginIn extends BasicFrameSimplePage implements ItemListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TextFieldWithLabel usernameField;
	private JPasswordField passwordText;
	private JPasswordField confirm_passwordText;
	private TextFieldWithLabel firstnameField;
	private TextFieldWithLabel lastnameField;
	private TextFieldWithLabel nameField;
	private TextFieldWithLabel position;
	private TextFieldWithLabel contactField;
	
	private JButton OkButton;
	private JButton CancelButton;
	
	private ButtonGroup group_usertype;
	private JRadioButton radCustomer;
	private JRadioButton radRestaurant;
	private JRadioButton radCourier;
	
	private ButtonGroup group_contact;
	private JRadioButton radEmail;
	private JRadioButton radPhone;
	
	private ButtonGroup group_option;
	private JRadioButton radAgreeNotified;
	private JRadioButton radRefuseNotified;
	private JRadioButton radOnDuty;
	private JRadioButton radOffDuty;
	
	private JPanel subPanel5;
	private JPanel subPanel8;
	
	private User user;
	private boolean notified = false;
	private boolean duty = false;

	public LoginIn() {
		super("login in");

		headerLabel.setText("Login up");
		statusLabel.setText("login in as a restaurant, a customer or a courier ");
	}
	
	@Override
	public void placeComponents() {

		GridLayout layout = new GridLayout();
		layout.setRows(9);
		layout.setColumns(1);
		layout.setVgap(10);
		controlPanel.setLayout(layout);
		
		// 1. choose user type
		JPanel subPanel1 = new JPanel();
		JLabel indicate_userType = new JLabel("You want to register an acount of which type ? ");
		subPanel1.add(indicate_userType);
		radCustomer = new JRadioButton("Customer");
		radCustomer.addItemListener(this);
		radRestaurant = new JRadioButton("Restaurant");
		radRestaurant.addItemListener(this);
		radCourier = new JRadioButton("Courier", true);
		radCourier.addItemListener(this);
		subPanel1.add(radCustomer);
		subPanel1.add(radCourier);
		subPanel1.add(radRestaurant);
		group_usertype = new ButtonGroup();
		group_usertype.add(radCustomer);
		group_usertype.add(radRestaurant);
		group_usertype.add(radCourier);
		controlPanel.add(subPanel1);
		
		// 2. username
		JPanel subPanel2 = new JPanel();
		usernameField = new TextFieldWithLabel("username:", subPanel2);
		controlPanel.add(subPanel2);
		
		// 3. password
		JPanel subPanel3 = new JPanel();
		JLabel passwordLabel = new JLabel("password:");
		subPanel3.add(passwordLabel);
		passwordText = new JPasswordField(20);
		subPanel3.add(passwordText);
		controlPanel.add(subPanel3);
		
		// 4. confirm password
		JPanel subPanel4 = new JPanel();	
		JLabel makesure_passwordLabel = new JLabel("confrim password:");
		subPanel4.add(makesure_passwordLabel);	
		confirm_passwordText = new JPasswordField(20);
		subPanel4.add(confirm_passwordText);
		controlPanel.add(subPanel4);
		
		// 5. first/last name or name
		subPanel5 = new JPanel();
		controlPanel.add(subPanel5);
		
		// 6. address/position
		JPanel subPanel6 = new JPanel();
		position = new TextFieldWithLabel("address/position", subPanel6);
		controlPanel.add(subPanel6);
		
		// 7. indicate contact
		JPanel subPanel7 = new JPanel();
		JLabel indicate_contact = new JLabel("contact: email or phone ?");
		subPanel7.add(indicate_contact);
		radEmail = new JRadioButton("Email   ");
		radEmail.addItemListener(this);
		radPhone = new JRadioButton("Phone   ",true);
		radPhone.addItemListener(this);
		subPanel7.add(radEmail);
		subPanel7.add(radPhone);
		group_contact = new ButtonGroup();
		group_contact.add(radEmail);
		group_contact.add(radPhone);
		contactField = new TextFieldWithLabel("", subPanel7);
		controlPanel.add(subPanel7);
		
		// 8. customer/courier option
		subPanel8 = new JPanel();
		controlPanel.add(subPanel8);
			
		// 9. buttons
		JPanel subPanel9 = new JPanel();
		// button for login up
		OkButton = new BasicButton("OK");
		OkButton.addActionListener(this);
		subPanel9.add(OkButton);
		// button for login in 
		CancelButton = new BasicButton("cancel");
		CancelButton.addActionListener(this);
		subPanel9.add(CancelButton);
		controlPanel.add(subPanel9);
		
		radCustomer.doClick();	
		radEmail.doClick();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==OkButton){
			System.out.println("OK");
			String username = usernameField.getTextFieldContent();
			if(MyFoodora.getInstance().hasUser(username)){
				JOptionPane.showMessageDialog(this, "username already exists!", "Warning", JOptionPane.ERROR_MESSAGE);
				usernameField.getTextfield().setText(null);
			}
			if(!String.valueOf(passwordText.getPassword()).equals(String.valueOf(confirm_passwordText.getPassword()))){
				JOptionPane.showMessageDialog(this, "password not match!", "Warning", JOptionPane.ERROR_MESSAGE);
				confirm_passwordText.setText(null);
			}
			
			loginInSuccess();
		}
		else if(e.getSource()==CancelButton){
			System.out.println("Cancel");
			System.exit(0);
		}
	}
	
	public void loginInSuccess(){
		if(group_usertype.getSelection()==radCustomer) {
			user = new Customer(firstnameField.getTextFieldContent(), 
					lastnameField.getTextFieldContent(), 
					usernameField.getTextFieldContent(), 
					new AddressPoint(position.getTextFieldContent()), 
					String.valueOf(passwordText.getPassword()));
			((Customer)user).setActived(notified);
		}else if(group_usertype.getSelection()==radCourier){
			user = new Courier(firstnameField.getTextFieldContent(), 
					lastnameField.getTextFieldContent(), 
					usernameField.getTextFieldContent(),  
					new AddressPoint(position.getTextFieldContent()), 
					String.valueOf(passwordText.getPassword()));
			((Courier)user).setOn_duty(duty);
		}else if(group_usertype.getSelection()==radRestaurant){
			user = new Restaurant(nameField.getTextFieldContent(), 
					usernameField.getTextFieldContent(), 
					new AddressPoint(position.getTextFieldContent()), 
					String.valueOf(passwordText.getPassword()));
		}
		
		if(group_contact.getSelection()==radEmail){
			user.setEmail(contactField.getTextFieldContent());
		}else if(group_contact.getSelection()==radPhone){
			user.setPhone(contactField.getTextFieldContent());
		}
		
		MyFoodora.getInstance().addUser(user);
		JOptionPane.showMessageDialog(this, "login in successful", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource()==radCustomer){
			subPanel5.removeAll();
			subPanel8.removeAll();
			// first/last name 
			firstnameField = new TextFieldWithLabel("firstname:", subPanel5);
			lastnameField = new TextFieldWithLabel("lastname:", subPanel5);
			
			// agree/refuse to be notified
			JLabel indicate_customer_option = new JLabel("Do you agree to be notified about the special offer ? It's no by default.");
			subPanel8.add(indicate_customer_option);
			radAgreeNotified = new JRadioButton("Yes");
			radAgreeNotified.addItemListener(this);
			radRefuseNotified = new JRadioButton("No", true);
			radRefuseNotified.addItemListener(this);
			subPanel8.add(radAgreeNotified);
			subPanel8.add(radRefuseNotified);
			group_option = new ButtonGroup();
			group_option.add(radAgreeNotified);
			group_option.add(radRefuseNotified);
		}else if(e.getSource()==radCourier){
			subPanel5.removeAll();
			subPanel8.removeAll();
			// first/last name 
			firstnameField = new TextFieldWithLabel("firstname:", subPanel5);
			lastnameField = new TextFieldWithLabel("lastname:", subPanel5);
			
			// agree/refuse to be notified
			JLabel indicate_courier_option = new JLabel("Do you want to set your current duty status as on-duty ? By default it's off-duty.");
			subPanel8.add(indicate_courier_option);
			radOnDuty = new JRadioButton("Yes");
			radOnDuty.addItemListener(this);
			radOffDuty = new JRadioButton("No", true);
			radOffDuty.addItemListener(this);
			subPanel8.add(radOnDuty);
			subPanel8.add(radOffDuty);
			group_option = new ButtonGroup();
			group_option.add(radOnDuty);
			group_option.add(radOffDuty);
		}else if(e.getSource()==radRestaurant){
			subPanel5.removeAll();
			subPanel8.removeAll();
			// name
			nameField = new TextFieldWithLabel("name:", subPanel5);;
		}else if(e.getSource()==radEmail){
			contactField.getLabel().setText("Email");
		}else if(e.getSource()==radPhone){
			contactField.getLabel().setText("Phone");
		}else if(e.getSource()==radAgreeNotified){
			notified = true;		
		}else if(e.getSource()==radRefuseNotified){
			notified = false;	
		}else if(e.getSource()==radOnDuty){
			duty = true;		
		}else if(e.getSource()==radOffDuty){
			duty = false;		
		}
		
		controlPanel.validate();
	}	
}
