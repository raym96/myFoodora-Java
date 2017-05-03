/*
 * 
 */
package restaurant;

import java.util.ArrayList;
import java.util.stream.Collectors;

import exceptions.DishTypeErrorException;
import system.ConcreteShoppingCartVisitor;
import system.ShoppingCartVisitor;
import user.model.Restaurant;


/**
 * The Class Meal.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Meal implements Item{

	/** The name. */
	protected String name;
	
	/** The dishes. */
	protected ArrayList<Dish> dishes;
		
	/** The restaurant. */
	protected Restaurant restaurant;
	
	/** The is special. */
	private boolean isSpecial;
	
	/** The meal type. */
	//standard,vegetarian,gluten-free
	protected String mealType;
	
	/** The saved. */
	private boolean saved;
	
	/**
	 * Instantiates a new meal.
	 *
	 * @param name the name
	 */
	public Meal(String name) {
		super();
		this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
		this.dishes = new ArrayList<Dish>();
		this.saved = false;
	}

	/**
	 * Checks if is saved.
	 *
	 * @return true, if is saved
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the dishes.
	 *
	 * @param dishes the new dishes
	 */
	public void setDishes(ArrayList<Dish> dishes) {
		this.dishes = dishes;
	}

	/**
	 * Sets the saved.
	 *
	 * @param saved the new saved
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	/**
	 * Adds the dish. Error if there a more than 1 starter/main-dish/dessert
	 *
	 * @param dish the dish
	 * @throws DishTypeErrorException the dish type error exception
	 */
	public void addDish(Dish dish) throws DishTypeErrorException {
		// TODO Auto-generated method stub
		for (Dish d : getDishes()){
			if (d instanceof Starter && dish instanceof Starter) throw new DishTypeErrorException();
			if (d instanceof MainDish && dish instanceof MainDish) throw new DishTypeErrorException();
			if (d instanceof Dessert && dish instanceof Dessert) throw new DishTypeErrorException();
		}
		dishes.add(dish);
		refreshMealType();
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
	
	
	/**
	 * Sets the restaurant.
	 *
	 * @param restaurant the new restaurant
	 */
	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}
	
	
	/**
	 * Checks if is special.
	 *
	 * @return true, if is special
	 */
	public boolean isSpecial() {
		return isSpecial;
	}

	/**
	 * Sets the special.
	 *
	 * @param isSpecial the new special
	 */
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	/* (non-Javadoc)
	 * @see restaurant.Item#accept(system.ShoppingCartVisitor)
	 */
	public double accept(ShoppingCartVisitor visitor){
		return visitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str ="";
		double price = new ConcreteShoppingCartVisitor().visit(this);
		if (this instanceof HalfMeal) str+= "Half-meal <";
		if (this instanceof FullMeal) str+= "Full-meal <";
		//the stream.map.collector allows to collect only dishnames
		str += getName() + "> " +dishes.stream().map(Dish::getDishName).collect(Collectors.toList())+" type "+getType()+" "+price+" euros";
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

	/**
	 * Gets the restaurant.
	 *
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
}
