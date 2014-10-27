import javax.swing.JFrame;

import model.Order;
import ordersdisplay.controller.OrdersController;
import ordersdisplay.model.OrdersModel;

import org.parse4j.util.ParseRegistry;


public class OrdersDisplayTest {
	public static void mani(String[] args){
		ParseRegistry.registerSubclass(Order.class);	
		OrdersModel ordersModel = new OrdersModel();
		OrdersController ordersController = new OrdersController(ordersModel);
		
		
		
		
		JFrame frame = new JFrame();
		frame.setSize(1280, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(ordersController.getOrdersView());
		
		frame.repaint();
		frame.setVisible(true);
	}
}
