package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import org.omg.CORBA.REBIND;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import exceptions.NameAlreadyExistsException;
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
import restaurant.Dessert;
import restaurant.Dish;
import restaurant.FullMeal;
import restaurant.HalfMeal;
import restaurant.Item;
import restaurant.MainDish;
import restaurant.Meal;
import restaurant.MealMenu;
import restaurant.Menu;
import restaurant.Starter;
import system.ConcreteShoppingCartVisitor;
import system.Message;
import system.Order;
import system.ShoppingCartVisitor;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.RestaurantService;

public class RestaurantGUI extends BasicFrameWithTabs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Restaurant restaurant;
	private RestaurantService service;
	private Menu menu;
	private MealMenu mealMenu;
	private MealMenu specialmealMenu;
	private Dish dish;
	private Meal meal;
	private Meal specialmeal;
	
	private JPanel panel_menu;
	private JPanel panel_performance;
	private JPanel panel_setting;
	private JPanel panel_info;
	private JPanel panel_messageBoard;
	private MsgBoardPanel msgBoard;
	
	private MyListWithButtons dishList;
	private MyListWithButtons mealList;
	private MyListWithButtons specialmealList;
	private MyRadioButton dishCategory;
	private MyComboBox dishCombo;
	private MyComboBox mealCombo;
	private MyComboBox specialmealCombo;
	
	public RestaurantGUI(User user) {
		super("restaurant");

		restaurant = (Restaurant)user;
		service = restaurant.getService();
		this.setTitle("Restaurant - " + restaurant.getUsername());
		
		menu = restaurant.getMenu();
		mealMenu = restaurant.getMealMenu();
		specialmealMenu = restaurant.getSpecialmealmenu();
		
		placeComponents();
	}

	@Override
	public void placeComponents() {

		addTab("Menu");
//		addTab("Performance");
		addTab("Setting");
		addTab("personal information");
		addTab("message board");
		
		panel_menu = (JPanel) tabbedPane.getComponentAt(0);
//		panel_performance = (JPanel) tabbedPane.getComponentAt(1);
		panel_setting = (JPanel) tabbedPane.getComponentAt(1);
		panel_info = (JPanel) tabbedPane.getComponentAt(2);
		panel_messageBoard = (JPanel) tabbedPane.getComponentAt(3);
		
		layoutTabMenu();
		layoutTabSetting();
		layoutTabInfo();
		layoutTabMsgBoard();
	}

	public void layoutTabMenu(){
		panel_menu.removeAll();
		panel_menu.setLayout(new BoxLayout(panel_menu, BoxLayout.Y_AXIS));
		final int gap_usual = 80;
		panel_menu.add(Box.createVerticalStrut(gap_usual));
		final int gap_lists = 70;
		
		JPanel label_menuManagement = new MyLabel("menu management", new Font("Arial", Font.ITALIC, 20), Color.BLACK).generateMyLabelPanel(50);
		panel_menu.add(label_menuManagement);
		panel_menu.add(Box.createVerticalStrut(10));
		
		JPanel subPanel_lists = new JPanel();
		panel_menu.add(subPanel_lists);
		subPanel_lists.setLayout(new BoxLayout(subPanel_lists, BoxLayout.X_AXIS));
		
		
		subPanel_lists.add(Box.createHorizontalStrut(gap_lists));
		dishList = new MyListWithButtons("dishes (sorted by shipped orders)", SystemData.getDishnamesFromRestaurant(restaurant, "desc"), subPanel_lists, new String[] {"Detail", "Add new", "Remove"});
		dishList.bindActionListener(this);
		mealList = new MyListWithButtons("meals", SystemData.getMealnamesFromRestaurant(restaurant), subPanel_lists, new String[] {"Detail", "Add new", "Add as special meal", "Remove"});
		mealList.bindActionListener(this);
		specialmealList = new MyListWithButtons("special meals", SystemData.getSpecialmealnamesFromRestaurant(restaurant), subPanel_lists, new String[] {"Detail", "Remove"});
		specialmealList.bindActionListener(this);
		subPanel_lists.add(Box.createHorizontalStrut(gap_lists));
		
		panel_menu.add(Box.createVerticalStrut(gap_usual));
		
	}
	
	public void layoutTabSetting(){
		
		panel_setting.removeAll();
		
		JPanel subPanel_content = new JPanel();
		panel_setting.add(subPanel_content);
		subPanel_content.setLayout(new BoxLayout(subPanel_content, BoxLayout.Y_AXIS));
		
		final int gap_usual = 100;
		subPanel_content.add(Box.createVerticalStrut(gap_usual));
		
		final int gap = 50;
		
		TextFieldWithLabel gdf_setting = new TextFieldWithLabel("generic discount factor: ", "please input a factor between 0 and 1 !", subPanel_content);
		gdf_setting.setTextFieldContent(String.valueOf(restaurant.getGeneric_discount_factor()));
		subPanel_content.add(Box.createVerticalStrut(gap_usual));
		
		TextFieldWithLabel sdf_setting = new TextFieldWithLabel("special discount factor: ", "please input a factor between 0 and 1 !", subPanel_content);
		sdf_setting.setTextFieldContent(String.valueOf(restaurant.getSpecial_discount_factor()));
		subPanel_content.add(Box.createVerticalStrut(gap_usual));
		
		JPanel subPanel_btns = new JPanel();
		subPanel_content.add(subPanel_btns);
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		(new BasicButton("Save modification", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double new_gdf = Double.valueOf(gdf_setting.getTextFieldContent().trim());
				double new_sdf = Double.valueOf(gdf_setting.getTextFieldContent().trim());
				
				if(new_gdf>=0 && new_gdf<=1){
					service.setGenericDiscountFactor(new_gdf);
					gdf_setting.hideIllegal();
				}else{
					gdf_setting.showIllegal();
				}
				if(new_sdf>=0 && new_sdf<=1){
					service.setSpecialDiscountFactor(new_sdf);
					sdf_setting.hideIllegal();
				}else{
					sdf_setting.showIllegal();
				}
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Reset", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gdf_setting.setTextFieldContent(String.valueOf(restaurant.getGeneric_discount_factor()));
				sdf_setting.setTextFieldContent(String.valueOf(restaurant.getSpecial_discount_factor()));
			}
		});
	}

	public void layoutTabInfo(){
		panel_info.removeAll();
		new UserInfoPanel(restaurant.getUsername(), panel_info);
	}
	
	public void layoutTabMsgBoard(){
		panel_messageBoard.removeAll();
		msgBoard = new MsgBoardPanel(restaurant, panel_messageBoard);
	}
	
	public ChildFrame createItemDetailChildFrame(String name, String itemType){
		
		ChildFrame itemDetail = new ChildFrame(name + " information", this);
		JPanel panel = new JPanel();
		itemDetail.controlPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final int gap = 20;
		panel.add(Box.createVerticalStrut(50));
		
		JPanel subp1 = new JPanel();
		panel.add(subp1);
		TextFieldWithLabel nameField = new TextFieldWithLabel("name: ", "name already exists !", subp1);
		panel.add(Box.createVerticalStrut(gap));
		
		MyRadioButton rbtn_type = new MyRadioButton("type", panel, new String[] {"standard", "vegetarian", "gluten-free"});
		panel.add(Box.createVerticalStrut(gap));
		
		JPanel subp2 = new JPanel();
		panel.add(subp2);
		TextFieldWithLabel priceField = new TextFieldWithLabel("price: ", "please input a valid price !", subp2);
		panel.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_option = new JPanel();
		panel.add(subPanel_option);
		panel.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_btns = new JPanel();
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		panel.add(subPanel_btns);
		
		(new BasicButton("Save modification", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean success = false;
				
				if(itemType.equalsIgnoreCase("dish")){
					dish = restaurant.getMenu().getDish(name);
					
					String newname = nameField.getTextFieldContent();
					if(SystemData.getDishnamesFromRestaurant(restaurant).contains(newname) && !newname.equalsIgnoreCase(dish.getDishName())){
						nameField.showIllegal();
					}else{
						nameField.hideIllegal();
						dish.setDishName(nameField.getTextFieldContent());
					}

					if(rbtn_type.getButton("standard").isSelected()){
						dish.setDishType("standard");
					}else if(rbtn_type.getButton("vegetarian").isSelected()){
						dish.setDishType("vegetarian");
					}else if(rbtn_type.getButton("gluten-free").isSelected()){
						dish.setDishType("gluten-free");
					}
					dish.setPrice(Double.valueOf(priceField.getTextFieldContent()));
					success = true;
					
				}else if(itemType.equalsIgnoreCase("meal")){
					meal = mealMenu.getMeal(name);
					
					String starter = dishCombo.getComboSelected();
					String main = mealCombo.getComboSelected();
					String dessert = specialmealCombo.getComboSelected();
					
					String newname = nameField.getTextFieldContent();
					if(SystemData.getMealnamesFromRestaurant(restaurant).contains(newname) && !newname.equalsIgnoreCase(meal.getName())){
						nameField.showIllegal();
					}else{
						nameField.hideIllegal();
						
						if(!starter.equals("null") && !dessert.equals("null")){
							meal = new FullMeal(nameField.getTextFieldContent(), menu.getDish(starter), menu.getDish(main), menu.getDish(dessert));
						}else if(!starter.equals("null") && dessert.equals("null")){
							meal = new HalfMeal(nameField.getTextFieldContent(), menu.getDish(starter), menu.getDish(main));
						}else if(starter.equals("null") && !dessert.equals("null")){
							meal = new HalfMeal(nameField.getTextFieldContent(), menu.getDish(dessert), menu.getDish(main));
						}
						meal.refreshMealType();
						rbtn_type.getButton(meal.getMealType()).setSelected(true);
						meal.setRestaurant(restaurant);
						priceField.setTextFieldContent(String.valueOf(meal.accept(new ConcreteShoppingCartVisitor())));
						
						try {
							mealMenu.removeMeal(name);
							mealMenu.addMeal(meal);
							
							success = true;
						} catch (NameNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NameAlreadyExistsException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							nameField.showIllegal();
						}
					}
					
					
					
				}else if(itemType.equalsIgnoreCase("specialmeal")){
					
				}
				if(success){
					refreshMenu("desc");
//					JOptionPane.showMessageDialog(itemDetail, "Modify successful !");
					restaurant.getMessageBoard().addMessage(new Message(restaurant.getUsername(), "You have modified the information of item : " + name));
					msgBoard.refresh();
				}
			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemDetail.close();
			}
		});
		
		if(itemType.equalsIgnoreCase("dish")){
			dish = restaurant.getMenu().getDish(name);
			nameField.setTextFieldContent(dish.getDishName());
			priceField.setTextFieldContent(String.valueOf(dish.getPrice()));
			rbtn_type.getButton(dish.getDishType()).setSelected(true);
			
			dishCategory = new MyRadioButton("Category", subPanel_option, new String[] {"Starter", "Main", "Dessert"});
			if(dish instanceof Starter){
				dishCategory.getButton("Starter").setSelected(true);
			}else if(dish instanceof MainDish){
				dishCategory.getButton("Main").setSelected(true);
			}else if(dish instanceof Dessert){
				dishCategory.getButton("Dessert").setSelected(true);
			}
			dishCategory.setRBtnEnable(false);
			
		}else if(itemType.equalsIgnoreCase("meal")){
			meal = restaurant.getMealMenu().getMeal(name);
			nameField.setTextFieldContent(meal.getName());
			meal.setRestaurant(restaurant);
			priceField.setTextFieldContent(String.valueOf(meal.accept(new ConcreteShoppingCartVisitor())));
			priceField.setTextEditable(false);
			meal.refreshMealType();
			rbtn_type.getButton(meal.getMealType()).setSelected(true);
			rbtn_type.setRBtnEnable(false);
			
			
			subPanel_option.setLayout(new BoxLayout(subPanel_option, BoxLayout.X_AXIS));
			
			subPanel_option.add(Box.createHorizontalStrut(70));
			dishCombo = new MyComboBox("Starter", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Starter"));
			mealCombo = new MyComboBox("Main", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Main"));
			specialmealCombo = new MyComboBox("Dessert", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Dessert"));
			subPanel_option.add(Box.createHorizontalStrut(70));
			for(Dish d : meal.getDishes()){
				if(d instanceof Starter){
					dishCombo.setComboSelected(d.getDishName()+MyComboBox.item_suffix);
				}else if(d instanceof MainDish){
					mealCombo.setComboSelected(d.getDishName()+MyComboBox.item_suffix);
				}else if(d instanceof Dessert){
					specialmealCombo.setComboSelected(d.getDishName()+MyComboBox.item_suffix);
				}
			}
			
		}else if(itemType.equalsIgnoreCase("specialmeal")){
			specialmeal = restaurant.getSpecialmealmenu().getMeal(name);
			nameField.setTextFieldContent(specialmeal.getName());
			nameField.setTextEditable(false);
			specialmeal.setRestaurant(restaurant);
			priceField.setTextFieldContent(String.valueOf(specialmeal.accept(new ConcreteShoppingCartVisitor())));
			priceField.setTextEditable(false);
			rbtn_type.getButton(specialmeal.getMealType()).setSelected(true);
			rbtn_type.setRBtnEnable(false);
			
			subPanel_option.setLayout(new BoxLayout(subPanel_option, BoxLayout.X_AXIS));
			
			subPanel_option.add(Box.createHorizontalStrut(70));
			dishCombo = new MyComboBox("Starter", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Starter"));
			mealCombo = new MyComboBox("Main", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Main"));
			specialmealCombo = new MyComboBox("Dessert", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Dessert"));
			subPanel_option.add(Box.createHorizontalStrut(70));
			for(Dish d : specialmeal.getDishes()){
				if(d instanceof Starter){
					dishCombo.setComboSelected(d.getDishName()+MyComboBox.item_suffix);
				}else if(d instanceof MainDish){
					mealCombo.setComboSelected(d.getDishName()+MyComboBox.item_suffix);
				}else if(d instanceof Dessert){
					specialmealCombo.setComboSelected(d.getDishName()+MyComboBox.item_suffix);
				}
			}
			dishCombo.setComboEnable(false);
			mealCombo.setComboEnable(false);
			specialmealCombo.setComboEnable(false);
			subPanel_btns.getComponent(0).setEnabled(false);
		}
		
		return itemDetail;
	}

	public ChildFrame createAddItemChildFrame(String itemType){
		ChildFrame addItem = new ChildFrame("add new item", this);
		JPanel panel = new JPanel();
		addItem.controlPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final int gap = 20;
		panel.add(Box.createVerticalStrut(gap));
		
		JPanel subPanel_option = new JPanel();
		panel.add(subPanel_option);
		panel.add(Box.createVerticalStrut(gap));
		
		TextFieldWithLabel nameField = new TextFieldWithLabel("name: ", "name already exists !", panel);
		panel.add(Box.createVerticalStrut(gap));
		
		MyRadioButton rbtn_type = new MyRadioButton("type", panel, new String[] {"standard", "vegetarian", "gluten-free"});
		rbtn_type.getButton("standard").setSelected(true);
		panel.add(Box.createVerticalStrut(gap));
		
		TextFieldWithLabel priceField = new TextFieldWithLabel("price: ", "please input a valid price !", panel);
		panel.add(Box.createVerticalStrut(gap));
				
		JPanel subPanel_btns = new JPanel();
		subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.X_AXIS));
		panel.add(subPanel_btns);
		
		(new BasicButton("Add", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(itemType.equalsIgnoreCase("dish")){
					dish = null;
					String type = null;
					if(rbtn_type.getButton("standard").isSelected()){
						type = "standard";
					}else if(rbtn_type.getButton("vegetarian").isSelected()){
						type = "vegetarian";
					}else if(rbtn_type.getButton("gluten-free").isSelected()){
						type = "gluten-free";
					}
					
					if(dishCategory.getButton("Starter").isSelected()){
						dish = new Starter(nameField.getTextFieldContent(), type, Double.valueOf(priceField.getTextFieldContent()));
					}else if(dishCategory.getButton("Main").isSelected()){
						dish = new MainDish(nameField.getTextFieldContent(), type, Double.valueOf(priceField.getTextFieldContent()));
					}else if(dishCategory.getButton("Dessert").isSelected()){
						dish = new Dessert(nameField.getTextFieldContent(), type, Double.valueOf(priceField.getTextFieldContent()));
					}
					if(dish != null){
						try {
							menu.addDish(dish);
						} catch (NameAlreadyExistsException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							nameField.showIllegal();
						}
						refreshMenu("desc");
						JOptionPane.showMessageDialog(addItem, "Add successful !");
						restaurant.getMessageBoard().addMessage(new Message(restaurant.getUsername(), "You have added a new dish : " + dish.getDishName()));
						msgBoard.refresh();
					}
						
				}else if(itemType.equalsIgnoreCase("meal")){
					meal = null;
					

					String starter = dishCombo.getComboSelected();
					String main = mealCombo.getComboSelected();
					String dessert = specialmealCombo.getComboSelected();
					
					if(!starter.equals("null") && !dessert.equals("null")){
						meal = new FullMeal(nameField.getTextFieldContent(), menu.getDish(starter), menu.getDish(main), menu.getDish(dessert));
					}else if(!starter.equals("null") && dessert.equals("null")){
						meal = new HalfMeal(nameField.getTextFieldContent(), menu.getDish(starter), menu.getDish(main));
					}else if(starter.equals("null") && !dessert.equals("null")){
						meal = new HalfMeal(nameField.getTextFieldContent(), menu.getDish(dessert), menu.getDish(main));
					}
					meal.refreshMealType();
					rbtn_type.getButton(meal.getMealType()).setSelected(true);
					meal.setRestaurant(restaurant);
					priceField.setTextFieldContent(String.valueOf(meal.accept(new ConcreteShoppingCartVisitor())));

					if(meal != null){
						try {
							mealMenu.addMeal(meal);
						} catch (NameAlreadyExistsException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							nameField.showIllegal();
						}
						refreshMenu("desc");
						JOptionPane.showMessageDialog(addItem, "Add successful !");
						restaurant.getMessageBoard().addMessage(new Message(restaurant.getUsername(), "You have added a new meal : " + meal.getName()));
						msgBoard.refresh();
					}
				}else if(itemType.equalsIgnoreCase("specialmeal")){
					
				}

			}
		});
		subPanel_btns.add(Box.createHorizontalStrut(100));
		(new BasicButton("Back", subPanel_btns)).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addItem.close();
			}
		});
		
		if(itemType.equalsIgnoreCase("dish")){

			dishCategory = new MyRadioButton("Category", subPanel_option, new String[] {"Starter", "Main", "Dessert"});
			dishCategory.getButton("Starter").setSelected(true);
			
		}else if(itemType.equalsIgnoreCase("meal")){
			subPanel_option.setLayout(new BoxLayout(subPanel_option, BoxLayout.X_AXIS));
			
			dishCombo = new MyComboBox("Starter", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Starter"));
			mealCombo = new MyComboBox("Main", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Main"));
			specialmealCombo = new MyComboBox("Dessert", subPanel_option, SystemData.getDishnamesFromRestaurantByCategory(restaurant, "Dessert"));
			
			priceField.setTextEditable(false);
			rbtn_type.setRBtnEnable(false);
	
		}else if(itemType.equalsIgnoreCase("specialmeal")){
		
		}
		
		return addItem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
		
		// for tab page "menu"
		if(e.getSource()==dishList.getButton("Detail")){
			ChildFrame itemDetail = createItemDetailChildFrame(dishList.getSelectedValue().split(" - ")[0], "dish");
		}else if(e.getSource()==dishList.getButton("Add new")){
			ChildFrame itemDetail = createAddItemChildFrame("dish");
		}else if(e.getSource()==dishList.getButton("Remove")){
			boolean success = true;
			for(Order order : MyFoodora.getInstance().getHistory().getOrders()){
				if(order.getRestaurant()==restaurant){
					for(Item item : order.getItems()){
						if(item instanceof Dish){
							if(((Dish)item).getDishName().equalsIgnoreCase(dishList.getSelectedValue().split(" - ")[0])){
								success = false;
							}
						}
					}
				}
				
			}
			if(success){
				try {
					menu.removeDish(dishList.getSelectedValue().split(" - ")[0]);
				} catch (NameNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshMenu("desc");
			}else{
				JOptionPane.showMessageDialog(RestaurantGUI.this, "This dish is included in an order, you can't delete it.");
			}
			
		}else if(e.getSource()==mealList.getButton("Detail")){
			ChildFrame itemDetail = createItemDetailChildFrame(mealList.getSelectedValue().trim(), "meal");
		}else if(e.getSource()==mealList.getButton("Add new")){
			ChildFrame itemDetail = createAddItemChildFrame("meal");
		}else if(e.getSource()==mealList.getButton("Add as special meal")){
			try {
				specialmealMenu.addMeal(mealMenu.getMeal(mealList.getSelectedValue().trim()));
				mealMenu.removeMeal(mealList.getSelectedValue().trim());
				restaurant.getMessageBoard().addMessage(new Message(restaurant.getUsername(), "You have added the meal : " + mealList.getSelectedValue().trim() + " as a specialmeal"));
				msgBoard.refresh();
				refreshMenu("desc");
			} catch (NameAlreadyExistsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(e.getSource()==mealList.getButton("Remove")){
			boolean success = true;
			for(Order order : MyFoodora.getInstance().getHistory().getOrders()){
				if(order.getRestaurant()==restaurant){
					for(Item item : order.getItems()){
						if(item instanceof Meal){
							if(((Meal)item).getName().equalsIgnoreCase(mealList.getSelectedValue().trim())){
								success = false;
							}
						}
						
					}
				}
			}
			if(success){
				try {
					mealMenu.removeMeal(mealList.getSelectedValue().trim());
				} catch (NameNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshMenu("desc");
			}else{
				JOptionPane.showMessageDialog(RestaurantGUI.this, "This meal is included in an order, you can't delete it.");
			}
			
		}else if(e.getSource()==specialmealList.getButton("Detail")){
			ChildFrame itemDetail = createItemDetailChildFrame(specialmealList.getSelectedValue().trim(), "specialmeal");
		}else if(e.getSource()==specialmealList.getButton("Remove")){
			try {
				mealMenu.addMeal(specialmealMenu.getMeal(specialmealList.getSelectedValue().trim()));
				specialmealMenu.removeMeal(specialmealList.getSelectedValue().trim());
				restaurant.getMessageBoard().addMessage(new Message(restaurant.getUsername(), "You have removed the meal : " + specialmealList.getSelectedValue().trim() + " from specialmeals"));
				msgBoard.refresh();
				refreshMenu("desc");
			} catch (NameAlreadyExistsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	public void refreshMenu(String sortingType){
		dishList.refresh(SystemData.getDishnamesFromRestaurant(restaurant, sortingType));
		mealList.refresh(SystemData.getMealnamesFromRestaurant(restaurant));
		specialmealList.refresh(SystemData.getSpecialmealnamesFromRestaurant(restaurant));
	}
	
}
