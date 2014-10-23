package order.model;

import model.Order;

import org.parse4j.Parse;
import org.parse4j.ParseObject;

import other.DataBaseConnection;
import settings.Settings;

public class OrderModel {
	private Order order;
	private ParseObject orderParse;
	public OrderModel(){
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	public void addOrder(String pickUpAddress, String customerRemarks, Number passangerCount){
		
		order = (Order)new ParseObject("Order");
		//order.setDispatcher(value); <- pobranie aktualnie zalogowanego Dispatchera
		order.setPickupAddress(pickUpAddress);
		//order.setPickupAddressGeo(value); <- wyliczenie adresu Geo przez system
		order.setCustomerRemarks(customerRemarks);
		order.setPassengerCount(passangerCount);
		order.setStatus(0); //"oczekuj¹ce"
	//	order.setCustomer(value); <- osobna metoda
	//	setDriverId();
		order.saveInBackground();
		
//		orderParse = new ParseObject("Order");
//		orderParse.put("pickupAddress", pickUpAddress);
//		orderParse.saveInBackground();
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
