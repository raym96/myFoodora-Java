package gui;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import exceptions.NameNotFoundException;
import gui.model.BasicFrameSimplePage;
import gui.model.BasicFrameWithTabs;
import gui.model.MsgBoardPanel;
import gui.model.MyComboBox;
import gui.model.MyListWithButtons;
import gui.model.TextFieldWithLabel;
import gui.model.UserInfoPanel;
import restaurant.Dish;
import restaurant.Item;
import restaurant.Meal;
import restaurant.MealMenu;
import restaurant.Menu;
import system.ConcreteShoppingCartVisitor;
import system.Order;
import user.model.Customer;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.CustomerService;

/**
 * The Class CustomerGUI.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class CustomerGUI extends BasicFrameWithTabs{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The customer. */
	private Customer customer;
	
	/** The service. */
	private CustomerService service;
	
	/** The restaurants. */
	private ArrayList<User> restaurants;
	
	/** The r selected. */
	private Restaurant r_selected;
	
	/** The orders. */
	private ArrayList<Order> orders;
	
	/** The order. */
	private Order order;
	
	/** The panel function. */
	private JPanel panel_function;
	
	/** The panel info. */
	private JPanel panel_info;
	
	/** The panel message board. */
	private JPanel panel_messageBoard;
	
	/** The msg board. */
	private MsgBoardPanel msgBoard;
	
	/**
	 * Instantiates a new customer GUI.
	 *
	 * @param user the user
	 */
	public CustomerGUI(User user) {
		super("customer");
		
		customer = (Customer)user;
		service = customer.getService();
		this.setTitle("Customer - " + customer.getUsername());
		restaurants = MyFoodora.getInstance().getUsersOfAssignedType("RESTAURANT");
		
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
		new UserInfoPanel(customer.getUsername(), panel_info);
	}
	
	/**
	 * Layout tab msg board.
	 */
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(customer, panel_messageBoard);
	}
	
//	public void fffff() {
//		// TODO Auto-generated method stub
//		panel_function.setLayout(new GridLayout(5, 1));
//		
//		/* 1. label */
//		JLabel indicate = new JLabel("Please add orders to your shopchart :", JLabel.CENTER);
//		panel_function.add(indicate);
//		
//		/* 2. restaurant field */
//		ArrayList<String> restaurantnames = new ArrayList<String>();
//		for(User r : restaurants){
//			restaurantnames.add(r.getUsername());
//		}
//		MyListWithButtons restaurantlist = new MyListWithButtons(restaurantnames, controlPanel, new String[] {"Choose"});
//		String selected = restaurantlist.getSelectedValue();
//		
//		ArrayList<String> dishnames = getItemnamesFromRestaurant(selected, "dish");
//		ArrayList<String> mealnames = getItemnamesFromRestaurant(selected, "meal");
//		ArrayList<String> specialmealnames = getItemnamesFromRestaurant(selected, "specialmeal");
//		
//		/* 3. dish/meal choose field */
////		JPanel subPanel_choose = new JPanel(new GridLayout(1, 3));
////		MyListWithButtons dishlist = new MyListWithButtons(dishnames, subPanel_choose, new String[] {"Add to shopchart"});
////		MyListWithButtons meallist = new MyListWithButtons(mealnames, subPanel_choose, new String[] {"Add to shopchart"});
////		MyListWithButtons specialmeallist = new MyListWithButtons(specialmealnames, subPanel_choose, new String[] {"Add to shopchart"});
////		controlPanel.add(subPanel_choose);
//		
//		/* 4. order field */
//		JPanel subPanel_order = new JPanel(new GridLayout(1, 2));
//		order = new Order(customer, r_selected, "order-" + customer.getUsername() + "-" + r_selected.getUsername());
//		ArrayList<String> itemnames = getItemnamesFromOrder(order);
//		MyListWithButtons itemsInorder = new MyListWithButtons(itemnames, subPanel_order, new String[] {"confirm", "remove", "clear"});
//			
//		orders = new ArrayList<Order>();
//		ArrayList<String> ordernames = new ArrayList<String>();
//		MyListWithButtons orderField = new MyListWithButtons(ordernames, subPanel_order, new String[] {"pay all"});
//		controlPanel.add(subPanel_order);
//		
//		/* 5. total price */
//		TextFieldWithLabel totalPrice = new TextFieldWithLabel("Total Price", controlPanel);
//		totalPrice.getTextfield().setText(String.valueOf(0.0));
//		
//		
//		/* listeners */
//		restaurantlist.getButton("Choose").addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String selected = restaurantlist.getSelectedValue();
//				ArrayList<String> dishnames = getItemnamesFromRestaurant(selected, "dish");
//				ArrayList<String> mealnames = getItemnamesFromRestaurant(selected, "meal");
//				ArrayList<String> specialmealnames = getItemnamesFromRestaurant(selected, "specialmeal");
//				dishlist.refresh(dishnames);
//				meallist.refresh(mealnames);
//				specialmeallist.refresh(specialmealnames);
//				order = new Order(customer, r_selected, "order-" + customer.getUsername() + "-" + r_selected.getUsername());
//				itemsInorder.refresh(new ArrayList<String>());
//				totalPrice.getTextfield().setText(String.valueOf(0.0));
//			}
//		});
//		dishlist.getButton("Add to shopchart").addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String itemname =  dishlist.getSelectedValue().split(" - ")[0];
//				addItem2Order(itemname, order);
//				ArrayList<String> itemnames = getItemnamesFromOrder(order);
//				itemsInorder.refresh(itemnames);
//				totalPrice.getTextfield().setText(String.valueOf(order.accept(new ConcreteShoppingCartVisitor())));
//			}
//		});
//		meallist.getButton("Add to shopchart").addActionListener(new ActionListener() {
//					
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String itemname =  meallist.getSelectedValue().split(" - ")[0];
//				addItem2Order(itemname, order);
//				ArrayList<String> itemnames = getItemnamesFromOrder(order);
//				itemsInorder.refresh(itemnames);
//				totalPrice.getTextfield().setText(String.valueOf(order.accept(new ConcreteShoppingCartVisitor())));
//			}
//		});
//		specialmeallist.getButton("Add to shopchart").addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String itemname =  specialmeallist.getSelectedValue().split(" - ")[0];
//				addItem2Order(itemname, order);
//				ArrayList<String> itemnames = getItemnamesFromOrder(order);
//				itemsInorder.refresh(itemnames);
//				totalPrice.getTextfield().setText(String.valueOf(order.accept(new ConcreteShoppingCartVisitor())));
//			}
//		});
//		itemsInorder.getButton("confirm").addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				orders.add(order);
//				order.clearItem();
//				itemsInorder.refresh(new ArrayList<String>());
//				
//				totalPrice.getTextfield().setText(String.valueOf(order.accept(new ConcreteShoppingCartVisitor())));
//				
//				for(Order order : orders){
//					ordernames.add(order.getName() + " - " + order.accept(new ConcreteShoppingCartVisitor()));
//				}
//				orderField.refresh(ordernames);
//			}
//		});
//		itemsInorder.getButton("remove").addActionListener(new ActionListener() {
//					
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String itemname =  itemsInorder.getSelectedValue().split(" - ")[0];
//				removeItemFromOrder(itemname, order);
//				ArrayList<String> itemnames = getItemnamesFromOrder(order);
//				itemsInorder.refresh(itemnames);
//				
//				totalPrice.getTextfield().setText(String.valueOf(order.accept(new ConcreteShoppingCartVisitor())));
//			}
//		});
//		itemsInorder.getButton("clear").addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				itemsInorder.refresh(new ArrayList<String>());
//				order.clearItem();
//				totalPrice.getTextfield().setText(String.valueOf(0.0));
//			}
//		});
//	}

	/**
 * Adds the item 2 order.
 *
 * @param itemName the item name
 * @param order the order
 */
