package main.view;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ScrollPaneConstants;

import org.jdesktop.swingx.JXMapViewer;
import org.parse4j.callback.UserModel;
import org.parse4j.util.ParseRegistry;

import ch.qos.logback.core.Layout;

import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;

import drivers.controller.DriversController;
import drivers.model.DriversModel;
import drivers.view.DriversView;
import main.controller.MainMenuController;
import main.controller.MapController;
import main.model.MapModel;
import model.Driver;
import model.Order;
import order.controller.OrderController;
import order.model.OrderModel;
import order.view.AddOrderJPanel;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class MainMenuView extends JFrame{
	MapModel menuModel;
	MainMenuController menuController;
	JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	public MainMenuView(MainMenuController menuController, MapModel menuModel) {
		this.menuController = menuController;
		this.menuModel = menuModel;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setBounds(100, 100, 1280, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addTabbedPane();
		addMapPanel();
		addOrderPanel();
		addDriverPanel();

		this.setVisible(true);
	}
	
	public void addTabbedPane(){
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void addMapPanel(){
		JPanel mapPanel = new JPanel();
		tabbedPane.addTab("Mapa", null, mapPanel, null);
		mapPanel.setLayout(null);
		MapModel mapModel = new MapModel();
		MapController mapController = new MapController(mapModel);
		
		//MapPanel map = new MapPanel();
		MapPanel view = mapController.getMapView();
		GridBagLayout gridBagLayout = (GridBagLayout) view.getMainMap().getLayout();
		gridBagLayout.rowWeights = new double[]{1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		view.setBounds(0, 0, 2000, 2000);
		mapController.drawAllWaypoints();
		//mapPanel.add(map);
		//map.setBounds(0, 0, 5000, 3500);
		
		JScrollPane scrollPane = new JScrollPane(view);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 1000, 700);
		
		mapPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(1058, 11, 167, 136);
		mapPanel.add(panel);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("C:\\\\Users\\\\Ewelina\\\\Documents\\\\Semestr 7\\\\ZPI\\\\zpi_taxi_main\\\\ZPI\\\\src\\\\main\\\\resources\\\\waypoint_white.png"));
		lblNewLabel.setOpaque(false); 
		panel.add(lblNewLabel);
		
		JButton btnBtn = new JButton();
		btnBtn.setIcon(new ImageIcon("C:\\Users\\Ewelina\\Documents\\Semestr 7\\ZPI\\zpi_taxi_main\\ZPI\\src\\main\\resources\\waypoint_white.png"));
		panel.add(btnBtn);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);
		
	}
	
	public void addOrderPanel(){
		OrderModel orderModel = new OrderModel();
		OrderController orderController = new OrderController(orderModel);
		tabbedPane.addTab("Dodaj zlecenie", null, orderController.getAddOrderView(), null);
		this.setVisible(true);
	}
	
	public void addDriverPanel(){
		DriversModel driversModel = new DriversModel();
		DriversController driversController = new DriversController(driversModel);
		
		driversModel.addObserver(driversController);
		tabbedPane.addTab("Kierowcy", null, driversController.getDriversView(), null);
//		this.repaint();
		this.setVisible(true);
	}
}
