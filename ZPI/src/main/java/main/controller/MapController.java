package main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.Timer;

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
	
	ActionListener taskPerformer;
	
	public MapController(MapModel mapModel) {
		this.mapModel = mapModel;
		mapView = new MapPanel(this, mapModel);
		mapModel.initialize();
		mapView.initialize();
		
		refreshMap();
//		refreshMapWithDelay();
	}
	
	public MapPanel getMapView(){
		return mapView;
	}
	
	public void addCarWaypoints(){
		Set<MapComponent> positions = getCarWaypoints();
		mapModel.addCarsWaypoints(positions);
	}
	
	public Set<MapComponent> getCarWaypoints(){
		List<Driver> drivers = mapModel.getAvalaibleDrivers();
		Set<MapComponent> positions = mapModel.getCarsPositions(drivers);
		return positions;
	}	
	
	public void addCustomersWaypoints(){
		Set<MapComponent> positions = getCustomerWaypoints();
		mapModel.addCustomersWaypoints(positions);
	}
	
	public Set<MapComponent> getCustomerWaypoints(){
		List<Order> orders = mapModel.getWaitingOrders();
		Set<MapComponent> positions = new HashSet<MapComponent>();
		if(orders != null){
			positions = mapModel.getWaitingCustomersPosition(orders);
		}
		return positions;
	}
	
	public void drawAllWaypoints(){
		addCustomersWaypoints();
		addCarWaypoints();
		final Set<MapComponent> allWaypoints = mapModel.getAllWaypoints();
		mapView.drawWaypointsComponent(allWaypoints);
	}
	
	public void refreshMapWithDelay(){
		int delay = 10000; //milliseconds
		  taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshMap();	
			}
		  };
		  new Timer(delay, taskPerformer).start();
	}
	
	public void refreshMap(){
		Set<MapComponent> updateWaypoints = new HashSet<MapComponent>();
		updateWaypoints.addAll(getCustomerWaypoints());
		updateWaypoints.addAll(getCarWaypoints());
		mapModel.setAllWaypoints(updateWaypoints);
		mapView.cleanMap();
		mapView.drawWaypointsComponent(mapModel.getAllWaypoints());
		mapView.repaint();
		
	}
	
	public void setQueryDriverStatusArray(ArrayList<Integer> queryDriverStatusArray) {
		mapModel.setQueryDriverStatusArray(queryDriverStatusArray);
	}

	public void setQueryOrderStatusArray(ArrayList<Integer> queryOrderStatusArray) {
		mapModel.setQueryOrderStatusArray(queryOrderStatusArray);
	}

	
}
