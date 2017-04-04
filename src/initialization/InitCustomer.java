package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.users.AddressPoint;
import model.users.Customer;

public class InitCustomer {
	public static ArrayList<Customer> init(String fileName){
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		String name;
		String surname;
		String username;
		AddressPoint address;
		String email;
		String phone;
		
		File file = new File(fileName);
		try{
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				name = s.nextLine();
				surname = s.nextLine();
				username = s.nextLine();
				String[] parts = s.nextLine().split(",");
				address = new AddressPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
				email = s.nextLine();
				phone = s.nextLine();
				Customer c = new Customer(name,surname,username,address,email,phone);
				customers.add(c);
			}
			s.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	return customers;
	}
	public static void main(String[] args) {
		ArrayList<Customer> customers = InitCustomer.init("src/txt files/customer.txt");
		int i =1;
		for (Customer c : customers){
			System.out.println("[Customer/"+i+"]");
			i++;
			System.out.println("name="+c.getName());
			System.out.println("surname="+c.getSurname());
			System.out.println("username="+c.getUsername());
			System.out.println("address="+c.getAddress());
			System.out.println("email="+c.getEmail());
			System.out.println("phone="+c.getPhone());
			System.out.println();
		}
	}
}