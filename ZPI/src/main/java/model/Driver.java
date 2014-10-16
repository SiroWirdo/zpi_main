package model;

public class Driver {
	private String id;
	private String name;
	private String surname;
	//Dajemy usera, czy parseUser?
	private User userId;
	private int phoneNumber;
	private String licenseNumber;
	private int pesel;
	private int status;
	private Car carId;

	public Driver(String id, String name, String surname, User userId,
			int phoneNumber, String licenseNumber, int pesel, int status,
			Car carId) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userId = userId;
		this.phoneNumber = phoneNumber;
		this.licenseNumber = licenseNumber;
		this.pesel = pesel;
		this.status = status;
		this.carId = carId;
	}
	
	public String getId() {
		return id;
	}

	/**id musi by� unikalne**/
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**licenseNumber musi by� unikalny**/
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public int getPesel() {
		return pesel;
	}

	/**PESEL musi by� unikalny**/
	public void setPesel(int pesel) {
		this.pesel = pesel;
	}
	
	/** 1-wolny, 2-kurs, 3-niedostepny, 4-przerwa, 5-zablokowany**/
	public int getStatus() {
		return status;
	}

	/** 1-wolny, 2-kurs, 3-niedostepny, 4-przerwa, 5-zablokowany**/
	public void setStatus(int status) {
		this.status = status;
	}

	public Car getCarId() {
		return carId;
	}

	public void setCarId(Car carId) {
		this.carId = carId;
	}
}
