package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import exceptions.NameNotFoundException;
import gui.model.BasicButton;
import gui.model.BasicFrameSimplePage;
import gui.model.BasicFrameWithTabs;
import gui.model.ChildFrame;
import gui.model.MsgBoardPanel;
import gui.model.MyComboBox;
import gui.model.MyListWithButtons;
import gui.model.SystemData;
import gui.model.TextFieldWithLabel;
import gui.model.UserInfoPanel;
import restaurant.Dish;
import restaurant.Item;
import restaurant.MainDish;
import restaurant.Meal;
import restaurant.MealMenu;
import restaurant.Menu;
import restaurant.Starter;
import system.ConcreteShoppingCartVisitor;
import system.Message;
import system.Order;
import user.model.Courier;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.CustomerService;

public class CustomerGUI extends BasicFrameWithTabs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private CustomerService service;
	private ArrayList<User> restaurants;
	private Restaurant restaurant;
	private Order order;
	private int order_count;
	private ArrayList<Item> items;
	private ArrayList<String> itemnames;
	private double totalPrice;
	private double balance;
	
	private JPanel panel_order;
	private JPanel panel_info;
	private JPanel panel_messageBoard;
	private MsgBoardPanel msgBoard;
	
	private MyListWithButtons starterList;
	private MyListWithButtons mainList;
	private MyListWithButtons dessertList;
	private MyListWithButtons mealList;
	private MyListWithButtons specialmealList;
	private MyListWithButtons shoppingCart;
	private JLabel totalPriceLabel;
	
	
 	public CustomerGUI(User user) {
		super("customer");
		
		customer = (Customer)user;
		service = customer.getService();
		this.setTitle("Customer - " + customer.getUsername());
		restaurants = MyFoodora.getInstance().getUsersOfAssignedType("RESTAURANT");
		
		for(Order order : MyFoodora.getInstance().getHistory().getOrders()){
			if(order.getCustomer()==customer){
				order_count++;
			}
		}
		balance = 10000;
		
		placeComponents();
	}

	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
		addTab("order");
		addTab("personal information");
		addTab("message board");
		
		panel_order = (JPanel) tabbedPane.getComponentAt(0);
		panel_info = (JPanel) tabbedPane.getComponentAt(1);
		panel_messageBoard = (JPanel) tabbedPane.getComponentAt(2);
		
		layoutTabOrder();
		layoutTabInfo();
		layoutTabMsgBoard();
		
	}
	
	public void layoutTabOrder(){
		panel_order.removeAll();
		
		JPanel panel_content = new JPanel();
		panel_order.add(panel_content);
		panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.Y_AXIS));
		
		panel_content.add(Box.createVerticalStrut(150));
		
		JPanel subp = new JPanel();
		panel_content.add(subp);
		
		subp.setLayout(new BoxLayout(subp, BoxLayout.X_AXIS));
		
		MyComboBox restaurant_chosen = new MyComboBox("Please select a restaurant to order your dish/meal: ", subp, SystemData.getUsernamesFromUsers(restaurants, "desc"));
		
		subp.add(Box.createHorizontalStrut(30));
			
		BasicButton enter = new BasicButton("Select", subp);
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChildFrame restaurantDetaik = createRestaurantDetailChildFrame(restaurant_chosen.getComboSelected().split(" - ")[0]);
			}
		});
		
	}
	
	
	public void layoutTabInfo(){
		panel_info.removeAll();
		new UserInfoPanel(customer.getUsername(), panel_info);
	}
	
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(customer, panel_messageBoard);
	}
	
	public ChildFrame createRestaurantDetailChildFrame(String restaurantName){
		ChildFrame restaurantDetail = new ChildFrame(restaurantName, this);
		restaurantDetail.setLocation(this.getLocation().x+20, this.getLocation().y+20);
		restaurantDetail.setSize(this.getWidth()-50, this.getHeight()-50);
		try {
			restaurant = (Restaurant) MyFoodora.getInstance().getService().selectUser(restaurantName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itemnames = new ArrayList<String>();
		items = new ArrayList<Item>();
		totalPrice = 0.0;
		
		JPanel panel_content = new JPanel();
		restaurantDetail.add(panel_content);
		panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.Y_AXIS));
		final int gap = 10;
		
		panel_content.add(Box.createVerticalStrut(20));
		JLabel welcome = new JLabel("Dear <" + customer.getUsername() + ">, welcome to " + restaurant.getUsername());
		panel_content.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_dishes = new JPanel();
		panel_content.add(subPanel_dishes);
		panel_content.add(Box.createVerticalStrut(gap));
		subPanel_dishes.setLayout(new BoxLayout(subPanel_dishes, BoxLayout.X_AXIS));
		subPanel_dishes.add(Box.createHorizontalStrut(70));
		
		starterList = new MyListWithButtons("Starter", SystemData.getDishnamesFromRestaurantByCategoryWithPrice(restaurant, "Starter"), subPanel_dishes, new String[] {"Add to shopping cart"});
		starterList.bindActionListener(this);
		mainList = new MyListWithButtons("Main", SystemData.getDishnamesFromRestaurantByCategoryWithPrice(restaurant, "Main"), subPanel_dishes, new String[] {"Add to shopping cart"});
		mainList.bindActionListener(this);
		dessertList = new MyListWithButtons("Dessert", SystemData.getDishnamesFromRestaurantByCategoryWithPrice(restaurant, "Dessert"), subPanel_dishes, new String[] {"Add to shopping cart"});
		dessertList.bindActionListener(this);
		subPanel_dishes.add(Box.createHorizontalStrut(70));
		
		JPanel subPanel_meals = new JPanel();
		panel_content.add(subPanel_meals);
		panel_content.add(Box.createVerticalStrut(gap));
		subPanel_meals.setLayout(new BoxLayout(subPanel_meals, BoxLayout.X_AXIS));
		subPanel_meals.add(Box.createHorizontalStrut(70));	
		mealList = new MyListWithButtons("Meals", SystemData.getMealnamesFromRestaurantWithDishesWithPrice(restaurant), subPanel_meals, new String[] {"Add to shopping cart"});
		mealList.bindActionListener(this);
		specialmealList = new MyListWithButtons("Special meals", SystemData.getSpecialmealnamesFromRestaurantWithDishesWithPrice(restaurant), subPanel_meals, new String[] {"Add to shopping cart"});
		specialmealList.bindActionListener(this);
		subPanel_meals.add(Box.createHorizontalStrut(70));	
		
		itemnames = new ArrayList<String>();
		shoppingCart = new MyListWithButtons("shopping cart", itemnames, panel_content, new String[] {"Remove", "Clear"});
		shoppingCart.bindActionListener(this);
		panel_content.add(Box.createVerticalStrut(gap));

		totalPriceLabel = new JLabel("TotalPrice : " + String.valueOf(totalPrice) + " euros");
		panel_content.add(totalPriceLabel);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_btns = new JPanel();
		panel_content.add(subPanel_btns);
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		subPanel_btns.add(Box.createHorizontalStrut(70));
		(new BasicButton("Submit and pay", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				order = new Order(customer, restaurant, "order "+order_count);
				order.setCourier(null);
				for(Item item : items){
					order.addItem(item);
				}
				createPayFrame(restaurantDetail, totalPrice);
				JOptionPane.showMessageDialog(restaurantDetail, "Successfully submitted !");
				ArrayList<Courier> availablecouriers = MyFoodora.getInstance().getAvailableCouriers();
				MyFoodora.getInstance().getService().findDeliverer(order, availablecouriers);
//				MyFoodora.getInstance().getHistory().addOrder(order);		
				customer.getMessageBoard().addMessage(new Message(customer.getUsername(), "You have submitted an order : " + order.toString()));
				msgBoard.refresh();
				items.clear();
				itemnames.clear();
				shoppingCart.refresh(itemnames);
				totalPrice = 0.0;
				totalPriceLabel.setText("TotalPrice : " + totalPrice + " euros");
				
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(70));
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				restaurantDetail.close();
			}
		});
		
		
		return restaurantDetail;
	}
	
	public ChildFrame createPayFrame(Container c, double totalPrice){
		ChildFrame payFrame = new ChildFrame("pay", c);
		
		JPanel panel_content = new JPanel();
		payFrame.controlPanel.add(panel_content);
		panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.Y_AXIS));
		final int gap = 40;
		
		panel_content.add(Box.createVerticalStrut(80));
		
		JLabel balanceLabel = new JLabel("balance : " + balance + " euros");
		panel_content.add(balanceLabel);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JLabel costLabel = new JLabel("cost : " + totalPrice + " euros");
		panel_content.add(costLabel);
		panel_content.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_btns = new JPanel();
		panel_content.add(subPanel_btns);
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		subPanel_btns.add(Box.createHorizontalStrut(70));
		(new BasicButton("pay", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				balance -= totalPrice;
				JOptionPane.showMessageDialog(payFrame, "Payment successful !");
				payFrame.close();
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(70));
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payFrame.close();
			}
		});
		
		
		return payFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
		
		if(e.getSource()==starterList.getButton("Add to shopping cart")){
			String selected = starterList.getSelectedValue();
			itemnames.add(selected);
			items.add(restaurant.getMenu().getDish(selected.split(" - ")[0]));
			totalPrice += Double.valueOf(selected.split(" - ")[selected.split(" - ").length-1].split(" ")[0]);
		}else if(e.getSource()==mainList.getButton("Add to shopping cart")){
			String selected = mainList.getSelectedValue();
			itemnames.add(selected);
			items.add(restaurant.getMenu().getDish(selected.split(" - ")[0]));
			totalPrice += Double.valueOf(selected.split(" - ")[selected.split(" - ").length-1].split(" ")[0]);
		}else if(e.getSource()==dessertList.getButton("Add to shopping cart")){
			String selected = dessertList.getSelectedValue();
			itemnames.add(selected);
			items.add(restaurant.getMenu().getDish(selected.split(" - ")[0]));
			totalPrice += Double.valueOf(selected.split(" - ")[selected.split(" - ").length-1].split(" ")[0]);
		}else if(e.getSource()==mealList.getButton("Add to shopping cart")){
			String selected = mealList.getSelectedValue();
			itemnames.add(selected);
			Meal m = restaurant.getMealMenu().getMeal(selected.split(" - ")[0]);
			m.setRestaurant(restaurant);
			items.add(m);
			totalPrice += Double.valueOf(selected.split(" - ")[selected.split(" - ").length-1].split(" ")[0]);
		}else if(e.getSource()==specialmealList.getButton("Add to shopping cart")){
			String selected = specialmealList.getSelectedValue();
			itemnames.add(selected);
			Meal m = restaurant.getSpecialmealmenu().getMeal(selected.split(" - ")[0]);
			m.setRestaurant(restaurant);
			items.add(m);
			totalPrice += Double.valueOf(selected.split(" - ")[selected.split(" - ").length-1].split(" ")[0]);
		}else if(e.getSource()==shoppingCart.getButton("Remove")){
			String selected = shoppingCart.getSelectedValue();
			itemnames.remove(selected);
			items.remove(restaurant.getMealMenu().getMeal(selected.split(" - ")[0]));
			totalPrice -= Double.valueOf(selected.split(" - ")[selected.split(" - ").length-1].split(" ")[0]);
		}else if(e.getSource()==shoppingCart.getButton("Clear")){
			itemnames.clear();
			items.clear();
			totalPrice = 0.0;
		}
		shoppingCart.refresh(itemnames);
		totalPriceLabel.setText("TotalPrice : " + String.valueOf(Math.floor(totalPrice*100)/100) + " euros");
	}
}
