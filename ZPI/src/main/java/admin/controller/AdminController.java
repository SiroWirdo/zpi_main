package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.driver.add.controller.AddDriverController;
import admin.driver.add.model.AddDriverModel;
import admin.model.AdminModel;
import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;
import admin.view.AdminView;

public class AdminController {
	AdminModel adminModel;
	AdminView adminView;

	public AdminController(AdminModel adminModel){
		this.adminModel = adminModel;
		adminView = new AdminView(this, adminModel);
		
		adminModel.initialize();
		adminView.initialize();
	}
	
	public AddDriverListener getAddDriverListener(){
		return new AddDriverListener();
	}
	
	public EditDriverListener getEditDriverListener(){
		return new EditDriverListener();
	}
	
	public AddUserListener getAddUserListener(){
		return new AddUserListener();
	}
	
	
	private class AddDriverListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			AddDriverModel addDriverModel = new AddDriverModel();
			AddDriverController addDriverController = new AddDriverController(addDriverModel);
		}
		
	}
	
	private class EditDriverListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class AddUserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			AddUserModel addUserModel = new AddUserModel();
			AddUserController addUserController = new AddUserController(addUserModel);
			
		}
		
	}
	
	
}
