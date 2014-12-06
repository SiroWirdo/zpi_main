package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.dispatcher.add.controller.AddDispatcherController;
import admin.dispatcher.add.model.AddDispatcherModel;
import admin.dispatcher.edit.controller.EditDispatcherController;
import admin.dispatcher.edit.model.EditDispatcherModel;
import admin.driver.add.controller.AddDriverController;
import admin.driver.add.model.AddDriverModel;
import admin.driver.edit.controller.EditDriverController;
import admin.driver.edit.model.EditDriverModel;
import admin.model.AdminModel;
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
	
	public AddDispatcherListener getAddDispatcherListener(){
		return new AddDispatcherListener();
	}
	
	public EditDispatcherListener getEditDispatcherListener(){
		return new EditDispatcherListener();
	}

	public CloseButtonListener getCloseButtonListener(){
		return new CloseButtonListener();
	}


	private class AddDriverListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AddDriverModel addDriverModel = new AddDriverModel();
			AddDriverController addDriverController = new AddDriverController(addDriverModel);
		}

	}

	private class EditDriverListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			EditDriverModel editDriverModel = new EditDriverModel();
			EditDriverController editDriverController = new EditDriverController(editDriverModel);
			editDriverModel.addObserver(editDriverController);

		}

	}
	
	private class AddDispatcherListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AddDispatcherModel addDispatcherModel = new AddDispatcherModel();
			AddDispatcherController addDispatcherController = new AddDispatcherController(addDispatcherModel);
		}

	}
	
	private class EditDispatcherListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			EditDispatcherModel editDispatcherModel = new EditDispatcherModel();
			EditDispatcherController editDispatcherController = new EditDispatcherController(editDispatcherModel);
			editDispatcherModel.addObserver(editDispatcherController);
		}
		
	}

	private class CloseButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			adminView.dispose();
			System.exit(0);
		}

	}


}
