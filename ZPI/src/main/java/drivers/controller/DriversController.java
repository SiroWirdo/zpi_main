package drivers.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Driver;

import org.parse4j.ParseObject;

import settings.Settings;
import drivers.model.DriverChanges;
import drivers.model.DriversModel;
import drivers.view.DriversView;

public class DriversController implements Observer {
	private DriversModel driversModel;
	private DriversView driversView;
	
	public DriversController(DriversModel driversModel){
		this.driversModel = driversModel;
		this.driversView = new DriversView(this, driversModel);
		
		this.driversView.initialize();
		this.driversModel.initialize();
		
		DriverChanges driverChanges = null;
		while(driverChanges == null){
			driverChanges = driversModel.getChanges();
			System.out.print("");
		}
		List<Driver> drivers = driverChanges.getDrivers();
		for(Driver driver : drivers){
					
			addRow(driver);
		}		
		
		driversModel.start();
	}
	
	public DriversView getDriversView(){
		return driversView;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 == driversModel){
			DriverChanges driverChanges = null;
			while(driverChanges == null){
				driverChanges = driversModel.getChanges();
				
			}
			List<Driver> drivers = driverChanges.getDrivers();
			for(Driver driver : drivers){
						
				addRow(driver);
			}		
		}
	}
	
	public void addRow(Driver driver){
		String status = Settings.driverStatus[driver.getStatus()];
		driversView.addRow(new Object[]{driver.getName(), driver.getSurname(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getPESEL(), status});

	}
}
