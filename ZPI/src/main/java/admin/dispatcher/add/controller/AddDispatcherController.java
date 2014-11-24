package admin.dispatcher.add.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.parse4j.ParsePointer;
import org.parse4j.ParseUser;

import validation.DriverDispatcherValidation;
import validation.UserValidation;
import admin.dispatcher.add.model.AddDispatcherModel;
import admin.dispatcher.add.view.AddDispatcherView;
import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;
import admin.user.add.view.AddUserView;


public class AddDispatcherController {
	private AddDispatcherView addDispatcherView;
	private AddDispatcherModel addDispatcherModel;
	private AddUserView addUserView;
	private AddUserModel addUserModel;
	private AddUserController addUserController;

	public AddDispatcherController(AddDispatcherModel addDispatcherModel){
		this.addDispatcherModel = addDispatcherModel;
		this.addDispatcherView = new AddDispatcherView(this, addDispatcherModel);

		this.addDispatcherModel.initialize();
		this.addDispatcherView.initialize();
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
			String[] values = addDispatcherView.getValues();
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
			
			boolean validPesel = true;
			if(values[2].length() != 11){
				validPesel = false;
			}

			if(userValues[1].equals(userValues[2])){
				password = true;
			}
			
			boolean uniqueUsername = UserValidation.isUserNameUnique(userValues[0]);
			boolean uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);

			if(valid && uniqueUsername && validPesel && uniquePesel){
				if(password){
					System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST");
					boolean admin = addUserView.isAdminSelected();
					ParseUser user = addUserModel.addUser(userValues[0], userValues[1], userValues[3], admin);
					ParsePointer pointer = new ParsePointer("_User", user.getObjectId());
					/*String id = user.getObjectId();
					user.setDirty();
					
					ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
					query.whereEqualTo("objectId", id);
					ParseUser us = null;
					try {
						us = query.find().get(0);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*JSONObject pointer = new JSONObject();
					System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST22222222222222222");
				      if (user.getObjectId() != null) {
				    	  pointer.put("__type", "Pointer");
				    	  pointer.put("className", user.getClassName());
				    	  pointer.put("objectId", user.getObjectId());
				      }

				      System.out.println(pointer);
				      */

					addDispatcherModel.addDispatcher(values[0], values[1], values[2], pointer);

					addDispatcherView.clearTextFields();
					addUserView.clearTextFields();
				}else{
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JOptionPane.showMessageDialog(frame, "Has�a nie zgadzaj� si�");
				}
			}else{
				if(!uniqueUsername){
					printError("U�ytkownik o takiej nazwie ju� istnieje");
				}
				
				if(!validPesel){
					printError("PESEL powinien zawiera� 11 znak�w");
				}
				
				if(!uniquePesel){
					printError("Taki PESEL jest ju� przypisany do innej osoby");
				}
				
				if(!valid){
					printError("Wprowad� wszystkie dane");
				}
			}

		}

	}
	
	public void printError(String text){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, text);
	}
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			addDispatcherView.dispose();

		}

	}
}
