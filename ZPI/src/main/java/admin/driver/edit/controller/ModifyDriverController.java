package admin.driver.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import validation.DriverDispatcherValidation;
import admin.driver.edit.model.EditCarModel;
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

	public RestartPasswordButtonListener getRestartPasswordButtonListener(){
		return new RestartPasswordButtonListener();
	}
	
	public EditCarButtonListener getEditCarButtonListener(){
		return new EditCarButtonListener();
	}

	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			modifyDriverView.dispose();
		}

	}
	

	private class EditCarButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			EditCarModel editCarModel = new EditCarModel();
			EditCarController editCarController = new EditCarController(editCarModel, modifyDriverView.getCarId());
		}
		
	}
	
	
	private class RestartPasswordButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String[] values = modifyDriverView.getValues();
			try {
				ParseUser.requestPasswordReset(values[7]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private class EditButtonListener implements ActionListener{
		private String error = "";
		@Override
		public void actionPerformed(ActionEvent e) {
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

			boolean uniquePesel = true;
			boolean uniqueLicense = DriverDispatcherValidation.isLicenseUnique(values[3]);
			
			if(validPesel){
				uniquePesel = DriverDispatcherValidation.isPeselUnique(values[4]);
			}

			if(values[4].equals(new Long(driverOld.getPESEL()).toString())){
				uniquePesel = true;
			}

			if(values[3].equals(driverOld.getLicenseNumber())){
				uniqueLicense = true;
			}

			if(valid && validPesel && uniqueLicense && uniquePesel){
				modifyDriverModel.editDriver(driver, values);
				modifyDriverView.dispose();
			}else{
				if(!uniquePesel){
					error += "- Taki PESEL jest ju¿ przypisany do innej osoby \n";
				}

				if(!uniqueLicense){
					error += "- Taka licencja jest ju¿ przypisana do innego kierowcy \n";
				}

				if(!validPesel){
					error += "- PESEL powinien mieæ 11 znaków \n";
				}

				if(!valid){
					error += "WprowadŸ wszystkie dane \n";

				}
				printError(error);
				error = "";
			}
		}

	}

	public void printError(String text){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, text);
	}
}
