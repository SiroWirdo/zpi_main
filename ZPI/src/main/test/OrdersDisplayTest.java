import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Order;
import ordersdisplay.controller.OrdersController;
import ordersdisplay.model.OrdersModel;

import org.parse4j.util.ParseRegistry;


public class OrdersDisplayTest {
	public static void main(String[] args){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
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
