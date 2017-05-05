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

/**
 * The Class CourierGUI.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CourierGUI extends BasicFrameWithTabs{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The courier. */
	private Courier courier;
	
	/** The service. */
	private CourierService service;
	
	/** The panel function. */
	private JPanel panel_function;
	
	/** The panel info. */
	private JPanel panel_info;
	
	/** The panel message board. */
	private JPanel panel_messageBoard;
	
	/** The msg board. */
	private MsgBoardPanel msgBoard;

	/**
	 * Instantiates a new courier GUI.
	 *
	 * @param user the user
	 */
	public CourierGUI(User user) {
		super("courier");

		courier = (Courier)user;
		service = courier.getService();
		this.setTitle("Courier - " + courier.getUsername());

		
		placeComponents();
	}

	/* (non-Javadoc)
	 * @see gui.model.BasicFrame#placeComponents()
	 */
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
	
	/**
	 * Layout tab function.
	 */
	public void layoutTabFunction(){
		
	}
	
	/**
	 * Layout tab info.
	 */
	public void layoutTabInfo(){
		panel_info.removeAll();
		new UserInfoPanel(courier.getUsername(), panel_info);
	}
	
	/**
	 * Layout tab msg board.
	 */
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(courier, panel_messageBoard);
	}
		
}
