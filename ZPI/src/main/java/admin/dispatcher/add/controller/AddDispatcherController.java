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
		private String error = "";
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
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
			
			boolean uniqueUsername = true;
			boolean uniquePesel = true;
			if(validPesel){
				uniqueUsername = UserValidation.isUserNameUnique(userValues[0]);
				uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);
			}

			if(valid && uniqueUsername && validPesel && uniquePesel){
				if(password){
					System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST");
					boolean admin = addUserView.isAdminSelected();
					ParseUser user = addUserModel.addUser(userValues[0], userValues[1], userValues[3], admin);
					ParsePointer pointer = new ParsePointer("_User", user.getObjectId());		

					addDispatcherModel.addDispatcher(values[0], values[1], values[2], pointer);

					addDispatcherView.clearTextFields();
					addUserView.clearTextFields();
				}else{
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JOptionPane.showMessageDialog(frame, "Has³a nie zgadzaj¹ siê");
				}
			}else{
				if(!uniqueUsername){
					error += "- U¿ytkownik o takiej nazwie ju¿ istnieje. \n";
				}
				
				if(!validPesel){
					error += "- PESEL powinien zawieraæ 11 znaków. \n";
				}
				
				if(!uniquePesel){
					error += "- Taki PESEL jest ju¿ przypisany do innej osoby. \n";
				}
				
				if(!valid){
					error += "- WprowadŸ wszystkie dane. \n";
				}
				
				printError(error);
				error = "";
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
