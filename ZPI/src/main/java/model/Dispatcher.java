package model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;
import org.parse4j.ParsePointer;
import org.parse4j.ParseQuery;

@ParseClassName("Dispatcher")
public class Dispatcher extends ParseObject {

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

	public long getPESEL() {
		return getLong("PESEL");
	}

	public void setPESEL(long value) {
		put("PESEL", value);
	}

	public ParseObject getUser() {

		ParseObject user = getParseObject("userId");
		return user;
	}

	public void setUser(String id) {
		ParsePointer pointer = new ParsePointer("_User", id);
		put("userId", pointer);
	}

	public static ParseQuery<Dispatcher> getQuery() {
		return ParseQuery.getQuery(Dispatcher.class);
	}
}
