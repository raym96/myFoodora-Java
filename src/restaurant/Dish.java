/*
 * 
 */
package restaurant;


/**
 * The Class Dish.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class Dish implements Item{

	/** The dish name. */
	protected String dishName;
	
	/** The dish type. */
	// standard, vegetarian, gluten-free
	protected String dishType;
	
	/** The price. */
	protected double price;
	

	/**
	 * Instantiates a new dish.
	 */
	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Instantiates a new dish.
	 *
	 * @param dishName the dish name
	 * @param dishType the dish type
	 * @param price the price
	 */
	public Dish(String dishName, String dishType, double price) {
		super();
		this.dishName = dishName.substring(0,1).toUpperCase() + dishName.substring(1).toLowerCase();
		this.dishType = dishType.toLowerCase();
		this.price = price;
	}
	
	
	/**
	 * Make copy of a dish for factory pattern.
	 *
	 * @return the dish
	 */
	protected abstract Dish makeCopy();
	
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
		if (!(obj instanceof Dish))
			return false;
		Dish other = (Dish) obj;
		if (dishName == null) {
			if (other.dishName != null)
				return false;
		} else if (!dishName.equals(other.dishName))
			return false;
		if (dishType == null) {
			if (other.dishType != null)
				return false;
		} else if (!dishType.equals(other.dishType))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	

	/**
	 * Gets the dish name.
	 *
	 * @return the dish name
	 */
	public String getDishName() {
		return dishName;
	}

	/**
	 * Gets the dish type.
	 *
	 * @return the dish type
	 */
	public String getDishType() {
		return dishType;
	}

	
	
	/**
	 * Sets the dish type.
	 *
	 * @param dishType the new dish type
	 */
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}


	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<"+ dishName +"> " + dishType+ " " + price + " euros ";
	}
	
}
