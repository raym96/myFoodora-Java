/*
 * 
 */
package restaurant;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.DishNotFoundException;
import exceptions.NameAlreadyExistsException;



/**
 * The Class Menu.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Menu {

	/** The starters. */
	private ArrayList<Starter> starters;
	
	/** The maindishes. */
	private ArrayList<MainDish> maindishes;
	
	/** The desserts. */
	private ArrayList<Dessert> desserts;

	/**
	 * Instantiates a new menu.
	 */
	public Menu() {
		super();
		starters = new ArrayList<Starter>();
		maindishes = new ArrayList<MainDish>();
		desserts = new ArrayList<Dessert>();
//		initMenu();
	}
	
	/**
	 * Adds the dish.
	 *
	 * @param dish the dish
	 * @throws NameAlreadyExistsException the name already exists exception
	 */
	public void addDish(Dish dish) throws NameAlreadyExistsException{
		if (hasDish(dish.getDishName())){
			throw new NameAlreadyExistsException(dish.getDishName());
		}
		if (dish instanceof Starter){
			starters.add((Starter)dish);}
		if (dish instanceof MainDish){
			maindishes.add((MainDish)dish);}
		if (dish instanceof Dessert){
			desserts.add((Dessert)dish);
		}
	}
	
	/**
	 * Removes the dish.
	 *
	 * @param dishName the dish name
	 * @throws DishNotFoundException the dish not found exception
	 */
	public void removeDish(String dishName) throws DishNotFoundException{
		int count = 0;
		for(int i=0; i<starters.size(); i++){
			if( starters.get(i).getDishName().equalsIgnoreCase(dishName) ){
				starters.remove(i);
				count++;
			}
		}
		for(int i=0; i<maindishes.size(); i++){
			if( maindishes.get(i).getDishName().equalsIgnoreCase(dishName)){
				maindishes.remove(i);
				count++;
			}
		}
		for(int i=0; i<desserts.size(); i++){
			if( desserts.get(i).getDishName().equalsIgnoreCase(dishName) ){
				desserts.remove(i);
			}
		}
		if (count==0){
			throw new DishNotFoundException(dishName);
		}
	}
	

	/**
	 * Display.
	 */
	public void display(){
		System.out.println("\n[Menu]");
		System.out.println("\nStarters:");
		for (Dish dish:this.getStarters()){
			System.out.println(dish);
		}
		System.out.println("\nMain-dishes:");
		for (Dish dish:this.getMaindishes()){
			System.out.println(dish);
		}
		System.out.println("\nDesserts:");
		for (Dish dish:this.getDesserts()){
			System.out.println(dish);
		}
	}

	/**
	 * Gets the desserts.
	 *
	 * @return the desserts
	 */
	public ArrayList<Dessert> getDesserts() {
		return desserts;
	}

	/**
	 * Gets the dishes.
	 *
	 * @return the dishes
	 */
	public ArrayList<Dish> getDishes(){
		ArrayList<Dish> list = new ArrayList<Dish>();
		list.addAll(getStarters());
		list.addAll(getMaindishes());
		list.addAll(getDesserts());
		
		return list;
	}
	
	
	
	/**
	 * Gets the maindishes.
	 *
	 * @return the maindishes
	 */
	public ArrayList<MainDish> getMaindishes() {
		return maindishes;
	}
	
	/**
	 * Gets the starters.
	 *
	 * @return the starters
	 */
	public ArrayList<Starter> getStarters() {
		return starters;
	}
	
	/**
	 * Checks for dish.
	 *
	 * @param dishName the dish name
	 * @return true, if successful
	 */
	public boolean hasDish(String dishName){
		for (Dish dish : this.getDishes()) {
			if (dish.getDishName().equalsIgnoreCase(dishName)){
				return true;
			}
		}
		return false;
	}
	
	public Dish getDish(String dishName){
		Dish dish = null;
		for (Dish d : this.getDishes()) {
			if (d.getDishName().equalsIgnoreCase(dishName)){
				dish = d;
			}
		}
		return dish;
	}
	
	/**
	 * Inits the menu for tests.
	 */
	public void initMenu(){
		
		starters.add(new Starter("foie gras", "standard", 1.5));
		starters.add(new Starter("salade", "standard", 1.5));
		maindishes.add(new MainDish("poulet", "standard", 1.5));
		maindishes.add(new MainDish("poisson", "standard", 1.5));
		desserts.add(new Dessert("glace", "standard", 1.5));
		desserts.add(new Dessert("cafe", "standard", 1.5));
	}
	
}
