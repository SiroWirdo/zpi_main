package main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import main.view.MapComponent;
import model.Car;
import model.Driver;
import model.Order;

import org.jdesktop.swingx.mapviewer.Waypoint;
import org.parse4j.ParseException;
import org.parse4j.ParseQuery;

import other.DataBaseConnection;
import settings.Settings;

public class MapModel{
	
	private List<Driver> avalaibleDrivers;
	private List<Order> waitingOrders;
	private Set<MapComponent> allWaypoints;
	private ParseQuery<Driver> queryDriver;
	private ParseQuery<Order> queryOrder;
	private ArrayList<Integer> queryDriverStatusArray;
	private ArrayList<Integer> queryOrderStatusArray;

	public MapModel() {		
		setAllWaypoints(new HashSet<MapComponent>());
		waitingOrders = new ArrayList<Order>();
		initializeStatusArrays();
		queryDriver = ParseQuery.getQuery(Driver.class);
	}

	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	public void initializeStatusArrays(){
		queryDriverStatusArray = new ArrayList<Integer>(Settings.driverStatus.length);
		queryDriverStatusArray.add(0);
		queryDriverStatusArray.add(1);
		queryDriverStatusArray.add(2);
		queryDriverStatusArray.add(3);
		queryDriverStatusArray.add(4);
		/*
		 * Tylko dwa statusy sa brane pod uwage: {0, 1}
		 */
		queryOrderStatusArray =  new ArrayList<Integer>(2);
	}
	
	public void getDriversPositionSet(){
		getAvalaibleDrivers();
	}
	
	public List<Driver> getAvalaibleDrivers(){
		avalaibleDrivers = null;
		queryDriver = ParseQuery.getQuery(Driver.class);
		queryDriver.whereContainedIn("status", queryDriverStatusArray);
		try {
			avalaibleDrivers = queryDriver.find();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return avalaibleDrivers;
	}
	
	public Set<MapComponent> getCarsPositions(List<Driver> avalaibleDrivers){
		Set<MapComponent> driversWaypoints = new HashSet<MapComponent>();
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
		Set<MapComponent> customersWaypoints = new HashSet<MapComponent>();
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
		
		/*for(MapComponent w: customersWaypoints){
			System.out.println("dl: " + w.getWaypoint().getPosition().getLatitude()
					+ " szr: " + w.getWaypoint().getPosition().getLatitude());
			}*/
		}
		
		return customersWaypoints;
	}
	
	public List<Order> getWaitingOrders(){
		waitingOrders = new ArrayList<Order>();
		queryOrder = ParseQuery.getQuery(Order.class);
		queryDriver.whereContainedIn("status", queryOrderStatusArray);
		try {
			waitingOrders = queryOrder.find();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return waitingOrders;
	}
	
	public void addCarsWaypoints(Set<MapComponent> carWaypoints) {
			allWaypoints.addAll(carWaypoints);
	}
	
	public void addCustomersWaypoints(Set<MapComponent> customersWaypoints) {
			allWaypoints.addAll(customersWaypoints);
	}

	public Set<MapComponent> getAllWaypoints() {
		return allWaypoints;
	}

	public void setAllWaypoints(Set<MapComponent> allWaypoints) {
		this.allWaypoints = allWaypoints;
	}

	public void setQueryDriverStatusArray(ArrayList<Integer> queryDriverStatusArray) {
		this.queryDriverStatusArray = queryDriverStatusArray;
	}

	public void setQueryOrderStatusArray(ArrayList<Integer> queryOrderStatusArray) {
		this.queryOrderStatusArray = queryOrderStatusArray;
	}

}
