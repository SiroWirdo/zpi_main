package admin.user.add.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import admin.user.add.model.AddUserModel;
import admin.user.add.view.AddUserView;

public class AddUserController {
	AddUserModel addUserModel;
	AddUserView addUserView;

	public AddUserController(AddUserModel addUserModel, AddUserView addUserView){
		this.addUserModel = addUserModel;
		this.addUserView = addUserView;
		this.addUserView.setUserController(this);

		this.addUserModel.initialize();
		this.addUserView.initialize();
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
			String[] values = addUserView.getValues();
			boolean valid = true;
			boolean password = false;

			for(String value : values){
				if(value.equals("")){
					valid = false;
				}
			}

			if(values[1].equals(values[2])){
				password = true;
			}

			if(valid){
				if(password){
					boolean admin = addUserView.isAdminSelected();
					addUserModel.addUser(values[0], values[1], values[3], admin);
					addUserView.clearTextFields();
				}else{
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JOptionPane.showMessageDialog(frame, "Hasła nie zgadzają się");
				}
			}else{
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(frame, "Wprowadź wszystkie dane");
			}

		}

	}

	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
	//		addUserView.dispose();
		}

	}
}
