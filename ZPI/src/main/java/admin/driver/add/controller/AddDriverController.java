package admin.driver.add.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import admin.driver.add.model.AddDriverModel;
import admin.driver.add.view.AddDriverView;

public class AddDriverController {
	private AddDriverView addDriverView;
	private AddDriverModel addDriverModel;
	
	public AddDriverController(AddDriverModel addDriverModel){
		this.addDriverModel = addDriverModel;
		this.addDriverView = new AddDriverView(this, addDriverModel);
		
		this.addDriverModel.initialize();
		this.addDriverView.initialize();
	}
	
	public AddButtonListener getAddButtonListener(){
		return new AddButtonListener();
	}
	
	public CancelButtonListener getCancelButtonListener(){
		return new CancelButtonListener();
	}
	
	private class AddButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String[] values = addDriverView.getValues();
			boolean valid = true;
			
			for(String value : values){
				if(value.equals("")){
					valid = false;
				}
			}
			
			if(valid){
				addDriverModel.addDriver(values[0], values[1], new Long(values[2]), new Long(values[3]), values[4], values[5]);
				addDriverView.clearTextFields();
			}else{
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(frame, "Wprowadü wszystkie dane");
			}

		}
		
	}
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			addDriverView.dispose();
			
		}
		
	}
}
