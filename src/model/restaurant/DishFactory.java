package model.restaurant;

import exceptions.DishNotFoundException;

//Create dishes for A-la-carte orders


public class DishFactory {
	
	//needs to know what's in the menu to create dishes
	private Menu menu;
	
	public DishFactory(Menu menu){
		this.menu=menu;
	}
	
	public Dish createDish(String dishName) throws DishNotFoundException{
		// TODO Auto-generated method stub
		for (Starter st : this.menu.getStarters()){
			if (st.getDishName().equals(dishName)){
				return st.makeCopy();
			}
		}
		for (MainDish md : this.menu.getMaindishes()){
			if (md.getDishName().equals(dishName)){
				return md.makeCopy();
			}
		}
		for (Dessert ds : this.menu.getDesserts()){
			if (ds.getDishName().equals(dishName)){
				return ds.makeCopy();
			}
		}
		throw new DishNotFoundException(dishName);
	}
}
		
