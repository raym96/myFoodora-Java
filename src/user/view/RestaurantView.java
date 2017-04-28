package user.view;

import user.model.Restaurant;

public class RestaurantView implements UserView{
	/** The restaurant. */
	private  Restaurant r;
	
	/**
	 * Instantiates a new restaurant view.
	 *
	 * @param restaurant the restaurant
	 */
	public RestaurantView(Restaurant restaurant) {
		super();
		this.r = restaurant;
	}
	
	public void showInfo(){
		String output = "";
		output+="<Restaurant> "+r.getUsername()+"; name = "+r.getName()+"; address="+r.getAddress();
		if (r.getEmail() !=null){
			output+="; email = "+r.getEmail();
		}
		if (r.getPhone() !=null){
			output+="; phone = "+r.getPhone();
		}
		System.out.println(output);
	}
	
	public void showHistory(){
		r.getHistory().display();
	}
	

	public void showDishes() {
		// TODO Auto-generated method stub
		r.getMenu().display();
	}


	public void showMeals() {
		// TODO Auto-generated method stub
		System.out.println("\n"+"[Meal menu]");
		r.getMealMenu().display();
	}

	public void showDiscountFactors(){
		System.out.println("Generic discount factor = "+r.getGeneric_discount_factor());
		System.out.println("Special discount factor = "+r.getSpecial_discount_factor());
	}
	
	public void showSpecialOffers(){
		// TODO Auto-generated method stub
		System.out.println("\n[Special Offers]");
		r.getSpecialmealmenu().display();
	}
	
	public void showTotalIncome(){
		System.out.println(r.getName()+"'s total income = " + r.getIncome()+ " euros.");
	}
	

	public void showMenu(){
		System.out.println("\n-----["+(r.getName().toUpperCase()+"]-----"));
		showDishes();
		showMeals();
		showSpecialOffers();
	}

}
