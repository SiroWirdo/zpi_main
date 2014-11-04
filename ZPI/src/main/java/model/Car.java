package model;

import org.jdesktop.swingx.mapviewer.Waypoint;
import org.parse4j.ParseClassName;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

import other.ConverterGeoPosition;

@ParseClassName("Car")
public class Car extends ParseObject{

	public String getId(){
		return getObjectId();
	}

	public String getRegistrationNumber() {
		return getString("registrationNumber");
	}

	public void setRegistrationNumber(String value) {
		put("registrationNumber", value);
	}

	public int getSideNumber() {
		return getInt("sideNumber");
	}

	public void setSideNumber(int value) {
		put("sideNumber", value);
	}

	public int getCarCapacity () {
		return getInt("carCapacity");
	}

	public void setCarCapacity(int value) {
		put("carCapacity", value);
	}

	public ParseGeoPoint getCurrentPosition() {
		return getParseGeoPoint("currentPosition");
	}
	
	public Waypoint getCurrentPositionWayPoint(){
		return ConverterGeoPosition.geoPointToWaypoint(this.getCurrentPosition());
	}

	public void setCurrentPosition(ParseGeoPoint value) {
		put("currentPosition", value);
	}

	public static ParseQuery<Car> getQuery() {
		return ParseQuery.getQuery(Car.class);
	}
}