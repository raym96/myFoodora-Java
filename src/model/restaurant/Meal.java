package model.restaurant;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Meal {

	protected String name;
	protected ArrayList<Dish> dishes;
	protected double rawprice;
	
	//standard,vegetarian,gluten-free
	protected String mealType;
	
	public Meal(String name) {
		super();
		this.name = name;
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
	public double getPrice() {
		return rawprice;
	}

	@Override
	public String toString() {
		String str;
		//the stream.map.collector allows to collect only dishnames
		str = "Formula type "+ getType()+" '" +getName() + "' " +dishes.stream().map(Dish::getDishName).collect(Collectors.toList());
		return str;
	}
}
