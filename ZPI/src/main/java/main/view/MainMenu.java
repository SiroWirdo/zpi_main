package main.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ScrollPaneConstants;

import model.Order;
import order.controller.OrderController;
import order.view.AddOrderJPanel;

public class MainMenu extends JFrame{

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 1280, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel mapPanel = new JPanel();
		tabbedPane.addTab("Mapa", null, mapPanel, null);
		mapPanel.setLayout(null);
//
//		JPanel panel = new JPanel();
//		panel.setBounds(10, 10, 862, 691);
//		mapPanel.add(panel);

		//AddOrderJPanel addOrderPanel = new AddOrderJPanel();
		//tabbedPane.addTab("Dodaj zlecenie", null, addOrderPanel, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
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
	
	public void add(){
		
	}
}
