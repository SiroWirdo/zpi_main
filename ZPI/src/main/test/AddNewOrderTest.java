import order.controller.OrderController;
import model.Order;


public class AddNewOrderTest {

	public static void main(String[] args) {
		Order model = new Order();
		OrderController controller = new OrderController(model);
	}
	
}
