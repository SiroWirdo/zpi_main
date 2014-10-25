package drivers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Driver;
import settings.Settings;
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

		//	driversModel.start();

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
			if(driversModel.getChanges().getFlag() == 0){
				List<Driver> drivers = driversModel.getChanges().getDrivers();
				for(Driver driver : drivers){

					addRow(driver);
				}
			}else{
				List<Driver> drivers = driversModel.getChanges().getDrivers();
				for(Driver driver : drivers){

					updateRow(driver);
				}
			}
		}
	}

	public void addRow(Driver driver){
		String status = Settings.driverStatus[driver.getStatus()];
		driversView.addRow(new Object[]{driver.getName(), driver.getSurname(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getPESEL(), status});
		driversView.repaint();
	}

	public void updateRow(Driver driver){
		String status = Settings.driverStatus[driver.getStatus()];
		Object[] values = new Object[]{driver.getName(), driver.getSurname(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getPESEL(), status};
		try{
			int row = driversView.getRowByPESEL(driver.getPESEL());
			driversView.updateRow(row, values);
			driversView.repaint();
		}catch(DriverNotFoundException e){
			System.out.println(e.getMessage());
		}

	}

	public List<Driver> filtr(List<Driver> drivers){
		List<Driver> newDrivers = new ArrayList<Driver>();
		for(Driver driver : drivers){
			String driverName = driver.getName();
			String driverSurname = driver.getSurname();
			String driverStatus = Settings.driverStatus[driver.getStatus()];
			if((driverName.equals(driversView.getName()) || driversView.getName().equals(""))
					&& (driverSurname.equals(driversView.getSurname()) || driversView.getSurname().equals("")) 
					&& driversView.isChecked(driverStatus)){
				newDrivers.add(driver);
			}
		}

		return newDrivers;
	}


	public FiltrListener getFiltrListener(){
		return new FiltrListener();
	}

	private class FiltrListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			driversView.clearTable();
			List<Driver> actualDrivers = driversModel.getActualData();
			
			if(actualDrivers == null){
				try {
					throw new DriverNotFoundException("Brak aktualnych kierowców");
				} catch (DriverNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			actualDrivers = filtr(actualDrivers);
			for(Driver driver : actualDrivers){
				addRow(driver);
			}
			
			driversView.repaint();
		}

	}
}
