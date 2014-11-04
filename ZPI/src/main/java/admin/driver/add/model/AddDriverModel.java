package admin.driver.add.model;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import other.DataBaseConnection;

public class AddDriverModel {

	public void initialize(){
		DataBaseConnection.initialize();
	}

	public void addDriver(String name, String surname, long pesel, long phone, String license, String userId, ParseUser user){
		Driver driver = new Driver();
		driver.setName(name);
		driver.setSurname(surname);
		driver.setPESEL(pesel);
		driver.setPhoneNumber(phone);
		driver.setLicenseNumber(license);
		driver.setStatus(4);
		driver.put("userId", user.getObjectId());
		try {
			driver.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//userId

	}
}
