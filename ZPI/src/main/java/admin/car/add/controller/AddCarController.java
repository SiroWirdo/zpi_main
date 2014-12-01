package admin.car.add.controller;

import javax.swing.JPanel;

import admin.car.add.model.AddCarModel;
import admin.car.add.view.AddCarView;

public class AddCarController extends JPanel {
	private AddCarView addCarView;
	private AddCarModel addCarModel;
	
	public AddCarController(AddCarModel addCarModel){
		this.addCarModel = addCarModel;
		this.addCarView = new AddCarView(this, addCarModel);
		
		addCarView.initialize();
	}
	
	public AddCarView getAddCarView(){
		return this.addCarView;
	}
}
