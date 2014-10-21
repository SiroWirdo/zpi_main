package order.controller;

import org.parse4j.ParseObject;

import order.view.AddOrderJPanel;
import model.Order;

public class OrderController {
	Order orderModel;
	AddOrderJPanel addOrderView;
	
	public OrderController(Order orderModel) {
		this.orderModel = orderModel;
		addOrderView = new AddOrderJPanel(orderModel, this);
		addOrderView.initialize();
		orderModel.initialize();	
	}
	
	public void addOrder(String customerRemarks){
		orderModel.addOrder(customerRemarks);
	}
}
