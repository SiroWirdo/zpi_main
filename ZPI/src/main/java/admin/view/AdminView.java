package admin.view;

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
	private JPanel mainPanel;


	public AdminView(AdminController adminController, AdminModel adminModel){
		this.adminController = adminController;
		this.adminModel = adminModel;
	}

	public void initialize(){
		this.setBounds(100, 100, 640, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		setVisible(true);
	}

}
