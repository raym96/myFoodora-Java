package gui.model;

import java.awt.Color;
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
import policies.LotteryCard;
import policies.PointCard;
import policies.StandardCard;
import system.AddressPoint;
import system.Message;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;

public class UserInfoPanel extends JPanel{
	
	private User user;
	
	private final int gap_usual = 50;
	private final int gap = 15;
	private final int gap_btn = 40;
	
	private JPanel header;
	private JLabel idField;
	private JLabel usernameField;
	private TextFieldWithLabel nameField;
	private TextFieldWithLabel positionField;
	private TextFieldWithLabel emailField;
	private TextFieldWithLabel phoneField;
	private JLabel activeField;
	private MyRadioButton optionField;
	private MyRadioButton rbtn_fidelitycard;
	
	private Container superContainer;

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

	public UserInfoPanel(String username, Container c) {
		this(username);
		c.add(this);
	}
	
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
		header = new MyLabel("User information: " + user.getUsername(), new Font("Arial", Font.ITALIC, 25), Color.BLACK).generateMyLabelPanel(50);
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
			if(((Customer)user).isNotified()){
				optionField.getButton("yes").setSelected(true);
			}else{
				optionField.getButton("no").setSelected(true);
			}
			this.add(Box.createVerticalStrut(gap));
			// fidelitycard
			rbtn_fidelitycard = new MyRadioButton("Fidelity card", this, new String[] {"Standard Card", "Lottery Card", "Point Card"});
			if(((Customer)user).getCard() instanceof StandardCard ){
				rbtn_fidelitycard.getButton("Standard Card").setSelected(true);
			}else if(((Customer)user).getCard() instanceof LotteryCard){
				rbtn_fidelitycard.getButton("Lottery Card").setSelected(true);
			}else if(((Customer)user).getCard() instanceof PointCard){
				rbtn_fidelitycard.getButton("Point Card").setSelected(true);
			}
			
		}else if(user instanceof Courier){
			optionField = new MyRadioButton("duty status", this, new String[] {"on", "off"});
			if(((Courier)user).isOn_duty()){
				optionField.getButton("on").setSelected(true);
			}else{
				optionField.getButton("off").setSelected(true);
			}
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
					if(rbtn_fidelitycard.getButton("Standard Card").isSelected()){
						((Customer)new_user).setCard(new StandardCard((Customer)new_user));
					}else if(rbtn_fidelitycard.getButton("Lottery Card").isSelected()){
						((Customer)new_user).setCard(new LotteryCard((Customer)new_user));
					}else if(rbtn_fidelitycard.getButton("Point Card").isSelected()){
						((Customer)new_user).setCard(new PointCard((Customer)new_user));
					}
				}else if(new_user instanceof Courier){
					((Courier)new_user).setFullName(nameField.getTextFieldContent());
					((Courier)new_user).setPosition(new AddressPoint(positionField.getTextFieldContent()));
				}
				MyFoodora.getInstance().removeUser(user);
				MyFoodora.getInstance().addUser(new_user);
				user.getMessageBoard().addMessage(new Message(user.getUsername(), "You have modified personal information."));
				
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
