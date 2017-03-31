package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.user.AddressPoint;
import model.user.Restaurant;

public class InitRestaurant {
	public static ArrayList<Restaurant> init(String fileName){
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		String name;
		String username;
		AddressPoint address;
		
		File file = new File(fileName);
		try{
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				name = s.nextLine();
				username = s.nextLine();
				String[] parts = s.nextLine().split(",");
				address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
				Restaurant r = new Restaurant(name,username,address);
				r.setMenu(InitDishMenu.init("src/txt files/menu.txt"));
				r.setMealMenu(InitMealMenu.init("src/txt files/mealmenu.txt"));
				restaurants.add(r);
			}
			s.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	return restaurants;
	}
//	public static void main(String[] args) {
//		ArrayList<Restaurant> restaurants = InitRestaurant.init("src/txt files/restaurant.txt");
//		for (Restaurant r:restaurants) System.out.println(r);
//	}
}
