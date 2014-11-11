package algorithm;



import java.util.List;

import model.Car;
import model.Driver;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParsePointer;
import org.parse4j.ParsePush;
import org.parse4j.ParseQuery;

public class Algorithm implements Runnable {
	List<Driver> drivers;
	Order order;
	
	public boolean chooseDriver(){
		// TODO  tutaj stworzenie listy wszystkich dostêpnych kierowców. Wywo³anie metod sortuj¹cych i rozpoczêcie powiadamiania kierowców.
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.whereEqualTo("status", 0);
		try {
			drivers = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.run();
		
		return true;
	}	

	public double countDistanceInStraightLine(Car car, Order order){
		ParseGeoPoint driverPosition = car.getCurrentPosition();
		ParseGeoPoint clientPosition = order.getPickupAddressGeo();
		
		double distance = driverPosition.distanceInKilometersTo(clientPosition);
		return distance;
	}
	
	public void notifyDriver(Driver driver, Order order){
		ParsePush push = new ParsePush();
		//ArrayList<String> channels = new ArrayList<String>();
		//sprawdziæ jakie potrzebne s¹ do listy rzeczy czy: ["",id] czy [id] czy inaczej
		ParsePointer pointer = (ParsePointer) driver.get("userId");
		String userId = (String)pointer.get("objectId");
		String channel = "user_" + userId;
		push.setMessage(order.getObjectId());
		push.setChannel(channel);
		try {
			push.send();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		boolean assigned = false;
		int i = 0;
		while(!assigned){
			notifyDriver(drivers.get(i), order);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ParsePointer pointer = (ParsePointer) order.get("driver");
			
			// nie wiem czy na pewno zwróci null jak nie jest przypisany
			if(pointer != null){
				assigned = true;
			}else{
				i++;
			}
		}
	}
	
	
}
