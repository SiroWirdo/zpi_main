package main.controller;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.jdesktop.swingx.mapviewer.Waypoint;

import main.model.MapModel;
import main.view.MapComponent;
import main.view.MapPanel;
import main.view.MapWaypoint;
import model.Driver;
import model.Order;

public class MapController{
	private MapModel mapModel;
	private MapPanel mapView;
	
	public MapController(MapModel mapModel) {
		this.mapModel = mapModel;
		mapView = new MapPanel(this, mapModel);
		mapModel.initialize();
		//mapModel.start();
		mapView.initialize();
	}
	
	public MapPanel getMapView(){
		return mapView;
	}
	
	public void addCarWaypoints(){
		List<Driver> drivers = mapModel.getAvalaibleDrivers();
		Set<MapComponent> positions = mapModel.getCarsPositions(drivers);
		mapModel.addCarsWaypoints(positions);
	}	
	
	public void addCustomersWaypoints(){
		List<Order> orders = mapModel.getWaitingOrders();
		Set<MapComponent> positions = mapModel.getWaitingCustomersPosition(orders);
		mapModel.addCustomersWaypoints(positions);
	}
	
	public void drawAllWaypoints(){
		addCustomersWaypoints();
		addCarWaypoints();
		final Set<MapComponent> allWaypoints = mapModel.getAllWaypoints();
		mapView.drawWaypointsComponent(allWaypoints);
	}
}
