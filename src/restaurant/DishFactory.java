/*
 * 
 */
package restaurant;

import exceptions.NameNotFoundException;

//Create dishes for A-la-carte orders


/**
 * A factory for creating Dish objects.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class DishFactory {
	
	/** The menu. */
	private Menu menu;
	
	/**
	 * Instantiates a new dish factory.
	 *
	 * @param menu the menu
	 */
	public DishFactory(Menu menu){
		this.menu=menu;
	}
	
	/**
	 * Creates a new Dish object.
	 *
	 * @param dishName the dish name
	 * @return the dish
	 * @throws NameNotFoundException the dish not found exception
	 */
	public Dish createDish(String dishName) throws NameNotFoundException{
		// TODO Auto-generated method stub
		for (Starter st : this.menu.getStarters()){
			if (st.getDishName().equalsIgnoreCase(dishName)){
				return st.makeCopy();
			}
		}
		for (MainDish md : this.menu.getMaindishes()){
			if (md.getDishName().equalsIgnoreCase(dishName)){
				return md.makeCopy();
			}
		}
		for (Dessert ds : this.menu.getDesserts()){
			if (ds.getDishName().equalsIgnoreCase(dishName)){
				return ds.makeCopy();
			}
		}
		throw new NameNotFoundException(dishName);
	}
}
		
