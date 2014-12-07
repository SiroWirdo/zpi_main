package admin.driver.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import validation.CarValidation;
import model.Car;
import admin.driver.edit.model.EditCarModel;
import admin.driver.edit.view.EditCarView;

public class EditCarController {
	EditCarModel editCarModel;
	EditCarView editCarView;
	Car oldCar;
	
	public EditCarController(EditCarModel editCarModel, String carId){
		this.editCarModel = editCarModel;
		this.editCarView = new EditCarView(this, editCarModel);
		
		editCarModel.initialize();
		editCarView.initialize();
		
		Car car = editCarModel.getCar(carId);
		editCarView.setValues(car);
		editCarView.repaint();
		this.oldCar = car;
	}
	
	public EditButtonListener getEditButtonListener(){
		return new EditButtonListener();
	}
	
	public CancelButtonListener getCancelButtonListener(){
		return new CancelButtonListener();
	}
	
	private class EditButtonListener implements ActionListener{
		private String error = "";
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// TODO Walidacja danych
			String[] values = editCarView.getValues();
			Car car = editCarView.getCar();
			boolean valid = true;
			
			for(String value: values){
				if(value.equals("")){
					valid = false;
				}
			}
			
			boolean uniqueRegistrationNumber = true;
			boolean uniqueSideNumber = true;
			
			if(valid){
				uniqueRegistrationNumber = CarValidation.isRegistrationNumberUnique(values[0]);
				uniqueSideNumber = CarValidation.isSideNumberUnique(values[1]);
			}
			
			if(values[0].equals(oldCar.getRegistrationNumber())){
				uniqueRegistrationNumber = true;
			}
			
			if(values[1].equals(new Integer(oldCar.getSideNumber()).toString())){
				uniqueSideNumber = true;
			}
			
			if(valid && uniqueRegistrationNumber && uniqueSideNumber){
				editCarModel.editCar(values, car);
				editCarView.dispose();
			}else{

				if(!valid){
					error += "- Wprowadź wszystkie dane \n";

				}
				
				if(!uniqueRegistrationNumber){
					error += "- Samochód o takim numerze rejestracyjnym już istnieje \n";
				}
				
				if(!uniqueSideNumber){
					error += "- Samochód o takim numerze bocznym już istnieje \n";
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
			editCarView.dispose();
		}
		
	}
}
