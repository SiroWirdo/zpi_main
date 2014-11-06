package main.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import main.view.MapComponent;
import main.view.MapWaypoint;
import model.Car;
import model.Driver;
import model.Order;

import org.jdesktop.swingx.mapviewer.Waypoint;
import org.parse4j.ParseException;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.GetCallback;

import other.DataBaseConnection;

public class MapModel{
	Set<MapComponent> customersWaypoints;
	Set<MapComponent> driversWaypoints;
	List<Driver> avalaibleDrivers;
	List<Order> waitingOrders;
	ActionListener taskPerformer;
	private Set<MapComponent> allWaypoints;
	
	public MapModel() {
		customersWaypoints = new HashSet<MapComponent>();
		driversWaypoints = new HashSet<MapComponent>();
		setAllWaypoints(new HashSet<MapComponent>());
		waitingOrders = new ArrayList<Order>();
	}

	public void initialize(){
		DataBaseConnection.initialize();
	}
	public void getCustomersPositionSet(){
		/*ParseQuery<Driver> availableDrivers = ParseQuery.getQuery(Driver.class);

		availableDrivers.whereMatchesQuery("status", )*/
	}
	
	public void getDriversPositionSet(){
		getAvalaibleDrivers();
	}
	
	public List<Driver> getAvalaibleDrivers(){
		avalaibleDrivers = null;
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		//warunek do zmiany potem?
		query.whereLessThanOrEqualTo("status", 4);
		try {
			avalaibleDrivers = query.find();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return avalaibleDrivers;
	}
	
	public Set<MapComponent> getCarsPositions(List<Driver> avalaibleDrivers){
		if(avalaibleDrivers != null){
			for(final Driver d: avalaibleDrivers){
				Car car = d.getCar();
				if(car != null && car.getCurrentPosition() != null){
					MapComponent locationWaypoint = new MapComponent(car.getCurrentPositionWayPoint(), d);
					driversWaypoints.add(locationWaypoint);
					System.out.println("DODANIE NOWEGO AUTA DO ZBIORU");
				}				
			}
		}
		System.out.println("Znaleziono kierowców: " + driversWaypoints.size());
		
		return driversWaypoints;
	}
	
	public Set<MapComponent> getWaitingCustomersPosition(List<Order> waitingOrders){
		synchronized (waitingOrders) {
		if(waitingOrders != null){
			for(Order o: waitingOrders){
				if(o.getPickupAddressGeo() != null){
					Waypoint locationWaypoint = o.getCurrentPositionWaypoint();
					customersWaypoints.add(new MapComponent(locationWaypoint, o));
				}
			}
		}
		else{
			System.out.println("Nie ma oczekuj¹cych klientów.");
		}
		
		for(MapComponent w: customersWaypoints){
			System.out.println("dl: " + w.getWaypoint().getPosition().getLatitude()
					+ " szr: " + w.getWaypoint().getPosition().getLatitude());
			}
		}
		
		return customersWaypoints;
	}
	
	public List<Order> getWaitingOrders(){
		waitingOrders = new ArrayList<Order>();
		ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
		//query.whereLessThanOrEqualTo("status", 1);
		try {
			waitingOrders = query.find();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return waitingOrders;
	}
	
	public void refreshMap(){
		int delay = 1000; //milliseconds
		  taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		  };
		  new Timer(delay, taskPerformer).start();
	}
	
	public void addCarsWaypoints(Set<MapComponent> carWaypoints) {
		for(MapComponent mapWaypoint: carWaypoints){
			allWaypoints.add(mapWaypoint);
		}
		
	}
	
	public void addCustomersWaypoints(Set<MapComponent> customersWaypoints) {
		for(MapComponent mapWaypoint: customersWaypoints){
			allWaypoints.add(mapWaypoint);
		}
		
	}

	public Set<MapComponent> getAllWaypoints() {
		return allWaypoints;
	}

	public void setAllWaypoints(Set<MapComponent> allWaypoints) {
		this.allWaypoints = allWaypoints;
	}
}
