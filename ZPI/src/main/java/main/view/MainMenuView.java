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

import javax.swing.ScrollPaneConstants;

import org.parse4j.callback.UserModel;

import main.controller.MainMenuController;
import main.model.MainMenuModel;
import model.Order;
import order.controller.OrderController;
import order.view.AddOrderJPanel;

public class MainMenuView extends JFrame{
	MainMenuModel menuModel;
	MainMenuController menuController;
	JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	public MainMenuView(MainMenuController menuController, MainMenuModel menuModel) {
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
//
//		JPanel panel = new JPanel();
//		panel.setBounds(10, 10, 862, 691);
//		mapPanel.add(panel);

		//AddOrderJPanel addOrderPanel = new AddOrderJPanel();
		//tabbedPane.addTab("Dodaj zlecenie", null, addOrderPanel, null);
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
		MapPanel map = new MapPanel();
		GridBagLayout gridBagLayout = (GridBagLayout) map.getMainMap().getLayout();
		gridBagLayout.rowWeights = new double[]{1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		map.setBounds(0, 0, 1000, 700);
		mapPanel.add(map);
		//map.setBounds(0, 0, 5000, 3500);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(10, 10, 1000, 700);
//
//		scrollPane.setVisible(true);
//		scrollPane.add(map);
		
//		mapPanel.add(scrollPane);
	//	JPanel panel
	//	mapPanel.add(map);
	}
	
	public void addOrderPanel(){
		Order orderModel = new Order();
		OrderController orderController = new OrderController(orderModel);
		
	}
	
	public void addDriverPanel(){
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Kierowcy", null, panel_2, null);
		this.setVisible(true);
	}
	
	public void addTab(String label, Icon icon, JPanel panelTab, String tip){
		tabbedPane.addTab(label, icon, panelTab, tip);
//		Order orderModel = new Order();
//		OrderController orderController = new OrderController(orderModel);
		
	}
}
