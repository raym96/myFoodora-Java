package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import com.sun.javafx.collections.MappingChange.Map;

import exceptions.NameNotFoundException;
import gui.model.BasicButton;
import gui.model.BasicFrameWithTabs;
import gui.model.ChildFrame;
import gui.model.MsgBoardPanel;
import gui.model.MyComboBox;
import gui.model.MyLabel;
import gui.model.MyListWithButtons;
import gui.model.MyRadioButton;
import gui.model.SystemData;
import gui.model.TextFieldWithLabel;
import gui.model.UserInfoPanel;
import policies.FairOccupationDeliveryPolicy;
import policies.FastestDeliveryPolicy;
import policies.SortingByCourierDeliveries;
import policies.SortingByCriteria;
import policies.SortingByRestaurant;
import policies.TargetProfit_DeliveryCost;
import policies.TargetProfit_Markup;
import policies.TargetProfit_ServiceFee;
import restaurant.Dish;
import restaurant.Item;
import restaurant.Meal;
import sun.net.www.content.image.jpeg;
import system.AddressPoint;
import system.ConcreteShoppingCartVisitor;
import system.Message;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.ManagerService;
import user.service.UserService;
import user.view.UserView;

public class ManagerGUI extends BasicFrameWithTabs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Manager manager;
	private ManagerService service;
	private User user;;
	private Courier courier;
	
	private JPanel panel_user;
	private JPanel panel_performance;
	private JPanel panel_setting;
	private JPanel panel_info;
	private JPanel panel_messageBoard;
	private MsgBoardPanel msgBoard;
	
	
	private MyRadioButton rbtn_notify;
	private MyRadioButton rbtn_duty;
	
	private MyFoodora myFoodora;
	private ArrayList<User> restaurants;
	private ArrayList<User> customers;
	private ArrayList<User> couriers;
	private Order order;

	private MyListWithButtons restaurantList;
	private MyListWithButtons courierList;
	private MyListWithButtons customerList;
	private MyListWithButtons assignedOrderList;
	private MyListWithButtons unassignedOrderList;
	private MyComboBox ordercourier;
	
	private HashMap<String, String> myfoodora_params;
	
	private String startingDate = "01/01/2017";

	public ManagerGUI(User user) {
		super("manager");

		manager = (Manager)user;
		service = manager.getService();
		this.setTitle("Manager - " + manager.getUsername());
		
		myFoodora = MyFoodora.getInstance();
		
		restaurants = myFoodora.getService().getUsersOfAssignedType("RESTAURANT");
		couriers = myFoodora.getService().getUsersOfAssignedType("COURIER");
		customers = myFoodora.getService().getUsersOfAssignedType("CUSTOMER");
		
		placeComponents();
	}

	@Override
	public void placeComponents() {

		addTab("users");
		addTab("Performance");
		addTab("Setting");
		addTab("personnel information");
		addTab("message board");
		
		panel_user = (JPanel) tabbedPane.getComponentAt(0);
		panel_performance = (JPanel) tabbedPane.getComponentAt(1);
		panel_setting = (JPanel) tabbedPane.getComponentAt(2);
		panel_info = (JPanel) tabbedPane.getComponentAt(3);
		panel_messageBoard = (JPanel) tabbedPane.getComponentAt(4);
		
		layoutTabUser();
		layoutTabPerformance();
		layoutTabSetting();
		layoutTabInfo();
		layoutTabMsgBoard();
	}
	
	public void layoutTabUser(){
		panel_user.removeAll();
		
		panel_user.setLayout(new BoxLayout(panel_user, BoxLayout.Y_AXIS));
		final int gap_usual = 50;
		panel_user.add(Box.createVerticalStrut(gap_usual));
		final int gap_lists = 70;
		
		JPanel label_userManagement = new MyLabel("User management", new Font("Arial", Font.ITALIC, 20), Color.BLACK).generateMyLabelPanel(50);
		panel_user.add(label_userManagement);
		panel_user.add(Box.createVerticalStrut(10));
		
		JPanel subPanel_lists = new JPanel();
		panel_user.add(subPanel_lists);
		subPanel_lists.setLayout(new BoxLayout(subPanel_lists, BoxLayout.X_AXIS));
			
		subPanel_lists.add(Box.createHorizontalStrut(gap_lists));
		restaurantList = new MyListWithButtons("restaurants", SystemData.getUsernamesFromUsers(restaurants, "desc"), subPanel_lists, new String[] {"Detail", "Add new"});
		restaurantList.bindActionListener(this);
		courierList = new MyListWithButtons("couriers", SystemData.getUsernamesFromUsers(couriers, "desc"), subPanel_lists, new String[] {"Detail", "Add new"});
		courierList.bindActionListener(this);
		customerList = new MyListWithButtons("customers", SystemData.getUsernamesFromUsers(customers), subPanel_lists, new String[] {"Detail", "Add new"});
		customerList.bindActionListener(this);
		subPanel_lists.add(Box.createHorizontalStrut(gap_lists));
		
		JPanel label_orderManagement = new MyLabel("Order management", new Font("Arial", Font.ITALIC, 20), Color.BLACK).generateMyLabelPanel(50);
		panel_user.add(label_orderManagement);
		panel_user.add(Box.createVerticalStrut(10));
		
		JPanel subPanel_orders = new JPanel();
		panel_user.add(subPanel_orders);
		subPanel_orders.setLayout(new BoxLayout(subPanel_orders, BoxLayout.X_AXIS));
		
		subPanel_orders.add(Box.createHorizontalStrut(gap_lists));
		assignedOrderList = new MyListWithButtons("assigned orders", SystemData.getAssignedOrderNames(), subPanel_orders, new String[] {"Detail"});
		assignedOrderList.bindActionListener(this);
		unassignedOrderList = new MyListWithButtons("unassigned orders", SystemData.getUnassignedOrderNames(), subPanel_orders, new String[] {"Detail"});
		unassignedOrderList.bindActionListener(this);
		subPanel_orders.add(Box.createHorizontalStrut(gap_lists));
		
	}

	public void layoutTabPerformance(){
		
		panel_performance.removeAll();
		myfoodora_params = SystemData.updateParams();
		
		JPanel subPanel_content = new JPanel();
		panel_performance.add(subPanel_content);
		subPanel_content.setLayout(new BoxLayout(subPanel_content, BoxLayout.Y_AXIS));
		
		final int gap_usual = 150;
		subPanel_content.add(Box.createVerticalStrut(gap_usual));
		
		final int gap = 40;
		
		Font font = new Font("Arial", Font.ITALIC, 20);
		final String Monetary_unit = " euros";
		
		JLabel time_periode = new JLabel("from " + startingDate + " until now :" );
		time_periode.setFont(font);
		subPanel_content.add(time_periode);
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		JLabel totalIncome = new JLabel("total income: " + myfoodora_params.get("total income") + Monetary_unit);
		totalIncome.setFont(font);
		subPanel_content.add(totalIncome);
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		JLabel totalProfit = new JLabel("total profit: " + myfoodora_params.get("total profit") + Monetary_unit);
		totalProfit.setFont(font);
		subPanel_content.add(totalProfit);
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		JLabel averageIncome = new JLabel("average income per customer: " + myfoodora_params.get("average income per customer") + Monetary_unit);
		averageIncome.setFont(font);
		subPanel_content.add(averageIncome);
		subPanel_content.add(Box.createVerticalStrut(gap));
	}
	
	public void layoutTabSetting(){
		
		panel_setting.removeAll();
		
		JPanel subPanel_content = new JPanel();
		panel_setting.add(subPanel_content);
		subPanel_content.setLayout(new BoxLayout(subPanel_content, BoxLayout.Y_AXIS));
		
		
		final int gap_usual = 150;
		subPanel_content.add(Box.createVerticalStrut(gap_usual));
		
		final int gap = 20;
		
		myfoodora_params = SystemData.updateParams();
		
		TextFieldWithLabel targetProfitField = new TextFieldWithLabel("current target profit: ", "data format wrong !", subPanel_content);
		targetProfitField.setTextFieldContent(myfoodora_params.get("targetProfit"));
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		MyRadioButton rbtn_targetProfitPolicy = new MyRadioButton("target policy policy", subPanel_content, new String[] {"delivery cost", "markup percentage", "service fee"});
		rbtn_targetProfitPolicy.getButton(myfoodora_params.get("targetprofitPolicy")).setSelected(true);
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		TextFieldWithLabel servicefeeField = new TextFieldWithLabel("service fee: ", "data format wrong !", subPanel_content);
		servicefeeField.setTextFieldContent(myfoodora_params.get("servicefee"));
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		TextFieldWithLabel markUpPercentageField = new TextFieldWithLabel("makeup percentage: ", "data format wrong !", subPanel_content);
		markUpPercentageField.setTextFieldContent(myfoodora_params.get("markupPercentage"));
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		TextFieldWithLabel deliveryCostField = new TextFieldWithLabel("delivery cost: ", "data format wrong !", subPanel_content);
		deliveryCostField.setTextFieldContent(myfoodora_params.get("deliveryCost"));
		subPanel_content.add(Box.createVerticalStrut(gap));
		
		MyRadioButton rbtn_deliveryPolicy = new MyRadioButton("delivery policy", subPanel_content, new String[] {"fair-occupation", "fastest"});
		rbtn_deliveryPolicy.getButton(myfoodora_params.get("deliveryPolicy")).setSelected(true);
		subPanel_content.add(Box.createVerticalStrut(gap));

		JPanel subPanel_btn = new JPanel();
		subPanel_content.add(subPanel_btn);
		subPanel_btn.setLayout(new BoxLayout(subPanel_btn, BoxLayout.X_AXIS));
		(new BasicButton("Save modification", subPanel_btn)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFoodora.setTargetprofit(Double.valueOf(targetProfitField.getTextFieldContent()));
				if(rbtn_targetProfitPolicy.getButton("delivery cost").isSelected()){
					service.setTargetProfitPolicy("delivery_cost");
					
					myFoodora.setService_fee(Double.valueOf(servicefeeField.getTextFieldContent()));
					myFoodora.setMarkup_percentage(Double.valueOf(markUpPercentageField.getTextFieldContent()));
					service.determineParam2MeetTargetProfit(myFoodora.getTargetprofit());
					servicefeeField.setTextEnable(true);
					markUpPercentageField.setTextEnable(true);
					deliveryCostField.setTextEnable(false);
					
				}else if(rbtn_targetProfitPolicy.getButton("markup percentage").isSelected()){
					service.setTargetProfitPolicy("markup_percentage");
					
					myFoodora.setService_fee(Double.valueOf(servicefeeField.getTextFieldContent()));
					myFoodora.setDelivery_cost(Double.valueOf(deliveryCostField.getTextFieldContent()));
					service.determineParam2MeetTargetProfit(myFoodora.getTargetprofit());
					servicefeeField.setTextEnable(true);
					markUpPercentageField.setTextEnable(false);
					deliveryCostField.setTextEnable(true);
					
				}else if(rbtn_targetProfitPolicy.getButton("service fee").isSelected()){
					service.setTargetProfitPolicy("service_fee");
					
					myFoodora.setMarkup_percentage(Double.valueOf(markUpPercentageField.getTextFieldContent()));
					myFoodora.setDelivery_cost(Double.valueOf(deliveryCostField.getTextFieldContent()));
					service.determineParam2MeetTargetProfit(myFoodora.getTargetprofit());
					servicefeeField.setTextEnable(false);
					markUpPercentageField.setTextEnable(true);
					deliveryCostField.setTextEnable(true);
					
				}

				servicefeeField.setTextFieldContent(String.valueOf(myFoodora.getService_fee()));
				markUpPercentageField.setTextFieldContent(String.valueOf(myFoodora.getMarkup_percentage()));
				deliveryCostField.setTextFieldContent(String.valueOf(myFoodora.getDelivery_cost()));
				
				if(rbtn_deliveryPolicy.getButton("fair-occupation").isSelected()){
					service.setDeliveryPolicy("fair");
				}else if(rbtn_deliveryPolicy.getButton("fastest").isSelected()){
					service.setDeliveryPolicy("fastest");
				}
				
				myfoodora_params = SystemData.updateParams();
			}
		});
		subPanel_btn.add(Box.createHorizontalStrut(100));
		(new BasicButton("Reset", subPanel_btn)).addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				targetProfitField.setTextFieldContent(myfoodora_params.get("targetProfit"));
				rbtn_targetProfitPolicy.getButton(myfoodora_params.get("targetprofitPolicy")).setSelected(true);
				servicefeeField.setTextFieldContent(myfoodora_params.get("servicefee"));
				markUpPercentageField.setTextFieldContent(myfoodora_params.get("markupPercentage"));
				deliveryCostField.setTextFieldContent(myfoodora_params.get("deliveryCost"));
				rbtn_deliveryPolicy.getButton(myfoodora_params.get("deliveryPolicy")).setSelected(true);
			}
		});
	}
	
	public void layoutTabInfo(){
		panel_info.removeAll();
		new UserInfoPanel(manager.getUsername(), panel_info);
	}
	
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(manager, panel_messageBoard);
	}
	
	public ChildFrame createUserDetailChildFrame(String username){
		user = null;
		try {
			 user = myFoodora.getService().selectUser(username);
		} catch (NameNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ChildFrame userDetail = new ChildFrame(username + " information", this);
		JPanel panel = new JPanel();
		userDetail.controlPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final int gap = 20;
		panel.add(Box.createVerticalStrut(gap));
		
		// id
		panel.add(new JLabel("ID: " + user.getID()));
		panel.add(Box.createVerticalStrut(gap));
		
		// username
		TextFieldWithLabel usernameField = new TextFieldWithLabel("username: ", "username already exists !",panel);
		usernameField.setTextFieldContent(user.getUsername());
		panel.add(Box.createVerticalStrut(gap));
		
		// name
		TextFieldWithLabel nameField = new TextFieldWithLabel("name: ", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// password
		TextFieldWithLabel psdField = new TextFieldWithLabel("password: ", panel);
		psdField.setTextFieldContent(user.getPassword());
		panel.add(Box.createVerticalStrut(gap));
		
		// address/position
		TextFieldWithLabel positionField = new TextFieldWithLabel("address/position: ", "address format wrong ! (example:2.1,8.5)", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// email
		TextFieldWithLabel emailField = new TextFieldWithLabel("email: ", panel);
		emailField.setTextFieldContent(user.getEmail());
		panel.add(Box.createVerticalStrut(gap));
		
		// phone
		TextFieldWithLabel phoneField = new TextFieldWithLabel("phone: ", panel);
		phoneField.setTextFieldContent(user.getPhone());
		panel.add(Box.createVerticalStrut(gap));
		
		// activated status
		MyRadioButton rbtn_active = new MyRadioButton("activated", panel, new String[] {"on", "off"});
		if(user.isActivated()){
			rbtn_active.getButton("on").setSelected(true);
		}else{
			rbtn_active.getButton("off").setSelected(true);
		}
		panel.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_option = new JPanel();
		panel.add(subPanel_option);
		panel.add(Box.createVerticalStrut(gap));
		
		// buttons
		JPanel subPanel_btns = new JPanel();
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		(new BasicButton("Save modification", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean success = false;
				
				if(user instanceof Restaurant){

					Restaurant restaurant = (Restaurant)user;
					
					String newUsername = usernameField.getTextFieldContent();
					if(SystemData.getUsernamesFromUsers(MyFoodora.getInstance().getUsers()).contains(newUsername) && !newUsername.equalsIgnoreCase(user.getUsername())){
						usernameField.showIllegal();
					}else{
						usernameField.hideIllegal();
						restaurant.setUsername(usernameField.getTextFieldContent());
					}
					
					restaurant.setName(nameField.getTextFieldContent());
					
					AddressPoint address = null;
					try{
						address = new AddressPoint(positionField.getTextFieldContent());
					} catch (Exception e3) {
						e3.printStackTrace();
					}
					if(address!=null){
						positionField.hideIllegal();
						restaurant.setAddress(address);
					}else{
						positionField.showIllegal();
					}
							
					restaurant.setPassword(psdField.getTextFieldContent());
					restaurant.setEmail(emailField.getTextFieldContent());
					restaurant.setPhone(phoneField.getTextFieldContent());
					if(rbtn_active.getButton("on").isSelected()){
						restaurant.setActivated(true);
					}else if(rbtn_active.getButton("off").isSelected()){
						restaurant.setActivated(false);
					}
		
					success = true;
				}else if(user instanceof Courier){
					Courier courier = (Courier)user;
					
					String newUsername = usernameField.getTextFieldContent();
					usernameField.hideIllegal();
					if(SystemData.getUsernamesFromUsers(MyFoodora.getInstance().getUsers()).contains(newUsername)){
						usernameField.showIllegal();
					}else{
						courier.setUsername(usernameField.getTextFieldContent());
					}
					
					courier.setFullName(nameField.getTextFieldContent());
					
					AddressPoint address = null;
					try{
						address = new AddressPoint(positionField.getTextFieldContent());
					} catch (Exception e3) {
						e3.printStackTrace();
					}
					if(address!=null){
						positionField.hideIllegal();
						courier.setPosition(address);
					}else{
						positionField.showIllegal();
					}
					
					courier.setPassword(psdField.getTextFieldContent());
					courier.setEmail(emailField.getTextFieldContent());
					courier.setPhone(phoneField.getTextFieldContent());
					if(rbtn_active.getButton("on").isSelected()){
						courier.setActivated(true);
					}else if(rbtn_active.getButton("off").isSelected()){
						courier.setActivated(false);
					}
					if(rbtn_duty.getButton("on").isSelected()){
						courier.setOn_duty(true);
					}else if(rbtn_duty.getButton("off").isSelected()){
						courier.setOn_duty(false);
					}
					success = true;
				}else if(user instanceof Customer){
					Customer customer = (Customer)user;
					
					String newUsername = usernameField.getTextFieldContent();
					usernameField.hideIllegal();
					if(SystemData.getUsernamesFromUsers(MyFoodora.getInstance().getUsers()).contains(newUsername)){
						usernameField.showIllegal();
					}else{
						customer.setUsername(usernameField.getTextFieldContent());
					}
					
					customer.setFullName(nameField.getTextFieldContent());
					
					AddressPoint address = null;
					try{
						address = new AddressPoint(positionField.getTextFieldContent());
					} catch (Exception e3) {
						e3.printStackTrace();
					}
					if(address!=null){
						positionField.hideIllegal();
						customer.setAddress(address);
					}else{
						positionField.showIllegal();
					}
					
					customer.setEmail(emailField.getTextFieldContent());
					customer.setPhone(phoneField.getTextFieldContent());
					if(rbtn_active.getButton("on").isSelected()){
						customer.setActivated(true);
					}else if(rbtn_active.getButton("off").isSelected()){
						customer.setActivated(false);
					}
					if(rbtn_notify.getButton("yes").isSelected()){
						customer.setNotified(true);
					}else if(rbtn_notify.getButton("no").isSelected()){
						customer.setNotified(false);
					}
					success = true;
				}
				if(success){
					refreshUserLists("desc");
//					JOptionPane.showMessageDialog(userDetail, "Modify successful !");
					manager.getMessageBoard().addMessage(new Message(manager.getUsername(), "You have modified the information of user : " + user.getUsername()));
					msgBoard.refresh();
					
				}
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userDetail.close();
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Delete", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = true;
				int result = JOptionPane.showConfirmDialog(userDetail, "Delete this user ? ", "Warning", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION){
					
					for(Order order : myFoodora.getHistory().getOrders()){
						if(user==order.getCourier() || user==order.getCustomer() || user==order.getRestaurant()){
							success = false;
						}
					}
					
					if(success){
						try {
							service.removeUser(user.getUsername());
						} catch (NameNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						userDetail.close();
						refreshUserLists("desc");
						manager.getMessageBoard().addMessage(new Message(manager.getUsername(), "You have deleted the user : " + username));
						msgBoard.refresh();
					}else{
						JOptionPane.showMessageDialog(userDetail, "This user has order, you can't delete it.");
					}
					
				}
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		panel.add(subPanel_btns);
		
		if(user instanceof Restaurant){
			Restaurant restaurant = (Restaurant) user;
			nameField.setTextFieldContent(restaurant.getName());
			positionField.setTextFieldContent(restaurant.getAddress().toString());
		}else if(user instanceof Courier){
			Courier courier = (Courier) user;
			nameField.setTextFieldContent(courier.getFullName());
			positionField.setTextFieldContent(courier.getPosition().toString());
			
			rbtn_duty = new MyRadioButton("duty status", subPanel_option, new String[] {"on", "off"});
			if(courier.isOn_duty()){
				rbtn_duty.getButton("on").setSelected(true);
			}else{
				rbtn_duty.getButton("off").setSelected(true);
			}
		}else if(user instanceof Customer){
			Customer customer = (Customer)user;
			nameField.setTextFieldContent(customer.getFullName());
			positionField.setTextFieldContent(customer.getAddress().toString());
			rbtn_notify = new MyRadioButton("agree to be notified", subPanel_option, new String[] {"yes", "no"});
			if(customer.isNotified()){
				rbtn_notify.getButton("yes").setSelected(true);
			}else{
				rbtn_notify.getButton("no").setSelected(true);
			}
		}
		
		return userDetail;
	}

	public ChildFrame createAddUserChildFrame(String userType){
		ChildFrame addUser = new ChildFrame("add new user", this);
		JPanel panel = new JPanel();
		addUser.controlPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final int gap = 20;
		panel.add(Box.createVerticalStrut(gap));
		
		// username
		TextFieldWithLabel usernameField = new TextFieldWithLabel("username: ", "username already exists !",panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// name
		TextFieldWithLabel nameField = new TextFieldWithLabel("name: ", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// password
		TextFieldWithLabel psdField = new TextFieldWithLabel("password: ", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// address/position
		TextFieldWithLabel positionField = new TextFieldWithLabel("address/position: ", "address format wrong ! (example:2.1,8.5)", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// email
		TextFieldWithLabel emailField = new TextFieldWithLabel("email: ", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// phone
		TextFieldWithLabel phoneField = new TextFieldWithLabel("phone: ", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		// activated status
		MyRadioButton rbtn_active = new MyRadioButton("activated", panel, new String[] {"on", "off"});
		rbtn_active.getButton("on").setSelected(true);
		panel.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_option = new JPanel();
		panel.add(subPanel_option);
		panel.add(Box.createVerticalStrut(gap));
		
		// buttons
		JPanel subPanel_btns = new JPanel();
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Add", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User user = null;
				boolean o1 = false;
				
				String newUsername = usernameField.getTextFieldContent();
				if(SystemData.getUsernamesFromUsers(MyFoodora.getInstance().getUsers()).contains(newUsername)){
					usernameField.showIllegal();
				}else{
					usernameField.hideIllegal();
					o1 = true;
				}
				
				AddressPoint address = null;
				try{
					address = new AddressPoint(positionField.getTextFieldContent());
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				if(address!=null){
					positionField.hideIllegal();
				}else{
					positionField.showIllegal();
				}
				
				if(o1 && address!=null){
					if(userType.equalsIgnoreCase("RESTAURANT")){
						user = new Restaurant(nameField.getTextFieldContent(), usernameField.getTextFieldContent(), new AddressPoint(positionField.getTextFieldContent()), psdField.getTextFieldContent());
					}else if(userType.equalsIgnoreCase("COURIER")){
						user = new Courier(nameField.getTextFieldContent().split(" ")[0], nameField.getTextFieldContent().split(" ")[1], usernameField.getTextFieldContent(), new AddressPoint(positionField.getTextFieldContent()), psdField.getTextFieldContent());
						if(rbtn_duty.getButton("on").isSelected()){
							((Courier)user).setOn_duty(true);
						}else if(rbtn_duty.getButton("off").isSelected()){
							((Courier)user).setOn_duty(false);
						}
					}else if(userType.equalsIgnoreCase("CUSTOMER")){
						user = new Customer(nameField.getTextFieldContent().split(" ")[0], nameField.getTextFieldContent().split(" ")[1], usernameField.getTextFieldContent(), new AddressPoint(positionField.getTextFieldContent()), psdField.getTextFieldContent());
						if(rbtn_notify.getButton("yes").isSelected()){
							((Customer)user).setNotified(true);
						}else if(rbtn_notify.getButton("no").isSelected()){
							((Customer)user).setNotified(false);
						}
					}
					user.setEmail(emailField.getTextFieldContent());
					user.setPhone(phoneField.getTextFieldContent());
					if(rbtn_active.getButton("on").isSelected()){
						user.setActivated(true);
					}else if(rbtn_active.getButton("off").isSelected()){
						user.setActivated(false);
					}
				}
				
				
				if(user != null){
					service.addUser(user);
					refreshUserLists("desc");
					JOptionPane.showMessageDialog(addUser, "Add successful !");
					manager.getMessageBoard().addMessage(new Message(manager.getUsername(), "You have added a new user : " + user.getUsername()));
					msgBoard.refresh();
				}
			}
		});
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addUser.close();
			}
		});

		subPanel_btns.add(Box.createHorizontalStrut(100));
		panel.add(subPanel_btns);
		
		if(userType.equalsIgnoreCase("RESTAURANT")){
			
		}else if(userType.equalsIgnoreCase("COURIER")){
			rbtn_duty = new MyRadioButton("duty status", subPanel_option, new String[] {"on", "off"});
			rbtn_duty.getButton("off").setSelected(true);
		}else if(userType.equalsIgnoreCase("CUSTOMER")){
			rbtn_notify = new MyRadioButton("agree to be notified", subPanel_option, new String[] {"yes", "no"});
			rbtn_notify.getButton("no").setSelected(true);
		}
	
		return addUser;
	}
	
	public ChildFrame createOrderDetailChildFrame(String selected) {
		
		order = MyFoodora.getInstance().getHistory().getOrderById(selected.split(" - ")[1]);
		courier = order.getCourier();
		
		ChildFrame orderDetail = new ChildFrame("order detail", this);
		
		JPanel panel_content = new JPanel();
		orderDetail.controlPanel.add(panel_content);
		panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.Y_AXIS));
		final int gap = 30;
		panel_content.add(Box.createVerticalStrut(gap));
		
		JLabel orderId = new JLabel("order ID : " + order.getOrderID());
		panel_content.add(orderId);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JLabel orderdate = new JLabel("order date : " + order.getDate());
		panel_content.add(orderdate);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JLabel ordename = new JLabel("order name : " + order.getName());
		panel_content.add(ordename);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JLabel orderrestaurant= new JLabel("restaurant : " + order.getRestaurant().getName());
		panel_content.add(orderrestaurant);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JLabel ordercustomer= new JLabel("customer : " + order.getCustomer().getName());
		panel_content.add(ordercustomer);
		panel_content.add(Box.createVerticalStrut(gap));
		
		if(courier != null){
			JLabel ordercourier= new JLabel("courier : " + order.getCourier().getName());
			panel_content.add(ordercourier);
			panel_content.add(Box.createVerticalStrut(gap));
		}else{
			ordercourier = new MyComboBox("courier : ", panel_content, SystemData.getUsernamesFromUsers(couriers));
			panel_content.add(Box.createVerticalStrut(gap));
		}
		
		String content = null;
		for(Item item : order.getItems()){
			if (item instanceof Dish){
				content+="A-la-carte <";
				content+= ((Dish)item).getDishName()+"> "+item.accept(new ConcreteShoppingCartVisitor())+"\n";
			}
			if (item instanceof Meal){
				content+="Meal <";
				content+= ((Meal)item).getName()+"> "+item.accept(new ConcreteShoppingCartVisitor())+"\n";
			}
		}
		
		JTextArea items= new JTextArea(content);
		items.setEditable(false);
		panel_content.add(items);
		panel_content.add(Box.createVerticalStrut(gap));
		
		// buttons
		JPanel subPanel_btns = new JPanel();
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Confirm", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(courier != null){
					orderDetail.close();
				}else{
					try {
						courier = (Courier) MyFoodora.getInstance().getService().selectUser(ordercourier.getComboSelected());
					} catch (NameNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(courier != null){
						order.setCourier(courier);
						orderDetail.close();
						manager.getMessageBoard().addMessage(new Message(manager.getUsername(), "You have assigned the order " + order.getName() + "to the courier " + courier.getUsername()));
						msgBoard.refresh();
						refreshOrderList();
					}
				}
				
			}
		});
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderDetail.close();
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		panel_content.add(subPanel_btns);

		return orderDetail;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);

		// for tab page "user"
		if(e.getSource()==restaurantList.getButton("Detail")){
			ChildFrame userDetail = createUserDetailChildFrame(restaurantList.getSelectedValue().split(" - ")[0]);
		}else if(e.getSource()==restaurantList.getButton("Add new")){;
			ChildFrame addUser = createAddUserChildFrame("RESTAURANT");
		}else if(e.getSource()==courierList.getButton("Detail")){;
			ChildFrame userDetail = createUserDetailChildFrame(courierList.getSelectedValue().split(" - ")[0]);
		}else if(e.getSource()==courierList.getButton("Add new")){
			ChildFrame addUser = createAddUserChildFrame("COURIER");
		}else if(e.getSource()==customerList.getButton("Detail")){
			ChildFrame userDetail = createUserDetailChildFrame(customerList.getSelectedValue());
		}else if(e.getSource()==customerList.getButton("Add new")){
			ChildFrame addUser = createAddUserChildFrame("CUSTOMER");
		}else if(e.getSource()==assignedOrderList.getButton("Detail")){;
			ChildFrame orderDetail = createOrderDetailChildFrame(assignedOrderList.getSelectedValue());
		}else if(e.getSource()==unassignedOrderList.getButton("Detail")){;
			ChildFrame orderDetail = createOrderDetailChildFrame(unassignedOrderList.getSelectedValue());
		}
	}
	
	
	public void refreshUserLists(String sortingType){
		restaurantList.refresh(SystemData.getUsernamesFromUsers(restaurants, sortingType));
		customerList.refresh(SystemData.getUsernamesFromUsers(customers, sortingType));
		courierList.refresh(SystemData.getUsernamesFromUsers(couriers, sortingType));
	}
	
	public void refreshOrderList(){
		assignedOrderList.refresh(SystemData.getAssignedOrderNames());
		unassignedOrderList.refresh(SystemData.getUnassignedOrderNames());
	}
}
