package org.parse4j.callback;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.controller.MainMenuController;
import main.model.MainMenuModel;
import main.model.MapModel;
import model.Dispatcher;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;
import org.parse4j.ParseUser;

import other.DataBaseConnection;
import settings.Settings;
import admin.controller.AdminController;
import admin.model.AdminModel;

public class UserModel {

	public boolean log(String username, String password) {
		try {
			ParseUser user = ParseUser.login(username, password);
			if(user.getBoolean("admin")){
				AdminModel adminModel = new AdminModel();
				AdminController adminController = new AdminController(adminModel);
				return true;
			}else{
			//	setUserObject(user);
				MapModel mapModel = new MapModel();
				MainMenuController menuController = new MainMenuController(mapModel);
				return true;
			}

		} catch (ParseException e) {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JOptionPane.showMessageDialog(frame, "Z³y login lub has³o");
			return false;
		}
	}

	public void initialize(){
		DataBaseConnection.initialize();
	}

	public void setUserObject(ParseUser user){
		ParseQuery<Dispatcher> query = ParseQuery.getQuery(Dispatcher.class);
		query.whereEqualTo("dispatcher", user);
		try {
			List<Dispatcher> list = new ArrayList<Dispatcher>();
			
			list = new ArrayList<Dispatcher>((ArrayList<Dispatcher>)query.find());
			 if(list.size() == 1){
	        	  Settings.USER_OBJECT = list.get(0);
			 }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
