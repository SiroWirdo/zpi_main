package model;

import java.util.Date;

import org.parse4j.Parse;
import org.parse4j.ParseClassName;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

@ParseClassName("Order")
public class Order extends ParseObject {

	public String getId(){
		return getString("objectId");
	}

	public ParseObject getDispatcher() {
		return getParseObject("dispatcherId");
	}

	public void setDispatcher(ParseObject value) {
		put("dispatcherId", value);
	}

	public String getPickupAddress() {
		return getString("pickupAddress");
	}

	public void setPickupAddress(String value) {
		put("pickupAddress", value);
	}


	public ParseGeoPoint getPickupAddressGeo() {
		return getParseGeoPoint("pickupAddressGeo");
	}

	public void setPickupAddressGeo(ParseGeoPoint value) {
		put("pickupAddressGeo", value);
	}

	public String getDestinationAddress() {
		return getString("destinationAddress");
	}

	public void setDestinationAddress(String value) {
		put("destinationAddress", value);
	}


	public ParseGeoPoint getDestinationAddressGeo() {
		return getParseGeoPoint("destinationAddressGeo");
	}

	public void setDestinationAddressGeo(ParseGeoPoint value) {
		put("destinationAddressGeo", value);
	}
	public ParseObject getCustomer() {
		return getParseObject("customerId");
	}

	public void setCustomer(ParseObject value) {
		put("customerId", value);
	}

	public Date getCreatedAt() {
		return getDate("createdAt");
	}

	public double getCost() {
		return getDouble("cost");
	}

	public void setCost(double value) {
		put("cost", value);
	}


	public String getCustomerRemarks() {
		return getString("customerRemarks");
	}

	public void setCustomerRemarks(String value) {
		put("customerRemarks", value);
	}

	public String getDriverRemarks() {
		return getString("driverRemarks");
	}

	public void setDriverRemarks(String value) {
		put("driverRemarks", value);
	}

	public int getPassengerCount() {
		return getInt("passengerCount");
	}

	public void setPassengerCount(int value) {
		put("passengerCount", value);
	}

	public int getStatus() {
		return getInt("status");
	}

	public void setStatus(int value) {
		put("status", value);
	}
	
	public static ParseQuery<Order> getQuery() {
		return ParseQuery.getQuery(Order.class);
	}
	
	public void initialize(){
		Parse.initialize("Ljocg29Z4B0Nihu6UgGFPDzyMWsd2bGEGvlWjG3U", "Oh8pFyCHvyGfypVSOw3gk9JIBuq3HkmQceN6wVtr");
	}
	
	public void addOrder(String customerRemarks){
		//this.setCustomer(customer);
		this.setStatus(0);
		this.setCustomerRemarks(customerRemarks);
		//TO DO dokonczyc
	}
}

