package model.restaurant;

public abstract class Dish {

	protected String dishName;
	
	// standard, vegetarian, gluten-free
	protected String dishType;
	
	protected double price;
	

	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dish(String dishName, String dishType, double price) {
		super();
		this.dishName = dishName.substring(0,1).toUpperCase() + dishName.substring(1).toLowerCase();
		this.dishType = dishType.toLowerCase();
		this.price = price;
	}
	
	
	//Copy constructor
	protected abstract Dish makeCopy();
	
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
	
	

	public String getDishName() {
		return dishName;
	}

	public String getDishType() {
		return dishType;
	}

	
	
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}


	public double getPrice() {
		return price;
	}
	
	public String toString() {
		return "<"+ dishName +"> " + dishType+ " " + price + " euros ";
	}
	
}
