package model.customer;

public class PointCard extends FidelityCard {
	private double balance;
	
	public PointCard(){
		balance=0;
	}
	public void addPoints(double points){
		balance+=points;
	}
	
	public void setPoints(double points){
		balance=points;
	}
	
	public double getPoints(){
		return balance;
	}
}
