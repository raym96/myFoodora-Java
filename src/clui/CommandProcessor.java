package clui;

import user.model.MyFoodora;
import user.model.User;

public class CommandProcessor{
	 private MyFoodora myfoodora;
	 User user;
	 
	 Command command;
	 String cmd;
	 String[] arguments;
	 
	 public void processCommand(String rawInput){
		 Command command = new Command(rawInput);
		 cmd = command.getCommand();
		 arguments = command.getArguments();
		 
		 switch (cmd.toLowerCase()){
		 case "login":
			 login();
			 break;
		 case "logout":
			 logout();
			 break;
		 case "registerCustomer":
			 registerCustomer();
			 break;
		 case "registerCourier":
			 registerCourier();
			 break;
		 case "addDishRestaurantMenu":
			 addDishRestaurantMenu();
			 break;
		 case "createMeal":
			 createMeal();
			 break;
		 case "addDish2Meal":
			 addDish2Meal();
		 case "showMeal":
			 showMeal();
		 case "saveMeal":
			 saveMeal();
		 case "setSpecialOffer":
			 setSpecialOffer();
		 case "removeFromSpecialOffer":
			 removeFromSpecialOffer();
		 case "createOrder":
			 createOrder();
		 case "addItem2Order":
			 addItem2Order();
		 case "endOrder":
			 endOrder();
		 case "onDuty":
			 onDuty();
		 case "offDuty":
			 offDuty();
		 case "findDeliverer":
			 findDeliverer();
		 case "setDeliveryPolicy":
			 setDeliveryPolicy();
		 case "setProfitPolicy":
			 setProfitPolicy();
		 case "associateCard":
			 associateCard();
		 case "showCourierDeliveries":
		 	showCourierDeliveries();
		 case "showRestaurantTop":
		 	showRestaurantTop();
		 case "showCustomers":
			 showCustomers();
		 case "showMenuItem":
			 showMenuItem();
		 case "showTotalProfit":
			 showTotalProfit();
		 case "runTest":
			 runTest();
		 case "help":
			 help();
	 }
}
