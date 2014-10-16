package model;

import org.parse4j.ParseGeoPoint;

public class Car {
	private String id;
	private String registrationNumber;
	private int sideNumber;
	private int carCapacity;
	private ParseGeoPoint currentPosition;
	
	public Car(String id, String registrationNumber, int sideNumber,
			int carCapacity, ParseGeoPoint currentPosition) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.sideNumber = sideNumber;
		this.carCapacity = carCapacity;
		this.currentPosition = currentPosition;
	}

	public String getId() {
		return id;
	}

	/**id musi byæ unikalne**/
	public void setId(String id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**registrationNumber musi byæ unikalny**/
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getSideNumber() {
		return sideNumber;
	}

	/**sideNumber musi byæ unikalny**/
	public void setSideNumber(int sideNumber) {
		this.sideNumber = sideNumber;
	}

	public int getCarCapacity() {
		return carCapacity;
	}

	
	public void setCarCapacity(int carCapacity) {
		this.carCapacity = carCapacity;
	}

	public ParseGeoPoint getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(ParseGeoPoint currentPosition) {
		this.currentPosition = currentPosition;
	}
	
}
