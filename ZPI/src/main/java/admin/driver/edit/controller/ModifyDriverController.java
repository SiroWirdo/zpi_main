package admin.driver.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CancellationException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import validation.DriverDispatcherValidation;
import validation.UserValidation;
import model.Driver;
import admin.driver.edit.model.ModifyDriverModel;
import admin.driver.edit.view.ModifyDriverView;

public class ModifyDriverController {
	ModifyDriverModel modifyDriverModel;
	ModifyDriverView modifyDriverView;
	Driver driverOld;

	public ModifyDriverController(ModifyDriverModel modifyDriverModel, Driver driver){
		this.modifyDriverModel = modifyDriverModel;

		this.modifyDriverView = new ModifyDriverView(this, this.modifyDriverModel);

		//this.modifyDriverModel.initialize();
		this.modifyDriverView.initialize();

		this.modifyDriverView.setValues(driver);
		this.driverOld = driver;
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
			//TODO zmienic edycje tak zeby jakos dodawac ten samochod i user
			// TODO Dodac walidacje usera i samochodu(sprawdzac czy nie jest juz do kogos przypisany
			Driver driver = modifyDriverView.getDriver();
			String[] values = modifyDriverView.getValues();
			boolean valid = true;
			for(String value : values){
				if(value.equals("")){
					valid = false;
				}
			}

			boolean validPesel = true;
			if(values[4].length() != 11){
				validPesel = false;
			}

			boolean uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);
			boolean uniqueLicense = DriverDispatcherValidation.isLicenseUnique(values[4]);

			if(values[4].equals(new Long(driverOld.getPESEL()).toString())){
				uniquePesel = true;
			}

			if(values[3].equals(driverOld.getLicenseNumber())){
				uniquePesel = true;
			}

			if(valid && validPesel && uniqueLicense && uniquePesel){
				modifyDriverModel.editDriver(driver, values);
				modifyDriverView.dispose();
			}else{
				if(!uniquePesel){
					printError("Taki PESEL jest ju¿ przypisany do innej osoby");
				}

				if(!uniqueLicense){
					printError("Taka licencja jest ju¿ przypisana do innego kierowcy");
				}

				if(!validPesel){
					printError("PESEL powinien mieæ 11 znaków");
				}

				if(!valid){
					printError("WprowadŸ wszystkie dane");

				}
			}
		}

	}

	public void printError(String text){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, text);
	}
}
