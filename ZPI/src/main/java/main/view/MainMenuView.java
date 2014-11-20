package main.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;
import drivers.controller.DriversController;
import drivers.model.DriversModel;
import main.controller.FilterMapController;
import main.controller.MainMenuController;
import main.controller.MapController;
import main.controller.StatisticController;
import main.model.FilterMapModel;
import main.model.MapModel;
import main.model.StatisticModel;

import javax.swing.JTabbedPane;
import order.controller.OrderController;
import order.model.OrderModel;
import ordersdisplay.controller.OrdersController;
import ordersdisplay.model.OrdersModel;


public class MainMenuView extends JFrame{
	MapModel menuModel;
	MainMenuController menuController;
	JTabbedPane tabbedPane;

	MapController mapController;

	/**
	 * Create the application.
	 */
	public MainMenuView(MainMenuController menuController, MapModel menuModel) {
		this.menuController = menuController;
		this.menuModel = menuModel;
		//initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setBounds(100, 100, 1280, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addTabbedPane();
		addMainPagePanels();
		addOrderPanel();
		addDriverPanel();
		addOrdersDisplayPanel();
		//this.setDefaultLookAndFeelDecorated(false);
		this.setVisible(true);
	}

	public void addTabbedPane(){
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void addMainPagePanels(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.add(getMapPanel());
		mainPanel.add(getStatisticPanel());
		mainPanel.add(getFilterPanel());
		mainPanel.setVisible(true);
		
		tabbedPane.addTab("Mapa", null, mainPanel, null); //TODO ustawic ikony?
	}

	public JScrollPane getMapPanel(){
		JScrollPane scrollPane = new JScrollPane(getMap());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 1000, 700);
		
		return scrollPane;
	}
	
	public JPanel getMap(){
		MapModel mapModel = new MapModel();
		mapController = new MapController(mapModel);

		MapPanel view = mapController.getMapView();
		GridBagLayout gridBagLayout = (GridBagLayout) view.getMainMap().getLayout();
		gridBagLayout.rowWeights = new double[]{1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		view.setBounds(0, 0, 2000, 2000);
		mapController.drawAllWaypoints();
		//mapPanel.add(map);
		//map.setBounds(0, 0, 5000, 3500);

		return view;
	}
	
	public JPanel getStatisticPanel(){
		StatisticModel statisticModel = new StatisticModel();
		StatisticController statisticController = new StatisticController(statisticModel);

		JPanel statisticPanel = statisticController.getView();
		//statisticPanel.setBounds(0, 0, 220, 300);
		statisticPanel.setBounds(1000, 0, 300, 300);
		return statisticPanel;
	}
	
	public JPanel getFilterPanel(){
		FilterMapModel filterModel;
		FilterMapController filterController;
		JPanel filterPanel = null;
		if(mapController != null){
			filterModel = new FilterMapModel();
			filterController = new FilterMapController(filterModel, mapController);
			filterPanel = filterController.getView();
			filterPanel.setBounds(1000, 350, 300, 300);
		}
		return filterPanel;
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

	public void addOrdersDisplayPanel(){
		OrdersModel ordersModel = new OrdersModel();
		OrdersController ordersController = new OrdersController(ordersModel);
		tabbedPane.addTab("Wy�wietl zlecenia", null, ordersController.getOrdersView(), null);
		this.setVisible(true);
	}

/*	public void addTab(String label, Icon icon, JPanel panelTab, String tip){
		tabbedPane.addTab(label, icon, panelTab, tip);
		Order orderModel = new Order();
		OrderController orderController = new OrderController(orderModel);

	}*/
}
