package clui;

import java.io.*;
import java.util.Scanner;

import user.model.Manager;
import user.model.MyFoodora;

public class InitialScenario {
	public static void main(String[] args) {
		PersonalStream.setOutputStream("output.txt");
		
		InitialScenario.load("my_foodora.ini");
		
		CommandProcessor c = new CommandProcessor();
		c.processCommand("login ceo 123456789");
	
	}
	
	public static void load(String filename) {
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("Loading initial scenario <"+filename+">");
		
		PrintStream originalStream = System.out;
		
		PrintStream invisibleStream    = new PrintStream(new OutputStream(){
		    public void write(int b) {
		        //does nothing, hide all system outputs
		    }
		});

		System.setOut(invisibleStream);
		
		Manager ceo = new Manager("ji","raymond","ceo","123456789");
		ceo.setActivated(true);
		MyFoodora.getInstance().addUser(ceo);
		
		CommandProcessor c = new CommandProcessor();
		
		File file = new File(filename);
		Scanner s;
		try {
			s = new Scanner(file);
			String rawInput = "";
			while (s.hasNextLine()){
				rawInput = s.nextLine();
				c.processCommand(rawInput);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyFoodora myfoodora = MyFoodora.getInstance();

		System.setOut(originalStream);
		
//		myfoodora.displayUsers();
//		myfoodora.displayAllMenus();
//		myfoodora.displayHistory();
		System.out.println("The initial file <"+filename+"> successfully loaded into the system.");
		System.out.println("-----------------------------------------------------------------------------------\n");

	}
}
