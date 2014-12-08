package admin.driver.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import model.Driver;
import settings.Settings;
import admin.driver.edit.model.EditDriverModel;
import admin.driver.edit.model.ModifyDriverModel;
import admin.driver.edit.view.EditDriverView;
import drivers.controller.DriverNotFoundException;

public class EditDriverController implements Observer{
	EditDriverModel editDriverModel;
	EditDriverView editDriverView;

	public EditDriverController(EditDriverModel editDriverModel){
		this.editDriverModel = editDriverModel;
		this.editDriverView = new EditDriverView(this, editDriverModel);

		editDriverModel.initialize();
		editDriverView.initialize();
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 == editDriverModel){
			if(editDriverModel.getChanges().getFlag() == 0){
				List<Driver> drivers = editDriverModel.getChanges().getDrivers();
				for(Driver driver : drivers){

					addRow(driver);
				}
			}else{
				List<Driver> drivers = editDriverModel.getChanges().getDrivers();
				for(Driver driver : drivers){

					updateRow(driver);
				}
			}
		}
	}

	public void addRow(Driver driver){
		String status = Settings.driverStatus[driver.getStatus()];
		editDriverView.addRow(new Object[]{driver.getName(), driver.getSurname(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getPESEL(), status});
		editDriverView.repaint();
	}

	public void updateRow(Driver driver){
		String status = Settings.driverStatus[driver.getStatus()];
		Object[] values = new Object[]{driver.getName(), driver.getSurname(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getPESEL(), status};
		try{
			int row = editDriverView.getRowByPESEL(driver.getPESEL());
			editDriverView.updateRow(row, values);
			editDriverView.repaint();
		}catch(DriverNotFoundException e){
			System.out.println(e.getMessage());
			addRow(driver);
		}

	}


	public FiltrListener getFiltrListener(){
		return new FiltrListener();
	}

	public EditButtonListener getEditButtonListener(){
		return new EditButtonListener();
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
				if(editDriverView.isChecked(status)){
					RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(status);
					statusFilters.add(filter);
				}
			}

			statusFilter = RowFilter.orFilter(statusFilters);

			if(editDriverView.getName() != ""){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(editDriverView.getName());
				nameFilters.add(filter);
			}

			if(editDriverView.getSurname() != ""){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(editDriverView.getSurname());
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

			editDriverView.setFilters(mainFilter);
			editDriverView.repaint();

		}

	}

	private class EditButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			long pesel = editDriverView.getPeselFromSelectedRow();
			if(pesel >=0 ){
				Driver driver = editDriverModel.getDriverByPesel(pesel);
				if(driver != null){
					ModifyDriverModel modifyDriverModel = new ModifyDriverModel();
					ModifyDriverController modifyDriverController = new ModifyDriverController(modifyDriverModel, driver);

				}else{
					//TODO nie wiem czy taka sytuacja w ogóle wystąpi
				}
			}else{
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(frame, "Nie wybrano kierowcy");
			}
		}

	}
}
