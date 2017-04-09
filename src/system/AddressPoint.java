/*
 * 
 */
package system;


/**
 * The Class AddressPoint.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class AddressPoint {

	/** The abcisse. */
	private double x;
	
	/** The ordonnee. */
	private double y;
	
	/**
	 * Instantiates a new address point.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public AddressPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Instantiates a new address point.
	 *
	 * @param address the address
	 */
	public AddressPoint(String address){
		super();
		//String under the form of "x,y" x and y separated by "a" ,
		String[] parts = address.split(",");
		this.x = Double.parseDouble(parts[0]);
		this.y = Double.parseDouble(parts[1]);
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return x+","+y;
	}

	/**
	 * Calculate distance.
	 *
	 * @param address the address
	 * @return the double
	 */
	public double calculateDistance(AddressPoint address){
		return Math.sqrt(Math.pow(x - address.getX(),2) + Math.pow(y - address.getY(), 2));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		if (!(obj instanceof AddressPoint))
			return false;
		AddressPoint other = (AddressPoint) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	
}
