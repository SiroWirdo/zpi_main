package main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import settings.Settings;
import main.model.MapModel;
import main.view.MapComponent;
import main.view.MapPanel;
import model.Driver;
import model.Order;

/*
 * Zarządza tworzeniem mapy, odświeżaniem jej, rysowaniem waypointów i komponentów na mapie
 */
public class MapController{
	private MapModel mapModel;
	private MapPanel mapView;
	
	public MapController(MapModel mapModel) {
		this.mapModel = mapModel;
		mapView = new MapPanel(this, mapModel);
		mapModel.initialize();
		mapView.initialize();
		
		refreshMap();
		refreshMapWithDelay(Settings.MAP_REFRESH_TIME);
	}
	
	public MapPanel getMapView(){
		return mapView;
	}
	
	public ButtonListener getButtonListener(){
		return new ButtonListener();
	}
	
	private void refreshMapWithDelay(final long millis){
		Thread refreshThread = new Thread() {
	        public void run() {
	        	while(true){
	        		refreshMap();
	        		try {
						Thread.sleep(millis);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
	      };
	      refreshThread.start();
	}
	
	public void refreshMap(){
		Set<MapComponent> updateWaypoints = new HashSet<MapComponent>();
		updateWaypoints.addAll(getCustomerWaypoints());
		updateWaypoints.addAll(getCarWaypoints());
		mapModel.setAllWaypoints(updateWaypoints);
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
				mapView.cleanMap();
				mapView.drawWaypointsComponent(mapModel.getAllWaypoints());
				mapView.repaint();
		    }
		  });
	}
	
	private Set<MapComponent> getCarWaypoints(){
		List<Driver> drivers = mapModel.getAvalaibleDrivers();
		Set<MapComponent> positions = mapModel.getCarsPositions(drivers);
		return positions;
	}	
	
	private void addCustomersWaypoints(){
		Set<MapComponent> positions = getCustomerWaypoints();
		mapModel.addCustomersWaypoints(positions);
	}
	
	private Set<MapComponent> getCustomerWaypoints(){
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
	
	public void setPosition(double latitude, double longitude){
		mapView.setPosition(latitude, longitude);
	}
	
	private void addCarWaypoints(){
		Set<MapComponent> positions = getCarWaypoints();
		mapModel.addCarsWaypoints(positions);
	}
	
	public void setQueryDriverStatusArray(ArrayList<Integer> queryDriverStatusArray) {
		mapModel.setQueryDriverStatusArray(queryDriverStatusArray);
	}

	public void setQueryOrderStatusArray(ArrayList<Integer> queryOrderStatusArray) {
		mapModel.setQueryOrderStatusArray(queryOrderStatusArray);
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(((JButton)e.getSource()).getName().equals("defaultPosition")){
				mapView.setDefaultPosition();
			}
		}
		
	}
	
}
