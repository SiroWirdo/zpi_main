package model;

import java.util.Date;

import org.parse4j.ParseGeoPoint;

public class Order {
	private String id;
	private String dispatcherId;
	private String pickupAddress;
	private ParseGeoPoint pickupAddressGeo;
	private String destinationAddress;
	private ParseGeoPoint destinationAddressGeo;
	private Date createdAt;
	private double cost;
	private String driverRemarks;
	private String customerRemarks;
	private int passengerCount;
	private int status;
	private String driverId;
	private String customerId;
	
	public Order(String id, String dispatcherId, String pickupAddress,
			ParseGeoPoint pickupAddressGeo, String destinationAddress,
			ParseGeoPoint destinationAddressGeo, Date createdAt, double cost,
			String driverRemarks, String customerRemarks, int passengerCount,
			int status, String driverId, String customerId) {
		super();
		this.id = id;
		this.dispatcherId = dispatcherId;
		this.pickupAddress = pickupAddress;
		this.pickupAddressGeo = pickupAddressGeo;
		this.destinationAddress = destinationAddress;
		this.destinationAddressGeo = destinationAddressGeo;
		this.createdAt = createdAt;
		this.cost = cost;
		this.driverRemarks = driverRemarks;
		this.customerRemarks = customerRemarks;
		this.passengerCount = passengerCount;
		this.status = status;
		this.driverId = driverId;
		this.customerId = customerId;
	}

	/**id musi byæ unikalne**/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(String dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public ParseGeoPoint getPickupAddressGeo() {
		return pickupAddressGeo;
	}

	public void setPickupAddressGeo(ParseGeoPoint pickupAddressGeo) {
		this.pickupAddressGeo = pickupAddressGeo;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public ParseGeoPoint getDestinationAddressGeo() {
		return destinationAddressGeo;
	}

	public void setDestinationAddressGeo(ParseGeoPoint destinationAddressGeo) {
		this.destinationAddressGeo = destinationAddressGeo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getDriverRemarks() {
		return driverRemarks;
	}

	public void setDriverRemarks(String driverRemarks) {
		this.driverRemarks = driverRemarks;
	}

	public String getCustomerRemarks() {
		return customerRemarks;
	}

	public void setCustomerRemarks(String customerRemarks) {
		this.customerRemarks = customerRemarks;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	
}
