package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import login.controller.LoginController;
import main.controller.FilterMapController;
import main.controller.MainMenuController;
import main.controller.MapController;
import main.controller.StatisticController;
import main.model.FilterMapModel;
import main.model.MapModel;
import main.model.StatisticModel;
import message.controller.MessageController;
import message.model.MessageModel;
import order.controller.OrderController;
import order.model.OrderModel;
import ordersdisplay.controller.OrdersController;
import ordersdisplay.model.OrdersModel;
import other.DataBaseConnection;
import settings.Settings;
import drivers.controller.DriversController;
import drivers.model.DriversModel;
import drivers.controller.DriversController;


public class MainMenuView extends JFrame{
	
	private MapModel menuModel;
	private MainMenuController menuController;
	private JTabbedPane tabbedPane;
	private JTabbedPane smallTabbedPane;
	private final String title = "ZPI TAXI";
	

	private MapController mapController;
	private StatisticController statisticController;

	/**
	 * Create the application.
	 */
	public MainMenuView(MainMenuController menuController, MapModel menuModel) {
		this.menuController = menuController;
		this.menuModel = menuModel;
		//initialize();
		Settings.turnOffLogBackLogger();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setBounds(10, 10, 1350, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);

		MapModel mapModel = new MapModel();
		mapController = new MapController(mapModel);
		
		DataBaseConnection.initialize();		
		addMainTabbedPane();

		this.setTitle(title);
		this.setVisible(true);
	}

	private void addMainTabbedPane(){
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1025, 800);
//		tabbedPane.setBorder((new LineBorder(Color.black, 5)));
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Mapa", null, getMainPanel(), null);
		tabbedPane.addTab("Kierowcy", null, getDriverPanel(), null);
		tabbedPane.addTab("Wyświetl zlecenia", null, getOrdersDisplayPanel(), null);
	}
	
	private JTabbedPane getSecondTabbedPane(){
		smallTabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		smallTabbedPane.setBorder((new LineBorder(Color.RED, 5)));
		smallTabbedPane.addTab("Dodaj zlecenie", null, getOrderPanel(), null);
		smallTabbedPane.addTab("Wyślij wiadomość", null, getMessagePanel(), null);
//		smallTabbedPane.addTab("Filtruj", null, getFilterPanel(), null);
		smallTabbedPane.addTab("Statystyki", null, getStatisticPanel(), null);
		smallTabbedPane.setBounds(0, 0, 420, 360);
		return smallTabbedPane;
	}

	public JSplitPane getMainPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(getSecondTabbedPane());
		
		JPanel filter = getFilterPanel();
		filter.setBounds(0, 360, 300, 700);
		panel.add(filter);
		panel.setBounds(0, 0, 350, 400);
		panel.setVisible(true);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getMap(), panel);	
		splitPane.setResizeWeight(0.75);
		return splitPane;
	}

	public JPanel getMap(){
		MapPanel view = mapController.getMapView();
		view.setBounds(10, 10, 1025, 700);

		return view;
	}


	public JPanel getStatisticPanel(){
		StatisticModel statisticModel = new StatisticModel();
		statisticController = new StatisticController(statisticModel);

		JPanel statisticPanel = statisticController.getView();
//		statisticPanel.setBounds(1025, 0, 300, 300);
	
		return statisticPanel;
	}

	public JPanel getFilterPanel(){
		FilterMapModel filterModel;
		FilterMapController filterController;
		JPanel filterPanel = null;
//		if(mapController != null){
			filterModel = new FilterMapModel();
			filterController = new FilterMapController(filterModel, mapController);
			filterPanel = filterController.getView();
//			filterPanel.setBounds(1025, 300, 400, 500);
//		}
		return filterPanel;
	}

	public JPanel getOrderPanel(){
		OrderModel orderModel = new OrderModel();
		OrderController orderController = new OrderController(orderModel, mapController);
		return orderController.getAddOrderJPanel();
	}

	public JPanel getDriverPanel(){
		DriversModel driversModel = new DriversModel();
		DriversController driversController = new DriversController(driversModel);

		driversModel.addObserver(driversController);
		
		return driversController.getDriversView();
	}

	public JPanel getOrdersDisplayPanel(){
		OrdersModel ordersModel = new OrdersModel();
		OrdersController ordersController = new OrdersController(ordersModel);
		return ordersController.getOrdersView();
	}
	
	public JPanel getMessagePanel(){
		MessageModel messageModel = new MessageModel();
		MessageController messageController = new MessageController(messageModel);
		return messageController.getMessageView();
	}

}
