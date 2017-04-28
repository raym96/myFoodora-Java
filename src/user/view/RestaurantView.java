package user.view;

import user.model.Restaurant;

public class RestaurantView implements UserView{
	/** The restaurant. */
	private  Restaurant restaurant;
	
	/**
	 * Instantiates a new restaurant view.
	 *
	 * @param restaurant the restaurant
	 */
	public RestaurantView(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	
	public void showInfo(){
		String output = "";
		output+="<Restaurant> "+restaurant.getUsername()+"; name = "+restaurant.getName()+"; address="+restaurant.getAddress();
		if (restaurant.getEmail() !=null){
			output+="; email = "+restaurant.getEmail();
		}
		if (restaurant.getPhone() !=null){
			output+="; phone = "+restaurant.getPhone();
		}
		System.out.println(output);
	}
	
	public void showHistory(){
		restaurant.getHistory().display();
	}
	

	public void showDishes() {
		// TODO Auto-generated method stub
		restaurant.getMenu().display();
	}


	public void showMeals() {
		// TODO Auto-generated method stub
		System.out.println("\n"+"[Meal menu]");
		restaurant.getMealMenu().display();
	}

	public void showDiscountFactors(){
		System.out.println("Generic discount factor = "+restaurant.getGeneric_discount_factor());
		System.out.println("Special discount factor = "+restaurant.getSpecial_discount_factor());
	}
	
	public void showSpecialOffers(){
		// TODO Auto-generated method stub
		System.out.println("\n[Special Offers]");
		restaurant.getSpecialmealmenu().display();
	}
	
	public void showTotalIncome(){
		System.out.println(restaurant.getName()+"'s total income = " + restaurant.getIncome()+ " euros.");
	}
	

	public void showMenu(){
		System.out.println("\n-----["+(restaurant.getName().toUpperCase()+" username <"+restaurant.getUsername()+">]-----"));
		showDishes();
		showMeals();
		showSpecialOffers();
	}

}
