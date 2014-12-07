package model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParsePointer;
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

	/** 0 - wolny, 1 - kurs, 2 - przerwa, 3 - zablokowany, 4 - niedostępny**/
	public int getStatus() {
		return getInt("status");
	}

	/** 0 - wolny, 1 - kurs, 2 - przerwa, 3 - zablokowany, 4 - niedostępny**/
	public void setStatus(int value) {
		put("status", value);
	}

	public ParseObject getUser() {

		ParseObject user = getParseObject("userId");
		return user;
	}

	public void setUser(String id) {
		ParsePointer pointer = new ParsePointer("_User", id);
		put("userId", pointer);
	}

	public Car getCar() {
		Car c = null;
		ParseObject car = getParseObject("carId");
		if(car != null){
			String carId = car.getObjectId();
			ParseQuery<Car> query = ParseQuery.getQuery("Car");
			try {
				c = query.get(carId);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return c;
	}

	public void setCar(String id) {
		Car car = null;
		ParseQuery<Car> query = ParseQuery.getQuery("Car");
		try {
			car = query.get(id);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		put("carId", car);
	}

	public void setCar(Car car) {
		put("carId", car);
	}

	public static ParseQuery<Driver> getQuery() {
		return ParseQuery.getQuery(Driver.class);
	}
}
