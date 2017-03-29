package model.restaurant;

public abstract class Dish {

	protected String dishName;
	
	// standard, vegetarian, gluten-free
	protected String dishType;
	
	protected double price;
	
	

	public Dish(String dishName, String dishType, double price) {
		super();
		this.dishName = dishName;
		this.dishType = dishType;
		this.price = price;
	}
	
	
	//Copy constructor
	protected abstract Dish makeCopy();
	
	//Two dishes are equal if they have the same name
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
		} else if (!dishName.equalsIgnoreCase(other.dishName))
			return false;
		return true;
	}


	public String getDishName() {
		return dishName;
	}

	public String getDishType() {
		return dishType;
	}

	public double getPrice() {
		return price;
	}
	
	public String toString() {
		return "<"+ dishName +"> " + dishType+ " " + price + "€ ";
	}
	
}
