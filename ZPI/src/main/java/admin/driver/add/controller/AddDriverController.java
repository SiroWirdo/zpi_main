package admin.driver.add.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Car;

import org.parse4j.ParsePointer;
import org.parse4j.ParseUser;

import validation.CarValidation;
import validation.DriverDispatcherValidation;
import validation.UserValidation;
import admin.car.add.controller.AddCarController;
import admin.car.add.model.AddCarModel;
import admin.car.add.view.AddCarView;
import admin.driver.add.model.AddDriverModel;
import admin.driver.add.view.AddDriverView;
import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;
import admin.user.add.view.AddUserView;

public class AddDriverController {
	private AddDriverView addDriverView;
	private AddDriverModel addDriverModel;
	private AddUserView addUserView;
	private AddUserModel addUserModel;
	private AddUserController addUserController;
	private AddCarView addCarView;
	private AddCarModel addCarModel;
	private AddCarController addCarController;

	public AddDriverController(AddDriverModel addDriverModel){
		this.addDriverModel = addDriverModel;
		this.addDriverView = new AddDriverView(this, addDriverModel);

		this.addDriverModel.initialize();
		this.addDriverView.initialize();
	}

	public AddUserView getUserView(){
		addUserModel = new AddUserModel();
		addUserView = new AddUserView(addUserModel);
		addUserController = new AddUserController(addUserModel, addUserView);
		return addUserView;

	}

	public AddCarView getAddCarView(){
		addCarModel = new AddCarModel();
		addCarController = new AddCarController(addCarModel);
		addCarView = addCarController.getAddCarView();
		return this.addCarView;
	}

	public AddButtonListener getAddButtonListener(){
		return new AddButtonListener();
	}

	public CancelButtonListener getCancelButtonListener(){
		return new CancelButtonListener();
	}

	private class AddButtonListener implements ActionListener{
		private String error = "";
//TODO przechwytywanie erroru ¿e mail ju¿ u¿yty
		//TODO pola numeryczne
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] values = addDriverView.getValues();
			String[] userValues = addUserView.getValues();
			String[] carValues = addCarView.getValues();
			boolean valid = true;
			boolean password = false;

			for(String value : values){
				if(value.equals("")){
					valid = false;
				}
			}

			for(String value : userValues){
				if(value.equals("")){
					valid = false;
				}
			}
			
			for(String value : carValues){
				if(value.equals("")){
					valid = false;
				}
			}

			boolean validPesel = true;
			if(values[2].length() != 11){
				validPesel = false;
			}

			if(userValues[1].equals(userValues[2])){
				password = true;
			}

			boolean uniquePesel = true;
			boolean uniqueLicense = DriverDispatcherValidation.isLicenseUnique(values[4]);
			boolean uniqueUsername = UserValidation.isUserNameUnique(userValues[0]);
			boolean uniqueRegistrationNumber = true;
			boolean uniqueSideNumber = true;
			
			if(valid){
				uniqueRegistrationNumber = CarValidation.isRegistrationNumberUnique(carValues[0]);
				uniqueSideNumber = CarValidation.isSideNumberUnique(carValues[1]);
			}

			if(validPesel){            	
				uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);

			}

			// TODO jeœli wywala ¿e nie jest unikalny to proces nie umiera...

			if(valid && uniquePesel && uniqueLicense && uniqueUsername && validPesel && password && uniqueRegistrationNumber && uniqueSideNumber){
				System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST");
				boolean admin = addUserView.isAdminSelected();
				ParseUser user = addUserModel.addUser(userValues[0], userValues[1], userValues[3], admin);
				ParsePointer pointer = new ParsePointer("_User", user.getObjectId());
				Car car = addCarModel.addCar(carValues[0], new Integer(carValues[1]), new Integer(carValues[2]));    

				addDriverModel.addDriver(values[0], values[1], new Long(values[2]), new Long(values[3]), values[4], pointer, car);

				addDriverView.clearTextFields();
				addUserView.clearTextFields();
				addCarView.clearTextFields();
			}else{
				if(!uniquePesel){
					error += "- Taki PESEL jest ju¿ przypisany do innej osoby \n";
				}
				if(!uniqueLicense){
					error += "- Taka licencja jest ju¿ przypisana do innego kierowcy \n";
				}
				if(!uniqueUsername){
					error += "- Taki u¿ytkownik ju¿ istnieje \n";
				}

				if(!validPesel){
					error += "- PESEL powinien mieæ 11 znaków \n";
				}

				if(!valid){
					error += "- WprowadŸ wszystkie dane \n";

				}

				if(!password){
					error += "- Has³a nie zgadzaj¹ siê \n";
				}
				
				if(!uniqueRegistrationNumber){
					error += "- Samochód o takim numerze rejestracyjnym ju¿ istnieje \n";
				}
				
				if(!uniqueSideNumber){
					error += "- Samochód o takim numerze bocznym ju¿ istnieje \n";
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

	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			addDriverView.dispose();

		}

	}

}
