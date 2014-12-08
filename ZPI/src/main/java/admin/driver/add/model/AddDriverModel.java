package admin.driver.add.model;

import model.Car;
import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParsePointer;

import other.DataBaseConnection;
import settings.Settings;

public class AddDriverModel {

	public void initialize(){
		DataBaseConnection.initialize();
	}

	public void addDriver(String name, String surname, long pesel, long phone, String license, ParsePointer user, Car car){
		Driver driver = new Driver();
		driver.setName(name);
		driver.setSurname(surname);
		driver.setPESEL(pesel);
		driver.setPhoneNumber(phone);
		driver.setLicenseNumber(license);
		driver.setStatus(Settings.UNAVALAIBLE_CAR_STATUS);
		driver.put("userId", user);
		driver.setCar(car);
		try {
			driver.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//userId

	}
}
