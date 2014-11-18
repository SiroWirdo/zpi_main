package admin.driver.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CancellationException;

import model.Driver;
import admin.driver.edit.model.ModifyDriverModel;
import admin.driver.edit.view.ModifyDriverView;

public class ModifyDriverController {
	ModifyDriverModel modifyDriverModel;
	ModifyDriverView modifyDriverView;

	public ModifyDriverController(ModifyDriverModel modifyDriverModel, Driver driver){
		this.modifyDriverModel = modifyDriverModel;

		this.modifyDriverView = new ModifyDriverView(this, this.modifyDriverModel);

		//this.modifyDriverModel.initialize();
		this.modifyDriverView.initialize();

		this.modifyDriverView.setValues(driver);
	}

	public CancelButtonListener getCancelButtonListener(){
		return new CancelButtonListener();
	}

	public EditButtonListener getEditButtonListener(){
		return new EditButtonListener();
	}


	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			modifyDriverView.dispose();
		}

	}

	private class EditButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Dodac walidacje usera i samochodu(sprawdzac czy nie jest juz do kogos przypisany
			Driver driver = modifyDriverView.getDriver();
			String[] values = modifyDriverView.getValues();

			modifyDriverModel.editDriver(driver, values);
			modifyDriverView.dispose();
		}

	}
}
