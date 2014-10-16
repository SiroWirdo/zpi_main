package model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.ParseUser;

@ParseClassName("Driver")
public class Driver extends ParseObject {

	public String getId(){
		return getString("objectId");
	}

	public String getSurname() {
		return getString("surname");
	}

	public void setSurname(String value) {
		put("surname", value);
	}

	public int getPhoneNumber() {
		return getInt("phoneNumber");
	}

	public void setPhoneNumber(int value) {
		put("phoneNumber", value);
	}

	public String getLicenseNumber() {
		return getString("licenseNumber");
	}

	public void setLicenseNumber(String value) {
		put("licenseNumber", value);
	}


	public int getPESEL() {
		return getInt("PESEL");
	}

	public void setPESEL(int value) {
		put("PESEL", value);
	}

	public int getStatus() {
		return getInt("status");
	}

	public void setStatus(int value) {
		put("status", value);
	}

	public ParseUser getUser() {
		return (ParseUser)getParseObject("userId");
	}

	public void setUser(ParseUser value) {
		put("Id", value);
	}

	public ParseObject getCar() {
		return getParseObject("carId");
	}

	public void setCar(ParseObject value) {
		put("carId", value);
	}

	public static ParseQuery<Driver> getQuery() {
		return ParseQuery.getQuery(Driver.class);
	}
}
