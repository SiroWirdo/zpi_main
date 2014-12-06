package order_info.model;

import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderInfoModel {
	//wa¿ne: na pocz¹tku listy s¹ najstarsze zlecenia
	private List<Order> lastOrders;
	
	public OrderInfoModel(){
		lastOrders = new ArrayList<Order>();
	}	
	
	public List<Order> getLastOrders() {
		return lastOrders;
	}

	public List<Order> refreshLastOrders(){
		for(Order o: lastOrders){
			o = Order.getOrderById(o.getId());	
		}
		return lastOrders;
	}
	
	public void addOrder(Order o){
		lastOrders.add(o);
	}
	
}
