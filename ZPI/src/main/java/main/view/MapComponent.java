package main.view;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Driver;
import model.Order;
import order.controller.OrderController;

import org.jdesktop.swingx.mapviewer.Waypoint;

import drivers.controller.DriversController;

public class MapComponent extends JLabel{

	private Image img;
	private Driver driver;
	private Order order;
	private Waypoint waypoint;
	
	/*
	 *Paths icons resources 
	 */
	private final String CAR_ICON_RESOURCE_PATCH = "src/main/resources/car_waypoints/";
	private final String CUSTOMER_ICON_RESOURCE_PATCH = "src/main/resources/customer_waypoints/";
	/*
	 * In DB Car.status = 0
	 */
	private final String CAR_WAYPOINT_FREE = "car_waypoint_free.png";
	/*
	 * In DB Car.status = 1
	 */
	private final String CAR_WAYPOINT_DRIVE = "car_waypoint_drive.png";
	/*
	 * In DB Car.status = 2
	 */
	private final String CAR_WAYPOINT_PAUSE = "car_waypoint_pause.png";
	/*
	 * In DB Car.status = 3
	 */
	private final String CAR_WAYPOINT_BLOCKED = "car_waypoint_blocked.png";
	/*
	 * In DB Car.status = 4
	 */
	private final String CAR_WAYPOINT_UNAVAILABLE = "car_waypoint_unavailable.png";
	
	/*
	 * In DB Order.status = 0
	 */
	private final String CUSTOMER_BLUE = "customer_blue.png";
	/*
	 * In DB Order.status = 4
	 */
	private final String CUSTOMER_YELLOW = "customer_yellow.png";
	
	public MapComponent(Waypoint waypoint, Order order) {
		if(order != null){
	     this.driver = null;
	     this.order = order;
	     this.waypoint = waypoint;
	     
	     this.setIcon(chooseCustomerIcon());
	     this.addMouseListener(new WaypointMouseListener());
		}
		else{
			throw new IllegalArgumentException("Order object is null!");
		}
		
	}
	
	public MapComponent(Waypoint waypoint, Driver driver) {
		if(driver != null){
	     this.driver = driver;
	     this.order = null;
	     this.waypoint = waypoint;
	     
	     this.setIcon(chooseCarIcon());
	     this.addMouseListener(new WaypointMouseListener());
		}
		else{
			throw new IllegalArgumentException("Driver object is null!");
		}
	}
	
	public MapComponent(double latitude, double longitude, final Driver driver) {
	     new MapComponent(new Waypoint(latitude, longitude), driver);
	}
	
	public MapComponent(double latitude, double longitude, final Order order) {
	     new MapComponent(new Waypoint(latitude, longitude), order);
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Order getCustomer() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Waypoint getWaypoint() {
		return waypoint;
	}

	public void setWaypoint(Waypoint waypoint) {
		this.waypoint = waypoint;
	}

	public ImageIcon chooseCarIcon(){
		String imageIconPatch = CAR_ICON_RESOURCE_PATCH;
		int driverStatus = driver.getStatus();
		
		StringBuilder sb = new StringBuilder(imageIconPatch);
		switch (driverStatus) {
		case 0:
			sb.append(CAR_WAYPOINT_FREE);
			break;
		case 1:
			sb.append(CAR_WAYPOINT_DRIVE);
			break;
		case 2:
			sb.append(CAR_WAYPOINT_PAUSE);
			break;
		case 3:
			sb.append(CAR_WAYPOINT_BLOCKED);
			break;
		case 4:
			sb.append(CAR_WAYPOINT_UNAVAILABLE);
			break;
		}
		return new ImageIcon(sb.toString());
	}

	public ImageIcon chooseCustomerIcon(){
		String imageIconPatch = CUSTOMER_ICON_RESOURCE_PATCH;
		int orderStatus = order.getStatus();
		
		StringBuilder sb = new StringBuilder(imageIconPatch);
		switch (orderStatus) {
		case 0:
			sb.append(CUSTOMER_BLUE);
			break;
		case 4:
			sb.append(CUSTOMER_YELLOW);
			break;
		}
		return new ImageIcon(sb.toString());
	}

	private class WaypointMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(order != null){
				Order orderDetailsModel = order;
				OrderController orderDetailsController = new OrderController(orderDetailsModel);
				orderDetailsController.setOrderDetails();
			}
			else{
				Driver driverDetailsModel = driver;
				DriversController driverDetailsController = new DriversController(driverDetailsModel);
				driverDetailsController.setDriverDetails();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
