package admin.car.add.model;

import org.parse4j.ParseException;

import other.DataBaseConnection;
import model.Car;

public class AddCarModel {

	public void initialize(){
		DataBaseConnection.initialize();
	}

	public Car addCar(String registrationNumber, int sideNumber, int carCapicity){
		Car car = new Car();
		car.setRegistrationNumber(registrationNumber);
		car.setSideNumber(sideNumber);
		car.setCarCapacity(carCapicity);
		try {
			car.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return car;
	}
}
