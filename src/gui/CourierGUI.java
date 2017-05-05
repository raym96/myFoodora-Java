package gui;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import exceptions.NameNotFoundException;
import gui.model.BasicButton;
import gui.model.BasicFrameSimplePage;
import gui.model.BasicFrameWithTabs;
import gui.model.ChildFrame;
import gui.model.MsgBoardPanel;
import gui.model.MyComboBox;
import gui.model.MyListWithButtons;
import gui.model.MyRadioButton;
import gui.model.SystemData;
import gui.model.TextFieldWithLabel;
import gui.model.UserInfoPanel;
import restaurant.Dish;
import restaurant.Item;
import restaurant.Meal;
import system.AddressPoint;
import system.ConcreteShoppingCartVisitor;
import system.Message;
import system.Order;
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
	private Order order;
	
	private JPanel panel_Order;
	private JPanel panel_info;
	private JPanel panel_messageBoard;
	private MsgBoardPanel msgBoard;
	
	private MyListWithButtons orderList;

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
		addTab("order");
		addTab("personnel information");
		addTab("message board");
		
		panel_Order = (JPanel) tabbedPane.getComponentAt(0);
		panel_info = (JPanel) tabbedPane.getComponentAt(1);
		panel_messageBoard = (JPanel) tabbedPane.getComponentAt(2);
		
		layoutTabOrder();
		layoutTabInfo();
		layoutTabMsgBoard();
		
	}
	
	public void layoutTabOrder(){
		panel_Order.removeAll();
		
		JPanel subPanel_orders = new JPanel();
		panel_Order.add(subPanel_orders);
		subPanel_orders.setLayout(new BoxLayout(subPanel_orders, BoxLayout.Y_AXIS));
		final int gap_lists = 150;
		
		subPanel_orders.add(Box.createVerticalStrut(gap_lists));
		orderList = new MyListWithButtons("orders assigned to you :¡¡", SystemData.getAssignedOrderNamesByCourier(courier), subPanel_orders, new String[] {"Detail"});
		orderList.bindActionListener(this);
	}
	
	public void layoutTabInfo(){
		panel_info.removeAll();
		new UserInfoPanel(courier.getUsername(), panel_info);
	}
	
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(courier, panel_messageBoard);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
		if(e.getSource()==orderList.getButton("Detail")){
			ChildFrame orderDetail = createOrderDetailChildFrame(orderList.getSelectedValue());
		}
	}
		
	public ChildFrame createOrderDetailChildFrame(String selected) {
		
		if(selected==null){
			return null;
		}
		
		order = MyFoodora.getInstance().getHistory().getOrderById(selected.split(" - ")[1]);
		
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
		
		JLabel ordercourier= new JLabel("courier : " + order.getCourier().getName());
		panel_content.add(ordercourier);
		panel_content.add(Box.createVerticalStrut(gap));
		
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
		(new BasicButton("Refuse", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				order.setCourier(null);
				courier.getMessageBoard().addMessage(new Message(courier.getUsername(), "You have refused the order " + order.getName()));
				msgBoard.refresh();
				orderDetail.close();
				refreshOrderList();
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderDetail.close();
			}
		});
		panel_content.add(subPanel_btns);

		return orderDetail;
	}

	public void refreshOrderList(){
		orderList.refresh(SystemData.getAssignedOrderNamesByCourier(courier));
	}
}
