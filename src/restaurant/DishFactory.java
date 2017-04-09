/*
 * 
 */
package restaurant;

import exceptions.DishNotFoundException;

// TODO: Auto-generated Javadoc
//Create dishes for A-la-carte orders


/**
 * A factory for creating Dish objects.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class DishFactory {
	
	/** The menu. */
	//needs to know what's in the menu to create dishes
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
	 * @throws DishNotFoundException the dish not found exception
	 */
	public Dish createDish(String dishName) throws DishNotFoundException{
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
		throw new DishNotFoundException(dishName);
	}
}
		
