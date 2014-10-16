package model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

@ParseClassName("Customer")
public class Customer extends ParseObject {

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


	public static ParseQuery<Customer> getQuery() {
		return ParseQuery.getQuery(Customer.class);
	}
}