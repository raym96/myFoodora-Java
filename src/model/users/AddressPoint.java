package model.users;

public class AddressPoint {

	private double x;
	private double y;
	
	public AddressPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public AddressPoint(String address){
		super();
		//String under the form of "x,y" x and y separated by "a" ,
		String[] parts = address.split(",");
		this.x = Double.parseDouble(parts[0]);
		this.y = Double.parseDouble(parts[1]);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	@Override
	public String toString() {
		return x+","+y;
	}

	public double calculateDistance(AddressPoint address){
		return Math.sqrt(Math.pow(x - address.getX(),2) + Math.pow(y - address.getY(), 2));
	}
}
