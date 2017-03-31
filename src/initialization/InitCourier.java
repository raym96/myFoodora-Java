package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.user.AddressPoint;
import model.user.Courier;


public class InitCourier {
	public static ArrayList<Courier> init(String fileName){
		ArrayList<Courier> couriers = new ArrayList<Courier>();
		
		String name;
		String surname;
		String username;
		AddressPoint position;
		String phone;
		
		File file = new File(fileName);
		try{
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				name = s.nextLine();
				surname = s.nextLine();
				username = s.nextLine();
				String[] parts = s.nextLine().split(",");
				position = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
				phone = s.nextLine();
				Courier c = new Courier(name,surname,username,position,phone);
				couriers.add(c);
			}
			s.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	return couriers;
	}
	public static void main(String[] args) {
		System.out.println(InitCourier.init("src/txt files/courier.txt"));
	}
}