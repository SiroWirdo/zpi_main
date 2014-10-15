package models;

public class Customer {
	private String id;
	private int phoneNumber;
	private String surname;
	
	public Customer(String id, int phoneNumber, String surname) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.surname = surname;
	}

	/**id musi byæ unikalne**/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
