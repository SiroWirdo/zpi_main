package ordersdisplay.model;

import java.util.List;

import model.Order;

public class OrderChanges {
	int flag;
	List<Order> orders;
	
	public OrderChanges(List<Order> orders, int flag){
		this.orders = orders;
		this.flag = flag;
	}
	
	public List<Order> getOrders(){
		return orders;		
	}
	
	public void setOrders(List<Order> orders){
		this.orders = orders;
	}
	
	public int getFlag(){
		return flag;
	}
	
	public void setFlag(int flag){
		this.flag = flag;
	}


}
