package drivers.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import model.Driver;

import org.parse4j.ParseObject;

import settings.Settings;
import drivers.model.DriverChanges;
import drivers.model.DriversModel;
import drivers.view.DriversView;

public class DriversController implements Observer {
	private DriversModel driversModel;
	private DriversView driversView;
	//private DriverChanges driverChanges;

	public DriversController(DriversModel driversModel){
		this.driversModel = driversModel;
		this.driversView = new DriversView(this, driversModel);
		driversModel.addObserver(this);

		this.driversView.initialize();
		this.driversModel.initialize();

/*Stara wersja pobierania zmian*/
		/*driverChanges = null;
		final DriversModel driverMod = driversModel;
		//while(driverChanges == null){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				while(driverMod.getChanges() == null){
					driverMod.
				}

				setDriverChanges(driverMod.getChanges());
				System.out.println("Przeszlo");
				//}
				List<Driver> drivers = driverChanges.getDrivers();
				for(Driver driver : drivers){

					addRow(driver);
				}

				driverMod.start();
			}
		});*/
		driversModel.start();
	}

	/*private void setDriverChanges(DriverChanges driverChanges){
		this.driverChanges = driverChanges;
	}*/



	public DriversView getDriversView(){
		return driversView;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 == driversModel){

			List<Driver> drivers = driversModel.getChanges().getDrivers();
			for(Driver driver : drivers){

				addRow(driver);
			}
		}
	}

	public void addRow(Driver driver){
		String status = Settings.driverStatus[driver.getStatus()];
		driversView.addRow(new Object[]{driver.getName(), driver.getSurname(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getPESEL(), status});
		driversView.repaint();
	}
}
