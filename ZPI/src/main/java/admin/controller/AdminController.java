package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.driver.add.controller.AddDriverController;
import admin.driver.add.model.AddDriverModel;
import admin.driver.edit.controller.EditDriverController;
import admin.driver.edit.model.EditDriverModel;
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

	public CloseButtonListener getCloseButtonListener(){
		return new CloseButtonListener();
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
			EditDriverModel editDriverModel = new EditDriverModel();
			EditDriverController editDriverController = new EditDriverController(editDriverModel);
			editDriverModel.addObserver(editDriverController);

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

	private class CloseButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			adminView.dispose();
		}

	}


}
