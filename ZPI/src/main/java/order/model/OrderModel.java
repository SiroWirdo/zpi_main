package order.model;

import model.Customer;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseRelation;

import other.DataBaseConnection;

public class OrderModel {
	private ParseObject order;
	private ParseObject customer;
	
	public OrderModel(){
	}
	
	public ParseObject getCustomer() {
		return customer;
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	public void addOrder(String surname, Number phoneNumber , String pickUpAddress, String customerRemarks, Number passangerCount){
		//create the order
		Order order = new Order();
		
		Customer customer = new Customer();
		customer.put("surname", surname);
		customer.put("phoneNumber", phoneNumber);
		try {
			customer.save();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		order.put("customerId", customer);
		order.put("pickupAddress", pickUpAddress);
		order.put("customerRemarks", customerRemarks);
		order.put("passangerCount", passangerCount);
		order.put("status", 0); //"oczekuj¹ce"
		order.saveInBackground();		
	
//		try {
//			customer.save();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ParseRelation<ParseObject> relation = order.getRelation("customerId");
//		relation.add(customer);
//		order.saveInBackground();
//		order.setPickupAddressGeo(value); <- wyliczenie adresu Geo przez system
//		setDriverId(); <--- algorytm
		//create the customer
	}
	
	public String addCustomer(String surname, Number phoneNumber){
		Customer customer = new Customer();
		customer.put("surname", surname);
		customer.put("phoneNumber", phoneNumber);
		try {
			customer.save();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(customer.getId());
		return customer.getId();
	}
	
	public String getCustomerId(){
		return customer.getString("objectId");
	}
	
	public void setDriverID(){
		/*
		 * Algorytm do przydzielania kierowcy do zleceniA!
		 */
	}
	/*KIEROWCA TO ROBI Z POZIOMU KODU APKI
	public void addDestinationAddress(String destinationAddress){
		order.setDestinationAddress(destinationAddress);
		//order.setDestinationAddressGeo(value); //<-- wyliczenie adresu Geo przez system
	}
	
	public void addCost(Double cost){
		order.setCost(cost);
	}
	
	public void addDriverRemarks(String driverRemarks){
		order.setDriverRemarks(driverRemarks);
	}*/
	
}
