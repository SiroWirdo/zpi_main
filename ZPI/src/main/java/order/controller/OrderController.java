package order.controller;

import org.parse4j.ParseObject;

import order.view.AddOrderJPanel;
import model.Order;

public class OrderController {
	private Order orderModel;
	private AddOrderJPanel addOrderView;
	
	public OrderController(Order orderModel) {
		this.orderModel = orderModel;
		addOrderView = new AddOrderJPanel(orderModel, this);
		addOrderView.initialize();
		orderModel.initialize();	
	}
	
	public AddOrderJPanel getAddOrderView() {
		return addOrderView;
	}

	public void addOrder(String customerRemarks){
		orderModel.addOrder(customerRemarks);
	}
}
