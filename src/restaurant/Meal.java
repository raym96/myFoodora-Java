/*
 * 
 */
package restaurant;

import java.util.ArrayList;
import java.util.stream.Collectors;

import system.ConcreteShoppingCartVisitor;
import system.ShoppingCartVisitor;
import user.model.Restaurant;


/**
 * The Class Meal.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class Meal implements Item{

	/** The name. */
	protected String name;
	
	/** The dishes. */
	protected ArrayList<Dish> dishes;
		
	protected Restaurant restaurant;
	
	private boolean isSpecial;
	
	/** The meal type. */
	//standard,vegetarian,gluten-free
	protected String mealType;
	
	/**
	 * Instantiates a new meal.
	 *
	 * @param name the name
	 */
	public Meal(String name) {
		super();
		this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
		this.dishes = new ArrayList<Dish>();
	}

	/**
	 * Adds the dish.
	 *
	 * @param dish the dish
	 */
	public void addDish(Dish dish) {
		// TODO Auto-generated method stub
		getDishes().add(dish);
	}
	
	
	/**Check if the meal is composed only by dishes of the same type (vegetarian or gluten-free)
	 *	if it is the case, the meal type is changed to the corresponding type.
	 * .
	 */
	public void refreshMealType(){
		if (this.sameMealType()){
			this.mealType = this.getDishes().get(0).getDishType();
		}
		else{
			this.mealType = "standard";
		}
	}
	
	/**
	 * Same meal type.
	 *
	 * @return true, if successful
	 */
	public boolean sameMealType(){
		for (int i = 0 ; i<this.getDishes().size()-1;i++){
			if (this.getDishes().get(i).getDishType()!=this.getDishes().get(i+1).getDishType()){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Gets the meal type.
	 *
	 * @return the meal type
	 */
	public String getMealType() {
		return mealType;
	}

	/**
	 * Sets the meal type.
	 *
	 * @param mealType the new meal type
	 */
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	/**
	 * Gets the dishes.
	 *
	 * @return the dishes
	 */
	public ArrayList<Dish> getDishes() {
		return dishes;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType(){
		return mealType;
	}
	
	/**
	 * Gets the rawprice.
	 *
	 * @return the rawprice
	 */
	public double getRawprice() {
		int rawprice = 0;
		for (Dish d:dishes){
			rawprice+=d.getPrice();
		}
		return rawprice;
	}
	
	
	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}
	
	
	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	public double accept(ShoppingCartVisitor visitor){
		return visitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str;
		double price = new ConcreteShoppingCartVisitor().visit(this);
		//the stream.map.collector allows to collect only dishnames
		str = "Formula <" +getName() + "> " +dishes.stream().map(Dish::getDishName).collect(Collectors.toList())+" type "+getType()+" "+price+" euros";
		return str;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
}
