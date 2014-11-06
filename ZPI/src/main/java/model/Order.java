package model;

import java.util.Date;

import org.jdesktop.swingx.mapviewer.Waypoint;
import org.parse4j.Parse;
import org.parse4j.ParseClassName;
import org.parse4j.ParseException;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

import other.ConverterGeoPosition;

@ParseClassName("Order")
public class Order extends ParseObject {
	
	public String getId(){
		return getObjectId();
	}

	public Dispatcher getDispatcher() {
		Dispatcher dispatcher = null;
		ParseObject d = getParseObject("dispatcher");
		if(d != null){
			String dispatcherId = d.getObjectId();
			ParseQuery<Dispatcher> query = ParseQuery.getQuery("Dispatcher");
			try {
				dispatcher = query.get(dispatcherId);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return dispatcher;
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
	
	public Waypoint getCurrentPositionWaypoint() {
		return ConverterGeoPosition.geoPointToWaypoint(getParseGeoPoint("pickupAddressGeo"));
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
	public Customer getCustomer() {
		Customer c = null;
		ParseObject customer = getParseObject("customerId");
		if(customer != null){
			String customerId = customer.getObjectId();
			ParseQuery<Customer> query = ParseQuery.getQuery("Customer");
			try {
				c = query.get(customerId);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return c;
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

	public Number getPassengerCount() {
		return getInt("passengerCount");
	}

	public void setPassengerCount(Number value) {
		put("passengerCount", value);
	}

	/** 0 - oczekuj¹ce, 1 - w trakcie realizacji, 2 - zrealizowane, 3 - anulowane, 4-zaakceptowane**/
	public int getStatus() {
		return getInt("status");
	}

	/** 0 - oczekuj¹ce, 1 - w trakcie realizacji, 2 - zrealizowane, 3 - anulowane, 4-zaakceptowane **/
	public void setStatus(int value) {
		put("status", value);
	}
	
	public static ParseQuery<Order> getQuery() {
		return ParseQuery.getQuery(Order.class);
	}
}

