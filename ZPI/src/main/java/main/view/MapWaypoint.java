package main.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import model.Customer;
import model.Driver;
import model.Order;

import org.jdesktop.swingx.mapviewer.Waypoint;

public class MapWaypoint extends Waypoint {
	private Image img;
	private JLabel component;
	private Driver driver;
	private Order order;
	
	public MapWaypoint() {
	     super();
	}
	
	public MapWaypoint(Waypoint waypoint, Driver driver, Order order) {
	     super(waypoint.getPosition());
	     this.driver = driver;
	     this.order = order;
	     component = new JLabel();
	     component.setIcon(new ImageIcon("C:\\\\Users\\\\Ewelina\\\\Documents\\\\Semestr 7\\\\ZPI\\\\zpi_taxi_main\\\\ZPI\\\\src\\\\main\\\\resources\\\\waypoint_white.png"));
	}
	
	public MapWaypoint(double latitude, double longitude, final Driver driver, Order order) {
	     new MapWaypoint(new Waypoint(latitude, longitude), driver, order);
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

	public JLabel getJLabel() {
		return component;
	}

	public void setJLabel(JLabel component) {
		this.component = component;
	}
	
}
