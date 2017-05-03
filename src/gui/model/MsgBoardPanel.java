package gui.model;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import exceptions.NameNotFoundException;
import system.Message;
import user.model.MyFoodora;
import user.model.User;

public class MsgBoardPanel extends JPanel{

	private final int gap_usual = 100;
	private final int gap = 20;
	private String content;
	private User owner;

	private JTextArea msgArea;
	
	public MsgBoardPanel(User user) {
		super();
	
		owner = user;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(gap_usual));
		
		JLabel header = new JLabel("message board of " + user.getUsername());
		this.add(header);
		this.add(Box.createVerticalStrut(gap));
		    
		msgArea = new JTextArea();
		msgArea.setColumns(80);
		msgArea.setRows(20);
		msgArea.setEditable(false);
		
		refresh();
		
		JScrollPane scrollPane = new JScrollPane(msgArea);
		this.add(scrollPane);
	}

	public MsgBoardPanel(User user, Container c){
		this(user);
		c.add(this);
	}
	
	public void refresh(){
		msgArea.setText(null);
		if(owner.getMessageBoard().getMessages().size()==0){
			msgArea.append("      There are no messages yet.");
		}else{
			msgArea.setText(null);
			for(Message msg : owner.getMessageBoard().getMessages()){
				msgArea.append("      " + msg.toString() + "\n");
			}
		}
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public static void main(String[] args) throws NameNotFoundException {
		
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		SystemData.initialMyFoodora();
		User user = MyFoodora.getInstance().getService().selectUser("dtrump");
		
		
		JPanel panel = new JPanel();
		frame.add(panel);

		panel.add(new MsgBoardPanel(user));
		frame.setVisible(true);
	}
}
