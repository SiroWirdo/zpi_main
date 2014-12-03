package admin.driver.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Car;
import admin.driver.edit.model.EditCarModel;
import admin.driver.edit.view.EditCarView;

public class EditCarController {
	EditCarModel editCarModel;
	EditCarView editCarView;
	
	public EditCarController(EditCarModel editCarModel, String carId){
		this.editCarModel = editCarModel;
		this.editCarView = new EditCarView(this, editCarModel);
		
		editCarModel.initialize();
		editCarView.initialize();
		
		Car car = editCarModel.getCar(carId);
		editCarView.setValues(car);
		editCarView.repaint();
	}
	
	public EditButtonListener getEditButtonListener(){
		return new EditButtonListener();
	}
	
	public CancelButtonListener getCancelButtonListener(){
		return new CancelButtonListener();
	}
	
	private class EditButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// TODO Walidacja danych
			String[] values = editCarView.getValues();
			Car car = editCarView.getCar();
			
			editCarModel.editCar(values, car);
			editCarView.dispose();
					
		}
		
	}
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			editCarView.dispose();
		}
		
	}
}
