package model.restaurant;

import java.util.ArrayList;
import java.util.stream.Collectors;

import model.user.Restaurant;

public abstract class Meal {

	protected String name;
	protected ArrayList<Dish> dishes;
	protected double rawprice; //sum of the prices of the dishes
	protected double price; //rawprice adjusted by the discount factor. Undefined if the meal is not in a meal menu
	
	//standard,vegetarian,gluten-free
	protected String mealType;
	
	public Meal(String name) {
		super();
		this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
		this.dishes = new ArrayList<Dish>();
	}

	public void addDish(Dish dish) {
		// TODO Auto-generated method stub
		getDishes().add(dish);
		this.rawprice += dish.getPrice();
	}
	
	
	//Check if the meal is composed only by dishes of the same type (vegetarian or gluten-free)
	//if it is the case, the meal type is changed to the corresponding type
	public void refreshMealType(){
		if (this.sameMealType()){
			this.mealType = this.getDishes().get(0).getDishType();
		}
		else{
			this.mealType = "standard";
		}
	}
	
	public boolean sameMealType(){
		for (int i = 0 ; i<this.getDishes().size()-1;i++){
			if (this.getDishes().get(i)!=this.getDishes().get(i+1)){
				return false;
			}
		}
		return true;
	}
	
	
	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public ArrayList<Dish> getDishes() {
		return dishes;
	}
	public String getName() {
		return name;
	}
	public String getType(){
		return mealType;
	}
	public double getRawprice() {
		return rawprice;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}

	@Override
	public String toString() {
		String str;
		//the stream.map.collector allows to collect only dishnames
		str = "Formula <" +getName() + "> " +dishes.stream().map(Dish::getDishName).collect(Collectors.toList())+" type "+getType()+" "+getPrice()+"€";
		return str;
	}
}
