package gui;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.model.BasicButton;
import gui.model.BasicFrameSimplePage;
import gui.model.BasicFrameWithTabs;
import gui.model.MsgBoardPanel;
import gui.model.MyRadioButton;
import gui.model.TextFieldWithLabel;
import gui.model.UserInfoPanel;
import system.AddressPoint;
import user.model.Courier;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.User;
import user.service.CourierService;

public class CourierGUI extends BasicFrameWithTabs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Courier courier;
	private CourierService service;
	
	private JPanel panel_function;
	private JPanel panel_info;
	private JPanel panel_messageBoard;
	private MsgBoardPanel msgBoard;

	public CourierGUI(User user) {
		super("courier");

		courier = (Courier)user;
		service = courier.getService();
		this.setTitle("Courier - " + courier.getUsername());

		
		placeComponents();
	}

	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
		addTab("function");
		addTab("personnel information");
		addTab("message board");
		
		panel_function = (JPanel) tabbedPane.getComponentAt(0);
		panel_info = (JPanel) tabbedPane.getComponentAt(1);
		panel_messageBoard = (JPanel) tabbedPane.getComponentAt(2);
		
		layoutTabFunction();
		layoutTabInfo();
		layoutTabMsgBoard();
		
	}
	
	public void layoutTabFunction(){
		
	}
	
	public void layoutTabInfo(){
		panel_info.removeAll();
		new UserInfoPanel(courier.getUsername(), panel_info);
	}
	
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(courier, panel_messageBoard);
	}
		
}
