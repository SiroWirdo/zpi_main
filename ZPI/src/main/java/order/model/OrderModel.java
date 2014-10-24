package order.model;

import org.parse4j.ParseObject;
import other.DataBaseConnection;

public class OrderModel {
	private ParseObject order;
	private ParseObject customer;
	
	public OrderModel(){
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	public void addOrder(String customerID, String pickUpAddress, String customerRemarks, Number passangerCount){
		

		order = new ParseObject("Order");
		order.put("customerId", customerID);
		order.put("pickupAddress", pickUpAddress);
		order.put("customerRemarks", customerRemarks);
		order.put("passangerCount", passangerCount);
		order.put("status", 0); //"oczekuj¹ce"
//		order.setPickupAddressGeo(value); <- wyliczenie adresu Geo przez system
//		setDriverId(); <--- algorytm
		order.saveInBackground();
	}
	
	public void addCustomer(String surname, Number phoneNumber){
		customer = new ParseObject("Customer");
		customer.put("surname", surname);
		customer.put("phoneNumber", phoneNumber);
		customer.saveInBackground();
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
