import order.controller.OrderController;
import order.model.OrderModel;
import model.Order;


public class AddNewOrderTest {

	public static void main(String[] args) {
		OrderModel model = new OrderModel();
		OrderController controller = new OrderController(model);
	}
	
}