public void addItem2Order(String itemName, Order order){
		
		Menu menu = order.getRestaurant().getMenu();
		MealMenu mealmenu = order.getRestaurant().getMealMenu();
		MealMenu specialmealmenu = order.getRestaurant().getSpecialmealmenu();
		Item item = null;
		if (menu.hasDish(itemName)){
			item = menu.getDish(itemName);
		}
		else if (mealmenu.hasMeal(itemName)){
			item = mealmenu.getMeal(itemName);
		}
		else if (specialmealmenu.hasMeal(itemName)){
			item = specialmealmenu.getMeal(itemName);
		}
	
		order.addItem(item);
	}
	
	/**
	 * Removes the item from order.
	 *
	 * @param itemName the item name
	 * @param order the order
	 */
	public void removeItemFromOrder(String itemName, Order order){
		Menu menu = order.getRestaurant().getMenu();
		MealMenu mealmenu = order.getRestaurant().getMealMenu();
		MealMenu specialmealmenu = order.getRestaurant().getSpecialmealmenu();
		Item item = null;
		if (menu.hasDish(itemName)){
			item = menu.getDish(itemName);
		}
		else if (mealmenu.hasMeal(itemName)){
			item = mealmenu.getMeal(itemName);
		}
		else if (specialmealmenu.hasMeal(itemName)){
			item = specialmealmenu.getMeal(itemName);
		}
		
		order.removeItem(item);
	}
	
	/**
	 * Gets the itemnames from order.
	 *
	 * @param order the order
	 * @return the itemnames from order
	 */
	public ArrayList<String> getItemnamesFromOrder(Order order){
		ArrayList<String> itemnames = new ArrayList<String>();
		for(Item item : order.getItems()){
			if(item instanceof Dish){
				itemnames.add(((Dish)item).getDishName() + " - " + ((Dish)item).getPrice());
			}else if(item instanceof Meal){
				itemnames.add(((Meal)item).getName() + " - " + ((Meal)item).getRawprice());
			}
		}
		return itemnames;
	}
	
	/**
	 * Gets the itemnames from restaurant.
	 *
	 * @param restaurantName the restaurant name
	 * @param type the type
	 * @return the itemnames from restaurant
	 */
	public ArrayList<String> getItemnamesFromRestaurant(String restaurantName, String type){
		ArrayList<String> itemnames = new ArrayList<String>();
		try {
			r_selected = (Restaurant) MyFoodora.getInstance().getService().selectUser(restaurantName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(type.equalsIgnoreCase("dish")){
			for(Dish d : r_selected.getMenu().getDishes()){
				itemnames.add(d.getDishName() + " - " + d.getPrice());
			}
		}else if(type.equalsIgnoreCase("meal")){
			for(Meal m : r_selected.getMealMenu().getMeals()){
				itemnames.add(m.getName() + " - " + m.getRawprice());
			}
		}else if(type.equalsIgnoreCase("specialmeal")){
			for(Meal m : r_selected.getSpecialmealmenu().getMeals()){
				itemnames.add(m.getName() + " - " + m.getRawprice());
			}
		}
		return itemnames;
	}
}
