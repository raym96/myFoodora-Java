package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.restaurant.*;

public class InitDishMenu {
	
	public static Menu init(String fileName){
		Menu menu = new Menu();
		
		String dishCategory; //Starter, main-dish or dessert
		String dishName;
		String dishType;
		double price;
		
		File file = new File(fileName);
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				dishCategory = s.nextLine();
				dishName = s.nextLine();
				dishType = s.nextLine();
				price = Double.parseDouble(s.nextLine());
				if (dishCategory.equals("Starter")){
					menu.addDish(new Starter(dishName,dishType,price));
				}
				if (dishCategory.equals("Main-dish")){
					menu.addDish(new MainDish(dishName,dishType,price));
				}
				if (dishCategory.equals("Dessert")){
					menu.addDish(new Dessert(dishName,dishType,price));
				}
			}
			s.close();
		} catch (IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return menu;
	}
	
//	public static void main(String arg[]){
//		Menu menu = init("src/txt files/menu.txt");
//		menu.display();
//	}
}
