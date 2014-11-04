package org.parse4j.callback;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.controller.MainMenuController;
import main.model.MapModel;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import other.DataBaseConnection;
import settings.Settings;

public class UserModel extends ParseUser {

	public void log(String username, String password) {
		try {
			ParseUser.login(username, password);
			//ParseUser dispatcher = ParseUser.getCurrentUser();
			MapModel menuModel = new MapModel();
			MainMenuController menuController = new MainMenuController(menuModel);
			
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
