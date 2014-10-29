package admin.driver.add.model;

import model.Driver;
import other.DataBaseConnection;

public class AddDriverModel {
	
	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	public void addDriver(String name, String surname, long pesel, long phone, String license, String userId){
		Driver driver = new Driver();
		driver.setName(name);
		driver.setSurname(surname);
		driver.setPESEL(pesel);
		driver.setPhoneNumber(phone);
		driver.setLicenseNumber(license);
		driver.setStatus(4);
		driver.saveInBackground();
		//userId
		
	}
}
