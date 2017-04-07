package model.restaurant;

import java.util.ArrayList;
import java.util.stream.Collectors;

import model.users.Restaurant;

public abstract class Meal {

	protected String name;
	protected ArrayList<Dish> dishes;
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
			if (this.getDishes().get(i).getDishType()!=this.getDishes().get(i+1).getDishType()){
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
		int rawprice = 0;
		for (Dish d:dishes){
			rawprice+=d.getPrice();
		}
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
		str = "Formula <" +getName() + "> " +dishes.stream().map(Dish::getDishName).collect(Collectors.toList())+" type "+getType()+" "+getPrice()+" euros";
		return str;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Meal))
			return false;
		Meal other = (Meal) obj;
		if (dishes == null) {
			if (other.dishes != null)
				return false;
		} else if (!dishes.equals(other.dishes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
