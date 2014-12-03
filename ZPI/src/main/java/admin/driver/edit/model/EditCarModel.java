package admin.driver.edit.model;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;

import model.Car;
import model.Dispatcher;
import other.DataBaseConnection;

public class EditCarModel {

	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	public void editCar(String[] values, Car car){
		car.setRegistrationNumber(values[0]);
		car.setSideNumber(new Integer(values[1]));
		car.setCarCapacity(new Integer(values[2]));
		
		try {
			car.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Car getCar(String carId){
		ParseQuery<Car> query = ParseQuery.getQuery(Car.class);
		query.whereEqualTo("objectId", carId);
		Car car = null;
		try {
			car = query.find().get(0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return car;
	}
}
