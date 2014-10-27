package model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.ParseUser;

@ParseClassName("Driver")
public class Driver extends ParseObject {

	public String getId(){
		return getObjectId();
	}
	
	public String getName() {
		return getString("name");
	}

	public void setName(String value) {
		put("name", value);
	}

	public String getSurname() {
		return getString("surname");
	}

	public void setSurname(String value) {
		put("surname", value);
	}

	public long getPhoneNumber() {
		return getLong("phoneNumber");
	}

	public void setPhoneNumber(long value) {
		put("phoneNumber", value);
	}

	public String getLicenseNumber() {
		return getString("licenseNumber");
	}

	public void setLicenseNumber(String value) {
		put("licenseNumber", value);
	}


	public long getPESEL() {
		return getLong("PESEL");
	}

	public void setPESEL(long value) {
		put("PESEL", value);
	}
	
	/** 0 - wolny, 1 - kurs, 2 - przerwa, 3 - zablokowany, 4 - niedostêpny**/
	public int getStatus() {
		return getInt("status");
	}

	/** 0 - wolny, 1 - kurs, 2 - przerwa, 3 - zablokowany, 4 - niedostêpny**/
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
