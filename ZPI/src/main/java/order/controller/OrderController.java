package order.controller;

import org.parse4j.ParseObject;

import order.model.OrderModel;
import order.view.AddOrderJPanel;
import order.view.OrderDetailsView;
import model.Order;

public class OrderController {
	private OrderModel orderModel;
	private AddOrderJPanel addOrderView;
	Thread customerThread;
	Thread orderThread;
	
	private Order orderDetailsModel;
	private OrderDetailsView orderView;
	
	public OrderController(OrderModel orderModel) {
		this.orderModel = orderModel;
		orderModel.initialize();
		addOrderView = new AddOrderJPanel(this, orderModel);
		addOrderView.initialize();
			
	}

	public OrderController(Order orderDetailsModel){
		this.orderDetailsModel = orderDetailsModel;
		orderView = new OrderDetailsView(this, orderDetailsModel);
	}
	
	public AddOrderJPanel getAddOrderView() {
		return addOrderView;
	}

	public void addOrder(String surname, Number phoneNumber, String pickUpAddress,
			String customerRemarks, Number passangerCount){
	//	String customerId = orderModel.addCustomer(surname, phoneNumber);
		orderModel.addOrder(surname, phoneNumber, pickUpAddress, customerRemarks, passangerCount);
	}
	
	public void setOrderDetails(){
		orderView.setAllDetailsInLabel();
	}
	
}
