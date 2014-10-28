package org.parse4j.callback;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.controller.MainMenuController;
import main.model.MainMenuModel;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import other.DataBaseConnection;
import admin.controller.AdminController;
import admin.model.AdminModel;

public class UserModel extends ParseUser {

	public void log(String username, String password) {
		try {
			ParseUser user = ParseUser.login(username, password);
			//ParseUser dispatcher = ParseUser.getCurrentUser();

			if(user.getBoolean("admin")){
				AdminModel adminModel = new AdminModel();
				AdminController adminController = new AdminController(adminModel);
			}else{
				MainMenuModel menuModel = new MainMenuModel();
				MainMenuController menuController = new MainMenuController(menuModel);
			}

		} catch (ParseException e) {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JOptionPane.showMessageDialog(frame, "Z³y login lub has³o");
		}
	}

	public void initialize(){
		DataBaseConnection.initialize();
	}

	public void getUserID(){

	}

}
