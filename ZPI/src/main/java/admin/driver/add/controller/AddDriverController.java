package admin.driver.add.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.parse4j.ParseUser;

import admin.driver.add.model.AddDriverModel;
import admin.driver.add.view.AddDriverView;
import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;
import admin.user.add.view.AddUserView;

public class AddDriverController {
	private AddDriverView addDriverView;
	private AddDriverModel addDriverModel;
	private AddUserView addUserView;
	private AddUserModel addUserModel;
	private AddUserController addUserController;

	public AddDriverController(AddDriverModel addDriverModel){
		this.addDriverModel = addDriverModel;
		this.addDriverView = new AddDriverView(this, addDriverModel);

		this.addDriverModel.initialize();
		this.addDriverView.initialize();
	}

	public AddUserView getUserView(){
		addUserModel = new AddUserModel();
		addUserView = new AddUserView(addUserModel);
		addUserController = new AddUserController(addUserModel, addUserView);
		return addUserView;

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
			String[] userValues = addUserView.getValues();
			boolean valid = true;
			boolean password = false;

			for(String value : values){
				if(value.equals("")){
					valid = false;
				}
			}

			for(String value : userValues){
				if(value.equals("")){
					valid = false;
				}
			}

			if(userValues[1].equals(userValues[2])){
				password = true;
			}

			if(valid){
				if(password){
					System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST");
					boolean admin = addUserView.isAdminSelected();
					ParseUser user = addUserModel.addUser(userValues[0], userValues[1], userValues[3], admin);
					/*JSONObject pointer = new JSONObject();
					System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST22222222222222222");
				      if (user.getObjectId() != null) {
				    	  pointer.put("__type", "Pointer");
				    	  pointer.put("className", user.getClassName());
				    	  pointer.put("objectId", user.getObjectId());
				      }
				      
				      System.out.println(pointer);
				      */
				      
					addDriverModel.addDriver(values[0], values[1], new Long(values[2]), new Long(values[3]), values[4], user);

					addDriverView.clearTextFields();
					addUserView.clearTextFields();
				}else{
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JOptionPane.showMessageDialog(frame, "Has³a nie zgadzaj¹ siê");
				}
			}else{
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(frame, "WprowadŸ wszystkie dane");
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
