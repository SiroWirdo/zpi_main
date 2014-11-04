package main.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Driver;
import model.Order;

import org.jdesktop.swingx.mapviewer.Waypoint;

public class MapComponent extends JLabel{

	private Image img;
	private Driver driver;
	private Order order;
	private Waypoint waypoint;
	
	public MapComponent() {
	     super();
	}
	
	public MapComponent(Waypoint waypoint, Driver driver, Order order) {
	     this.driver = driver;
	     this.order = order;
	     this.waypoint = waypoint;
	     this.setIcon(new ImageIcon("C:\\\\Users\\\\Ewelina\\\\Documents\\\\Semestr 7\\\\ZPI\\\\zpi_taxi_main\\\\ZPI\\\\src\\\\main\\\\resources\\\\waypoint_white.png"));
	}
	
	public MapComponent(double latitude, double longitude, final Driver driver, Order order) {
	     new MapComponent(new Waypoint(latitude, longitude), driver, order);
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
}
