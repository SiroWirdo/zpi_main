package org.parse4j.callback;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.controller.MainMenuController;
import main.model.MainMenuModel;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import other.DataBaseConnection;

public class UserModel extends ParseUser {

	public void log(String username, String password) {
		try {
			ParseUser.login(username, password);
			
			MainMenuModel menuModel = new MainMenuModel();
			MainMenuController menuController = new MainMenuController(menuModel);
			
		} catch (ParseException e) {		
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JOptionPane.showMessageDialog(frame, "Z�y login lub has�o");
		}
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}

}
