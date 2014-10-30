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
	private JButton addUser;
	private JButton exit;
	private JPanel mainPanel;



	public AdminView(AdminController adminController, AdminModel adminModel){
		this.adminController = adminController;
		this.adminModel = adminModel;
	}

	public void initialize(){
		this.setBounds(600, 300, 200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4,1));
		add(mainPanel);

		addDriver = new JButton("Dodaj kierowcê");
		addDriver.addActionListener(adminController.getAddDriverListener());
		mainPanel.add(addDriver);

		editDriver = new JButton("Edytuj kierowcê");
		editDriver.addActionListener(adminController.getEditDriverListener());
		mainPanel.add(editDriver);

		addUser = new JButton("Dodaj u¿ytkownika");
		addUser.addActionListener(adminController.getAddUserListener());
		mainPanel.add(addUser);

		exit = new JButton("WyjdŸ");
		exit.addActionListener(adminController.getCloseButtonListener());
		mainPanel.add(exit);

		setVisible(true);
	}

}
