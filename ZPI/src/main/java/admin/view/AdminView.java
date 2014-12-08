package admin.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admin.controller.AdminController;
import admin.model.AdminModel;

public class AdminView extends JFrame{
	private AdminController adminController;
	private AdminModel adminModel;
	private JButton addDriver;
	private JButton editDriver;
	//private JButton addUser;
	private JButton addDispatcher;
	private JButton editDispatcher;
	private JButton exit;
	private JPanel mainPanel;



	public AdminView(AdminController adminController, AdminModel adminModel){
		this.adminController = adminController;
		this.adminModel = adminModel;
	}

	public void initialize(){
		this.setBounds(600, 300, 200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Admin");

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5,1));
		add(mainPanel);

		addDriver = new JButton("Dodaj kierowcę");
		addDriver.addActionListener(adminController.getAddDriverListener());
		mainPanel.add(addDriver);

		editDriver = new JButton("Edytuj kierowcę");
		editDriver.addActionListener(adminController.getEditDriverListener());
		mainPanel.add(editDriver);

		/*addUser = new JButton("Dodaj użytkownika");
		addUser.addActionListener(adminController.getAddUserListener());
		mainPanel.add(addUser);*/

		addDispatcher = new JButton("Dodaj dyspozytora");
		addDispatcher.addActionListener(adminController.getAddDispatcherListener());
		mainPanel.add(addDispatcher);

		editDispatcher = new JButton("Edytuj dyspozytora");
		editDispatcher.addActionListener(adminController.getEditDispatcherListener());
		mainPanel.add(editDispatcher);

		exit = new JButton("Wyjdź");
		exit.addActionListener(adminController.getCloseButtonListener());
		mainPanel.add(exit);

		setVisible(true);
	}

}
