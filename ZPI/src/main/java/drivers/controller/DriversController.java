package drivers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import model.Driver;
import settings.Settings;
import drivers.model.DriversModel;
import drivers.view.DriversView;

public class DriversController implements Observer {
	private DriversModel driversModel;
	private DriversView driversView;

	public DriversController(DriversModel driversModel){
		this.driversModel = driversModel;
		this.driversView = new DriversView(this, driversModel);
		driversModel.addObserver(this);

		this.driversView.initialize();
		this.driversModel.initialize();	

	}

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
/*
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
*/

	public FiltrListener getFiltrListener(){
		return new FiltrListener();
	}

	private class FiltrListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			ArrayList<RowFilter<DefaultTableModel, Object>> statusFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
			ArrayList<RowFilter<DefaultTableModel, Object>> nameFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
			
			RowFilter<DefaultTableModel, Object> statusFilter;
			RowFilter<DefaultTableModel, Object> nameFilter;
			RowFilter<DefaultTableModel, Object> mainFilter;
			
			for(String status : Settings.driverStatus){
				if(driversView.isChecked(status)){
					RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(status);
					statusFilters.add(filter);
				}				
			}
			
			statusFilter = RowFilter.orFilter(statusFilters);
			
			if(driversView.getName() != ""){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(driversView.getName());
				nameFilters.add(filter);
			}
			
			if(driversView.getSurname() != ""){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(driversView.getSurname());
				nameFilters.add(filter);
			}
			
			if(nameFilters.size() > 0){
				nameFilter = RowFilter.andFilter(nameFilters);
				ArrayList<RowFilter<DefaultTableModel, Object>> combineFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
				combineFilters.add(statusFilter);
				combineFilters.add(nameFilter);
				mainFilter = RowFilter.andFilter(combineFilters);
			}else{
				mainFilter = statusFilter;
			}
			
			driversView.setFilters(mainFilter);
			driversView.repaint();
			
			
			/*driversView.clearTable();
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
			
			driversView.repaint();*/
		}

	}
}
