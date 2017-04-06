package test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import initialization.InitialScenario;
import model.restaurant.Meal;
import model.restaurant.Order;
import model.restaurant.StandardMealOrder;
import model.users.AddressPoint;
import model.users.*;
import service.CourierService;
import service.MyFoodoraService;
import service.impl.MyFoodoraServiceImpl;

public class CourierServiceTest {
	
	static Courier courier_test = new Courier("test","test","courier_test", new AddressPoint(0,0),"+06 00 00 00 00");
	static CourierService courier_service = courier_test.getCourierService();
		
	MyFoodora myfoodora = MyFoodora.getInstance();
	MyFoodoraService myfoodora_service = new MyFoodoraServiceImpl();

	Customer customer = (Customer)myfoodora_service.selectUser("customer_1");
	Restaurant restaurant = (Restaurant)myfoodora_service.selectUser("restaurant_1");
	Meal m = restaurant.getHalfMealMenu().getMeals().get(0);
	Order order = new StandardMealOrder(customer,restaurant,m);
	
	@BeforeClass
	public static void setUp() throws Exception {
		InitialScenario.load("init.ini");		
	}

	@Test
	public void testRegister() {
		System.out.println("-----testRegister-----");
		courier_service.register();
		//verify that courier_test is added to the end of the list of couriers
		assertTrue(myfoodora.getCouriers().contains(courier_test));
		courier_service.unregister();
	}

	@Test
	public void testUnregister() {
		System.out.println("-----testUnregister-----");
		courier_service.register();//we first register
		
		courier_service.unregister();
		System.out.println(myfoodora.getCouriers().get(myfoodora.getCouriers().size()-1));
		System.out.println(courier_test);
		//verify that the courier is not anymore in the list of couriers
		assertFalse(myfoodora.getCouriers().contains(courier_test));
		//neither in the list of available couriers
		assertFalse(myfoodora.getAvailableCouriers().contains(courier_test));
	}

	@Test
	public void testTurnOnDuty() {
		System.out.println("-----testTurnOnDuty-----");
		courier_service.register();
		courier_service.turnOnDuty();
		//is he on duty now ?
		assertTrue(courier_test.isOn_duty());
		//verify courier_test is among the available couriers
		assertTrue(myfoodora.getAvailableCouriers().contains(courier_test));
		courier_service.unregister();
	}

	@Test
	public void testTurnOffDuty() {
		System.out.println("-----testTurnOffDuty-----");
		courier_service.turnOffDuty();
		assertFalse(courier_test.isOn_duty());
		assertFalse(myfoodora.getAvailableCouriers().contains(courier_test));
	}

	@Test
	public void testChangePosition() {
		System.out.println("-----testChangePosition-----");
		courier_service.changePosition(new AddressPoint(2,2));
		assertEquals(courier_test.getPosition(),new AddressPoint(2,2));
	}

	@Test
	public void testAcceptCall() {
		System.out.println("-----testAcceptCall-----");
		courier_service.turnOnDuty();
		int delivery_count = courier_test.getCount();
		courier_test.addWaitingOrder(order); //tested in CourierTest.java
		
		courier_service.acceptCall(order);
		//verify that the order is no more in the waiting list
		assertFalse(courier_test.getWaitingOrders().contains(order));
		//verity that the order is added to the list of delivered missions
		assertTrue(courier_test.getDeliveredOrders().contains(order));
		//very that the delivery count of the courier increased
		assertEquals(courier_test.getCount(),delivery_count+1);
	}

	@Test
	public void testRefuseCall() {
		System.out.println("-----testRefuseCall-----");
		courier_service.turnOnDuty();
		courier_test.addWaitingOrder(order); //tested in CourierTest.java
		courier_service.refuseCall(order);
		//verify that the order is no more in the waiting list
		assertFalse(courier_test.getWaitingOrders().contains(order));
	}

}
