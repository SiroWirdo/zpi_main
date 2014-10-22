package drivers.controller;

import drivers.model.DriversModel;
import drivers.view.DriversView;

public class DriversController {
	private DriversModel driversModel;
	private DriversView driversView;
	
	public DriversController(DriversModel driversModel){
		this.driversModel = driversModel;
		this.driversView = new DriversView(this, driversModel);
		
		driversView.initialize();
		
		driversView.addRow(new Object[]{"test2", "test2", "test2", "test", "test", "test"});
	}
	
	public DriversView getDriversView(){
		return driversView;
	}
}
